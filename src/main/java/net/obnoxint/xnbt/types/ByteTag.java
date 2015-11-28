package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class ByteTag extends BaseTag {

    public ByteTag(final String name, final byte payload) {
        super(new NBTTagHeader(BaseType.BYTE.Id(), name), payload);
    }

    @Override
    public Byte getPayload() {
        return (byte) super.getPayload();
    }

    public void setPayload(final byte payload) {
        super.setPayload(payload);
    }

}
