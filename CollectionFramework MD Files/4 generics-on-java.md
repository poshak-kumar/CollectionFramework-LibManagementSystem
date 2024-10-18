Generics in Java is a language feature introduced in **Java 5** that allows developers to write flexible, reusable, and type-safe code. Generics enable types (classes and methods) to operate on objects of various types while providing compile-time type checking. The symbol `< >` is used to define generic parameters.

Generics help us **avoid type casting**, **catch errors at compile time**, and write **generic classes, interfaces, and methods**. Here's a complete, detailed explanation of Java Generics:

---

### 1. **What Are Generics?**

Generics allow a class or method to work with any data type in a **type-safe manner**. Instead of specifying a specific type (like `Integer`, `String`), you can define **generic placeholders** (`T`, `E`, `K`, `V`, etc.) that will be replaced by actual types when the code is executed.

#### Example:
Without generics:
```java
List list = new ArrayList();
list.add("Java");
String s = (String) list.get(0);  // Casting is required
```

With generics:
```java
List<String> list = new ArrayList<>();
list.add("Java");
String s = list.get(0);  // No casting required
```

---

### 2. **Why Use Generics?**

Generics provide the following key advantages:

1. **Type-Safety**: Generics enforce type constraints at compile time, reducing the chance of runtime `ClassCastException`.
2. **Eliminate Type Casting**: Without generics, explicit type casting is necessary when retrieving elements from collections. Generics eliminate this need.
3. **Code Reusability**: A generic class or method can work with different data types, allowing developers to write reusable code.

#### Example Without Generics:
```java
public Object add(Object a, Object b) {
    return a.toString() + b.toString();  // Type casting is required
}
```

#### Example With Generics:
```java
public <T> T add(T a, T b) {
    return (T) (a.toString() + b.toString());  // Reusable for any type
}
```

---

### 3. **Generic Syntax: The Diamond Operator `< >`**

Generics in Java are represented using the diamond operator (`< >`). The diamond contains type parameters, which define what types a generic class, interface, or method can accept.

#### Commonly Used Type Parameters:
- `T`: Type (used for any general type)
- `E`: Element (used in collections like `List`, `Set`)
- `K`: Key (used in `Map`)
- `V`: Value (used in `Map`)
- `N`: Number (used for numeric types)

Example:
```java
public class Box<T> {
    private T item;
    
    public void setItem(T item) {
        this.item = item;
    }
    
    public T getItem() {
        return item;
    }
}
```

In the above example:
- `Box<T>` is a generic class.
- `T` represents the type of the object contained in the `Box`.
- You can replace `T` with any type like `Box<String>` or `Box<Integer>`.

---

### 4. **Types of Generics**

Generics can be used in various ways:

#### 4.1. **Generic Classes**
A **generic class** is a class that can handle different data types. It uses a type parameter (or more) to specify the data types it can work with.

Example:
```java
public class Container<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
```

Usage:
```java
Container<String> stringContainer = new Container<>();
stringContainer.set("Hello");

Container<Integer> integerContainer = new Container<>();
integerContainer.set(123);
```

#### 4.2. **Generic Interfaces**
Just like classes, interfaces can also be generic.

Example:
```java
public interface GenericInterface<T> {
    T perform(T item);
}

public class StringWorker implements GenericInterface<String> {
    @Override
    public String perform(String item) {
        return item.toUpperCase();
    }
}
```

#### 4.3. **Generic Methods**
You can declare methods with type parameters that allow the method to be generic.

Example:
```java
public class Utility {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element);
        }
    }
}
```

Usage:
```java
String[] stringArray = {"Apple", "Banana"};
Integer[] intArray = {1, 2, 3};

Utility.printArray(stringArray);  // Generic method called with String array
Utility.printArray(intArray);     // Generic method called with Integer array
```

#### 4.4. **Generic Constructors**
Constructors can also be generic.

Example:
```java
public class GenericConstructor {
    private <T> GenericConstructor(T item) {
        System.out.println(item);
    }

    public static void main(String[] args) {
        new GenericConstructor("Hello");
        new GenericConstructor(100);
    }
}
```

---

### 5. **Bounded Type Parameters**

Sometimes, you want to restrict the types that can be used as generic arguments. **Bounded type parameters** allow you to specify that a generic type must be a subclass of a specific class or implement a certain interface.

#### 5.1. **Upper Bounded Wildcards (`extends`)**
Restricting a type to be a subclass of a specific class or interface.

Example:
```java
public class Utility {
    public static <T extends Number> void printNumbers(T[] numbers) {
        for (T num : numbers) {
            System.out.println(num);
        }
    }
}
```

Here, the type `T` is restricted to subclasses of `Number` (e.g., `Integer`, `Double`).

#### 5.2. **Lower Bounded Wildcards (`super`)**
Lower bounds ensure that the generic type must be a superclass of a specific type.

Example:
```java
public static void addNumbers(List<? super Integer> list) {
    list.add(10);  // You can add Integer or its subclasses
}
```

---

### 6. **Wildcards in Generics**

**Wildcards** are used in situations where you don't know the exact type. Wildcards can be:
- **`? extends Type`**: Accepts the specified type or its subclasses.
- **`? super Type`**: Accepts the specified type or its superclasses.
- **`?`**: Accepts any type.

#### Example of Wildcards:

```java
// ? extends Number: Accepts any subclass of Number (Integer, Double, etc.)
public static void printList(List<? extends Number> list) {
    for (Number n : list) {
        System.out.println(n);
    }
}

// ? super Integer: Accepts Integer or any superclass (Number, Object)
public static void addToList(List<? super Integer> list) {
    list.add(5);  // You can add Integer or its subclasses
}
```

---

### 7. **Type Erasure in Generics**

Java uses a mechanism called **type erasure** for implementing generics. This means that generic type information is erased at runtime, and only raw types remain. This ensures backward compatibility with older Java versions, but it also means that you cannot use primitives as generic types (you have to use their wrapper classes).

Example:
```java
List<Integer> intList = new ArrayList<>();
List<String> strList = new ArrayList<>();
```

At runtime, both `intList` and `strList` are treated as `List<Object>`, and the type-specific information (like `Integer` or `String`) is erased.

---

### 8. **Restrictions and Limitations of Generics**

- **Cannot Instantiate Generic Types with Primitives**: You cannot create instances of generic types with primitive types (e.g., `int`, `double`).
  ```java
  List<int> list = new ArrayList<>();  // Error
  ```

- **Cannot Create Generic Arrays**: You cannot instantiate generic arrays.
  ```java
  T[] arr = new T[10];  // Error
  ```

- **Cannot Use `instanceof` with Parameterized Types**: Since type information is erased at runtime, you cannot check for the specific type in `instanceof`.
  ```java
  if (obj instanceof List<String>) {  // Error
  }
  ```

---

### 9. **Conclusion**

Generics in Java provide a robust way to write type-safe, reusable, and flexible code. With features like **generic classes, methods, interfaces**, and **wildcards**, developers can write highly versatile code that adapts to different types while maintaining compile-time type safety. Despite some limitations (due to type erasure and restrictions on primitives), generics offer powerful solutions for managing type safety and code reuse.