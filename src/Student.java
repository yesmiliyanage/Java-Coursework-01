public class Student{
    private String studentId;
    private String studentName;
    private Module module1;
    private Module module2;
    private Module module3;

    public Student(){
        module1 = new Module(null);
        module2 = new Module(null);
        module3 = new Module(null);
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

    public Integer getModule1() {
        return module1.getModuleMark();
    }

    public void setModule1(Integer moduleMark1) {
        this.module1.setModuleMark(moduleMark1);
    }

    public Integer getModule2() {
        return module2.getModuleMark();
    }

    public void setModule2(Integer moduleMark2) {
        this.module2.setModuleMark(moduleMark2);
    }

    public Integer getModule3() {
        return module3.getModuleMark();
    }

    public void setModule3(Integer moduleMark3) {
        this.module3.setModuleMark(moduleMark3);
    }

    public Integer getTotalMark(){
        if(getModule1() != null && getModule2() != null && getModule3()!= null ){
            return getModule1() + getModule2() + getModule3();
        }
        else{
            return null;
        }
    }

    public Double getAverage(){
        if(getTotalMark() != null) {
            return (getModule1() + getModule2() + getModule3()) / 3.0;
        }
        else{
            return null;
        }
    }

    public String finalGrade() {
        Double average = getAverage();
        if (average != null) {
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
            return null;
        }
    }

    public String fullDetailsForStudent() {
        if (getTotalMark() == null) {
            return this.studentName + "," + this.studentId;
        } else {
            return this.studentName + "," + this.studentId + "," + getModule1() + "," + getModule2() + "," + getModule3();
        }
    }

    public void fullReport(Student student) {
        if (!student.studentName.equals("vacant") && !student.studentId.equals("vacant") && getTotalMark() != null) {
            System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s %-20s%n", student.studentName, student.studentId, module1.getModuleMark(), module2.getModuleMark(), module3.getModuleMark(), getTotalMark(), getAverage(), finalGrade());
        }
    }

}


