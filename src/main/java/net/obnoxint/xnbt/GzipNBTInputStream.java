package net.obnoxint.xnbt;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GzipNBTInputStream extends NBTInputStream {

    public GzipNBTInputStream(final DataInputStream in) throws IOException {
        super(new DataInputStream(new GZIPInputStream(in)));
    }

}
