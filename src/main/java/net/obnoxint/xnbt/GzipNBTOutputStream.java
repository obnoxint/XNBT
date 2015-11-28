package net.obnoxint.xnbt;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipNBTOutputStream extends NBTOutputStream {

    public GzipNBTOutputStream(final DataOutputStream out) throws IOException {
        super(new DataOutputStream(new GZIPOutputStream(out)));
    }

}
