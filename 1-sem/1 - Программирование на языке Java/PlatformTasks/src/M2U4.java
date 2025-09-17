import java.util.*;

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
 * <p>
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
        Scanner scanner = new Scanner(System.in);

        char s = scanner.nextLine().charAt(0);
        switch (s) {
            case 'h':
                System.out.println("Hello!");
                break;
            case 'i':
            case 'm':
            case 'k':
                System.out.println("I can decode!");
                break;
            case 'b':
                System.out.println("Bye!");
                break;
            default:
                System.out.println("I don't know these symbols :(");
                break;
        }
    }
}


/***
 * Задание 11
 * Создайте цикл, выводящий на экран чётные цифры из диапазона от 2 до 10 включительно. Выводите числа в столбик.
 */
class Task11 {
    public static void main(String[] args) {
        for (int x = 0; x <= 10; x += 2) {
            System.out.println(x);
        }
    }
}

/**
 * Задание 12
 * Напишите программу на Java, которая:
 * <p>
 * Считывает с клавиатуры два числа: вес в килограммах (double) и рост в метрах (double).
 * Если хотя бы одно из значений меньше или равно нулю — выводит сообщение:
 * Ошибка: вес и рост должны быть положительными числами.
 * <p>
 * Иначе вычисляет индекс массы тела (ИМТ = вес / (рост * рост)), округляет его до 1 знака после запятой и выводит
 * одну строку в формате:
 * ИМТ: [значение] — [сообщение]
 * <p>
 * где [сообщение] выбирается по правилам:
 * <p>
 * Если ИМТ < 18.5: Ваш индекс массы тела ниже нормы. Возможно, стоит обратить внимание на питание.
 * Если 18.5 ≤ ИМТ ≤ 24.9: Ваш индекс массы тела в норме! Продолжайте в том же духе.
 * Если 25 ≤ ИМТ ≤ 29.9: Ваш индекс массы тела немного выше нормы. Рассмотрите возможность сбалансированного питания
 * и физической активности.
 * Если ИМТ ≥ 30: Ваш индекс массы тела значительно выше нормы. Рекомендуем проконсультироваться с врачом или
 * специалистом по питанию для получения рекомендаций.
 * Программа должна выводить только одно сообщение, без дополнительных строк.
 */
class Task12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        double weight = scanner.nextDouble();
        double height = scanner.nextDouble();

        if (weight <= 0 || height <= 0) {
            System.out.println("Ошибка: вес и рост должны быть положительными");
            return;
        }

        double imt = weight / (height * height);
        imt = Math.round(imt * 10.0) / 10.0;

        System.out.print("ИМТ: " + imt + " — ");
        if (imt < 18.5)
            System.out.println("Ваш индекс массы тела ниже нормы. Возможно, стоит обратить внимание на питание.");
        else if ((imt <= 24.9))
            System.out.println("Ваш индекс массы тела в норме! Продолжайте в том же духе.");
        else if ((imt <= 29.9))
            System.out.println("Ваш индекс массы тела немного выше нормы. Рассмотрите возможность сбалансированного питания и физической активности.");
        else
            System.out.println("Ваш индекс массы тела значительно выше нормы. Рекомендуем проконсультироваться с врачом или специалистом по питанию для получения рекомендаций.");
    }
}


/**
 * Задание 13
 * Напишите программу на Java, которая:
 * <p>
 * Генерирует случайное число от 1 до 10 включительно.
 * Запрашивает у пользователя целое число.
 * После каждого ввода:
 * если введённое число равно загаданному — выводит Совершенно верно! Это и есть загаданное мною число! и завершает работу;
 * если число больше загаданного — выводит Загаданное мною число меньше.;
 * если число меньше загаданного — выводит Загаданное мною число больше.
 * Игра продолжается до тех пор, пока пользователь не угадает число.
 */
class Task13 {
    public static void main(String[] args) {
        Random rand = new Random();
        int randInt = rand.nextInt(10) + 1;
        Scanner scanner = new Scanner(System.in);

        int userInput;
        do {
            System.out.print("Введите число: ");
            userInput = scanner.nextInt();
            if (userInput > randInt)
                System.out.println("Загаданное мною число меньше");
            else if (userInput < randInt)
                System.out.println("Загаданное мною число больше");
        } while (userInput != randInt);

        System.out.println("Совершенно верно! Это и есть загаданное мною число!");
        scanner.close();
    }
}


/**
 * Задание 14
 * Напишите программу на Java, которая играет в «Угадай число» с фиксированными параметрами:
 * <p>
 * Программа генерирует целое число target от 1 до 20 включительно.
 * Пользователь делает не более 5 попыток (вводит по одному целому числу за попытку).
 * После каждой попытки:
 * — если guess > target, вывести: Загаданное мною число меньше.
 * — если guess < target, вывести: Загаданное мною число больше.
 * — если guess == target, вывести: Совершенно верно! Это и есть загаданное мною число! и завершить программу.
 * <p>
 * Если введены некорректные данные (не целое число):
 * — вывести: Ошибка: введите целое число!
 * — попытка не засчитывается
 * <p>
 * Если после 5 попыток число не угадано, вывести две строки:
 * Конец игры.
 * <p>
 * Мною было загадано число: X (где X — target).
 * <p>
 * Примечания:
 * <p>
 * Приглашения к вводу печатать не нужно
 * Некорректные попытки не уменьшают количество оставшихся попыток
 * Для генерации числа используйте Random с seed = 12345L
 */
class Task14 {
    final static int COUNT_OF_ATTEMPTS = 5;

    private static int getRndInt(int from_, int to_) {
        Random rand = new Random(12345L);
        return rand.nextInt(to_) + from_;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int target = getRndInt(1, 20);
        int guess;
        int attempts = 0;

        while (attempts < COUNT_OF_ATTEMPTS) {
            try {
                guess = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                scanner.next();
                continue;
            }

            if (guess > target)
                System.out.println("Загаданное мною число меньше.");
            else if (guess < target)
                System.out.println("Загаданное мною число больше.");
            else {
                System.out.println("Совершенно верно! Это и есть загаданное мною число!");
                return;
            }
            attempts++;
        }
        System.out.println("Конец игры.");
        System.out.println("Мною было загадано число: " + target);
        scanner.close();
    }
}