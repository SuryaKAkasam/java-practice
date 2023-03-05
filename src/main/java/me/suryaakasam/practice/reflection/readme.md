# CLASSES
### Retrieve Class Object
- If the type is available, then use the .class syntax
  - This is the easiest way to obtain the class for a primitive type
  - This syntax works on array types too
  ```
  ex:
  Class c = int[].class;
  Class classFromType = Employee.class;
  ```
- If an instance of an object is available, then use Object.getClass() method
  - This only works for reference types which all inherit from Object
  - This syntax works on array instances as well because they are Objects too
  ```
  ex:
  int[] numbers = new int[10];
  Class c = numbers.getClass();
  
  Employee john = new Employee("John", "Doe", Role.DEVELOPER, "IT", "9876543210", LocalDate.parse("1990-1-1"));
  Class classFromInstance = john.getClass();
  ```
- If the fully-qualified name of a class is available, then use the Class.forName() static method
  - Throws ClassNotFoundException exception
  - This cannot be used for primitive types
  - The syntax for names of array (both reference & primitive) classes is described by Class.getName()
  ```
  ex:
  Class classDoubleArr   = Class.forName("[D");                  --> Same as double[].class
  Class classString2DArr = Class.forName("[[Ljava.lang.String"); --> Same as String[][].class
  Class classFromQualifiedName = Class.forName("me.suryaakasam.practice.reflection.Employee");
  ```
- TYPE Field for Primitive Type Wrappers
    Class doubleClass = Double.TYPE; --> Same as double.class
- Methods that return Classes
  ```
  Class.getSuperclass()
  Class.getClasses()
  Class.getDeclaredClasses()
  ```
### Examine Class Modifiers
```
Access modifiers                                    : public, protected, and private
Modifier requiring override                         : abstract
Modifier restricting to one instance                : static
Modifier prohibiting value modification             : final
Modifier forcing strict floating point behavior     : strictfp
Annotations
```
### Examine Class Members (Fields, Methods & Constructors)
```
Member Type      Class API                   List of Members?    Inherited Members?    Private Members?
===========      =================           ================    ==================    ================
Field            getDeclaredField()          No                  No                    Yes
Field            getField()                  No                  Yes                   No
Field            getDeclaredFields()         Yes                 No                    Yes
Field            getFields()                 Yes                 Yes                   No
Method           getDeclaredMethod()         No                  No                    Yes
Method           getMethod()                 No                  Yes                   No
Method           getDeclaredMethods()        Yes                 No                    Yes
Method           getMethods()                Yes                 Yes                   No
Constructor      getDeclaredConstructor()    No                  N/A                   Yes
Constructor      getConstructor()            No                  N/A                   No
Constructor      getDeclaredConstructors()   Yes                 N/A                   Yes
Constructor      getConstructors()           Yes                 N/A                   No
Note: Constructors are not inherited
```

# FIELDS
### Field Types
- A field (java.lang.reflect.Field class) may be either of primitive or reference type.
- Primitive Types:
  - There are eight primitives: boolean, byte, short, int, long, char, float, and double.
- Reference Types:
  - A reference type is anything that is a direct or indirect subclass of java.lang.Object
  - This includes interfaces, arrays, and enumerated types.
### Examine Field Modifiers
```
Access modifiers                                    : public, protected, and private
Field-specific modifiers governing runtime behavior : transient and volatile
Modifier restricting to one instance                : static
Modifier prohibiting value modification             : final
Annotations
```

# METHODS
### Method Types
- A method (java.lang.reflect.Method class) contains executable code which may be invoked. 
- Methods are inherited and in non-reflective code behaviors such as overloading, overriding, and hiding are enforced by the compiler.
- In contrast, reflective code makes it possible for method selection to be restricted to a specific class without considering its superclasses.
- Superclass methods may be accessed, but it is possible to determine their declaring class, which is impossible to discover programmatically without reflection.
### Examine Method Information
```
Obtaining Method Type Information
Obtaining Names of Method Parameters
Retrieving and Parsing Method Modifiers
  Access modifiers                                                   : public, protected, and private
  Modifier restricting to one instance                               : static
  Modifier prohibiting value modification                            : final
  Modifier requiring override                                        : abstract
  Modifier preventing reentrancy                                     : synchronized
  Modifier indicating implementation in another programming language : native
  Modifier forcing strict floating point behavior                    : strictfp
  Annotations
Invoking Methods
```

# CONSTRUCTORS
### Constructor Types
- A constructor (java.lang.reflect.Constructor class) is used in the creation of an object that is an instance of a class. 
- Typically, it performs operations required to initialize the class before methods are invoked or fields are accessed. 
- Constructors are never inherited.