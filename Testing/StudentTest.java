import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    Student student = new Student();

    @org.junit.jupiter.api.Test
    void getAndSetStudentId() {
        String expected = "w2084737";
        student.setStudentId(expected);
        String actual = student.getStudentId();
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getAndSetStudentName() {
        String expected = "Yesmi";
        student.setStudentName(expected);
        String actual = student.getStudentName();
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getAndSetModule1() {
        double expected = 90;
        student.setModule1(90);
        double actual = student.getModule1();
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getAndSetModule2() {
        double expected = 100;
        student.setModule1(100);
        double actual = student.getModule1();
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getAndSetModule3() {
        double expected = 90;
        student.setModule1(90);
        double actual = student.getModule1();
        assertEquals(expected, actual);
    }


    @org.junit.jupiter.api.Test
    void getTotalMark() {
        student.setModule1(90);
        student.setModule2(80);
        student.setModule3(100);
        double expected = 270;
        double actual = student.getTotalMark();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getAverage() {
        student.setModule1(90);
        student.setModule2(80);
        student.setModule3(100);
        Double expected = 90.0;
        Double actual = student.getAverage();
        assertEquals(expected, actual);

    }

    @org.junit.jupiter.api.Test
    void finalGrade() {
        student.setModule1(90);
        student.setModule2(80);
        student.setModule3(100);
        String expected = "Distinction";
        String actual = student.finalGrade();
        assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void fullDetailsForStudent() {
        student.setStudentName("Yesmi");
        student.setStudentId("w2084737");
        student.setModule1(90);
        student.setModule2(100);
        student.setModule3(80);
        String expected = "Yesmi,w2084737,90.0,100.0,80.0";
        String actual = student.fullDetailsForStudent();
        assertEquals(expected, actual);
    }


    @Test
    void markAndGrade() {
        Module module1 = new Module(90);
        String actual = student.markAndGrade(module1);
        String expected = "90.0(Distinction)";
        assertEquals(expected,actual);
    }

    @Test
    void testFullReport() {
    }
}