package net.obnoxint.xnbt;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Before;
import org.junit.Test;

import net.obnoxint.xnbt.types.NBTTag.BaseType;
import net.obnoxint.xnbt.types.XNBTTag;

public class XNBTTest {

    private static String FILE_DOCUMENT = "test.document.";

    @Test
    public void documentWriteReadTest() throws Exception {

        // Write uncompressed
        try (NBTOutputStream os = new NBTOutputStream(new FileOutputStream(FILE_DOCUMENT + "uncompressed"))) {
            os.writeTag(new XNBTTag(new TestDocument().defaults()));
        }

        // Write compressed
        try (GzipNBTOutputStream os = new GzipNBTOutputStream(new FileOutputStream(FILE_DOCUMENT + "compressed"))) {
            os.writeTag(new XNBTTag(new TestDocument().defaults()));
        }

        // Read uncompressed
        try (NBTInputStream in = new NBTInputStream(new FileInputStream(FILE_DOCUMENT + "uncompressed"))) {
            final XNBTTag tag = (XNBTTag) in.readTag();
            XNBT.dump(0, tag, null, System.out);
        }

        // Read compressed
        try (GzipNBTInputStream in = new GzipNBTInputStream(new FileInputStream(FILE_DOCUMENT + "compressed"))) {
            final XNBTTag tag = (XNBTTag) in.readTag();
            XNBT.dump(0, tag, null, System.out);
        }

    }

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testGetBuilder() {
        for (final BaseType e : BaseType.values()) {
            if (e.equals(BaseType.END)) {
                continue;
            }
            assertNotNull(XNBT.getBuilder(e.Id()));
        }
    }

    @Test
    public void testGetReader() {
        for (final BaseType e : BaseType.values()) {
            if (e.equals(BaseType.END)) {
                continue;
            }
            assertNotNull(XNBT.getReader(e.Id()));
        }
    }

    @Test
    public void testGetWriter() {
        for (final BaseType e : BaseType.values()) {
            if (e.equals(BaseType.END)) {
                continue;
            }
            assertNotNull(XNBT.getWriter(e.Id()));
        }
    }

}
