public class Module{
    private Integer moduleMark;


    public Module(Integer moduleMark){
        this.moduleMark = moduleMark;
    }

    public Integer getModuleMark() {
        return moduleMark;
    }

    public void setModuleMark(Integer moduleMark) {
        if (moduleMark >= 0 && moduleMark <= 100) {
            this.moduleMark = moduleMark;
        }
        else {
            throw new IllegalArgumentException("Enter a valid mark");
        }
    }
}
