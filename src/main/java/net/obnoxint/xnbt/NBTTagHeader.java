package net.obnoxint.xnbt;

/**
 * <p>
 * The header of any {@link NBTTag}.
 * </p>
 * <p>
 * The header stores the immutable type id and name of an NBTTag.
 * </p>
 */
public class NBTTagHeader {

    private static final String NO_TAG_NAME = "";

    private final byte type;
    private final String name;

    /**
     * <p>
     * Creates a new NBTTagHeader.
     * </p>
     *
     * @param type
     *            the type
     * @param name
     *            the name
     */
    public NBTTagHeader(final byte type, final String name) {
        this.type = type;
        this.name = name == null ? NO_TAG_NAME : name;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NBTTagHeader other = (NBTTagHeader) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public byte getType() {
        return type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + type;
        return result;
    }

}
