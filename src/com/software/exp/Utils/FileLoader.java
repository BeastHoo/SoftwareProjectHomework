package com.software.exp.Utils;

import java.io.*;

public class FileLoader {
    private  File file;

    private boolean isFileExsist;


    private FileInputStream fileInputStream;

    public FileLoader(String filepath) {
        file=new File(filepath);
        if (!file.exists())
        {
            isFileExsist=false;
            System.out.println("未找到file");}
        else{
            isFileExsist=true;
            try {
                fileInputStream=new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }

    public boolean isFileExsist() {
        return isFileExsist;
    }

    public void close()
    {
        try {

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }
}
