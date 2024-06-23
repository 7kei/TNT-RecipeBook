package com.twinkletoes.recipebook;

import com.twinkletoes.recipebook.UI.Console.ConsoleApp;
import com.twinkletoes.recipebook.UI.JFX.JFXApp;

public class Main {
    public static void main(String[] args) {
        boolean consoleStart = true;

        for (String arg : args) {
            if (arg == "-c" || arg == "--console") consoleStart = true;
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
