package com.github.lanamirko04.ditronfm.ui;

import com.github.lanamirko04.ditronfm.utilities.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AppMenu extends JMenuBar implements ActionListener {

    private final String[] fileItemsTxt = { "Importa", "Esporta", "Esci" };
    private final String[] fileItemsNames = { "fmi_import", "fmi_export", "fmi_quit" };

    private JMenu file;
    private JMenu help;
    private JMenuItem[] fileItems;
    private JMenuItem helpItem;
    private AboutDialog about;
    private JFrame parent;
    private WardsList wards;
    private GroupList groups;
    private ReceiptHeader header;

    public AppMenu(JFrame parent, WardsList wards, GroupList groups, ReceiptHeader header) {
        this.parent = parent;
        this.wards = wards;
        this.groups = groups;
        this.header = header;
        setup();
    }

    private void setup() {
        int i;
        about = new AboutDialog(parent);
        file = new JMenu("File");
        help = new JMenu("Aiuto");
        fileItems = new JMenuItem[fileItemsNames.length];
        helpItem = new JMenuItem("About");

        for (i = 0; i < fileItems.length; ++i) {
            fileItems[i] = new JMenuItem(fileItemsTxt[i]);
            fileItems[i].setName(fileItemsNames[i]);
            fileItems[i].addActionListener(this);

            if (i == (fileItems.length - 1)) {
                file.addSeparator();
            }

            file.add(fileItems[i]);
        }

        helpItem.addActionListener(this);
        helpItem.setName("hmi_about");
        help.add(helpItem);

        this.add(file);
        this.add(help);
    }

    private void importFile() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = fc.showOpenDialog(parent);

        if (r == JFileChooser.APPROVE_OPTION) {
            int i;
            DitronFileParser fp = new DitronFileParser(fc.getSelectedFile());

            for (i = 0; i < WardsList.LENGHT; ++i) {
                wards.setWard(i, fp.getWards().getWard(i));
            }

            for (i = 0; i < GroupList.LENGTH; ++i) {
                groups.setGroup(i, fp.getGroups().getGroup(i));
            }

            for (i = 0; i < ReceiptHeader.LINES; ++i) {
                header.setLine(i, fp.getHeader().getLine(i));
            }

            ((AppFrame) parent).updateAll();
            parent.setTitle(fc.getSelectedFile().getName());
        }
    }

    private void exportFile() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = fc.showSaveDialog(parent);

        if (r == JFileChooser.APPROVE_OPTION) {
            if (fc.getSelectedFile().getName().endsWith(".txt") || fc.getSelectedFile().getName().endsWith(".TXT")) {
                DitronFileMaker.make(fc.getSelectedFile(), wards, groups, header);
                parent.setTitle(fc.getSelectedFile().getName());
            } else {
                File file = new File(fc.getSelectedFile().getAbsoluteFile() + ".txt");
                DitronFileMaker.make(file, wards, groups, header);
                parent.setTitle(fc.getSelectedFile().getName() + ".txt");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            switch (((JMenuItem) e.getSource()).getName()) {
                case "fmi_export":
                    exportFile();
                    break;

                case "fmi_import":
                    importFile();
                    break;

                case "fmi_quit":
                    parent.dispose();
                    break;

                case "hmi_about":
                    about.setVisible(true);
                    break;
            }
        }
    }
}
