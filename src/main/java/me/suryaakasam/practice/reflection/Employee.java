package me.suryaakasam.practice.reflection;

import java.time.LocalDate;
import java.util.*;

public class Employee extends User {
    private Role role;
    private String department;
    private Set<String> responsibilities;

    public Employee(String firstName, String lastName, Role role, String department, String phoneNo,
                    LocalDate dob, String... responsibilities) {
        super(firstName, lastName, phoneNo, dob);
        this.role = role;
        this.department = department;
        this.responsibilities = new HashSet<>(List.of(responsibilities));
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Set<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(Set<String> responsibilities) {
        this.responsibilities = responsibilities;
    }

    public boolean addResponsibility(String responsibility) {
        return this.responsibilities.add(responsibility);
    }

    public boolean deleteResponsibility(String responsibility) {
        return this.responsibilities.remove(responsibility);
    }

    @Override
    public String toString() {
        return new StringJoiner("\n", Employee.class.getSimpleName() + " [\n", "\n]")
                .add("  firstName='" + getFirstName() + "'")
                .add("  lastName='" + getLastName() + "'")
                .add("  dob='" + getDob() + "'")
                .add("  role='" + getRole() + "'")
                .add("  emailId='" + getEmailId() + "'")
                .add("  phoneNo='" + getPhoneNo() + "'")
                .add("  username='" + getUsername() + "'")
                .add("  password='" + getPassword() + "'")
                .add("  department='" + getDepartment() + "'")
                .add("  responsibilities=" + getResponsibilities())
                .toString();
    }
}
