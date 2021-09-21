package com.software.exp;
/*
 date:2021/9/13
 author:heyuqian
 */

import com.software.exp.Utils.FileLoader;
import com.software.exp.operations.FindLetterFrequence;
import com.software.exp.operations.FindWordFrequence;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class mainApplication {

    public static void main(String[] args) {
        FindLetterFrequence findLetterFrequence;
        FileLoader fileLoader;
        FindWordFrequence findWordFrequence;





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
            boolean flag=true;
            order=scanner.nextLine();


            //指令解析
                String[] command=order.split(" ");
                if (command.length==0)
                {
                    System.out.print("Please type in Something:");
                    flag=false;
                }
                if (command[0].length()!=2)
                {
                    System.out.println("NOT AN Exact Command! Please input -h for more infomation!");
                    flag=false;
                }
                filepath=command[command.length-1];

            if(flag)
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
                    findWordFrequence.exec();
                    break;



                case "-d":
                    if (command.length>2)
                    {
                        String c2=command[1];
                        if (c2.equals("-s"))
                        {
                            FileLoader f=new FileLoader(filepath);
                            if (!f.isFileDirectory())
                            {
                                File file = f.getFile();
                                String fileParent = file.getParent();
                            }
                        }
                        else if (c2.equals("-n"))
                        {
                            int num;
                            try{
                                num=Integer.parseInt(command[2]);
                            }catch (Exception e)
                            {
                                System.out.println("Please type in a correct Number after -n command!");
                                return;
                            }

                        }
                    }
                    else {
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
                            findWordFrequence.exec();
                        }
                    }

                    break;




                case "-n":
                    System.out.println("frequence interface");
                    break;


                case "-x -f":
                    System.out.println("frequence interface");
                    break;


                case "-p":
                    System.out.println("frequence interface");
                    break;


                case "-v":
                    System.out.println("frequence interface");
                    break;


                case "exit":
                    return;


                default:
                    System.out.println("NOT AN Exact Command! Please input -h for more infomation!");
            }
        }
    }
}
