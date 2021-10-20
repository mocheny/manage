package test.domain;

public class Dormitory {
    private int id;
    private String sex;
    private int num;
    private int s1;
    private int s2;
    private int s3;
    private int s4;

    public Dormitory() {
    }

    public Dormitory(int id, String sex, int num, int s1, int s2, int s3, int s4) {
        this.id = id;
        this.sex = sex;
        this.num = num;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS1() {
        return s1;
    }

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS2() {
        return s2;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public int getS3() {
        return s3;
    }

    public void setS3(int s3) {
        this.s3 = s3;
    }

    public int getS4() {
        return s4;
    }

    public void setS4(int s4) {
        this.s4 = s4;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "id=" + id +
                ", sex='" + sex + '\'' +
                ", num=" + num +
                ", s1=" + s1 +
                ", s2=" + s2 +
                ", s3=" + s3 +
                ", s4=" + s4 +
                '}';
    }
}
