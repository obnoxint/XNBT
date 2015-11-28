package net.obnoxint.xnbt.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.obnoxint.xnbt.BaseTag;
import net.obnoxint.xnbt.NBTTag;
import net.obnoxint.xnbt.NBTTagHeader;

@SuppressWarnings("unchecked")
public class ListTag extends BaseTag implements List<NBTTag> {

    private static List<NBTTag> sanitizePayload(final List<NBTTag> payload) {

        if (payload == null) {
            return new ArrayList<>();
        }

        byte t = -1;

        for (final NBTTag e : payload) {

            if (e == null) {
                throw new NullPointerException();
            }

            if (t == -1) {
                t = e.getHeader().getType();
                if (t == BaseType.END.Id()) {
                    throw new IllegalArgumentException("ListTag may not contain EndTags");
                }
            } else if (t != e.getHeader().getType()) {
                throw new IllegalArgumentException("NBTTag of type " + e.getHeader().getType()
                        + " can't be added to ListTag because it already contains one or more elements of type " + t);
            }

        }

        return payload;
    }

    public ListTag(final String name, final List<NBTTag> payload) {
        super(new NBTTagHeader(BaseType.LIST.Id(), name), sanitizePayload(payload));
    }

    @Override
    public void add(final int index, final NBTTag element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(final NBTTag e) {
        if (e == null) {
            throw new NullPointerException();
        }

        if (e.equals(ENDTAG)) {
            throw new IllegalArgumentException("ListTag may not contain EndTags");
        }

        if (!isEmpty() && e.getHeader().getType() != getContentType()) {
            throw new IllegalArgumentException("NBTTag of type " + e.getHeader().getType()
                    + " can't be added to ListTag because it already contains one or more elements of type "
                    + getPayload().get(0).getHeader().getType());
        }

        return ((List<NBTTag>) super.getPayload()).add(e);
    }

    @Override
    public boolean addAll(final Collection<? extends NBTTag> c) {
        boolean r = false;

        for (final NBTTag e : c) {
            if (add(e)) {
                r = true;
            }
        }

        return r;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends NBTTag> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        ((List<NBTTag>) super.getPayload()).clear();
    }

    @Override
    public boolean contains(final Object o) {
        return ((List<NBTTag>) super.getPayload()).contains(o);
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        return ((List<NBTTag>) super.getPayload()).containsAll(c);
    }

    @Override
    public NBTTag get(final int index) {
        return ((List<NBTTag>) super.getPayload()).get(index);
    }

    public byte getContentType() {
        return isEmpty() ? 0 : getPayload().get(0).getHeader().getType();
    }

    @Override
    public List<NBTTag> getPayload() {
        return new ArrayList<>((List<NBTTag>) super.getPayload());
    }

    @Override
    public int indexOf(final Object o) {
        return ((List<NBTTag>) super.getPayload()).indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return ((List<NBTTag>) super.getPayload()).isEmpty();
    }

    @Override
    public Iterator<NBTTag> iterator() {
        return ((List<NBTTag>) super.getPayload()).iterator();
    }

    @Override
    public int lastIndexOf(final Object o) {
        return ((List<NBTTag>) super.getPayload()).lastIndexOf(o);
    }

    @Override
    public ListIterator<NBTTag> listIterator() {
        return ((List<NBTTag>) super.getPayload()).listIterator();
    }

    @Override
    public ListIterator<NBTTag> listIterator(final int index) {
        return ((List<NBTTag>) super.getPayload()).listIterator(index);
    }

    @Override
    public NBTTag remove(final int index) {
        return ((List<NBTTag>) super.getPayload()).remove(index);
    }

    @Override
    public boolean remove(final Object o) {
        return ((List<NBTTag>) super.getPayload()).remove(o);
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        return ((List<NBTTag>) super.getPayload()).removeAll(c);
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return ((List<NBTTag>) super.getPayload()).retainAll(c);
    }

    @Override
    public NBTTag set(final int index, final NBTTag element) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void setPayload(final List<NBTTag> payload) {
        super.setPayload(payload == null ? new ArrayList<>() : sanitizePayload(payload));
    }

    @Override
    public int size() {
        return ((List<NBTTag>) super.getPayload()).size();
    }

    @Override
    public List<NBTTag> subList(final int fromIndex, final int toIndex) {
        return ((List<NBTTag>) super.getPayload()).subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return ((List<NBTTag>) super.getPayload()).toArray();
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        return ((List<NBTTag>) super.getPayload()).toArray(a);
    }

}
