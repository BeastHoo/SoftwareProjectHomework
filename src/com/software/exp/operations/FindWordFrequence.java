package com.software.exp.operations;

import com.software.exp.Utils.FileLoader;
import com.software.exp.Utils.Normalize;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public  class FindWordFrequence {
    private Map<String, Integer> map1 = new HashMap<>();
    private Map<String, Boolean> stopwords;
    private FileLoader fileLoader;
    private Normalize normalize;
    private boolean flag;

    public   FindWordFrequence(FileLoader fileLoader)  {
        this.fileLoader=fileLoader;
        stopwords=new HashMap<>();
        flag=false;
        stopwords.put("re",true);
        stopwords.put("s",true);
        stopwords.put("m",true);
    }

    public void setNormalize(Normalize normalize) {
        this.normalize = normalize;
        flag=true;
    }

    public void setStopwords(Map<String, Boolean> stopwords) {
        if (stopwords!=null && !stopwords.isEmpty())
        {
            this.stopwords = stopwords;
            this.stopwords.put("re",true);
            this.stopwords.put("s",true);
            stopwords.put("m",true);
        }

    }

    public void exec(int n){

        String sz[];
        Integer num[];
        int MAXNUM=100;
        if (n!=0)
        {
            MAXNUM = n;
        }


        sz = new String[MAXNUM + 1];
        num = new Integer[MAXNUM + 1];

        int account;
        // Vector<String> ve1=new Vector<String>();
        try {
            calc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int g_run;

        for (g_run = 0; g_run < MAXNUM + 1; g_run++) {
            account = 1;
            for (Map.Entry<String, Integer> it : map1.entrySet()) {
                if (account == 1) {
                    sz[g_run] = it.getKey();
                    num[g_run] = it.getValue();
                    account = 2;
                }
                if (num[g_run] < it.getValue()) {
                    sz[g_run] = it.getKey();
                    num[g_run] = it.getValue();
                }
                // System.out.println("英文单词: "+it.getKey()+" 该英文单词出现次数: "+it.getValue());
            }
            map1.remove(sz[g_run]);
        }
        if (n<=0)
        {
            for (int i = 0; i < g_run; i++) {
                if (sz[i] == null)
                    continue;
                if (sz[i].equals(""))
                    continue;
                System.out.println("Word: " + sz[i] + "\t\t\t Times: " + num[i]);
            }
        }else {
            for (int i = 0; i < n; i++) {
                if (sz[i] == null)
                    continue;
                if (sz[i].equals(""))
                    continue;
                System.out.println("Word: " + sz[i] + "\t\t\t Times: " + num[i]);
            }
        }
        fileLoader.close();
    }

    private void calc() throws IOException {
        FileInputStream b = fileLoader.getFileInputStream();
        InputStreamReader c = new InputStreamReader(b, StandardCharsets.UTF_8);
        String string2 = "";
        String temp = "";
        while (c.ready()) {
            char string1 = (char) c.read();
            if (!isWord(string1)) {
                //弊端：当出现I`m 这样的缩写时，m会被当作一个单词
                if (map1.containsKey(string2)) {
                        Integer num1 = map1.get(string2) + 1;
                        map1.put(string2, num1);
                } else {
                    if (!stopwords.containsKey(string2))
                    {
                        if (flag)
                        {
                            temp=normalize.normalizate(string2);
                            if(!temp.equals(""))
                                string2=temp;
                            if (map1.containsKey(string2))
                            {
                                Integer num1 = map1.get(string2)+1;
                                map1.put(string2, num1);
                                continue;
                            }
                        }
                        Integer num1 = 1;
                        map1.put(string2, num1);
                    }
                }
                string2 = "";
            } else {
                string2 += string1;
            }
        }
        if (!string2.isEmpty()) {
            if (map1.containsKey(string2)) {
                Integer num1 = map1.get(string2) + 1;
                map1.put(string2, num1);
            } else {
                if (!stopwords.containsKey(string2))
                {
                    if (flag)
                    {
                        temp=normalize.normalizate(string2);
                        if(!temp.equals(""))
                            string2=temp;
                    }
                    Integer num1 = 1;
                    map1.put(string2, num1);
                }
            }
            string2 = "";

        }
        c.close();
        b.close();
    }


    public boolean isWord(char a) {
        return a <= 'z' && a >= 'a' || a <= 'Z' && a >= 'A';
    }

}
