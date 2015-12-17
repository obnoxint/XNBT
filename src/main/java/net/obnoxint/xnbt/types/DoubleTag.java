package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;

/**
 * <p>
 * Contains a Double.
 * </p>
 */
public class DoubleTag extends BaseTag {

    public DoubleTag(final String name) {
        this(name, 0D);
    }

    public DoubleTag(final String name, final double payload) {
        super(new TagHeader(BaseType.DOUBLE.Id(), name), payload);
    }

    @Override
    public Double getPayload() {
        return (double) super.getPayload();
    }

    public void setPayload(final double payload) {
        super.setPayload(payload);
    }

}
