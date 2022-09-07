package com.github.lanamirko04.ditronfm.ui.custom_componets.panels;

import com.github.lanamirko04.ditronfm.ui.custom_componets.textfields.FocusTextField;
import com.github.lanamirko04.ditronfm.ui.listener.keyadapter.GroupsKeyAdapter;
import com.github.lanamirko04.ditronfm.utilities.GroupList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GroupsPanel extends JPanel {

    private final int LINES = 5;
    private final Dimension TEXT_FIELD_SIZE = new Dimension(100, 24);
    private PaddingPanel padding;
    private JLabel[] labels;
    private FocusTextField[] textFields;
    private JButton btn;
    private GroupList groups;
    private ActionListener actionListener;

    public GroupsPanel(GroupList groups, ActionListener actionListener) {
        this.groups = groups;
        this.actionListener = actionListener;
        setup();
    }

    public void updateTextFields() {
        for (int i = 0; i < textFields.length; ++i) {
            textFields[i].setText(groups.getGroup(i).getName());
        }
    }

    public void saveGroups() {
        for (int i = 0; i < LINES; ++i) {
            groups.getGroup(i).setName((textFields[i].getText().length() == 0) ? groups.getGroup(i).getName() : textFields[i].getText());
        } /* copy-paste from old project: I'm lazy */
    }

    private void setup() {
        int i;
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(0, 0, 3, 0);

        padding = new PaddingPanel(20);
        padding.setLayout(new GridBagLayout());

        textFields = new FocusTextField[LINES];
        for (i = 0; i < LINES; ++i) {
            textFields[i] = new FocusTextField(true);
            textFields[i].setText(groups.getGroup(i).getName());
            textFields[i].setPreferredSize(TEXT_FIELD_SIZE);
            textFields[i].addKeyListener(new GroupsKeyAdapter(textFields[i]));

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.insets = insets;
            padding.add(textFields[i], gbc);
        }

        insets.right = 5;

        labels = new JLabel[LINES];
        for (i = 0; i < LINES; ++i) {
            labels[i] = new JLabel((i + 1) + ":");
            labels[i].setLabelFor(textFields[i]);

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = insets;
            padding.add(labels[i], gbc);
        }

        btn = new JButton("Salva");
        btn.setName("gp_btn");
        btn.addActionListener(actionListener);
        btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn.doClick();
                }
            }
        });

        insets.top = 10;
        insets.right = 0;
        insets.bottom = 0;

        gbc.gridy = LINES;
        gbc.insets = insets;
        gbc.gridwidth = 2;
        padding.add(btn, gbc);

        add(padding);
        setBorder(BorderFactory.createTitledBorder("Gruppi"));
    }
}