import java.util.Locale;
import java.util.Scanner;

/**
 * Задание 1.
 * Исправьте ошибки в следующей программе таким образом, чтобы
 * она отображала числа, кратные 5, от 0 до 100 включительно.
 * Выведите числа по одному в строке.
 *
 */
class Task1 {
    public static void main(String[] args) {
        for (byte x = 0; x <= 100; x += 5) {
            System.out.println(x);
        }
    }
}


/**
 * Задание 2.
 * Напишите программу на Java, которая выводит в консоль числовую пирамиду, используя вложенные циклы for.
 * <p>
 * Особенности форматирования:
 * <p>
 * - Между числами выводятся два пробела
 * - После последнего числа в строке пробелы не ставятся
 * - Каждая строка заканчивается числом без пробела после него
 * <p>
 * Пример вывода:
 * <p>
 * ```text
 * 0
 * 0  1
 * 0  1  2
 * 0  1  2  3
 * 0  1  2  3  4
 * 0  1  2  3  4  5
 * 0  1  2  3  4
 * 0  1  2  3
 * 0  1  2
 * 0  1
 * 0
 * ```
 * <p>
 * ```text
 * 0
 * 0  1
 * 0  1  2
 * 0  1
 * 0
 * ```
 *
 */
class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Высота пирамиды: ");
        int max_h = scanner.nextInt();

        for (int row = 0; row < max_h * 2; row++) {
            for (int col = 0; col < (row <= max_h ? row : max_h * 2 - row); col++) {
                System.out.print("  ".repeat(col == 0 ? 0 : 1));
                System.out.print(col);
            }
            System.out.println();
        }
    }
}


/**
 * Задание 3.
 * Перепишите приведённый ниже условный оператор if-else в одну строку, сохранив ту же логику.
 */
class Task3 {
    public static void main(String[] args) {
        int age = 0; // пример значения
        int ticketPrice;

        // Напишите код с решением
        ticketPrice = (age >= 16 ? 20 : 10);
    }
}


/**
 * Задание 4.
 * Напишите программу на Java, которая запрашивает у пользователя год (целое число) и определяет, является ли он високосным.
 * <p>
 * Год считается високосным, если он кратен 4, но не кратен 100, либо кратен 400.
 * <p>
 * Результат нужно вывести в формате: "[год] год — високосный: [да/нет]".
 */
class Task4 {
    public static void main(String[] args) {
        int year;
        boolean isLeapYear;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите год: ");
        year = scanner.nextInt();

        isLeapYear = ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
        System.out.println(year + " год – високосный: " + (isLeapYear ? "да" : "нет"));
    }
}


/**
 * Задание 5
 * Напишите программу на Java, которая:
 * <p>
 * сначала выводит приглашение к вводу: Введите оператор: (с пробелом в конце);
 * считывает с клавиатуры один символ (+ или =) и выводит соответствующее сообщение.
 * При вводе символа + программа должна вывести "Считан плюс", а при вводе = - "Считано равно". Для решения используйте
 * оператор switch. Программа должна выводить сообщение ровно в указанном формате без дополнительных символов.
 * <p>
 * Примечание: Программа должна корректно работать только с указанными символами (+ и =), обработка других символов
 * не требуется. Для ввода данных используйте класс Scanner.
 */
class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите оператор: ");
        String symbol = scanner.next();

        switch (symbol) {
            case "+":
                System.out.println("Считан плюс");
                break;
            case "-":
                System.out.println("Считано равно");
                break;
            default:
                break;
        }
        scanner.close();
    }
}

/**
 * Задание 6.
 * Напишите программу на Java, которая:
 * <p>
 * Сначала выводит приглашение к вводу: Введите x: (с пробелом в конце).
 * Считывает целое число x с клавиатуры.
 * Вычисляет результаты следующих логических выражений для введённого x:
 * (true) && (3 > 4)
 * <p>
 * !(x > 0) && (x > 0)
 * <p>
 * (x > 0) || (x < 0)
 * <p>
 * (x != 0) || (x == 0)
 * <p>
 * (x >= 0) || (x < 0)
 * <p>
 * (x != 1) == !(x == 1)
 * <p>
 * Выводит результаты в столбик в формате:
 * Выражение 1: [результат]
 * <p>
 * Выражение 2: [результат]
 */
class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите x: ");
        int x = scanner.nextInt();

        System.out.println("Выражение 1 " + ((true) && (3 > 4)));
        System.out.println("Выражение 2 " + (!(x > 0) && (x > 0)));
        System.out.println("Выражение 3 " + ((x > 0) || (x < 0)));
        System.out.println("Выражение 4 " + ((x != 0) || (x == 0)));
        System.out.println("Выражение 5 " + ((x >= 0) || (x < 0)));
        System.out.println("Выражение 6 " + ((x != 1) == !(x == 1)));
    }
}


/**
 * Задание 7.
 * Напишите программу на Java, которая:
 * <p>
 * Считывает целое число num с клавиатуры.
 * Вычисляет логическое выражение, которое равно true, если число находится в диапазоне от 1 до 100 не включительно, или если число является отрицательным.
 * Выводит результат в виде true или false.
 */
class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = scanner.nextInt();
        System.out.println((num > 1 && num < 100) || (num < 0));
    }
}


/**
 * Задание 8
 * Напишите программу на Java, которая:
 * <p>
 * Считывает целое число variable с клавиатуры.
 * Если число больше 10 — вычитает из него 10, иначе прибавляет к нему 10.
 * Перепишите условие с использованием тернарного оператора.
 * Выведите результат в консоль.
 */
class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int variable = scanner.nextInt();
        variable = (variable > 10) ? variable - 10 : variable + 10;
        System.out.println(variable);
    }
}


/**
 * Задание 9
 * Напишите программу на Java, которая:
 * <p>
 * Считывает с клавиатуры строку, обозначающую сигнал светофора: красный, желтый или зеленый.
 * В зависимости от введенного сигнала выводит:
 * остановиться — если сигнал красный;
 * подождать — если сигнал желтый;
 * ехать — если сигнал зеленый.
 * Используйте оператор switch.
 * <p>
 * Программа должна выводить сообщение ровно в указанном формате, без лишних символов.
 */
class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String signal = scanner.nextLine().trim();
        switch (signal.toLowerCase(Locale.ROOT)) {
            case "красный":
                System.out.println("остановиться");
                break;
            case "желтый":
                System.out.println("подождать");
                break;
            case "зеленый":
                System.out.println("ехать");
                break;
            default:
                break;
        }
    }
}


/**
 * Задание 10
 * Напишите программу на Java, которая:
 *
 * Считывает один символ с клавиатуры.
 * Выводит строку по правилам дешифрации:
 * 'h' → Hello!
 * 'i', 'm', 'k' → I can decode!
 * 'b' → Bye!
 * любой другой символ → I don't know these symbols :(
 * Используйте оператор switch.
 * Программа должна выводить сообщение ровно в указанном формате, без лишних символов.
 * Ввод производится строчным символом. Ведущие и завершающие пробелы игнорируются.
 */
class Task10 {
    public static void main(String[] args) {
        
    }
}