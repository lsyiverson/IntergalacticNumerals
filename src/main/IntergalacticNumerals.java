package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class IntergalacticNumerals {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputPath = "";

        IntergalacticNumeralTranslator translator = IntergalacticNumeralTranslator
                .init();

        while(true) {
            System.out.println("Please input the text file path:");
            // Input a the test data file path
            inputPath = scan.nextLine();
            File data = new File(inputPath);
            if (data.exists() && data.isFile()) {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(data));
                    String tmpLine;
                    while((tmpLine = reader.readLine()) != null) {
                        translator.handleInput(tmpLine);
                    }
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                break;
            } else {
                System.out.println("\"" +inputPath+"\" is a wrong path");
            }
        }
        scan.close();
    }
}
