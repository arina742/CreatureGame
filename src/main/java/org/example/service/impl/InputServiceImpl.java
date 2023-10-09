package org.example.service.impl;

import org.example.service.ImplInputService;

import java.util.Scanner;

public class InputServiceImpl implements ImplInputService {
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
