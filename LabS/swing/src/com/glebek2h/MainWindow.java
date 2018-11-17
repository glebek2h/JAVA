package com.glebek2h;

/**
 * swing
 * Created by fpm.kazachin on 17.11.2018 23:11
 */

import javax.swing.*;

public class MainWindow extends JFrame
{
    public MainWindow()
    {
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args)
    {
        MainWindow mainWindow = new MainWindow();
    }

}