package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class LongTag extends BaseTag {

    public LongTag(final String name, final long payload) {
        super(new NBTTagHeader(BaseType.LONG.Id(), name), payload);
    }

    @Override
    public Long getPayload() {
        return (long) super.getPayload();
    }

    public void setPayload(final long payload) {
        super.setPayload(payload);
    }

}
