package net.obnoxint.xnbt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.obnoxint.xnbt.BaseTag.BaseType;

public final class XNBT {

    private static final Map<Byte, TagPayloadReader> extReaders = new HashMap<>();
    private static final Map<Byte, TagBuilder> extBuilders = new HashMap<>();
    private static final Map<Byte, TagPayloadWriter> extWriters = new HashMap<>();

    public static List<NBTTag> readFromFile(final File file) throws IOException {
        return readFromFile(file, true);
    }

    public static List<NBTTag> readFromFile(final File file, final boolean isZipped) throws IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            return readFromStream(is, isZipped);
        }
    }

    public static List<NBTTag> readFromStream(final InputStream is) throws IOException {
        return readFromStream(is, true);
    }

    public static List<NBTTag> readFromStream(final InputStream is, final boolean isZipped) throws IOException {
        final List<NBTTag> r = new ArrayList<>();

        try (NBTInputStream in = isZipped
                ? new GzipNBTInputStream(new DataInputStream(is))
                : new NBTInputStream(new DataInputStream(is))) {
            while (true) {
                final NBTTag tag = in.readTag();
                if (tag.equals(BaseTag.ENDTAG)) {
                    break;
                }
                r.add(tag);
            }
        } catch (final EOFException e) {} // Expected if no end tag is present

        return r;
    }

    public static void registerType(final byte type, final TagIOHandler handler) {
        registerType(type, handler, handler, handler);
    }

    public static void registerType(final byte type, final TagPayloadReader reader, final TagBuilder builder,
            final TagPayloadWriter writer) {

        if (extReaders.containsKey(type)) {
            throw new IllegalArgumentException("type already registered: " + type);
        }

        if (type <= BaseType.reservedIds()) {
            throw new IllegalArgumentException("illegal type: " + type);
        }

        if (reader == null) {
            throw new IllegalArgumentException("reader must not be null");
        }

        if (builder == null) {
            throw new IllegalArgumentException("builder must not be null");
        }

        if (writer == null) {
            throw new IllegalArgumentException("writer must not be null");
        }

        extReaders.put(type, reader);
        extWriters.put(type, writer);

    }

    public static void unregisterType(final byte type) {
        extReaders.remove(type);
        extWriters.remove(type);
    }

    public static void writeToFile(final List<NBTTag> tags, final File file) throws IOException {
        writeToFile(tags, file, true);
    }

    public static void writeToFile(final List<NBTTag> tags, final File file, final boolean zip) throws IOException {
        try (FileOutputStream os = new FileOutputStream(file)) {
            writeToStream(tags, os, zip);
        }
    }

    public static void writeToStream(final List<NBTTag> tags, final OutputStream os) throws IOException {
        writeToStream(tags, os, true);
    }

    public static void writeToStream(final List<NBTTag> tags, final OutputStream os, final boolean zip)
            throws IOException {

        if (tags == null) {
            throw new IllegalArgumentException("tags must not be null");
        }

        if (os == null) {
            throw new IllegalArgumentException("os must not be null");
        }

        if (tags.isEmpty()) {
            return;
        }

        if (tags.contains(null)) {
            throw new IllegalArgumentException("tags must not contain null elements");
        }

        if (tags.contains(BaseTag.ENDTAG)) {
            throw new IllegalArgumentException("tags must not contain end tags");
        }

        try (NBTOutputStream out = zip
                ? new GzipNBTOutputStream(new DataOutputStream(os))
                : new NBTOutputStream(new DataOutputStream(os))) {
            for (final NBTTag t : tags) {
                out.writeTag(t);
            }
            // out.writeTag(BaseTag.ENDTAG);
        }

    }

    static TagBuilder getBuilder(final byte type) {
        return type <= BaseType.reservedIds() ? BaseTagBuilder.getBuilder(type) : extBuilders.get(type);
    }

    static TagPayloadReader getReader(final byte type) {
        return type <= BaseType.reservedIds() ? BaseTagReader.getReader(type) : extReaders.get(type);
    }

    static TagPayloadWriter getWriter(final byte type) {
        return type <= BaseType.reservedIds() ? BaseTagWriter.getWriter(type) : extWriters.get(type);
    }

    private XNBT() {}
}
