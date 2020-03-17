package edu.touro.mco364;

import java.io.*;

public class Cypher {
    public static void encrypt(String sourceFile, String destinationFile, int letterShift) {
        letterShift = letterShift % 26;
        String encrypted = shiftChars(getInputData(sourceFile), letterShift);

        if(encrypted.equals("Fail")) {
            System.out.println("Something went wrong!");
            return;
        }

        try (FileOutputStream fos = new FileOutputStream(destinationFile)) {
            fos.write(encrypted.getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInputData(String sourceFile) {
        try (FileInputStream fis = new FileInputStream(sourceFile)) {
            return new String(fis.readAllBytes());
        } catch(IOException e) {
            e.printStackTrace();
            return "Fail";
        }
    }

    private static String shiftChars(String inputData, int letterShift) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < inputData.length(); i++) {
            sb.append(shiftLetter(inputData.charAt(i), letterShift));
        }
        return sb.toString();
    }

    private static char shiftLetter(char c, int letterShift) {
        if('A' <= c && c <= 'Z') {
            c += letterShift;
            if(c > 'Z')
                c -= 26;
        }
        else if('a' <= c && c <= 'z') {
            c += letterShift;
            if(c > 'z')
                c -= 26;
        }
        return c;
    }

    public static void main(String[] args) {
        encrypt("test.txt", "dest.txt", 1);
    }
}