package com.software.exp;
/*
 date:2021/9/13
 author:heyuqian
 */

import com.software.exp.Utils.FileLoader;
import com.software.exp.operations.FindLetterFrequence;
import com.software.exp.operations.FindPhraseFrequence;
import com.software.exp.operations.FindWordFrequence;
import com.software.exp.operations.Msg;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class mainApplication {

    public static void main(String[] args) {
        FindLetterFrequence findLetterFrequence;
        FileLoader fileLoader;
        FindWordFrequence findWordFrequence;
        FindPhraseFrequence findPhraseFrequence;
        int num=0;
        int phraselenth=0;
        boolean isS=false;
        Map<String, Boolean> map=null;
        boolean flag=false;





        System.out.println("" +
                " __       __                            __        ________                                                                               \n" +
                "|  \\  _  |  \\                          |  \\      |        \\                                                                              \n" +
                "| $$ / \\ | $$  ______    ______    ____| $$      | $$$$$$$$______    ______    ______   __    __   ______   _______    _______   ______  \n" +
                "| $$/  $\\| $$ /      \\  /      \\  /      $$      | $$__   /      \\  /      \\  /      \\ |  \\  |  \\ /      \\ |       \\  /       \\ /      \\ \n" +
                "| $$  $$$\\ $$|  $$$$$$\\|  $$$$$$\\|  $$$$$$$      | $$  \\ |  $$$$$$\\|  $$$$$$\\|  $$$$$$\\| $$  | $$|  $$$$$$\\| $$$$$$$\\|  $$$$$$$|  $$$$$$\\\n" +
                "| $$ $$\\$$\\$$| $$  | $$| $$   \\$$| $$  | $$      | $$$$$ | $$   \\$$| $$    $$| $$  | $$| $$  | $$| $$    $$| $$  | $$| $$      | $$    $$\n" +
                "| $$$$  \\$$$$| $$__/ $$| $$      | $$__| $$      | $$    | $$      | $$$$$$$$| $$__| $$| $$__/ $$| $$$$$$$$| $$  | $$| $$_____ | $$$$$$$$\n" +
                "| $$$    \\$$$ \\$$    $$| $$       \\$$    $$      | $$    | $$       \\$$     \\ \\$$    $$ \\$$    $$ \\$$     \\| $$  | $$ \\$$     \\ \\$$     \\\n" +
                " \\$$      \\$$  \\$$$$$$  \\$$        \\$$$$$$$       \\$$     \\$$        \\$$$$$$$  \\$$$$$$$  \\$$$$$$   \\$$$$$$$ \\$$   \\$$  \\$$$$$$$  \\$$$$$$$\n" +
                "                                                                                   | $$                                                  \n" +
                "                                                                                   | $$                                                  \n" +
                "                                                                                    \\$$                                                  ");

        String order="";
        String filepath="";
        String cmd="";
        while(true)
        {
            System.out.println();
            System.out.println("Input -h for details");
            Scanner scanner=new Scanner(System.in);
            order=scanner.nextLine();


            //指令解析
            String[] command=order.split(" ");
            filepath=command[command.length-1];
            for (int i=0;i< command.length;i++)
            {
                if (command[i].equals("-n"))
                {
                    try{
                        num=Integer.parseInt(command[++i]);
                    }catch (Exception e)
                    {
                        System.out.println("Please enter a number after -n!");
                        i-=1;
                        flag=true;
                    }

                }else if (command[i].equals("-s"))
                {
                    isS=true;
                }
                else if(command[i].equals("-x"))
                {
                    map = getStopWords();
                    if (map==null) {
                        System.out.println("Null of StopWords!");
                        flag=true;
                    }
                }
                else if (command[i].equals("-h") || command[i].equals("-c") || command[i].equals("-d") || command[i].equals("-f")
                        || command[i].equals("-p") || command[i].equals("-v") || command[i].equals("exit")) {
                    cmd=command[i];
                    if (command[i].equals("-p"))
                    {
                        try{
                            phraselenth=Integer.parseInt(command[++i]);
                        }catch (Exception e)
                        {
                            i-=1;
                            System.out.println("Please enter a number after -p!");
                            flag=true;
                        }
                    }
                }
            }
            
            if (flag)
                continue;
            
            switch (cmd)
            {
                case "-h":
                    Msg.show();
                    break;



                case "-c":

                    if (filepath.equals(""))
                    {
                        System.out.println("ERROR: LACK OF FILE PATH! Please type in file path after orders!");
                        break;
                    }
                    fileLoader = new FileLoader(filepath);
                    if(!fileLoader.isFileExsist())
                    {
                        System.out.println("ERROR: INVALID FILE PATH! Check your file path carefully!");
                        break;
                    }
                    findLetterFrequence = new FindLetterFrequence(fileLoader);
                    findLetterFrequence.Execute();
                    break;



                case "-f":
                    if (filepath.equals(""))
                    {
                        System.out.println("ERROR: LACK OF FILE PATH! Please type in file path after orders!");
                        break;
                    }
                    fileLoader = new FileLoader(filepath);
                    if(!fileLoader.isFileExsist())
                    {
                        System.out.println("ERROR: INVALID FILE PATH! Check your file path carefully!");
                        break;
                    }
                    findWordFrequence = new FindWordFrequence(fileLoader);
                    System.out.println(filepath+": ");
                    findWordFrequence.setStopwords(map);
                    findWordFrequence.exec(num);
                    break;



                case "-d":
                    if (isS)
                    {
                        fileLoader=new FileLoader(filepath);
                        if (!fileLoader.isFileDirectory())
                        {
                            File file = fileLoader.getFile();
                            filepath = file.getParent();
                        }

                        //通过递归来进行子目录的遍历
                        analysis(new File(filepath),num, map);
                        break;
                    }

                    if (filepath.equals(""))
                    {
                        System.out.println("ERROR: LACK OF FILE PATH! Please type in file path after orders!");
                        break;
                    }
                    fileLoader = new FileLoader(filepath);
                    if(!fileLoader.isFileExsist())
                    {
                        System.out.println("ERROR: INVALID FILE PATH! Check your file path carefully!");
                        break;
                    }

                    List<String> list=fileLoader.getFiles();

                    for (String str:list)
                    {
                        fileLoader=new FileLoader(str);
                        System.out.println("--------------------");
                        System.out.println(str+": ");
                        findWordFrequence = new FindWordFrequence(fileLoader);
                        findWordFrequence.setStopwords(map);
                        findWordFrequence.exec(num);
                    }
                    break;


                case "-p":
                    if (filepath.equals(""))
                    {
                        System.out.println("ERROR: LACK OF FILE PATH! Please type in file path after orders!");
                        break;
                    }
                    fileLoader = new FileLoader(filepath);
                    if(!fileLoader.isFileExsist())
                    {
                        System.out.println("ERROR: INVALID FILE PATH! Check your file path carefully!");
                        break;
                    }
                    findPhraseFrequence=new FindPhraseFrequence(fileLoader);
                    findPhraseFrequence.exec(phraselenth);
                    break;


                case "-v":
                    System.out.println("frequence interface");
                    break;


                case "exit":
                    System.out.println("ByeBye!");
                    return;


                default:
                    System.out.println("NOT AN Exact Command! Please input -h for more infomation!");
            }
        }
    }

    private static void analysis(@NotNull File dir , int num, Map<String,Boolean> map){
        if(dir.exists()){
            //抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件和目录。
            File[] files = dir.listFiles();
            if(null!=files){
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        analysis(files[i], num, map);
                    } else {
//                        System.out.println(files[i].getAbsolutePath().substring(files[i].getAbsolutePath().lastIndexOf(".")));
                        String path=files[i].getAbsolutePath();
                        String suffix=path.substring(path.lastIndexOf("."));
                        if (suffix.equals(".txt"))
                        {
                            System.out.println();
                            System.out.println(path);
                            System.out.println("--------------------");
                            FileLoader fileLoader=new FileLoader();
                            fileLoader.setFile(files[i]);
                            FindWordFrequence findWordFrequence=new FindWordFrequence(fileLoader);
                            findWordFrequence.setStopwords(map);
                            findWordFrequence.exec(num);
                        }
                    }
                }
            }
        }else{
            System.out.println("NO such a File! ");
        }
    }

    private static Map<String, Boolean> getStopWords()
    {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter absolute path of StopWords(txt): ");
        String fp=scanner.nextLine();
        System.out.println(fp);
        File file=new File(fp);
        Map<String,Boolean> map1= new HashMap<>();
        String string2="";
        if (!file.exists())
        {
            System.out.println("Invalid file path!");
            return null;
        }
        else {
            if(file.isDirectory())
            {
                System.out.println("File Path leads to a Directory, not a file!");
                return null;
            }
            try {
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader reader=new InputStreamReader(fileInputStream);
            while (reader.ready()) {
                char string1 = 0;

                string1 = (char) reader.read();

                if (!isWord(string1)) {
                    //弊端：当出现I`m 这样的缩写时，m会被当作一个单词
                    map1.put(string2, true);
                    string2 = "";
                } else {
                    string2 += string1;
                }
            }
            if (!string2.isEmpty()) {
                map1.put(string2, true);
                string2 = "";
            }
            reader.close();
            fileInputStream.close();
            } catch (IOException e) {
                System.out.println("Error happened while loading "+fp);
                e.printStackTrace();
            }
        }

        return map1;
    }

    private static boolean isWord(char a) {
        return a <= 'z' && a >= 'a' || a <= 'Z' && a >= 'A';
    }
}
