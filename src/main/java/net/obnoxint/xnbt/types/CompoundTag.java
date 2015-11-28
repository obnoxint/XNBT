package net.obnoxint.xnbt.types;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTag;
import net.obnoxint.xnbt.NBTTagHeader;

@SuppressWarnings("unchecked")
public class CompoundTag extends BaseTag implements Map<String, NBTTag> {

    private static Map<String, NBTTag> sanitizePayload(final Map<String, NBTTag> payload) {
        if (payload == null) {
            return new HashMap<>();
        }

        if (payload.containsKey(null) || payload.containsValue(null)) {
            throw new NullPointerException();
        }

        return new HashMap<>(payload);
    }

    public CompoundTag(final String name, final Map<String, NBTTag> payload) {
        super(new NBTTagHeader(BaseType.COMPOUND.Id(), name), sanitizePayload(payload));
    }

    @Override
    public void clear() {
        ((Map<String, NBTTag>) super.getPayload()).clear();
    }

    @Override
    public boolean containsKey(final Object key) {
        return ((Map<String, NBTTag>) super.getPayload()).containsKey(key);
    }

    @Override
    public boolean containsValue(final Object value) {
        return ((Map<String, NBTTag>) super.getPayload()).containsValue(value);
    }

    @Override
    public Set<java.util.Map.Entry<String, NBTTag>> entrySet() {
        return ((Map<String, NBTTag>) super.getPayload()).entrySet();
    }

    @Override
    public NBTTag get(final Object key) {
        return ((Map<String, NBTTag>) super.getPayload()).get(key);
    }

    @Override
    public Map<String, NBTTag> getPayload() {
        return new HashMap<>((Map<String, NBTTag>) super.getPayload());
    }

    @Override
    public boolean isEmpty() {
        return ((Map<String, NBTTag>) super.getPayload()).isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return ((Map<String, NBTTag>) super.getPayload()).keySet();
    }

    public NBTTag put(final NBTTag value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return ((Map<String, NBTTag>) super.getPayload()).put(value.getHeader().getName(), value);
    }

    @Override
    public NBTTag put(final String key, final NBTTag value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void putAll(final Collection<? extends NBTTag> tags) {
        for (final NBTTag tag : tags) {
            put(tag);
        }
    }

    @Override
    public void putAll(final Map<? extends String, ? extends NBTTag> m) {
        putAll(m.values());
    }

    @Override
    public NBTTag remove(final Object key) {
        return ((Map<String, NBTTag>) super.getPayload()).remove(key);
    }

    public void setPayload(final Map<String, NBTTag> payload) {
        super.setPayload(sanitizePayload(payload));
    }

    @Override
    public int size() {
        return ((Map<String, NBTTag>) super.getPayload()).size();
    }

    @Override
    public Collection<NBTTag> values() {
        return ((Map<String, NBTTag>) super.getPayload()).values();
    }

}
