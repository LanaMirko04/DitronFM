package com.github.lanamirko04.ditronfm.utilities;

import javax.sound.sampled.Line;

public class ReceiptHeader {

    public static final int LINES = 7;

    public static class HeaderLine {

        private final int MAX_LENGTH = 30;

        private int num;
        private String txt;
        private boolean bold;

        public HeaderLine(int num, String txt) {
            this.num = num;
            this.txt = txt;
            bold = false;
        }

        public HeaderLine(int num, String txt, boolean bold) {
            this.num = num;
            this.txt = txt;
            this.bold = bold;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public boolean isBold() {
            return bold;
        }

        public void setBold(boolean bold) {
            this.bold = bold;
        }

        private String centeredText(String txt) {
            return " ".repeat(Math.max(0, (MAX_LENGTH - txt.length()) / 2)) + txt;
        }

        @Override
        public String toString() {
            if (bold)
                return "L" + num + "='" + centeredText(DitronStringTools.toBold(txt)) + "'";
            return "L" + num + "='" + centeredText(txt) + "'";
        }
    }

    private HeaderLine[] lines;

    public ReceiptHeader() {
        lines = new HeaderLine[LINES];
        for (int i = 0; i < LINES; )
            lines[i] = new HeaderLine(++i, "");
    }

    public ReceiptHeader(HeaderLine[] lines) {
        this.lines = lines;
    }

    public HeaderLine getLine(int i) {
        return lines[i];
    }

    public void setLine(int i, HeaderLine line) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("PROG NOPRINT\r\nPINT ");
        for (HeaderLine hl : lines)
            s.append(hl).append((hl.num == 7) ? "\r\n" : ", ");

        return (s + "FINEPROG\r");
    }
}
