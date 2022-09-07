package com.github.lanamirko04.ditronfm.ui;

import com.github.lanamirko04.ditronfm.ui.custom_componets.panels.GroupsPanel;
import com.github.lanamirko04.ditronfm.ui.custom_componets.panels.HeaderPanel;
import com.github.lanamirko04.ditronfm.ui.custom_componets.panels.PaddingPanel;
import com.github.lanamirko04.ditronfm.ui.custom_componets.panels.WardsPanel;
import com.github.lanamirko04.ditronfm.utilities.GroupList;
import com.github.lanamirko04.ditronfm.utilities.ReceiptHeader;
import com.github.lanamirko04.ditronfm.utilities.WardsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppFrame extends JFrame implements ActionListener {

    private final Dimension APP_MIN_SIZE = new Dimension(840, 785);

    /* UI Elements */
    private PaddingPanel padding;
    private WardsPanel wpanel;
    private HeaderPanel hpanel;
    private GroupsPanel gpanel;

    private WardsList wards;
    private GroupList groups;
    private ReceiptHeader header;

    public AppFrame() {
        setup();
    }

    public void updateAll() {
        wpanel.updatePanel();
        gpanel.updateTextFields();
        hpanel.updateTextFields();
    }

    private void setup() {
        wards = new WardsList();
        groups = new GroupList();
        header = new ReceiptHeader();
        padding = new PaddingPanel(20);
        wpanel = new WardsPanel(wards, groups, this);
        hpanel = new HeaderPanel(header, this);
        gpanel = new GroupsPanel(groups, this);
        // TODO: wards list custom component
        GridBagConstraints gbc = new GridBagConstraints();

        padding.setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        padding.add(wpanel, gbc);

        PaddingPanel tmpPanel = new PaddingPanel(0, 150);
        tmpPanel.add(new JLabel("Coming soon"));

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        padding.add(tmpPanel, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;
        padding.add(gpanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        padding.add(hpanel, gbc);

        setTitle("NUOVA_CONFIGURAZIONE.TXT");
        add(padding);
        setJMenuBar(new AppMenu(this, wards, groups, header));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(APP_MIN_SIZE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JComponent) {
            JComponent source = (JComponent) e.getSource();

            switch (source.getName()) {
                case "wp_btn":
                    wpanel.saveWard();
                    break;

                case "hp_btn":
                    hpanel.saveHeader();
                    break;

                case "gp_btn":
                    gpanel.saveGroups();
                    wpanel.reloadGroups();
                    break;

                case "wp_cbox":
                    if (wpanel != null) {
                        wpanel.updatePanel();
                    }
                    break;

                default:
                    break;
            }
        }
    }
}