package com.codegen;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class OneFileTest {

    private File source = new File("src\\main\\java\\com\\codegen\\WordWriter.java");

    private File tempFolder = new File(FileUtils.getTempDirectory(), "codeGenExmaple");

    private File winWordFile = new File(tempFolder, "codeGenExmaple.docx");

    @Test
    public void TestWriteFile() {
        WordWriter writer = new WordWriter(winWordFile);
        writer.writeSouce(source);
        System.out.println(winWordFile.exists());

    }

    @Before
    public void setup() {
        if (tempFolder.exists()) {
            FileUtils.deleteQuietly(tempFolder);
        }

        tempFolder.mkdirs();
        System.out.println(tempFolder);
    }
}
