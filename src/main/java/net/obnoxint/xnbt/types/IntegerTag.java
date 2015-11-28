package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class IntegerTag extends BaseTag {

    public IntegerTag(final String name, final int payload) {
        super(new NBTTagHeader(BaseType.INTEGER.Id(), name), payload);
    }

    @Override
    public Integer getPayload() {
        return (int) super.getPayload();
    }

    public void setPayload(final int payload) {
        super.setPayload(payload);
    }

}
