public class Module{
    private Integer moduleMark1;
    private Integer moduleMark2;
    private Integer moduleMark3;


    public Module(){
        this.moduleMark1 = null;
        this.moduleMark2 = null;
        this.moduleMark3 = null;
    }

    public void setMark1(Integer moduleMark1) {
        if (moduleMark1 >= 0 && moduleMark1 <= 100) {
            this.moduleMark1 = moduleMark1;
        }
        else {
            throw new IllegalArgumentException("Enter a valid mark");
        }
    }

    public Integer getMark1(){
        return moduleMark1;
    }

    public void setMark2(Integer moduleMark2) {
        if (moduleMark2 >= 0 && moduleMark2 <= 100) {
            this.moduleMark2 = moduleMark2;
        }
        else {
            throw new IllegalArgumentException("Enter a valid mark");
        }
    }

    public Integer getMark2(){

        return moduleMark2;
    }

    public void setMark3(Integer moduleMark3) {
        if (moduleMark3 >= 0 && moduleMark3 <= 100) {
            this.moduleMark3 = moduleMark3;
        }
        else {
            throw new IllegalArgumentException("Enter a valid mark");
        }
    }

    public Integer getMark3(){
        return moduleMark3;
    }

    public Integer getTotalMark(){
        if(moduleMark1 != null && moduleMark2 != null && moduleMark3 != null ){
            return moduleMark1 + moduleMark2 + moduleMark3;
        }
        else{
            return null;
        }
    }

    public Double getAverage(){
        if(getTotalMark() != null) {
            return (moduleMark1 + moduleMark2 + moduleMark3) / 3.0;
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

}
