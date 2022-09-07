package com.github.lanamirko04.ditronfm;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.lanamirko04.ditronfm.ui.AboutDialog;
import com.github.lanamirko04.ditronfm.ui.AppFrame;
import com.github.lanamirko04.ditronfm.utilities.*;

import javax.swing.*;
import java.io.FileNotFoundException;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        setAppLookAndFeel(System.getProperty("os.name"));
        AppFrame f = new AppFrame();
        f.setVisible(true);
    }

    private static void setAppLookAndFeel(String os) {
        try {
            if (os.startsWith("Windows")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                FlatIntelliJLaf.setup();
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println("An error has occurred ( T^T)");
            ex.printStackTrace();
        }

    }
}
