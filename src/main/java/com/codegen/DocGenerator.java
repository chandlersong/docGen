package com.codegen;

import java.io.File;
import java.io.FilenameFilter;
import java.util.*;

public class DocGenerator {

    private WordWriter writer;

    private File source;

    private String XML =".xml";

    private String JAVA =".java";

    public DocGenerator(File newFile, File source) {
        writer = new WordWriter(newFile);

        this.source = source;
    }

    public void writerToFile(int maxPage){
        ArrayList<File> folders = listSubDirectory();

        Random r = new Random();
        int size = folders.size();
        while(true){

            File folder = folders.get(r.nextInt(size));

            List<File> codeFiles = Arrays.asList(folder.listFiles(new FilenameFilter(){

                @Override public boolean accept(File dir, String name) {
                    return name.endsWith(XML)||name.endsWith(JAVA);
                }
            }));

            for(File code:codeFiles){
                writer.writeSouce(code);

                if(writer.totolPages() >maxPage){
                    return;
                }
            }
        }
    }

    public ArrayList<File> listSubDirectory(){

        final ArrayList<File> result = new ArrayList<>();

        return listChildrens(source);
    }

    private ArrayList<File> listChildrens(File source) {


        final ArrayList<File> result = new ArrayList<>();

        Optional<File[]> a = Optional.ofNullable(source.listFiles(file->file.isDirectory()));
        List<File> childrens = Arrays.asList(a.orElse(new File[0]));

        result.addAll(childrens);

        childrens.stream().forEach(directory->{
            result.addAll(listChildrens(directory));
        });

        return result;
    }
}
