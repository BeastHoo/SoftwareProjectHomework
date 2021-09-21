package com.software.exp.operations;

import com.software.exp.Utils.FileLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public  class FindWordFrequence {
    private Map<String, Integer> map1 = new HashMap<>();
    private FileLoader fileLoader;

    public   FindWordFrequence(FileLoader fileLoader)  {
        this.fileLoader=fileLoader;
    }

    public void exec(){

        String sz[];
        Integer num[];
        final int MAXNUM = 20;

        sz = new String[MAXNUM + 1];
        num = new Integer[MAXNUM + 1];

        int account = 1;
        // Vector<String> ve1=new Vector<String>();
        try {
            calc();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int g_run = 0;

        for (g_run = 0; g_run < MAXNUM + 1; g_run++) {
            account = 1;
            for (Map.Entry<String, Integer> it : map1.entrySet()) {
                if (account == 1) {
                    sz[g_run] = it.getKey();
                    num[g_run] = it.getValue();
                    account = 2;
                }
                if (account == 0) {
                    account = 1;
                    continue;
                }
                if (num[g_run] < it.getValue()) {
                    sz[g_run] = it.getKey();
                    num[g_run] = it.getValue();
                }
                // System.out.println("英文单词: "+it.getKey()+" 该英文单词出现次数: "+it.getValue());
            }
            map1.remove(sz[g_run]);
        }

        for (int i = 0; i < g_run; i++) {
            if (sz[i] == null)
                continue;
            if (sz[i].equals(""))
                continue;
            System.out.println("Word: " + sz[i] + "\t\t\t Times: " + num[i]);
        }
    }

    private void calc() throws IOException {
        FileInputStream b = fileLoader.getFileInputStream();
        InputStreamReader c = new InputStreamReader(b, "UTF-8");
        String string2 = new String();
        while (c.ready()) {
            char string1 = (char) c.read();
            if (!isWord(string1)) {
                //弊端：当出现I`m 这样的缩写时，m会被当作一个单词
                if (map1.containsKey(string2)) {
                    Integer num1 = map1.get(string2) + 1;
                    map1.put(string2, num1);
                } else {
                    Integer num1 = 1;
                    map1.put(string2, num1);
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
                Integer num1 = 1;
                map1.put(string2, num1);
            }
            string2 = "";
        }
        c.close();
        b.close();
    }


    public boolean isWord(char a) {
        if (a <= 'z' && a >= 'a' || a <= 'Z' && a >= 'A')
            return true;
        return false;
    }

}
