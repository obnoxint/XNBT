package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class FloatTag extends BaseTag {

    public FloatTag(final String name, final float payload) {
        super(new NBTTagHeader(BaseType.FLOAT.Id(), name), payload);
    }

    @Override
    public Float getPayload() {
        return (float) super.getPayload();
    }

    public void setPayload(final float payload) {
        super.setPayload(payload);
    }

}
