package com.codegen;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class DocGeneratorTest {

    private File tempFolder = new File(FileUtils.getTempDirectory(), "codeGenExmaple");

    private File newFile = new File(tempFolder, "docGenerator.docx");

    private File source = new File(".");

    @Test
    public void testListSubDirectory(){

        System.out.println((new DocGenerator(newFile,source)).listSubDirectory());
    }

    @Test
    public void testGenerator(){

          (new DocGenerator(newFile,source)).writerToFile(50);
    }

    @Test
    public void testGenerator5(){

        for(int i =0;i<5;i++) {
            (new DocGenerator(new File(tempFolder, i+".docx"), new File("../TestCase"))).writerToFile(50);
        }
    }


    @Before
    public void setup(){
        if (tempFolder.exists()) {
            FileUtils.deleteQuietly(tempFolder);
        }

        tempFolder.mkdirs();
        System.out.println(tempFolder);
        System.out.println(source.getAbsolutePath());
    }
}
