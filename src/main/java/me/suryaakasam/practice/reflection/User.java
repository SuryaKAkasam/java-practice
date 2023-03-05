package me.suryaakasam.practice.reflection;

import java.time.LocalDate;
import java.util.Random;
import java.util.StringJoiner;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailId;
    private String phoneNo;
    private LocalDate dob;
    private static final String DOMAIN = "xyz.io";

    public User(String firstName, String lastName, String phoneNo, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = generateUserName(firstName, lastName);
        this.password = generatePassword();
        this.emailId = generateEmailId(firstName, lastName);
        this.phoneNo = phoneNo;
        this.dob = dob;

        System.out.printf("%n%nUsername:%s, Password:%s%n", this.username, this.password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String generateUserName(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }

    public String getPassword() {
        return "********";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String generatePassword() {
        int leftLimit = 48;   // numeral '0'
        int rightLimit = 122; // letter  'z'
        int targetStringLength = 8;

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    private String generateEmailId(String firstName, String lastName) {
        return firstName + "." + lastName + "@" + DOMAIN;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return new StringJoiner("\n", User.class.getSimpleName() + " [\n", "\n]")
                .add("  firstName='" + getFirstName() + "'")
                .add("  lastName='" + getLastName() + "'")
                .add("  dob='" + getDob() + "'")
                .add("  emailId='" + getEmailId() + "'")
                .add("  phoneNo='" + getPhoneNo() + "'")
                .add("  username='" + getUsername() + "'")
                .add("  password='" + getPassword() + "'")
                .toString();
    }
}
