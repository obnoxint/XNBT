package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;

/**
 * <p>
 * Contains a Byte.
 * </p>
 */
public class ByteTag extends BaseTag {

    public ByteTag(final String name) {
        this(name, (byte) 0);
    }

    public ByteTag(final String name, final byte payload) {
        super(new TagHeader(BaseType.BYTE.Id(), name), payload);
    }

    @Override
    public Byte getPayload() {
        return (byte) super.getPayload();
    }

    public void setPayload(final byte payload) {
        super.setPayload(payload);
    }

}
