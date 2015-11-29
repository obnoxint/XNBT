package net.obnoxint.xnbt.types;

import java.util.Collection;
import java.util.Map;

import net.obnoxint.xnbt.NBTTag;

public class CompoundTag extends AbstractCompoundTag {

    public CompoundTag(final String name, final Map<String, NBTTag> payload) {
        super(name, payload);
    }

    @Override
    public NBTTag put(final NBTTag value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return getPayload().put(value.getHeader().getName(), value);
    }

    @Override
    public void putAll(final Collection<? extends NBTTag> tags) {
        for (final NBTTag tag : tags) {
            put(tag);
        }
    }

}
