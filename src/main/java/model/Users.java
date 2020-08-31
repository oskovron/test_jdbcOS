package model;

public class Users {
    private int idusers;
    private String first_name;
    private String last_name;
    private int age;

    public Users(int idusers,String first_name, String last_name, int age){
        this.idusers=idusers;
        this.first_name=first_name;
        this.last_name=last_name;
        this.age=age;
    }

    public int getIdusers(){return idusers;}
    public void setIdusers(int idusers){this.idusers=idusers;}

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idusers=" + idusers +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                '}';
    }
}
