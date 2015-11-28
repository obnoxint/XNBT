package net.obnoxint.xnbt;

import java.util.HashMap;
import java.util.Map;

import net.obnoxint.xnbt.types.EndTag;

/**
 * <p>
 * The base implementation which all tag types extend from.
 * </p>
 */
public class BaseTag implements NBTTag {

    /**
     * <p>
     * Contains all base NBT types.
     * </p>
     */
    public static enum BaseType {

        END((byte) 0),
        BYTE((byte) 1),
        SHORT((byte) 2),
        INTEGER((byte) 3),
        LONG((byte) 4),
        FLOAT((byte) 5),
        DOUBLE((byte) 6),
        BYTE_ARRAY((byte) 7),
        STRING((byte) 8),
        LIST((byte) 9),
        COMPOUND((byte) 10),
        INTEGER_ARRAY((byte) 11);

        private static final Map<Byte, BaseType> idMap = new HashMap<>();

        static {
            for (final BaseType e : values()) {
                idMap.put(e.id, e);
            }
        }

        /**
         * @param id
         *            the id
         * @return the BaseType of the given id
         */
        public static BaseType byId(final byte id) {
            return idMap.get(id);
        }

        /**
         * @return the highest id of all BaseTypes
         */
        public static int reservedIds() {
            return idMap.size() - 1;
        }

        private final byte id;

        private BaseType(final byte id) {
            this.id = id;
        }

        /**
         * @return the id
         */
        public byte Id() {
            return id;
        }

    }

    /**
     * No reason to instantiate an {@link EndTag} every time you need one.
     */
    public static final EndTag ENDTAG = new EndTag();

    private final NBTTagHeader header;

    private Object payload;

    /**
     * <p>
     * Creates a new BaseTag.
     * </p>
     *
     * @param header
     *            the header
     * @param payload
     *            the payload
     */
    protected BaseTag(final NBTTagHeader header, final Object payload) {

        if (header.getType() < 0) {
            throw new IllegalArgumentException("illegal tag type: " + header.getType());
        }

        this.header = header;
        this.payload = payload;
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
        final BaseTag other = (BaseTag) obj;
        if (header == null) {
            if (other.header != null) {
                return false;
            }
        } else if (!header.equals(other.header)) {
            return false;
        }
        return true;
    }

    @Override
    public NBTTagHeader getHeader() {
        return header;
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((header == null) ? 0 : header.hashCode());
        return result;
    }

    /**
     * @param payload
     *            the new payload
     */
    protected void setPayload(final Object payload) {
        this.payload = payload;
    }

}
