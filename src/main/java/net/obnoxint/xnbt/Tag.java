package net.obnoxint.xnbt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.obnoxint.xnbt.types.ByteArrayTag;
import net.obnoxint.xnbt.types.ByteTag;
import net.obnoxint.xnbt.types.CompoundTag;
import net.obnoxint.xnbt.types.DoubleTag;
import net.obnoxint.xnbt.types.FloatTag;
import net.obnoxint.xnbt.types.IntegerArrayTag;
import net.obnoxint.xnbt.types.IntegerTag;
import net.obnoxint.xnbt.types.ListTag;
import net.obnoxint.xnbt.types.LongTag;
import net.obnoxint.xnbt.types.ShortTag;
import net.obnoxint.xnbt.types.StringTag;
import net.obnoxint.xnbt.types.XNBTTag;

/**
 * <p>
 * Classes annotated with Tag can be serialized and deserialized using a {@link XNBTTag}.
 * This annotation can be omitted on a class, if its fields contain only primitive types.
 * Fields without this annotation will be ignored.
 * </p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface Tag {

    /**
     * <p>
     * See {@link XNBTTag}.
     * </p>
     */
    byte XNBT = (byte) -1;

    /**
     * <p>
     * Automatically determines the type of the tag by this fields type class. (best effort)
     * </p>
     */
    byte AUTO = (byte) 0;

    /**
     * <p>
     * See {@link ByteTag}.
     * </p>
     */
    byte BYTE = (byte) 1;

    /**
     * <p>
     * See {@link ShortTag}.
     * </p>
     */
    byte SHORT = (byte) 2;

    /**
     * <p>
     * See {@link IntegerTag}.
     * </p>
     */
    byte INTEGER = (byte) 3;

    /**
     * <p>
     * See {@link LongTag}.
     * </p>
     */
    byte LONG = (byte) 4;

    /**
     * <p>
     * See {@link FloatTag}.
     * </p>
     */
    byte FLOAT = (byte) 5;

    /**
     * <p>
     * See {@link DoubleTag}
     * </p>
     */
    byte DOUBLE = (byte) 6;

    /**
     * <p>
     * See {@link ByteArrayTag}.
     * </p>
     */
    byte BYTE_ARRAY = (byte) 7;

    /**
     * <p>
     * See {@link StringTag}.
     * </p>
     */
    byte STRING = (byte) 8;

    /**
     * <p>
     * See {@link ListTag}.
     * </p>
     */
    byte LIST = (byte) 9;

    /**
     * <p>
     * See {@link CompoundTag}.
     * </p>
     */
    byte COMPOUND = (byte) 10;

    /**
     * <p>
     * See {@link IntegerArrayTag}.
     * </p>
     */
    byte INTEGER_ARRAY = (byte) 11;

    /**
     * <p>
     * The name of the class or field. If this value is omitted, the simple name of the class or the name of the field
     * will be used.
     * </p>
     *
     * @return the name
     */
    String name() default "";

    /**
     * <p>
     * </p>
     *
     * @return the type
     */
    byte type() default AUTO;

}
