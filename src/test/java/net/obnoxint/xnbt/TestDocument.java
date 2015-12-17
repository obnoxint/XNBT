package net.obnoxint.xnbt;

import static net.obnoxint.xnbt.Tag.BYTE;
import static net.obnoxint.xnbt.Tag.BYTE_ARRAY;
import static net.obnoxint.xnbt.Tag.COMPOUND;
import static net.obnoxint.xnbt.Tag.DOUBLE;
import static net.obnoxint.xnbt.Tag.FLOAT;
import static net.obnoxint.xnbt.Tag.INTEGER;
import static net.obnoxint.xnbt.Tag.INTEGER_ARRAY;
import static net.obnoxint.xnbt.Tag.LIST;
import static net.obnoxint.xnbt.Tag.LONG;
import static net.obnoxint.xnbt.Tag.SHORT;
import static net.obnoxint.xnbt.Tag.STRING;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.obnoxint.xnbt.types.IntegerTag;
import net.obnoxint.xnbt.types.NBTTag;

@Tag(name = "document")
public class TestDocument {

    @Tag(type = BYTE)
    private Byte _byte;

    @Tag(type = SHORT)
    private Short _short;

    @Tag(type = INTEGER)
    private Integer _integer;

    @Tag(type = LONG)
    private Long _long;

    @Tag(type = FLOAT)
    private Float _float;

    @Tag(type = DOUBLE)
    private Double _double;

    @Tag(type = BYTE_ARRAY)
    private byte[] _byteA;

    @Tag(type = STRING)
    private String _string;

    @Tag(type = LIST)
    private List<NBTTag> _list;

    @Tag(type = COMPOUND)
    private Map<String, NBTTag> _compound;

    @Tag(type = INTEGER_ARRAY)
    private int[] _integerA;

    public TestDocument defaults() {

        _byte = Byte.MAX_VALUE;
        _short = Short.MAX_VALUE;
        _integer = Integer.MAX_VALUE;
        _long = Long.MAX_VALUE;
        _float = Float.MAX_VALUE;
        _double = Double.MAX_VALUE;

        _byteA = new byte[Math.abs(Byte.MIN_VALUE) + Byte.MAX_VALUE + 1];
        for (int i = 0; i < _byteA.length; i++) {
            _byteA[i] = (byte) (i + Byte.MIN_VALUE);
        }

        _string = "test";

        _list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            _list.add(new IntegerTag("listitem" + i, i));
        }

        _compound = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            final String n = "compounditem" + i;
            _compound.put(n, new IntegerTag(n, i));
        }

        _integerA = new int[1000];
        for (int i = 0; i < _integerA.length; i++) {
            _integerA[i] = i;
        }

        return this;
    }

}
