package net.obnoxint.xnbt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.obnoxint.xnbt.BaseTag.BaseType;

abstract class BaseTagReader implements TagPayloadReader {

    private static final BaseTagReader[] baseTags = new BaseTagReader[BaseType.reservedIds() + 1];

    static {

        // End tag
        baseTags[BaseType.END.Id()] = null;

        // Byte tag
        baseTags[BaseType.BYTE.Id()] = new BaseTagReader() {

            @Override
            public Byte read(final NBTInputStream in) throws IOException {
                return in.readByte();
            }
        };

        // Short tag
        baseTags[BaseType.SHORT.Id()] = new BaseTagReader() {

            @Override
            public Short read(final NBTInputStream in) throws IOException {
                return in.readShort();
            }
        };

        // Integer tag
        baseTags[BaseType.INTEGER.Id()] = new BaseTagReader() {

            @Override
            public Integer read(final NBTInputStream in) throws IOException {
                return in.readInt();
            }
        };

        // Long tag
        baseTags[BaseType.LONG.Id()] = new BaseTagReader() {

            @Override
            public Long read(final NBTInputStream in) throws IOException {
                return in.readLong();
            }
        };

        // Float tag
        baseTags[BaseType.FLOAT.Id()] = new BaseTagReader() {

            @Override
            public Float read(final NBTInputStream in) throws IOException {
                return in.readFloat();
            }
        };

        // Double tag
        baseTags[BaseType.DOUBLE.Id()] = new BaseTagReader() {

            @Override
            public Double read(final NBTInputStream in) throws IOException {
                return in.readDouble();
            }
        };

        // Byte array tag
        baseTags[BaseType.BYTE_ARRAY.Id()] = new BaseTagReader() {

            @Override
            public byte[] read(final NBTInputStream in) throws IOException {
                final byte[] p = new byte[in.readInt()];
                System.out.println("Reading bytes ... " + p.length);
                for (int i = 0; i < p.length; i++) {
                    p[i] = in.readByte();
                }
                System.out.println("... done");
                return p;
            }
        };

        // String tag
        baseTags[BaseType.STRING.Id()] = new BaseTagReader() {

            @Override
            public String read(final NBTInputStream in) throws IOException {
                return in.readUTF();
            }
        };

        // List tag
        baseTags[BaseType.LIST.Id()] = new BaseTagReader() {

            @Override
            public List<NBTTag> read(final NBTInputStream in) throws IOException {
                final byte ct = in.readByte();
                final int s = in.readInt();

                final List<NBTTag> r = new ArrayList<>();

                for (int i = 0; i < s; i++) {
                    r.add(new BaseTag(new NBTTagHeader(ct, null), XNBT.getReader(ct).read(in)));
                }

                return r;
            }
        };

        // Compound tag
        baseTags[BaseType.COMPOUND.Id()] = new BaseTagReader() {

            @Override
            public Map<String, NBTTag> read(final NBTInputStream in) throws IOException {
                final Map<String, NBTTag> r = new HashMap<>();

                while (true) {
                    final NBTTag t = in.readTag();
                    if (t.equals(BaseTag.ENDTAG)) {
                        break;
                    }
                    r.put(t.getHeader().getName(), t);
                }

                return r;
            }
        };

        // Integer array tag
        baseTags[BaseType.INTEGER_ARRAY.Id()] = new BaseTagReader() {

            @Override
            public int[] read(final NBTInputStream in) throws IOException {
                final int[] p = new int[in.readInt()];
                for (int i = 0; i < p.length; i++) {
                    p[i] = in.readInt();
                }
                return p;
            }
        };

    }

    static TagPayloadReader getReader(final byte type) {
        return baseTags[type];
    }

}
