package com.github.lanamirko04.ditronfm.ui.custom_componets.panels;

import javax.swing.*;

public class PaddingPanel extends JPanel {

    public PaddingPanel(int a) {
        setBorder(BorderFactory.createEmptyBorder(a, a, a, a));
    }

    public PaddingPanel(int tb, int lr) {
        setBorder(BorderFactory.createEmptyBorder(tb, lr, tb, lr));
    }

    public PaddingPanel(int t, int l, int b, int r) {
        setBorder(BorderFactory.createEmptyBorder(t, l, b, r));
    }

}
