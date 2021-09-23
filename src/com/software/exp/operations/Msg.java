package com.software.exp.operations;

public class Msg {
    public static void show()
    {
        System.out.println("-c [FILEPATH]:");
        System.out.println("\tCount frequency of 52 English alphabets (A-Z & a-z)");
        System.out.println("/********************************/");
        System.out.println("-f [FILEPATH]:");
        System.out.println("\tCount frequency of all words in the file");
        System.out.println("\t-n [Integer]: Print the Top n words in ASC order");
        System.out.println("\tIf you don't want to use -n command, the program is going to show the top 100 words");
        System.out.println("/********************************/");
        System.out.println("-d [DIRECTORY]:");
        System.out.println("\tExecute -f command on all files in the DIRECTORY");
        System.out.println("\t-n [Integer]: Print the Top n words in ASC order");
        System.out.println("\tIf you don't want to use -n command, the program is going to show the top 100 words");
        System.out.println("\t-s :To traverse and execute \"-f\" on all files in directories under the DIRECTORY you'v entered");
        System.out.println("/********************************/");
        System.out.println("-x:");
        System.out.println("\tUse Stop Words");
        System.out.println("/********************************/");
        System.out.println("-p [Number] [FILEPATH]:");
        System.out.println("\tCount frequency of phrase which contains [Number] words");
        System.out.println("/********************************/");
        System.out.println("-v [FILEPATH]:");
        System.out.println("\tTransfer all irregular verbs into its original tense and count the frequency");
        System.out.println("/********************************/");
        System.out.println("Example -1: -d -s -n 20 -x C:\\***");
        System.out.println("Example -2: -p 2 C:\\***");
        System.out.println();
    }
}
