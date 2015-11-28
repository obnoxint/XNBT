package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class ByteArrayTag extends BaseTag {

    private static byte[] sanitizePayload(final byte[] payload) {
        return payload == null ? new byte[0] : payload;
    }

    public ByteArrayTag(final String name, final byte[] payload) {
        super(new NBTTagHeader(BaseType.BYTE_ARRAY.Id(), name), sanitizePayload(payload));
    }

    @Override
    public byte[] getPayload() {
        return (byte[]) super.getPayload();
    }

    public void setPayload(final byte[] payload) {
        super.setPayload(sanitizePayload(payload));
    }

}
