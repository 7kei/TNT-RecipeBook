package com.twinkletoes.recipebook;

import com.twinkletoes.recipebook.UI.Console.ConsoleApp;
import com.twinkletoes.recipebook.UI.JFX.JFXApp;

public class Main {
    public static void main(String[] args) {
        boolean consoleStart = false;

        for (String arg : args) {
            if (arg.strip().equals("-c") || arg.strip().equals("--console"))
                consoleStart = true;
        }

        if (consoleStart) { 
            ConsoleApp consoleApp = new ConsoleApp();
            consoleApp.main();
        } else {
            JFXApp jfxApp = new JFXApp();
            jfxApp.main();
        }

    }
}
