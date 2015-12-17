package net.obnoxint.xnbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.obnoxint.xnbt.XNBT.TagIOHandler;
import net.obnoxint.xnbt.XNBT.TagPayloadReader;
import net.obnoxint.xnbt.types.EndTag;
import net.obnoxint.xnbt.types.NBTTag;
import net.obnoxint.xnbt.types.NBTTag.BaseType;

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
    public NBTInputStream(final InputStream in) {
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

        return XNBT.getBuilder(type).build(type, readUTF(), XNBT.getReader(type).read(this));

    }

}
