package lv2;

public abstract class Calculator {
    // 연산자에 따른 사칙연산 수행
    private int calculate(int a, int b, char oper) {
        return switch (oper) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            default -> 0; // 잘못된 연산자는 기본값 0 반환
        };
    }
    public int releaseCalculation(int a, int b, char oper){
        return this.calculate(a, b, oper);
    }
}
