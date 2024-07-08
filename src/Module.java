public class Module{
    private double moduleMark;
    private String moduleGrade;

    public Module(double moduleMark){
        this.moduleMark = moduleMark;
    }

    public double getModuleMark() {
        return moduleMark;
    }

    public void setModuleMark(double moduleMark) {
        if (moduleMark >= 0 && moduleMark <= 100) {
            this.moduleMark = moduleMark;
        }
        else {
            throw new IllegalArgumentException("Enter a valid mark");
        }
    }


    public void moduleGrade(){
        if (moduleMark >= 80) {
            moduleGrade = "Distinction";
        } else if (moduleMark >= 70) {
            moduleGrade =  "Merit";
        } else if (moduleMark >= 40) {
            moduleGrade =  "Pass";
        } else {
            moduleGrade =  "Fail";
        }
    }


    public String getModuleGrade(){
        moduleGrade();
        return moduleGrade;
    }
}
