package main;

import java.util.Scanner;

public class IntergalacticNumerals {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = "";

        IntergalacticNumeralTranslator translator = IntergalacticNumeralTranslator
                .init();

        while(true) {
            System.out.print("[Numeral Convert]$ ");
            input = scan.nextLine();
            if (input.equals("exit")) {
                // Input "exit" to exit the program
                System.out.println("Exit program");
                scan.close();
                break;
            }
            translator.handleInput(input);
        }
    }
}
