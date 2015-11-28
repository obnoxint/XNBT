package net.obnoxint.xnbt;

public interface TagBuilder {

    NBTTag build(byte type, String name, Object payload);

}
