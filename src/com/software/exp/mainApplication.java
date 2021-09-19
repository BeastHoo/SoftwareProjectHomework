package com.software.exp;
/*
 date:2021/9/13
 author:heyuqian
 */

import com.software.exp.Utils.FileLoader;
import com.software.exp.operations.FindLetterFrequence;

import java.util.Scanner;

public class mainApplication {

    public static void main(String[] args) {
        FindLetterFrequence findLetterFrequence;
        FileLoader fileLoader;




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
        String to="";
        while(true)
        {
            System.out.println();
            System.out.println("Input -h for details");
            Scanner scanner=new Scanner(System.in);
            order=scanner.nextLine();


            //指令解析
            try
            {
                to=order.substring(0,2);
                order=order.substring(order.lastIndexOf(" ")+1);
            }catch (Exception e)
            {
                System.out.println("NOT AN Exact Command! Please input -h for more infomation!");
            }

            if(!to.equals(""))
            switch (to)
            {
                case "-h":
                    System.out.println("help interface");
                    break;



                case "-c":
                    String fileName=order;
                    if (fileName.equals(""))
                    {
                        System.out.println("ERROR: LACK OF FILE PATH! Please type in file path after orders!");
                        break;
                    }
                    fileLoader = new FileLoader(fileName);
                    if(!fileLoader.isFileExsist())
                    {
                        System.out.println("ERROR: INVALID FILE PATH! Check your file path carefully!");
                        break;
                    }
                    findLetterFrequence = new FindLetterFrequence(fileLoader);
                    findLetterFrequence.Execute();
                    break;



                case "-f":
                    System.out.println("frequence interface");
                    break;



                case "-d":
                    System.out.println("frequence interface");
                    break;


                case "-d -s":
                    System.out.println("frequence interface");
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
