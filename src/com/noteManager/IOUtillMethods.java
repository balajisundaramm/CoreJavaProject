package com.noteManager;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by Balaji on 28/11/17.
 */
public class IOUtillMethods {
    public static void readerClose(Reader reader) {
        if(reader!=null)
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void writerClose(Writer writer) {
        if(writer!=null)
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
