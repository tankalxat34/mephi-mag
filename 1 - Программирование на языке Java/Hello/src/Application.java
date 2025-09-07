class Application {
    private static int getSum(int a, int b) {
        return a + b;
    }

    private static int getMultiplication(int a, int b) {
        return a * b;
    }

    public static void main(String[] args) {
        var a = 5;
        var b = 4;

//        System.out.print("Hello world!");
//        System.out.print("Hello, world!");
        System.out.print(Application.getSum(a, b));
        System.out.print("\n");
        System.out.print(Application.getMultiplication(a, b));
    }
}
