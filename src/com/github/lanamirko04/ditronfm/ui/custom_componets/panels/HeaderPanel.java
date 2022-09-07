package com.github.lanamirko04.ditronfm.ui.custom_componets.panels;

import com.github.lanamirko04.ditronfm.ui.custom_componets.textfields.FocusTextField;
import com.github.lanamirko04.ditronfm.ui.listener.keyadapter.HeaderKeyAdapter;
import com.github.lanamirko04.ditronfm.utilities.ReceiptHeader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HeaderPanel extends JPanel {

    private final int LINES = 7;
    private final Dimension TEXT_FIELD_SIZE = new Dimension(180, 24);

    private PaddingPanel padding;
    private JLabel[] labels;
    private FocusTextField[] textFields;
    private JCheckBox[] checkBoxes;
    private JButton btn;
    private ActionListener actionListener;
    private ReceiptHeader header;

    public HeaderPanel(ReceiptHeader header, ActionListener actionListener) {
        this.actionListener = actionListener;
        this.header = header;
        setup();
    }

    public void updateTextFields() {
        for (int i = 0; i < textFields.length; ++i) {
            textFields[i].setText(header.getLine(i).getTxt());
        }
    }

    public void saveHeader() {
        for (int i = 0; i < LINES; ++i) {
            if (checkBoxes[i].isSelected()) {
                header.setLine(i, new ReceiptHeader.HeaderLine(i + 1, (textFields[i].getText().length() > 15) ? textFields[i].getText().substring(0, 14) : textFields[i].getText(), true));
                textFields[i].setText(header.getLine(i).getTxt());
            } else {
                header.setLine(i, new ReceiptHeader.HeaderLine(i + 1, textFields[i].getText()));
            }
        }
    }
    private void setup() {
        int i;
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(0, 0, 3, 0);

        padding = new PaddingPanel(20);
        padding.setLayout(new GridBagLayout());

        gbc.insets = insets;
        checkBoxes = new JCheckBox[LINES];
        for (i = 0; i < LINES; ++i) {
            checkBoxes[i] = new JCheckBox("Grassetto");

            gbc.gridx = 2;
            gbc.gridy = i;
            padding.add(checkBoxes[i], gbc);
        }

        insets.right = 5;
        gbc.insets = insets;
        textFields = new FocusTextField[LINES];
        for (i = 0; i < LINES; ++i) {
            textFields[i] = new FocusTextField(true);
            textFields[i].setPreferredSize(TEXT_FIELD_SIZE);
            textFields[i].setText(header.getLine(i).getTxt());
            textFields[i].addKeyListener(new HeaderKeyAdapter(textFields[i], checkBoxes[i]));

            gbc.gridx = 1;
            gbc.gridy = i;
            padding.add(textFields[i], gbc);
        }

        labels = new JLabel[LINES];
        for (i = 0; i < LINES; ++i) {
            labels[i] = new JLabel((i + 1) +  ":");
            labels[i].setLabelFor(textFields[i]);

            gbc.gridx = 0;
            gbc.gridy = i;
            padding.add(labels[i], gbc);
        }

        btn = new JButton("Salva");
        btn.setName("hp_btn");
        btn.addActionListener(actionListener);
        btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn.doClick();
                }
            }
        });

        insets.top = 7;
        insets.right = 0;
        insets.bottom = 0;
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        padding.add(btn, gbc);

        this.add(padding);
        this.setBorder(BorderFactory.createTitledBorder("Intestazione"));
    }

}
