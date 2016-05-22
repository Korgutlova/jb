import java.io.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Boolean.parseBoolean;

public class Main {

    //проверка, является ли данное выражение маской файла
    public static boolean isMask(String mask){
        char [] a = {'\\', '/', '|' , ':', '<', '>' ,'"'};
        for (int i = 0; i < mask.length(); i++) {
            for(char k : a){
                if (mask.charAt(i) == k)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        String [] s = sc.nextLine().split(" ");
        File inputDir = new File(s[0]);
        if(!inputDir.exists()){
            throw new IllegalArgumentException("Illegal symbol in input path:" + s[0] + " (or this directory doesn't exists)");
        }
        File outputDir = new File(s[1]);
        if(!outputDir.exists()){
            throw new IllegalArgumentException("Illegal symbol in input path:" + s[1] + " (or this directory doesn't exists)");
        }
        String mask = s[2];
        if(!isMask(mask)){
            throw new IllegalArgumentException("Illegal symbols in mask:" + mask);
        }
        int waitInterval = parseInt(s[3]);
        if(waitInterval < 0){
            throw new IllegalArgumentException("Incorrect value for waitInterval:" + waitInterval +  " (must be positive int value)");
        }
        boolean includeSubfolders = false;
        boolean autoDelete = false;
        if(s[4].equals("true")|| s[4].equals("false")){
            includeSubfolders = parseBoolean(s[4]);
        }
        else{
            throw new IllegalArgumentException("Invalid value for includeSubfolders:" + s[4] + "(must be true or false");
        }

        if(s[5].equals("true")|| s[5].equals("false")){
            autoDelete = parseBoolean(s[5]);
        }
        else{
            throw new IllegalArgumentException("Invalid value for includeSubfolders:" + s[5] + "(must be true or false");
        }

        DirectoryScanner scanner = new DirectoryScanner(inputDir, outputDir, mask, waitInterval, includeSubfolders, autoDelete);
        scanner.scan();

    }
}
