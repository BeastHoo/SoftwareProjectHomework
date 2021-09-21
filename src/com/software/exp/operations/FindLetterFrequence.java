package com.software.exp.operations;

import com.software.exp.Utils.FileLoader;

import java.io.FileInputStream;
import java.util.Arrays;

public class FindLetterFrequence {
    private FileLoader fileLoader;



    public FindLetterFrequence(FileLoader fileLoader) {
        this.fileLoader=fileLoader;
    }

    public void Execute()
    {
        if (fileLoader.isFileDirectory())
        {
            System.out.println("Your File Path leads to a DIRECTORY, NOT A FILE!");
            fileLoader.close();
            return;
        }
        FileInputStream fileInputStream = fileLoader.getFileInputStream();
        int temp = 0;
        StringBuffer stringBuffer=new StringBuffer();
        //当temp等于-1时，表示已经到了文件结尾，停止读取
        try{
            while ((temp = fileInputStream.read()) != -1) {
                stringBuffer.append((char) temp);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            calc(stringBuffer);
            fileLoader.close();
        }
    }


    private void calc(StringBuffer str)
    {
        double capitalletter[]=new double[26];
        double lowercaseletter[]=new double[26];
        int count=0;
        for(int i=0;i<str.length();i++) {
            char ch=str.charAt(i);
            if(ch>='A'&&ch<='Z'||ch>='a'&&ch<='z') {
                for(int j=0;j<26;j++) {
                    if(ch=='A'+j)
                        capitalletter[j]++;
                }
                for(int k=0;k<26;k++) {
                    if(ch=='a'+k)
                        lowercaseletter[k]++;
                }
                count++;
            }
        }

        double percentage1[]=new double[52];
        double percentage2[]=new double[52];
        for(int i=0;i<26;i++) {
            percentage1[i]=capitalletter[i]/count;
            percentage2[i]=percentage1[i];
        }
        for(int i=26;i<52;i++) {
            percentage1[i]=lowercaseletter[i-26]/count;
            percentage2[i]=percentage1[i];
        }
        Arrays.sort(percentage1);
        for(int i=51;i>=0;i--) {
            int max=0;
            for(int j=0;j<52;j++) {
                if(percentage2[j]==percentage1[i])
                    max=j;
            }
            if(max>=26)
                System.out.print(((char)('a'+max-26))+"：");
            else
                System.out.print(((char)('A'+max))+"：");
            System.out.println(String.format("%.2f",percentage1[i]*100)+'%');
        }
        System.out.println("Total："+count);
    }

}
