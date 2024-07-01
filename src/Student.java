public class Student extends Module{
    private String studentId;
    private String studentName;
    Module module;


    public Student(){
        super();
        this.studentName = "vacant";
        this.studentId = "vacant";
    }

    public String getStudentId(){
        return this.studentId;
    }
    public void setStudentId(String id){
        studentId = id;
    }

    public void setStudentName(String name){
        studentName = name;
    }

    public String getStudentName(){
        return this.studentName;
    }


    public String fullDetailsForStudent() {
        if (getTotalMark() != null) {
            return this.studentName + "," + this.studentId;
        }
        else{
            return this.studentName + "," + this.studentId + "," + getMark1() + "," + getMark2() + "," + getMark3();
        }
    }

    public void fullReport(Student student) {
        if (!student.studentName.equals("vacant") && !student.studentId.equals("vacant") && getTotalMark() != null) {
            System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s %-20s%n", student.studentName, student.studentId, getMark1(), getMark2(), getMark3(), getTotalMark(), getAverage(), finalGrade());
        }
    }
}
