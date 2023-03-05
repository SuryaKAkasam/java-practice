package me.suryaakasam.practice.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

public class Practice {
    public static void main(String[] args) throws Exception {
        ClassSpy.spy(Employee.class);
        manageObjectViaReflection();
        simulateDependencyInjection();
    }

    private static void simulateDependencyInjection() throws Exception {
        //Construct the dependencies/services
        Class<?> serviceClass = SMSService.class;
        Constructor<?> serviceClassConstructor = serviceClass.getDeclaredConstructor();
        SMSService serviceObject = (SMSService) serviceClassConstructor.newInstance();

        //Inject the dependencies/services to the dependent/client classes
        Class<?> clientClass = Notifier.class;
        Constructor<?> clientClassConstructor = clientClass.getDeclaredConstructor(NotificationService.class);
        Notifier clientObject = (Notifier) clientClassConstructor.newInstance(serviceObject);

        //Invoke the client class
        clientObject.notify("Jane", "Good morning!");
    }

    private static void manageObjectViaReflection() throws Exception {
        //Get Class
        Class<?> clazz = Employee.class;

        //Get Constructor
        Constructor<?> employeeConstructor = clazz.getDeclaredConstructor(String.class, String.class,
                Role.class, String.class, String.class, LocalDate.class, String[].class);

        //Instantiate new object via. reflection
        Employee employee = (Employee) employeeConstructor.newInstance("John", "Doe", Role.DEVELOPER,
                "IT", "9876543210", LocalDate.parse("1990-01-01"), new String[]{"System Design", "Build Applications"});

        //Print the object
        System.out.println("\n\nAfter construction:\n" + employee);

        //Getting and Setting Field values via. reflection
        Field role = clazz.getDeclaredField("role"); //Throws IllegalAccessException - attempting to access a private field
        role.setAccessible(true); //Unsafe workaround - avoid this!!

        Field department = clazz.getDeclaredField("department"); //Throws IllegalAccessException - attempting to access a private field
        department.setAccessible(true); //Unsafe workaround - avoid this!!

        Field responsibilities = clazz.getDeclaredField("responsibilities"); //Throws IllegalAccessException - attempting to access a private field
        responsibilities.setAccessible(true); //Unsafe workaround - avoid this!!

        System.out.println("\n\nBefore Setting: ");
        System.out.println("Role             = " + role.get(employee));
        System.out.println("Department       = " + department.get(employee));
        System.out.println("Responsibilities = " + responsibilities.get(employee));

        role.set(employee, Role.ANALYST);
        department.set(employee, "Business");
        responsibilities.set(employee, new HashSet<>(List.of("Analyze Usecases", "Document Business Rules")));

        System.out.println("\n\nAfter Setting: ");
        System.out.println("Role             = " + role.get(employee));
        System.out.println("Department       = " + department.get(employee));
        System.out.println("Responsibilities = " + responsibilities.get(employee));

        //Finding and Invoking a Method via. reflection
        Method getDepartment = clazz.getDeclaredMethod("getDepartment");
        Method addResponsibility = clazz.getDeclaredMethod("addResponsibility", String.class);
        Method getResponsibilities = clazz.getDeclaredMethod("getResponsibilities");
        System.out.println("\n\nInvoking Methods: ");
        System.out.println("Department       = " + getDepartment.invoke(employee));
        addResponsibility.invoke(employee, "Knowledge Transfer");
        System.out.println("Responsibilities = " + getResponsibilities.invoke(employee));

        //Print final state
        System.out.println("\n\nAfter updates via. reflection:\n" + employee);
    }
}
