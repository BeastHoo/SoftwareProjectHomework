package com.software.exp.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Normalize {
    private  Map<String,String> dic ;


    public Normalize()
    {
        File file=new File("src/com/software/exp/Utils/irregular verbs.txt");
        if (file.exists())
        {
            dic=new HashMap<>();
            try {
                BufferedReader bf =new BufferedReader(new FileReader(file));
                String str=null;
                String[] s;
                while((str=bf.readLine()) != null)
                {
                    s=str.split("\t");
                    for (int i=1;i<s.length;i++)
                    {
                        dic.put(s[i],s[0]);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error loading Irregular Verbs!");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Irregular verbs: No file was found!");
        }

    }


    public String normalizate(String verb) {
        return dic.getOrDefault(verb, "");
    }

}
