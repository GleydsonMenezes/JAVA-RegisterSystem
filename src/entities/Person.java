package entities;

public class Person {

    private String name;
    private String email;
    private Integer age;
    private Double height;


    public Person(String name, String email, Integer age, Double height) {
        if (age < 18){
            throw new PersonExceptions("Precisa ser maior de 18 anos para registrar");
        }
        if (name.length() < 10){
            throw new PersonExceptions("O nome precisa ter mais que 10 caracteres");
        }
        if (!email.contains("@")) {
            throw new PersonExceptions("O email precisa conter @");
        }
        this.email = email;
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
            this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
