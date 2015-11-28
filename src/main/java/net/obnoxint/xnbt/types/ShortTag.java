package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class ShortTag extends BaseTag {

    public ShortTag(final String name, final short payload) {
        super(new NBTTagHeader(BaseType.SHORT.Id(), name), payload);
    }

    @Override
    public Short getPayload() {
        return (short) super.getPayload();
    }

    public void setPayload(final short payload) {
        super.setPayload(payload);
    }

}
