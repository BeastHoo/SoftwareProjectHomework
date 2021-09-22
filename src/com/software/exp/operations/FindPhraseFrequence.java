package com.software.exp.operations;

import com.software.exp.Utils.FileLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FindPhraseFrequence {
    private Map<String, Integer> map1 = new HashMap<>();
    private FileLoader fileLoader;

    public   FindPhraseFrequence(FileLoader fileLoader)  {
        this.fileLoader=fileLoader;

    }



    public void exec(int phraselenth){

        String sz[];
        Integer num[];
        int MAXNUM=100;

        sz = new String[MAXNUM + 1];
        num = new Integer[MAXNUM + 1];

        int account;
        // Vector<String> ve1=new Vector<String>();
        try {
            calc(phraselenth);
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
                System.out.println("Phrase: " + sz[i] + "\t\t\t Times: " + num[i]);
            }
            fileLoader.close();
    }

    private void calc(int n) throws IOException {
        FileInputStream b = fileLoader.getFileInputStream();
        InputStreamReader c = new InputStreamReader(b, StandardCharsets.UTF_8);
        String string2 = "";
        String[] str;
        int apptime=0;
        while (c.ready()) {
            char string1 = (char) c.read();
            if (!isWord(string1)) {
                //弊端：当出现I`m 这样的缩写时，m会被当作一个单词
                if (string1==' ')
                {
                    if(apptime!=n)
                    {
                        apptime++;
                        string2+=string1;
                        if (string2.equals(" "))
                            string2="";
                        continue;
                    }

                    if (map1.containsKey(string2)) {
                        Integer num1 = map1.get(string2) + 1;
                        map1.put(string2, num1);
                    } else {
                        str = string2.split(" ");
                        if (str.length == n) {
                            Integer num1 = 1;
                            map1.put(string2, num1);
                        }
                    }
                    apptime=0;
                    string2 = "";
                }
            } else {
                string2 += string1;
            }
        }
        if (!string2.isEmpty()) {
            if (map1.containsKey(string2)) {
                Integer num1 = map1.get(string2) + 1;
                map1.put(string2, num1);
            } else {
                str=string2.split(" ");
                if (str.length==n)
                {
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
