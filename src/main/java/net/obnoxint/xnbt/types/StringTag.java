package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class StringTag extends BaseTag {

    private static String sanitizePayload(final String payload) {
        return payload == null ? "" : payload;
    }

    public StringTag(final String name, final String payload) {
        super(new NBTTagHeader(BaseType.STRING.Id(), name), sanitizePayload(payload));
    }

    @Override
    public String getPayload() {
        return (String) super.getPayload();
    }

    public void setPayload(final String payload) {
        super.setPayload(sanitizePayload(payload));
    }

}
