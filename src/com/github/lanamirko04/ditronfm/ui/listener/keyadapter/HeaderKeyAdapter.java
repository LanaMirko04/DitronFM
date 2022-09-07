package com.github.lanamirko04.ditronfm.ui.listener.keyadapter;

import com.github.lanamirko04.ditronfm.ui.custom_componets.textfields.FocusTextField;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HeaderKeyAdapter extends KeyAdapter {

    private FocusTextField textField;
    private JCheckBox checkBox;

    public HeaderKeyAdapter(FocusTextField textField, JCheckBox checkBox) {
        this.textField = textField;
        this.checkBox = checkBox;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int maxLength;
        if (textField.getText().length() >= (maxLength = (checkBox.isSelected() ? 15 : 30 ))) {
            e.consume();
        }
    }
}
