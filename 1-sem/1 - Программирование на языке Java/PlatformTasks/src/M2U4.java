import java.util.Scanner;

/*
 * Задание 1.
 * Исправьте ошибки в следующей программе таким образом, чтобы
 * она отображала числа, кратные 5, от 0 до 100 включительно.
 * Выведите числа по одному в строке.
 * */
class Task1 {
    public static void main(String[] args) {
        for (byte x = 0; x <= 100; x += 5) {
            System.out.println(x);
        }
    }
}


/*
 * Задание 2.
* Напишите программу на Java, которая выводит в консоль числовую пирамиду, используя вложенные циклы for.

Особенности форматирования:

- Между числами выводятся два пробела
- После последнего числа в строке пробелы не ставятся
- Каждая строка заканчивается числом без пробела после него

Пример вывода:

```text
0
0  1
0  1  2
0  1  2  3
0  1  2  3  4
0  1  2  3  4  5
0  1  2  3  4
0  1  2  3
0  1  2
0  1
0
```
*
* ```text
* 0
* 0  1
* 0  1  2
* 0  1
* 0
* ```
* */
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
