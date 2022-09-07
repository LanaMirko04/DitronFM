package com.github.lanamirko04.ditronfm.utilities;

public class GroupList {

    public static int LENGTH = 5;

    public static class Group {

        private int num;
        private String name;

        public Group(int num, String name) {
            this.num = num;
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
           return "PGRU NR=" + num + ", DES='"+ name +"'\r\n";
        }

    }

    private Group[] groups;

    public GroupList() {
        groups = new Group[LENGTH];
        for (int i = 0; i < LENGTH; )
            groups[i] = new Group(++i, "GRUPPO " + i);
    }

    public GroupList(String[] names) {
        groups = new Group[LENGTH];
        for (int i = 0; i < LENGTH; ++i)
            groups[i] = new Group(i+1, names[i]);
    }

    public GroupList(Group[] groups) {
        this.groups = groups;
    }

    public Group getGroup(int i) {
        return groups[i];
    }

    public void setGroup(int i, Group g) {
        groups[i] = g;
    }

    @Override
    public String toString() {
       String s = "PROG NOPRINT\r\n";
       for (Group g : groups)
           s += g;

       return (s + "FINEPROG\r");
    }
}