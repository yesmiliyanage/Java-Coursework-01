public class Module {
    Integer moduleMark1;
    Integer moduleMark2;
    Integer moduleMark3;

    public Module(Integer mark1, Integer mark2, Integer mark3){
        this.moduleMark1 = mark1;
        this.moduleMark2 = mark2;
        this.moduleMark3 = mark3;
    }

    public Integer getMark1(){

        return moduleMark1;
    }

    public Integer getMark2(){

        return moduleMark2;
    }

    public Integer getMark3(){

        return moduleMark3;
    }

    public Double getAverage(){
        if(moduleMark1 != null && moduleMark2 != null && moduleMark3 != null ) {
            return (moduleMark1 + moduleMark2 + moduleMark3) / 3.0;
        }
        else{
            return null;
        }
    }

    public Integer getTotalMark(){
        if(moduleMark1 != null && moduleMark2 != null && moduleMark3 != null ){
            return moduleMark1 + moduleMark2 + moduleMark3;
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
