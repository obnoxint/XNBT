package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;

/**
 * <p>
 * Used to finalize {@link CompoundTag}s and files in IO operations. Shouldn't be used programmatically.
 * </p>
 */
public class EndTag extends BaseTag {

    public EndTag() {
        super(new TagHeader(BaseType.END.Id(), null), null);
    }

    @Override
    public String getPayload() {
        return null;
    }

    public void setPayload(final String payload) {}

}
