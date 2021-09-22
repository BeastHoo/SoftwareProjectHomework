package com.software.exp;
/*
 date:2021/9/13
 author:heyuqian
 */

import com.software.exp.Utils.FileLoader;
import com.software.exp.operations.FindLetterFrequence;
import com.software.exp.operations.FindWordFrequence;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class mainApplication {

    public static void main(String[] args) {
        FindLetterFrequence findLetterFrequence;
        FileLoader fileLoader;
        FindWordFrequence findWordFrequence;
        int num=0;
        boolean isS=false;





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
                    num=Integer.parseInt(command[i+1]);
                    i++;
                }else if (command[i].equals("-s"))
                {
                    isS=true;
                }
            }
            

            
            switch (command[0])
            {
                case "-h":
                    System.out.println("help interface");
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
                        analysis(new File(filepath),num);
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
                        findWordFrequence.exec(num);
                    }


                    break;


                case "-p":
                    System.out.println("frequence interface");
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

    private static void analysis(@NotNull File dir , int num){
        if(dir.exists()){
            //抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件和目录。
            File[] files = dir.listFiles();
            if(null!=files){
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        analysis(files[i], num);
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
                            findWordFrequence.exec(num);
                        }
                    }
                }
            }
        }else{
            System.out.println("NO such a File! ");
        }
    }
}
