public class Student{
    private String studentId;
    private String studentName;
    Module module;

    public Student(String id, String name, Module module){
        this.studentName = name;
        this.studentId = id;
        this.module = module;
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


    public void setModule(Module module){
        this.module = module;
    }

    public Module getModule(){
        return module;
    }

    public Integer getMark1(){
        if(this.module != null) {
            return this.module.getMark1();
        }
        else{
            return null;
        }
    }

    public Integer getMark2(){
        return this.module.getMark2();
    }

    public Integer getMark3(){
        return this.module.getMark3();
    }

    public String fullDetailsForStudent() {
        if (getMark1() == null || getMark2() == null || getMark3() == null) {
            return this.studentName + "," + this.studentId;
        }
        else{
            return this.studentName + "," + this.studentId + "," + getMark1() + "," + getMark2() + "," + getMark3();
        }
    }

    public void fullReport(Student student) {
        if (!student.studentName.equals("vacant") && !student.studentId.equals("vacant") && student.getMark1() != null && student.getMark2() != null && getMark3() != null) {
            System.out.printf("%-20s %-20s %-10s %-10s %-10s %-10s %-20s %-20s%n", student.studentName, student.studentId, student.getMark1(), student.getMark2(), student.getMark3(), student.module.getTotalMark(), student.module.getAverage(), student.module.finalGrade());
        }
    }
}
