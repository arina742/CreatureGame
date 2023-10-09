package org.example.servises;

import java.util.Scanner;

public class InputService implements ImplInput{
    Scanner in = new Scanner(System.in);

    @Override
    public int entryIntData() {
        return in.nextInt();
    }

    @Override
    public byte entryByteData() {
        return in.nextByte();
    }
}
