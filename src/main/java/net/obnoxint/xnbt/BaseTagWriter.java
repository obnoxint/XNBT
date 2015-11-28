package net.obnoxint.xnbt;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.obnoxint.xnbt.BaseTag.BaseType;

abstract class BaseTagWriter implements TagPayloadWriter {

    private static final BaseTagWriter[] baseTags = new BaseTagWriter[12];

    static {

        // End tag
        baseTags[BaseType.END.Id()] = null;

        // Byte tag
        baseTags[BaseType.BYTE.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeByte((byte) payload);
            }
        };

        // Short tag
        baseTags[BaseType.SHORT.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeShort((short) payload);
            }
        };

        // Integer tag
        baseTags[BaseType.INTEGER.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeInt((int) payload);
            }
        };

        // Long tag
        baseTags[BaseType.LONG.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeLong((long) payload);
            }
        };

        // Float tag
        baseTags[BaseType.FLOAT.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeFloat((float) payload);
            }
        };

        // Double tag
        baseTags[BaseType.DOUBLE.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeDouble((double) payload);
            }
        };

        // Byte array tag
        baseTags[BaseType.BYTE_ARRAY.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                final byte[] p = (byte[]) payload;
                out.writeInt(p.length);
                for (int i = 0; i < p.length; i++) {
                    out.writeByte(p[i]);
                }
            }
        };

        // String tag
        baseTags[BaseType.STRING.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                out.writeUTF((String) payload);
            }
        };

        // List tag
        baseTags[BaseType.LIST.Id()] = new BaseTagWriter() {

            @SuppressWarnings("unchecked")
            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {
                final List<NBTTag> list = (List<NBTTag>) payload;

                byte ct;

                if (list.isEmpty()) {
                    ct = 0;
                } else {
                    ct = list.get(0).getHeader().getType();
                }

                final int s = list.size();

                out.writeByte(ct);
                out.writeInt(s);

                for (int i = 0; i < s; i++) {
                    XNBT.getWriter(ct).write(list.get(i).getPayload(), out);
                }
            }
        };

        // Compound tag
        baseTags[BaseType.COMPOUND.Id()] = new BaseTagWriter() {

            @SuppressWarnings("unchecked")
            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {
                for (final NBTTag t : ((Map<String, NBTTag>) payload).values()) {
                    out.writeTag(t);
                }
                out.writeTag(BaseTag.ENDTAG);
            }
        };

        // Integer array tag
        baseTags[BaseType.INTEGER_ARRAY.Id()] = new BaseTagWriter() {

            @Override
            public void write(final Object payload, final NBTOutputStream out) throws IOException {

                final int[] p = (int[]) payload;
                out.writeInt(p.length);
                for (int i = 0; i < p.length; i++) {
                    out.writeInt(p[i]);
                }
            }
        };

    }

    static TagPayloadWriter getWriter(final byte type) {
        return baseTags[type];
    }

}
