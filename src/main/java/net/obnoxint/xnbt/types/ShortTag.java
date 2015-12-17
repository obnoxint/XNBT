package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;

/**
 * <p>
 * Contains a Short.
 * </p>
 */
public class ShortTag extends BaseTag {

    public ShortTag(final String name) {
        this(name, (short) 0);
    }

    public ShortTag(final String name, final short payload) {
        super(new TagHeader(BaseType.SHORT.Id(), name), payload);
    }

    @Override
    public Short getPayload() {
        return (short) super.getPayload();
    }

    public void setPayload(final short payload) {
        super.setPayload(payload);
    }

}
