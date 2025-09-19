import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double operand1 = scanner.nextDouble();     // первое число
        char operation = scanner.next().charAt(0);  // символ операции
        double operand2 = scanner.nextDouble();     // второе число

        double result; // переменная для хранения результата

        switch (operation) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    System.out.println("Ошибка: Деление на ноль невозможно.");
                    return;
                }
                result = operand1 / operand2;
                break;
            default:
                System.out.println("Ошибка: Операция не распознана. Поддерживаемые операции: +, -, *, /");
                return;
        }
        System.out.println(result);
        scanner.close();
    }
}
