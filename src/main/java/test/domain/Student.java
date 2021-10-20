package test.domain;

public class Student {
    private int id;
    private String name;
    private String sex;
    private String college;
    private String dor;
    private String phone;

    public Student() {
    }

    public Student(int id, String name, String sex, String college, String dor, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.dor = dor;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", dor='" + dor + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
