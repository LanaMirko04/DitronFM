package com.github.lanamirko04.ditronfm.utilities;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DitronFileParser {

    private final String FINEPROG = "FINEPROG";

    private File file;
    private WardsList wards;
    private GroupList groups;
    private ReceiptHeader header;

    public DitronFileParser(File file) {
        wards = new WardsList();
        groups = new GroupList();
        header = new ReceiptHeader();
        setFile(file);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        parse();
    }

    public WardsList getWards() {
        return wards;
    }

    public void setWards(WardsList wards) {
        this.wards = wards;
    }

    public GroupList getGroups() {
        return groups;
    }

    public void setGroups(GroupList groups) {
        this.groups = groups;
    }

    public ReceiptHeader getHeader() {
        return header;
    }

    public void setHeader(ReceiptHeader header) {
        this.header = header;
    }

    private void parse() {
        try {
            int i = 0;
            String str, tmp;
            BufferedReader bf = new BufferedReader(new FileReader(file));


            // Wards
            while (!Objects.equals(str = bf.readLine(), FINEPROG)) {
                StringTokenizer st = new StringTokenizer(str, ",");

                if ( !Objects.equals(str, "PROG NOPRINT") ) {
                    while (st.hasMoreElements()) {
                        StringTokenizer stmp = new StringTokenizer((String) st.nextElement(), "=");

                        switch ((String) stmp.nextElement()) {
                            case " DES":
                                tmp = (String) stmp.nextElement();
                                wards.getWard(i).setDes(tmp.substring(1, tmp.length() - 1));
                                break;

                            case " PRE":
                                wards.getWard(i).setPrc((String) stmp.nextElement());
                                break;

                            case " GRU":
                                wards.getWard(i).setGrp(Integer.parseInt((String) stmp.nextElement()));
                                break;

                            default:
                                break;
                        }
                    }

                    wards.getWard(i).setNum(++i);
                }
            }

            // Header
            i = 0;
            while (!Objects.equals(str = bf.readLine(), FINEPROG)) {
                if (!Objects.equals(str.substring(0, 4), FINEPROG)) {
                    StringTokenizer st = new StringTokenizer(str, "=");
                    while (st.hasMoreElements()) {
                        tmp = (String) st.nextElement();
                        if (tmp.startsWith("'")) {
                            tmp = tmp.substring(1, tmp.length() - 5);
                            header.getLine(i).setBold(DitronStringTools.isBold(tmp));
                            header.getLine(i).setTxt(DitronStringTools.removeTilde(tmp));
                            header.getLine(i).setNum(++i);
                        }
                    }
                }
            }

            // Useless reading loop
            while (!Objects.equals(str = bf.readLine(), FINEPROG));

            // Groups
            i = 0;
            while (!Objects.equals(str = bf.readLine(), FINEPROG)) {
                StringTokenizer st = new StringTokenizer(str, "=");

                while (st.hasMoreElements()) {
                    if (Objects.equals(((String) st.nextElement()).substring(3, 6), "DES")) {
                        tmp = ((String) st.nextElement());

                        groups.getGroup(i).setName(tmp.substring(1, tmp.length() - 1));
                        groups.getGroup(i).setNum(++i);
                    }
                }
            }

            bf.close();
        } catch (IOException | NoSuchElementException e) {
            System.err.println("An error has occurred... ( T^T)");
            e.printStackTrace();
        }
    }
}
