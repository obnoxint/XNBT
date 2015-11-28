package net.obnoxint.xnbt;

import java.io.DataInputStream;
import java.io.IOException;

import net.obnoxint.xnbt.BaseTag.BaseType;
import net.obnoxint.xnbt.types.EndTag;

/**
 * <p>
 * Reads {@link NBTTag}s from a DataInputStream.
 * </p>
 * <p>
 * Supports extended tags. See {@link TagIOHandler} for more details.
 * </p>
 */
public class NBTInputStream extends DataInputStream {

    /**
     * Creates a new NBTInputStream.
     *
     * @param in
     *            the DataInputStream to read from
     */
    public NBTInputStream(final DataInputStream in) {
        super(in);
    }

    /**
     * <p>
     * Reads a {@link NBTTag} from this stream.
     * </p>
     *
     * @return a NBTTag
     * @throws IOException
     *             if the NBTTag is not a BaseType and there is no registered {@link TagPayloadReader}
     */
    public NBTTag readTag() throws IOException {
        final byte type = readByte();

        if (type == BaseType.END.Id()) {
            return new EndTag();
        }

        final String name = readUTF();
        Object payload = null;

        if (type > BaseType.reservedIds()) {
            final TagPayloadReader reader = XNBT.getReader(type);
            if (reader == null) {
                throw new IOException("no reader registered for type " + type);
            }
            payload = reader.read(this);
        } else {
            payload = BaseTagReader.getReader(type).read(this);
        }

        return XNBT.getBuilder(type).build(type, name, payload);

    }

}
