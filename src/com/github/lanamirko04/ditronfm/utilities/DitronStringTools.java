package com.github.lanamirko04.ditronfm.utilities;

public class DitronStringTools {

    public static boolean isBold (String s) {
       return (s.chars().filter(ch -> ch == '~').count() == s.chars().filter(ch -> ch != '~').count());
    }

    public static String toBold(String s) {
        StringBuilder bs = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            bs.append("~").append(s.charAt(i));
        }

        return bs.toString();
    }

    public static String removeTilde(String s) {
        StringBuilder ns = new StringBuilder();

        if (!isBold(s)) {
            return s;
        }

        for (int i = 1; i < s.length(); i += 2) {
            ns.append(Character.toString(s.charAt(i)));
        }

        return ns.toString();
    }

}
