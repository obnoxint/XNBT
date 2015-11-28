package net.obnoxint.xnbt;

import java.util.List;
import java.util.Map;

import net.obnoxint.xnbt.BaseTag.BaseType;
import net.obnoxint.xnbt.types.ByteArrayTag;
import net.obnoxint.xnbt.types.ByteTag;
import net.obnoxint.xnbt.types.CompoundTag;
import net.obnoxint.xnbt.types.DoubleTag;
import net.obnoxint.xnbt.types.EndTag;
import net.obnoxint.xnbt.types.FloatTag;
import net.obnoxint.xnbt.types.IntegerArrayTag;
import net.obnoxint.xnbt.types.IntegerTag;
import net.obnoxint.xnbt.types.ListTag;
import net.obnoxint.xnbt.types.LongTag;
import net.obnoxint.xnbt.types.ShortTag;
import net.obnoxint.xnbt.types.StringTag;

abstract class BaseTagBuilder implements TagBuilder {

    private static final BaseTagBuilder[] baseTags = new BaseTagBuilder[BaseType.reservedIds() + 1];

    static {

        // End tag
        baseTags[BaseType.END.Id()] = new BaseTagBuilder() {

            @Override
            public EndTag build(final byte type, final String name, final Object payload) {
                return BaseTag.ENDTAG;
            }
        };

        // Byte tag
        baseTags[BaseType.BYTE.Id()] = new BaseTagBuilder() {

            @Override
            public ByteTag build(final byte type, final String name, final Object payload) {
                return new ByteTag(name, (Byte) payload);
            }
        };

        // Short tag
        baseTags[BaseType.SHORT.Id()] = new BaseTagBuilder() {

            @Override
            public ShortTag build(final byte type, final String name, final Object payload) {
                return new ShortTag(name, (Short) payload);
            }
        };

        // Integer tag
        baseTags[BaseType.INTEGER.Id()] = new BaseTagBuilder() {

            @Override
            public IntegerTag build(final byte type, final String name, final Object payload) {
                return new IntegerTag(name, (Integer) payload);
            }
        };

        // Long tag
        baseTags[BaseType.LONG.Id()] = new BaseTagBuilder() {

            @Override
            public LongTag build(final byte type, final String name, final Object payload) {
                return new LongTag(name, (Long) payload);
            }
        };

        // Float tag
        baseTags[BaseType.FLOAT.Id()] = new BaseTagBuilder() {

            @Override
            public FloatTag build(final byte type, final String name, final Object payload) {
                return new FloatTag(name, (Float) payload);
            }
        };

        // Double tag
        baseTags[BaseType.DOUBLE.Id()] = new BaseTagBuilder() {

            @Override
            public DoubleTag build(final byte type, final String name, final Object payload) {
                return new DoubleTag(name, (Double) payload);
            }
        };

        // Byte array tag
        baseTags[BaseType.BYTE_ARRAY.Id()] = new BaseTagBuilder() {

            @Override
            public ByteArrayTag build(final byte type, final String name, final Object payload) {
                return new ByteArrayTag(name, (byte[]) payload);
            }
        };

        // String tag
        baseTags[BaseType.STRING.Id()] = new BaseTagBuilder() {

            @Override
            public StringTag build(final byte type, final String name, final Object payload) {
                return new StringTag(name, (String) payload);
            }
        };

        // List tag
        baseTags[BaseType.LIST.Id()] = new BaseTagBuilder() {

            @SuppressWarnings("unchecked")
            @Override
            public ListTag build(final byte type, final String name, final Object payload) {
                return new ListTag(name, (List<NBTTag>) payload);
            }
        };

        // Compound tag
        baseTags[BaseType.COMPOUND.Id()] = new BaseTagBuilder() {

            @SuppressWarnings("unchecked")
            @Override
            public CompoundTag build(final byte type, final String name, final Object payload) {
                return new CompoundTag(name, (Map<String, NBTTag>) payload);
            }
        };

        // Integer array tag
        baseTags[BaseType.INTEGER_ARRAY.Id()] = new BaseTagBuilder() {

            @Override
            public IntegerArrayTag build(final byte type, final String name, final Object payload) {
                return new IntegerArrayTag(name, (int[]) payload);
            }
        };

    }

    static BaseTagBuilder getBuilder(final byte type) {
        return baseTags[type];
    }

}
