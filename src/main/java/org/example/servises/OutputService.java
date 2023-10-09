package org.example.servises;

public class OutputService implements ImplOutput{

    @Override
    public void outputData(String data) {
        System.out.println(data);
    }
}
