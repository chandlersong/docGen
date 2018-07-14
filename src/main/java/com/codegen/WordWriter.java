package com.codegen;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;

public class WordWriter {

    private File source;

    private XWPFDocument document;

    private int totalLine = 0;

    public WordWriter(File source) {
        this.source = source;
        document = new XWPFDocument();

    }

    public void writeSouce(File source) {

        final XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setText(source.getName());
        run.addBreak();
        totalLine++;
        try (LineIterator lineIterator = FileUtils.lineIterator(source)) {
            while (lineIterator.hasNext()) {
                run.setText(lineIterator.next());
                totalLine++;
                run.addBreak();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        System.out.println("totoal page:" + totolPages());
        this.commit();
    }

    public void commit() {
        try {
            FileOutputStream out = new FileOutputStream(source);
            document.write(out);
            out.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public int totolPages() {

        return totalLine/50+1;
    }
}
