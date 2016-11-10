package net.obnoxint.xnbt.types;

import net.obnoxint.xnbt.BaseTag;

/**
 * <p>
 * Used to finalize {@link CompoundTag}s and files in IO operations. Shouldn't be used programmatically.
 * </p>
 */
public final class EndTag extends BaseTag {

    /**
     * No reason to instantiate an {@link EndTag} every time you need one.
     */
    public static final EndTag INSTANCE = new EndTag();

    private EndTag() {
        super(new TagHeader(BaseType.END.Id(), null), null);
    }

    @Override
    public Object getPayload() {
        return null;
    }

    public void setPayload(final Object payload) {}

}
