package net.obnoxint.xnbt;

/**
 * <p>
 * Represents a tag. A tag contains a header (see {@link NBTTagHeader}) and a payload.
 * </p>
 */
public interface NBTTag {

    /**
     * @return the NBTTagHeader
     */
    NBTTagHeader getHeader();

    /**
     * @return the payload
     */
    Object getPayload();

}
