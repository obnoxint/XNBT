package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class DoubleTag extends BaseTag {

    public DoubleTag(final String name, final double payload) {
        super(new NBTTagHeader(BaseType.DOUBLE.Id(), name), payload);
    }

    @Override
    public Double getPayload() {
        return (double) super.getPayload();
    }

    public void setPayload(final double payload) {
        super.setPayload(payload);
    }

}
