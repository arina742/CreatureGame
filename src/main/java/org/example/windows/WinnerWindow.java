package org.example.windows;

import javax.swing.*;

public class WinnerWindow {
    private static final int width = 170;
    private static final int height = 210;

    private String winner;
    public WinnerWindow(String winner){
        this.winner = winner;
    }

    public void showWinnerWindow(){
        JFrame frame = new JFrame();

        frame.setSize(width, height);
        frame.setLocation(300, 300);

        JPanel panel = new JPanel();
        panel.setSize(200, 200);
        frame.add(panel);

        JLabel label1 = new JLabel();

        label1.setText("Победил " + winner + "!!!");
        panel.add(label1);

        var icon = new ImageIcon( "src/main/resources/prize.png");

        panel.add(new JLabel(icon));
        frame.setVisible(true);
    }
}
