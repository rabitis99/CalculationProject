package lv2;

public abstract class Calculator {
    // 연산자에 따른 사칙연산 수행
    private int calculate(int a, int b, char oper) {
        return switch (oper) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                yield a / b;
            }
            case '%' -> {
                if (b == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                yield a % b;
            }
            default -> throw new IllegalStateException("Unexpected value: " + oper);
        };
    }
    public int releaseCalculation(int a, int b, char oper){
        return this.calculate(a, b, oper);
    }
}
