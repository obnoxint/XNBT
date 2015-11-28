package net.obnoxint.xnbt;

import java.io.IOException;

public interface TagPayloadReader {

    Object read(NBTInputStream in) throws IOException;

}
