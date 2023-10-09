package org.example.service.impl;

import org.example.service.ImplOutputService;

public class OutputServiceImpl implements ImplOutputService {

    @Override
    public void outputData(String data) {
        System.out.println(data);
    }
}
