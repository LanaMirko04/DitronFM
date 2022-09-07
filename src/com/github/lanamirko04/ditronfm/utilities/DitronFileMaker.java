package com.github.lanamirko04.ditronfm.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DitronFileMaker {

    private static final String OPTION_FILE_SECTION = "PROG NOPRINT\r\n" +
            "POPZ NCASSA='001', ARROT=1, VARPRE=SI, CICAL=SI, OBLOPER=NO, AZZPROG=NO, CARTA=2, QTY1=NO\r\n" +
            "POPZ OBLSUB=NO, NUMART=NO, TIPOCHIU=4, TALLON=SI, SHIFT=NO, AVV24=NO, ORA24=0000\r\n" +
            "POPZ PREZZI0=SI, ORAIDLE=NO, CASSAMAX=0, IVASCO=NO, CNTRESTO=SI, DOPPIOPER=SI\r\n" +
            "POPZ MSGROT=NO\r\n" +
            "FINEPROG\r\n";
    private static final String FUNCTIONS_FILE_SECTION = "PROG NOPRINT\r\n" +
            "PSTEN NR=1, TIPO=1, DES='SUBTENDER 01            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=2, TIPO=1, DES='SUBTENDER 02            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=3, TIPO=1, DES='SUBTENDER 03            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=4, TIPO=1, DES='SUBTENDER 04            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=5, TIPO=1, DES='SUBTENDER 05            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=6, TIPO=1, DES='SUBTENDER 06            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=7, TIPO=1, DES='SUBTENDER 07            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=8, TIPO=1, DES='SUBTENDER 08            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=9, TIPO=1, DES='SUBTENDER 09            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=10, TIPO=1, DES='SUBTENDER 10            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=11, TIPO=1, DES='SUBTENDER 11            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=12, TIPO=1, DES='SUBTENDER 12            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=13, TIPO=1, DES='SUBTENDER 13            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=14, TIPO=1, DES='SUBTENDER 14            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=15, TIPO=1, DES='SUBTENDER 15            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=16, TIPO=1, DES='SUBTENDER 16            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=17, TIPO=1, DES='SUBTENDER 17            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=18, TIPO=1, DES='SUBTENDER 18            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=19, TIPO=1, DES='SUBTENDER 19            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "PSTEN NR=20, TIPO=1, DES='SUBTENDER 20            ', FLAGS=12, PRES=0, LIS=4, CONV=0\r\n" +
            "FINEPROG\r\n" +
            "PROG NOPRINT\r\n" +
            "PTAST POS=1, TASTO=DPT32\r\n" +
            "PTAST POS=2, TASTO=DPT33\r\n" +
            "PTAST POS=3, TASTO=DPT34\r\n" +
            "PTAST POS=4, TASTO=DPT35\r\n" +
            "PTAST POS=5, TASTO=DPT7\r\n" +
            "PTAST POS=6, TASTO=DPT14\r\n" +
            "PTAST POS=7, TASTO=DPT21\r\n" +
            "PTAST POS=8, TASTO=DPT26\r\n" +
            "PTAST POS=9, TASTO=DPT31\r\n" +
            "PTAST POS=10, TASTO=TSFREE\r\n" +
            "PTAST POS=12, TASTO=DPT36\r\n" +
            "PTAST POS=13, TASTO=DPT37\r\n" +
            "PTAST POS=14, TASTO=DPT6\r\n" +
            "PTAST POS=15, TASTO=DPT13\r\n" +
            "PTAST POS=16, TASTO=DPT20\r\n" +
            "PTAST POS=17, TASTO=DPT25\r\n" +
            "PTAST POS=18, TASTO=DPT30\r\n" +
            "PTAST POS=19, TASTO=TSMACRO1, MACRO='NUM6 TSSCEL TSCONF NUM1 TSKEY TSFREE '\r\n" +
            "PTAST POS=20, TASTO=DPT38\r\n" +
            "PTAST POS=21, TASTO=DPT39\r\n" +
            "PTAST POS=22, TASTO=DPT40\r\n" +
            "PTAST POS=23, TASTO=DPT5\r\n" +
            "PTAST POS=24, TASTO=DPT12\r\n" +
            "PTAST POS=25, TASTO=DPT19\r\n" +
            "PTAST POS=26, TASTO=DPT24\r\n" +
            "PTAST POS=27, TASTO=DPT29\r\n" +
            "PTAST POS=28, TASTO=TSSM\r\n" +
            "PTAST POS=32, TASTO=DPT4\r\n" +
            "PTAST POS=33, TASTO=DPT11\r\n" +
            "PTAST POS=34, TASTO=DPT18\r\n" +
            "PTAST POS=35, TASTO=DPT23\r\n" +
            "PTAST POS=36, TASTO=DPT28\r\n" +
            "PTAST POS=37, TASTO=TSX\r\n" +
            "PTAST POS=41, TASTO=DPT3\r\n" +
            "PTAST POS=42, TASTO=DPT10\r\n" +
            "PTAST POS=43, TASTO=DPT17\r\n" +
            "PTAST POS=44, TASTO=DPT22\r\n" +
            "PTAST POS=45, TASTO=DPT27\r\n" +
            "PTAST POS=46, TASTO=TSTVOID\r\n" +
            "PTAST POS=50, TASTO=DPT2\r\n" +
            "PTAST POS=51, TASTO=DPT9\r\n" +
            "PTAST POS=52, TASTO=DPT16\r\n" +
            "PTAST POS=53, TASTO=TSFINE\r\n" +
            "PTAST POS=54, TASTO=TSSTDS\r\n" +
            "PTAST POS=58, TASTO=TSVIRG\r\n" +
            "PTAST POS=59, TASTO=DPT1\r\n" +
            "PTAST POS=60, TASTO=DPT8\r\n" +
            "PTAST POS=61, TASTO=DPT15\r\n" +
            "PTAST POS=62, TASTO=TSFREE\r\n" +
            "PTAST POS=63, TASTO=TSCA\r\n" +
            "FINEPROG\r\n";

    public static void make(File file, WardsList w, GroupList g, ReceiptHeader h) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(w + "" + h + OPTION_FILE_SECTION + g + FUNCTIONS_FILE_SECTION);
            fw.close();
        } catch (IOException ex) {
            System.err.println("FileWriter can not create " + file.getName());
            ex.printStackTrace();
        }
    }

}
