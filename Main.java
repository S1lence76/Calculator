import java.util.Scanner;

// Интерфейс стратегии для выполнения операций
interface OperationStrategy {
    double execute(double a, double b);
}

// Реализация стратегии для сложения
class AdditionStrategy implements OperationStrategy {
    @Override
    public double execute(double a, double b) {
        return a + b;
    }
}

// Реализация стратегии для умножения
class MultiplicationStrategy implements OperationStrategy {
    @Override
    public double execute(double a, double b) {
        return a * b;
    }
}

// Реализация стратегии для деления
class DivisionStrategy implements OperationStrategy {
    @Override
    public double execute(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return a / b;
    }
}

// Фабрика для создания стратегии в зависимости от выбранной операции
class OperationStrategyFactory {
    public static OperationStrategy createStrategy(String operation) {
        switch (operation) {
            case "+":
                return new AdditionStrategy();
            case "*":
                return new MultiplicationStrategy();
            case "/":
                return new DivisionStrategy();
            default:
                throw new IllegalArgumentException("Неподдерживаемая операция: " + operation);
        }
    }
}

// Класс калькулятора, использующий стратегии для выполнения операций
class Calculator {
    public double calculate(double a, double b, String operation) {
        OperationStrategy strategy = OperationStrategyFactory.createStrategy(operation);
        double result = strategy.execute(a, b);
        logOperation(a, b, operation);
        return result;
    }

    // Метод для вывода информации о выполненной операции
    private void logOperation(double a, double b, String operation) {
        System.out.println("Сделана операция " + operation + " для чисел " + a + " и " + b);
    }
}

// Метод main для запуска калькулятора из командной строки
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первое число:");
        double a = scanner.nextDouble();

        System.out.println("Введите второе число:");
        double b = scanner.nextDouble();

        System.out.println("Выберите операцию (+, *, /):");
        String operation = scanner.next();

        Calculator calculator = new Calculator();
        double result = calculator.calculate(a, b, operation);
        System.out.println("Результат: " + result);

        scanner.close();
    }
}
