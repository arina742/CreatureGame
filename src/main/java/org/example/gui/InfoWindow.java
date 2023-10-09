package org.example.gui;

import javax.swing.*;

public class InfoWindow {
    private static final int width = 435;
    private static final int height = 280;
    public InfoWindow(){

    }

    public void showInfoWindow(){
        JFrame frame = new JFrame();

        frame.setSize(width, height);
        frame.setLocation(200, 200);

        JPanel panel = new JPanel();
        panel.setSize(200, 200);
        frame.add(panel);

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JLabel label8 = new JLabel();
        JLabel label9 = new JLabel();

        label1.setText("                         ❗ПРАВИЛА❗                              ");
        panel.add(label1);

        label2.setText("Ты играешь за игрока.\n Цель игры - победить монстра!");
        panel.add(label2);

        label3.setText("В начале рандомным образом");
        panel.add(label3);

        label4.setText("определяется уровень здоровья (в диапазоне от 150 до 200)");
        panel.add(label4);

        label5.setText("Вводишь силу аттаки от 1 до 30, если она пройдет успешно,");
        panel.add(label5);

        label6.setText("то необходимо указать диапазон урона, котрый ты хочешь нанести");
        panel.add(label6);

        label7.setText("Этот диапазон будет применяться и по отношению к монстру");
        panel.add(label7);

        label8.setText("Ты можешь исцелить себя на 30% от здоровья");
        panel.add(label8);

        label9.setText("     Но помни, что исцелиться, ты можешь только 4 раза!");
        panel.add(label9);

        JButton button = new JButton("Начать");
        button.setSize(100, 50);

        button.addActionListener(actionEvent->frame.dispose());
        panel.add(button);
        frame.setVisible(true);
    }
}
