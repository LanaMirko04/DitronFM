package com.github.lanamirko04.ditronfm.ui;

import com.github.lanamirko04.ditronfm.ui.custom_componets.panels.PaddingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class AboutDialog extends JDialog {

    private final Dimension TEXT_AREA_SIZE = new Dimension(270, 40);
    private final String DESC = "This software is released under MIT licence\n" +
                                "Copyright © 2022 Lana Mirko";

    private PaddingPanel padding;
    private JLabel heading;
    private JTextArea desc;
    private JFrame parent;

    public AboutDialog(JFrame parent) {
        this.parent = parent;
        setup();
    }

    private void setup() {
        padding = new PaddingPanel(5, 15);
        heading = new JLabel("DitronFM (Ditron File Maker)");
        desc = new JTextArea(DESC);

        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 16));
        desc.setFont(desc.getFont().deriveFont(Font.PLAIN, 12));
        desc.setEditable(false);
        desc.setPreferredSize(TEXT_AREA_SIZE);

        padding.setLayout(new GridLayout(2, 1, 0, 10));
        padding.add(heading);
        padding.add(desc);

        setTitle("About");
        add(padding);
        setResizable(false);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        pack();
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(parent);
        super.setVisible(b);
    }
}
