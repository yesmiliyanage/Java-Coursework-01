import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student{
    private String studentId;
    private String studentName;
    private final Module module1;
    private final Module module2;
    private final Module module3;

    public Student(){
        module1 = new Module(-1);
        module2 = new Module(-1);
        module3 = new Module(-1);
        this.studentName = "vacant";
        this.studentId = "vacant";
    }

    public String getStudentId(){
        return this.studentId;

    }
    public void setStudentId(String id) {
        if(id.equals("vacant")){
            studentId = id;
        }
        else{
            Pattern pattern = Pattern.compile("^w\\d{7}$");
            Matcher matcher = pattern.matcher(id);
            if (matcher.find()) {
                this.studentId = id;
            }
            else{
                throw new IllegalArgumentException("Incorrect Format. Please enter the Student ID again (Ex: w4513490)");
            }
        }
    }

    public void setStudentName(String name){
        studentName = name;
    }

    public String getStudentName(){
        return this.studentName;
    }

    public double getModule1() {
        return module1.getModuleMark();
    }

    public void setModule1(double moduleMark1) {
        this.module1.setModuleMark(moduleMark1);
    }

    public double getModule2() {
        return module2.getModuleMark();
    }

    public void setModule2(double moduleMark2) {
        this.module2.setModuleMark(moduleMark2);
    }

    public double getModule3() {
        return module3.getModuleMark();
    }

    public void setModule3(double moduleMark3) {
        this.module3.setModuleMark(moduleMark3);
    }

    public double getTotalMark(){
        if(getModule1() != -1 && getModule2() != -1 && getModule3()!= -1 ){
            return getModule1() + getModule2() + getModule3();
        }
        else{
            return -1;
        }
    }

    public double getAverage(){
        if(getTotalMark() != -1) {
            return (getModule1() + getModule2() + getModule3()) / 3.0;
        }
        else{
            return -1;
        }
    }

    public String finalGrade() {
        double average = getAverage();
        if (average != -1.0) {
            if (average >= 80) {
                return "Distinction";
            } else if (average >= 70) {
                return "Merit";
            } else if (average >= 40) {
                return "Pass";
            } else {
                return "Fail";
            }
        }
        else{
            return "Issue with the average";
        }
    }

    public String fullDetailsForStudent() {
        if (getTotalMark() == -1) {
            return this.studentName + "," + this.studentId;
        } else {
            return this.studentName + "," + this.studentId + "," + getModule1() + "," + getModule2() + "," + getModule3();
        }
    }

    public String markAndGrade(Module module){
        return module.getModuleMark()+"("+module.getModuleGrade()+")";
    }

    public void fullReport() {
        if (!studentName.equals("vacant") && !studentId.equals("vacant") && getTotalMark() != -1) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-10s %-20s %-20s%n", studentName, studentId, markAndGrade(module1), markAndGrade(module2), markAndGrade(module3), getTotalMark(), getAverage(), finalGrade());
        }
    }

}


