package com.software.exp.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    private  File file;

    private boolean isFileExsist;

    private boolean isFileDirectory;

    private FileInputStream fileInputStream;

    public FileLoader(){}

    public FileLoader(String filepath) {
        file=new File(filepath);
        isFileDirectory=false;
        if (!file.exists())
        {
            isFileExsist=false;
            System.out.println("No File was Found! ");}
        else{
            isFileExsist=true;
            if (!file.isDirectory())
            try {
                fileInputStream=new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            else
            {
                isFileDirectory=true;
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
            if (fileInputStream!=null)
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFile(@NotNull File file) {
        this.file = file;
        if (!file.exists())
        {
            isFileExsist=false;
            System.out.println("No File was Found! ");}
        else{
            isFileExsist=true;
            if (!file.isDirectory())
                try {
                    fileInputStream=new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            else
            {
                isFileDirectory=true;
            }
        }
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public  List<String> getFiles() {
        List<String> files = new ArrayList<>();
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                String fp=tempList[i].toString();
                files.add(fp);
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
                System.out.println("File: "+fp);
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

    public boolean isFileDirectory() {
        return isFileDirectory;
    }
}
