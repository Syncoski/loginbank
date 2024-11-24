package LoginPackage;

import java.time.LocalDate;

public class User {
    private String fullName;
    private String password;
    private char gender;
    private LocalDate birthday;

    public User(String fullName, String password, char gender, LocalDate birthday) {
        this.fullName = fullName;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }
    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
