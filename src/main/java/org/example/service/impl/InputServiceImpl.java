package org.example.service.impl;

import org.example.service.ImplInputService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputServiceImpl implements ImplInputService {


    @Override
    public int entryIntData() {
        Scanner in = new Scanner(System.in);
        int number;
           try {
               number = in.nextInt();
           } catch (NumberFormatException e) {
               OutputServiceImpl out = new OutputServiceImpl();
               out.outputData("Вы неккоректно ввели число");
               out.outputData("Попробуйте еще раз");
               return entryIntData();
           }
        return number;
    }

    @Override
    public byte entryByteData() {
        Scanner in = new Scanner(System.in);
        byte number;
            try {
                number = in.nextByte();
            } catch (NumberFormatException | InputMismatchException e) {
                OutputServiceImpl out = new OutputServiceImpl();
                out.outputData("Вы неккоректно ввели число");
                out.outputData("Попробуйте еще раз");
                return entryByteData();
            }
        return number;
    }
}
