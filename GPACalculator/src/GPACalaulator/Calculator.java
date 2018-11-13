package GPACalaulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GPA Calculator
 * 
 * @author Sejung Hwang
 * @version v 0.1.0
 */
public class Calculator {
    public static final String FILE = "GPA.csv";
    public static final String HELP = "help.txt";
    public static final String VERSION = "0.1.0";
    public static final String SEP = ",";
    public static ArrayList<Record> list;

    public static void main(String[] args) throws IOException {
        
        list = new ArrayList<Record>();
        
        try {
            
            Scanner scan = new Scanner(new File(FILE));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line != null) {
                    String[] s = line.split(SEP);
                    Record record = new Record(s[0], s[1], s[2], s[3], s[4]);
                    list.add(record);
                }
            }
            scan.close();
            
        } catch(FileNotFoundException e) {
            File file = new File(FILE);
            file.createNewFile();
        }
        
        System.out.println("GPA Calculator [Version " + VERSION + "]\n"
            + "(c) 2018 Sejung Hwang. All rights reserved.\n");
        
        new Command(list);
        
        try {
            PrintStream ps;
            ps = new PrintStream(new FileOutputStream(FILE));
            for (int i = 0; i < list.size(); i++) {
                Record r = list.get(i);
                ps.println(r.term() + SEP + r.subject() + SEP + r.course() + SEP + r.credit() + SEP + r.grade());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

}
