package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTagHeader;

public class IntegerArrayTag extends BaseTag {

    private static int[] sanitizePayload(final int[] payload) {
        return payload == null ? new int[0] : payload;
    }

    public IntegerArrayTag(final String name, final int[] payload) {
        super(new NBTTagHeader(BaseType.INTEGER_ARRAY.Id(), name), sanitizePayload(payload));
    }

    @Override
    public int[] getPayload() {
        return (int[]) super.getPayload();
    }

    public void setPayload(final int[] payload) {
        super.setPayload(sanitizePayload(payload));
    }

}
