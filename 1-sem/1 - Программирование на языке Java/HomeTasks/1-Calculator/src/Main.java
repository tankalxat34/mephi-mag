import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double operand1 = scanner.nextDouble();     // первое число
        char operation = scanner.next().charAt(0);  // символ операции
        double operand2 = scanner.nextDouble();     // второе число

        switch (operation) {
            case '+':
                System.out.println(operand1 + operand2);
                break;
            case '-':
                System.out.println(operand1 - operand2);
                break;
            case '*':
                System.out.println(operand1 * operand2);
                break;
            case '/':
                System.out.println(operand1 / operand2);
                break;
            case 'q':
                return;
            case 'c':
                scanner.next();
                break;
            default:
                System.out.println("Ошибка: Операция не распознана. Поддерживаемые операции: +, -, *, /");
                break;
        }
        scanner.close();
    }
}
