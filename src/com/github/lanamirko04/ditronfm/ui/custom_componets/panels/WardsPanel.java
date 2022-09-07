package com.github.lanamirko04.ditronfm.ui.custom_componets.panels;

import com.github.lanamirko04.ditronfm.ui.custom_componets.textfields.FocusTextField;
import com.github.lanamirko04.ditronfm.utilities.GroupList;
import com.github.lanamirko04.ditronfm.utilities.WardsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WardsPanel extends JPanel {

    private final String[] LABELS_TEXT = { "Reparto:", "Descrizione:", "Prezzo:", "Gruppo:" };
    private final Dimension SMALL_INPUT_COMPONENT_SIZE = new Dimension(80, 24);
    private final Dimension LARGE_INPUT_COMPONENT_SIZE = new Dimension(135, 24);

    private PaddingPanel padding;
    private JLabel[] labels;
    private JComboBox<Integer> w_comboBox;
    private JComboBox<String> g_comboBox;
    private FocusTextField d_textField;
    private FocusTextField p_textField;
    private JButton btn;
    private WardsList wards;
    private GroupList groups;
    private ActionListener actionListener;

    public WardsPanel(WardsList wards, GroupList groups, ActionListener actionListener) {
        this.wards = wards;
        this.groups = groups;
        this.actionListener = actionListener;
        setup();
    }

    public void updatePanel() {
        if (w_comboBox.getSelectedIndex() >= 0) {
            d_textField.setText(wards.getWard(w_comboBox.getSelectedIndex()).getDes());
            p_textField.setText(wards.getWard(w_comboBox.getSelectedIndex()).getPrc());
            g_comboBox.setSelectedIndex(wards.getWard(w_comboBox.getSelectedIndex()).getGrp() - 1);
        }
    }

    public void reloadGroups() {
        int ix = g_comboBox.getSelectedIndex();

        g_comboBox.removeAllItems();
        for (int i = 0; i < GroupList.LENGTH; ++i) {
            g_comboBox.addItem(groups.getGroup(i).getName());
        }
        g_comboBox.setSelectedIndex(ix);
    }

    public void saveWard() {
        int si = w_comboBox.getSelectedIndex();

        if (si >= 0) {
            wards.setWard(si, new WardsList.Ward(wards.getWard(si).getNum(), d_textField.getText(), p_textField.getText(), g_comboBox.getSelectedIndex() + 1));
            w_comboBox.setSelectedIndex(si + 1);
        }
    }

    private void setup() {
        int i;
        GridBagConstraints gbc = new GridBagConstraints();
        Insets insets = new Insets(0, 5, 0, 0);

        padding = new PaddingPanel(20);
        padding.setLayout(new GridBagLayout());

        labels = new JLabel[4];
        for (i = 0; i < labels.length; ++i) {
            labels[i] = new JLabel(LABELS_TEXT[i]);
        }

        labels[0].setLabelFor(w_comboBox);
        labels[1].setLabelFor(d_textField);
        labels[2].setLabelFor(p_textField);
        labels[3].setLabelFor(g_comboBox);

        gbc.gridy = 0;
        for (i = 0; i < labels.length; ++i) {
            gbc.gridx = i + i;
            gbc.insets = insets;
            padding.add(labels[i], gbc);
        }


        w_comboBox = new JComboBox<>();
        w_comboBox.setName("wp_cbox");
        w_comboBox.setPreferredSize(SMALL_INPUT_COMPONENT_SIZE);
        w_comboBox.addActionListener(actionListener);

        for (i = 0; i < WardsList.LENGHT; ++i) {
            w_comboBox.addItem(wards.getWard(i).getNum());
        }
        w_comboBox.setSelectedIndex(0);

        insets.right = 10;
        gbc.gridx = 1;
        gbc.insets = insets;
        padding.add(w_comboBox, gbc);

        d_textField = new FocusTextField(true);
        d_textField.setPreferredSize(LARGE_INPUT_COMPONENT_SIZE);
        d_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (d_textField.getText().length() >= 16 ) {
                    e.consume(); // if text length is 16, you can't type anything
                }
                
                if (e.getKeyChar() == '\'') {
                    FocusTextField tf = (FocusTextField) e.getSource();
                    tf.setText(tf.getText() + "'");
                }
            }

        });
        d_textField.setText(wards.getWard(0).getDes());

        gbc.gridx = 3;
        padding.add(d_textField, gbc);

        p_textField = new FocusTextField();
        p_textField.setPreferredSize(SMALL_INPUT_COMPONENT_SIZE);
        p_textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    switch (e.getKeyChar()) {
                        case '.':
                        case ',':
                            if (p_textField.getText().indexOf('.') != -1) {
                                e.consume();
                            } else {
                                e.setKeyChar('.');
                            }
                            break;

                        default:
                            e.consume();
                            break;
                    }
                }
            }
        });
        p_textField.setText(wards.getWard(0).getPrc());

        gbc.gridx = 5;
        padding.add(p_textField, gbc);

        g_comboBox = new JComboBox<>();
        g_comboBox.setPreferredSize(LARGE_INPUT_COMPONENT_SIZE);
        g_comboBox.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() - '0') <= GroupList.LENGTH) {
                    g_comboBox.setSelectedIndex(e.getKeyChar() - '1');
                }
            }
        });

        for (i = 0; i < GroupList.LENGTH; ++i) {
            g_comboBox.addItem(groups.getGroup(i).getName());
        }
        g_comboBox.setSelectedIndex(wards.getWard(0).getGrp() - 1);

        gbc.gridx = 7;
        padding.add(g_comboBox, gbc);

        btn = new JButton("Salva");
        btn.setName("wp_btn");
        btn.addActionListener(actionListener);
        btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btn.doClick();
                }
            }
        });

        insets.right = 0;
        insets.top = 15;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 8;
        padding.add(btn, gbc);

        add(padding);
        setBorder(BorderFactory.createTitledBorder("Reparti"));
    }

}
