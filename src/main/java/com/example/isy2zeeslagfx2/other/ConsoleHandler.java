package com.example.isy2zeeslagfx2.other;

import java.util.Scanner;

public class ConsoleHandler {
    public static String getConsoleInput(String message)
    {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
