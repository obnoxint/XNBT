package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class EndTag extends BaseTag {

    public EndTag() {
        super(new NBTTagHeader(BaseType.END.Id(), null), null);
    }

    @Override
    public String getPayload() {
        return null;
    }

    public void setPayload(final String payload) {}

}
