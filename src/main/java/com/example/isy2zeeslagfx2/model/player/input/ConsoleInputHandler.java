package com.example.isy2zeeslagfx2.model.player.input;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    @Override
    public String getInput(String message)
    {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
