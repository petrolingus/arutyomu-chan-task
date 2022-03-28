package me.arutyomu.act;

import javax.swing.*;
import java.awt.*;

public class Task1 extends JFrame {

    public Task1() {
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

//        c.add(child);

        setTitle("Task 1");
        setPreferredSize(new Dimension(640, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Task1();
    }

}
