import java.lang.reflect.*;
import java.util.Scanner;

public class DynamicMathLib {
    
    // --- Step 1: Define "Library" methods ---
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            System.out.println("Division by zero not allowed!");
            return 0;
        }
        return (double) a / b;
    }

    // --- Step 2: Main Application (Testing dynamically using reflection) ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter two numbers:");
        int num1 = sc.nextInt();
        int num2 = sc.nextInt();

        System.out.println("Choose operation: add, subtract, multiply, divide");
        String op = sc.next().toLowerCase();

        try {
            // Load class dynamically
            Class<?> cls = Class.forName("DynamicMathLib");

            // Create object
            Object obj = cls.getDeclaredConstructor().newInstance();

            // Dynamically get the method based on user input
            Method method = null;
            switch (op) {
                case "add":
                    method = cls.getMethod("add", int.class, int.class);
                    break;
                case "subtract":
                    method = cls.getMethod("subtract", int.class, int.class);
                    break;
                case "multiply":
                    method = cls.getMethod("multiply", int.class, int.class);
                    break;
                case "divide":
                    method = cls.getMethod("divide", int.class, int.class);
                    break;
                default:
                    System.out.println("Invalid operation!");
                    sc.close();
                    return;
            }

            // Invoke method dynamically
            Object result = method.invoke(obj, num1, num2);

            // Display result
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
