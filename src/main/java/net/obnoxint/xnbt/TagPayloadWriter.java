package net.obnoxint.xnbt;

import java.io.IOException;

public interface TagPayloadWriter {

    void write(Object payload, NBTOutputStream out) throws IOException;

}
