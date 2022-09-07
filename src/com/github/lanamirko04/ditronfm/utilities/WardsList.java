package com.github.lanamirko04.ditronfm.utilities;

public class WardsList {

    public static int LENGHT = 40;

    public static class Ward {

        private int num;
        private String des;
        private String prc;
        private int grp;

        public Ward(int num, String des, String prc, int grp) {
            this.num = num;
            this.des = des;
            this.prc = prc;
            this.grp = grp;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getPrc() {
            return prc;
        }

        public void setPrc(String prc) {
            this.prc = prc;
        }

        public int getGrp() {
            return grp;
        }

        public void setGrp(int grp) {
            this.grp = grp;
        }

        @Override
        public String toString() {
            return "PREP NR=" + num + ", DES='" + des + "', LIS=50.00, BAT=NO, IVA=4, PRE=" + prc + ", GRU=" + grp + ", KPRN=0, FLGDESCR=NO, FLGPUNTI=SI\r\n";
        }
    }

    private Ward[] wards;

    public WardsList() {
        wards = new Ward[LENGHT];
        for (int i = 0; i < LENGHT; )
            wards[i] = new Ward(++i, "REPARTO " + i, "0", 0);
    }

    public WardsList(Ward[] wards) {
        this.wards = wards;
    }

    public Ward getWard(int i) {
        return wards[i];
    }

    public void setWard(int i, Ward w) {
        wards[i] = w;
    }

    @Override
    public String toString() {
        String s = "PROG NOPRINT\r\n";
        for (Ward w : wards)
            s += w;

        return (s + "FINEPROG\r");
    }
}
