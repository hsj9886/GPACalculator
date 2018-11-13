package GPACalaulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Command {
    private ArrayList<Record> list;

    public Command(ArrayList<Record> list) {
        this.list = list;

        String input = input();
        while (!input.toLowerCase().equals("exit")) {
            execute(input);
            input = input();
        }
    }

    private String input() {
        System.out.print("GPA Calculator> ");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }


    private void execute(String input) {
        String[] command = input.split(" ");

        if (command[0].equals("add")) {
            if (command.length != 1) {
                String[] r = input.substring(4, input.length()).split(Calculator.SEP);
                r = trim(r);
                if (r.length == 5) {
                    Record record = new Record(r[0].toUpperCase(), r[1].toUpperCase(), r[2], r[3], r[4].toUpperCase());
                    list.add(findterm(r[0].toUpperCase()), record);
                    System.out.println("Course added: " + record.toString());
                } else {
                    System.out.println("add: usage: add [term], [subject], [course], [credit], [grade]\n" + 
                        "Try 'help' for more information");
                }
            } else {
                System.out.println("add: usage: add [term], [subject], [course], [credit], [grade]\n" + 
                    "Try 'help' for more information");
            }
        } else if (command[0].equals("update")) {
            if (command.length != 1) {
                String[] r = input.substring(7, input.length()).split(Calculator.SEP);
                r = trim(r);
                r[0] = r[0].toUpperCase();
                if (r.length == 3) {
                    boolean exist = false;
                    for (int i = 0; i < list.size(); i++) {
                        Record record = list.get(i);
                        if (record.subject().equals(r[0]) && record.course().equals(r[1])) {
                            record.setGrade(r[2].toUpperCase());
                            System.out.println("Course updated: " + record.toString());
                            exist = true;
                            break;
                        }
                    }

                    if (!exist) {
                        System.out.println("Cannot update, course |" + r[0] + " " + r[1] + "| not found in the database.");
                    }
                } else {
                    System.out.println("update: usage: update [subject], [course], [grade]\n" + 
                        "Try 'help' for more information");
                }
            } else {
                System.out.println("update: usage: update [subject], [course], [grade]\n" + 
                    "Try 'help' for more information");
            }

        } else if (command[0].equals("delete")) {
            String[] r = input.substring(7, input.length()).split(Calculator.SEP);
            r = trim(r);
            r[0] = r[0].toUpperCase();
            if (r.length == 2) {
                boolean exist = false;
                for (int i = 0; i < list.size(); i++) {
                    Record record = list.get(i);
                    if (record.subject().equals(r[0]) && record.course().equals(r[1])) {
                        list.remove(i);
                        System.out.println("Course deleted: " + record.toString());
                        exist = true;
                        break;
                    }
                }

                if (!exist) {
                    System.out.println("Cannot delete, course |" + r[0] + " " + r[1] + "| not found in the database.");
                }
            } else {
                System.out.println("delete: usage: delete [subject], [course]\n" + 
                    "Try 'help' for more information");
            }
        } else if (command[0].equals("list")) {
            if (command.length == 1) {
                double[] total = GPACalc(list);

                System.out.println("Term\t\tSubject\tCourse\tCredit\tGrade\tGPA");
                System.out.println("-----------------------------------------------------");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i).toString());
                }
                System.out.println("-----------------------------------------------------");
                System.out.printf("\t\t\t\t%.0f\t\t%.2f\n", total[0], total[3]);

            } else if (command[1].equals("subject")) {
                if (command.length == 3) {
                    command[2] = command[2].toUpperCase();
                    ArrayList<Record> newlist = new ArrayList<Record>();
                    boolean exist = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (command[2].equals(list.get(i).subject())) {
                            newlist.add(list.get(i));
                            exist = true;
                        }
                    }

                    if (exist) {
                        double[] total = GPACalc(newlist);
                        System.out.println("Term\t\tSubject\tCourse\tCredit\tGrade\tGPA");
                        System.out.println("-----------------------------------------------------");
                        for (int i = 0; i < newlist.size(); i++) {
                            System.out.println(newlist.get(i).toString());
                        }
                        System.out.println("-----------------------------------------------------");
                        System.out.printf("\t\t\t\t%.0f\t\t%.2f\n", total[0], total[3]);
                    } else {
                        System.out.println("Cannot list, subject |" + command[2] + "| not found in the database.");
                    }
                } else {
                    System.out.println("list: usage: list subject [subject]\n" + 
                        "Try 'help' for more information");
                }
                

            } else if (command[1].equals("term")) {
                if (command.length > 2) {
                    String term = input.substring(10, input.length()).toUpperCase();
                    ArrayList<Record> newlist = new ArrayList<Record>();
                    boolean exist = false;
                    for (int i = 0; i < list.size(); i++) {
                        if (term.equals(list.get(i).term())) {
                            newlist.add(list.get(i));
                            exist = true;
                        }
                    }

                    if (exist) {
                        double[] total = GPACalc(newlist);
                        System.out.println("Term\t\tSubject\tCourse\tCredit\tGrade\tGPA");
                        System.out.println("-----------------------------------------------------");
                        for (int i = 0; i < newlist.size(); i++) {
                            System.out.println(newlist.get(i).toString());
                        }
                        System.out.println("-----------------------------------------------------");
                        System.out.printf("\t\t\t\t%.0f\t\t%.2f\n", total[0], total[3]);
                    } else {
                        System.out.println("Cannot list, term |" + term + "| not found in the database.");
                    }
                } else {
                    System.out.println("list: usage: list term [term]\n" + 
                        "Try 'help' for more information");
                }
            } else {
                System.out.println("list: usage: list [option] [value]\n" + 
                    "Try 'help' for more information");
            }
        }  else if (command[0].equals("help")) {
            System.out.println("" +
                "add [term], [subject], [course], [credit], [grade]\n\n" + 
                "update [subject], [course], [grade]\n\n" + 
                "delete [subject], [course]\n\n" + 
                "list\n" + 
                "list subject [subject]\n" + 
                "list term [term]\n\n" +
                "exit\n");
        } else {
            System.out.println(command[0] + ": command not found...\n"
                + "Try 'help' to see available commands");
        }
    }

    private String[] trim(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }

    private int findterm(String term) {
        int pos = list.size() - 1;
        for (int i = 0; i < list.size(); i++) {
            if (term.equals(list.get(i).term())) {
                pos = i;
            }
        }

        return pos + 1;
    }

    public double[] GPACalc(ArrayList<Record> list) {
        double[] total = new double[4];
        total[0] = 0; // total actual credit
        total[1] = 0; // total credit
        total[2] = 0; // total points
        for (int i = 0; i < list.size(); i++) {
            Record record = list.get(i);
            if (record.gpa() > 0) {
                total[1] += record.credit();
                total[2] += record.credit() * record.gpa();
            }
            total[0] += record.credit();
        }
        if (total[1] != 0) {
            total[3] = total[2] / total[1]; // average GPA
        }
        return total;
    }

}
