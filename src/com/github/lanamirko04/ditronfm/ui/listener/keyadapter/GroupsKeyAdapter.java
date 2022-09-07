package com.github.lanamirko04.ditronfm.ui.listener.keyadapter;

import com.github.lanamirko04.ditronfm.ui.custom_componets.textfields.FocusTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GroupsKeyAdapter extends KeyAdapter {

    private FocusTextField tf;

    public GroupsKeyAdapter(FocusTextField tf) {
        this.tf = tf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       if (tf.getText().length() >= 10) {
           e.consume();
       }
    }
}
