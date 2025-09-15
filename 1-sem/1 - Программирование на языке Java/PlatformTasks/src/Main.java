public class Main {
    public static void main(String[] args) {
        short[] xs = {45, 67, 101};

        for (short x : xs) {
            System.out.println(x >= 50 && x <= 100);
        }
    }
}
