package GPACalaulator;

public class Record {
    private String term, subject, course, grade;
    private int credit;
    private double gpa;
    
    public Record(String term, String subject, String course, String credit, String grade) {
        this.term = term;
        this.subject = subject;
        this.course = course;
        this.credit = Integer.parseInt(credit);
        this.grade = grade;
        
        switch(grade) {
            case "A": 
                gpa = 4.0;
                break;
            case "A-":
                gpa = 3.7;
                break;
            case "B+":
                gpa = 3.3;
                break;
            case "B":
                gpa = 3.0;
                break;
            case "B-":
                gpa = 2.7;
                break;
            case "C+":
                gpa = 2.3;
                break;
            case "C":
                gpa = 2.0;
                break;
            case "C-":
                gpa = 1.7;
                break;
            case "D+":
                gpa = 1.3;
                break;
            case "D":
                gpa = 1.0;
                break;
            default:
                gpa = 0;
                break;
        }
    }
    
    public String term() {
        return term;
    }
    
    public String subject() {
        return subject;
    }
    
    public String course() {
        return course;
    }
    
    public int credit() {
        return credit;
    }
    
    public String grade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
        switch(grade) {
            case "A": 
                gpa = 4.0;
                break;
            case "A-":
                gpa = 3.7;
                break;
            case "B+":
                gpa = 3.3;
                break;
            case "B":
                gpa = 3.0;
                break;
            case "B-":
                gpa = 2.7;
                break;
            case "C+":
                gpa = 2.3;
                break;
            case "C":
                gpa = 2.0;
                break;
            case "C-":
                gpa = 1.7;
                break;
            case "D+":
                gpa = 1.3;
                break;
            case "D":
                gpa = 1.0;
                break;
            default:
                gpa = 0;
                break;
        }
    }
    
    public double gpa() {
        return gpa;
    }
    
    @Override
    public String toString() {
        return term + "\t" + subject + "\t" + course + "\t" + credit + "\t" + grade + "\t" + gpa;
    }
    
}
