package com.github.lanamirko04.ditronfm.ui.custom_componets.textfields;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusTextField extends JTextField {

    private boolean upper;
    private String tmp;

    public FocusTextField() {
        upper = false;
    }

    public FocusTextField(boolean upper) {
        this.upper = upper;
    }

    public boolean isUpper() {
        return upper;
    }

    public void setUpper(boolean upper) {
        this.upper = upper;
    }

    {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tmp = getText();
                selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                select(0, 0);

                if (getText().length() == 0)
                    setText(tmp);

                if (upper)
                    setText(getText().toUpperCase());
            }
        });
    }
}
