package me.suryaakasam.practice.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ClassSpy {
    public static void spy(Class<?> clazz) throws ClassNotFoundException {
        String header = clazz.getName(), filler, format = "%s:%s%n";
        int length;

        if (header.length() % 2 != 0) header = header.concat(" ");
        length = ((150 - header.length() - 2) / 2);
        filler = "-".repeat(Math.max(length, 0));

        out.printf("%n%n%s %s %s%n", filler, header, filler);
        out.printf(format, "Name",           print(clazz.getCanonicalName()));
        out.printf(format, "Types",          print(getDetails(clazz.getTypeParameters(), TypeVariable::getName)));
        out.printf(format, "Package",        print(clazz.getPackageName()));
        out.printf(format, "Modifiers",      print(Modifier.toString(clazz.getModifiers())));
        out.printf(format, "Interfaces",     print(getDetails(clazz.getGenericInterfaces(), Type::toString)));
        out.printf(format, "Annotations",    print(getDetails(clazz.getAnnotations(), Annotation::toString)));
        out.printf(format, "SuperClasses",   print(getSuperClasses(clazz)));
        out.printf(format, "Constructors",   print(getDetails(clazz.getConstructors(), Constructor::toGenericString)));
        out.printf(format, "Member-Fields",  print(getMemberFields(clazz)));
        out.printf(format, "Member-Methods", print(getMemberMethods(clazz)));
    }

    private static String print(String string) {
        String formatter = "\n  ";
        return formatter + ((string != null) ? string : "N/A");
    }

    private static String print(Collection<String> collection) {
        String formatter = "\n  ";
        return formatter + ((collection.size() != 0) ? String.join(formatter, collection) : "N/A");
    }

    private static <T> List<String> getDetails(T[] array, Function<T, String> function) {
        return Arrays.stream(array).map(function).collect(Collectors.toList());
    }

    private static List<String> getMemberFields(Class<?> clazz) throws ClassNotFoundException {
        List<String> superClasses = getSuperClasses(clazz);
        List<String> allFields = new ArrayList<>();
        String formatter = "  ";

        if (clazz.isArray()) return allFields;

        for (String className: superClasses) {
            allFields.add("From " + className + ":");
            Field[] declaredFields = Class.forName(className).getDeclaredFields();

            if (declaredFields.length == 0) allFields.add(formatter + "N/A");
            else for (Field field : declaredFields) allFields.add(formatter + formatField(field));
        }

        return allFields;
    }

    private static String formatField(Field field) {
        return field.toGenericString();
    }

    private static List<String> getMemberMethods(Class<?> clazz) throws ClassNotFoundException {
        List<String> allMethods = new ArrayList<>();
        String formatter = "  ";

        if (clazz.isArray()) return allMethods;

        if (clazz.isInterface()) {
            Class<?>[] superInterfaces = clazz.getInterfaces();
            for (Class<?> i : superInterfaces) {
                allMethods.add("From " + i + " interface:");
                Method[] declaredMethods = i.getDeclaredMethods();

                if (declaredMethods.length == 0) allMethods.add(formatter + "N/A");
                else for (Method method : declaredMethods) allMethods.add(formatter + formatMember(method));
            }
        }

        List<String> superClasses = getSuperClasses(clazz);
        for (String c : superClasses) {
            Class<?> superClazz = Class.forName(c);
            Method[] declaredMethods = superClazz.getDeclaredMethods();
            allMethods.add("From " + superClazz.getCanonicalName() + (superClazz.isInterface() ? " interface:" : " class:"));

            if (declaredMethods.length == 0) allMethods.add(formatter + "N/A");
            else for (Method method : declaredMethods) allMethods.add(formatter + formatMember(method));
        }

        return allMethods;
    }

    private static String formatMember(Method method) {
        return method.toGenericString();
    }

    private static List<String> getSuperClasses(Class<?> clazz) {
        Stack<String> hierarchy = new Stack<>();
        hierarchy.add(clazz.getCanonicalName());

        Class<?> ancestor = clazz.getSuperclass();
        while (ancestor != null) {
            hierarchy.add(ancestor.getCanonicalName());
            ancestor = ancestor.getSuperclass();
        }

        List<String> superClasses = new LinkedList<>();
        while (!hierarchy.isEmpty())
            superClasses.add(hierarchy.pop());

        return superClasses;
    }
}
