package lv3;


public abstract class ArithmeticCalculator {
    // 연산자에 따른 사칙연산 수행
    private Double arithmeticCalculate(Double a, Double b, Operator oper) {
        return switch (oper) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
            case MODULO -> a % b;
        };
    }
    public Double releaseArithmeticCalculator(Double a, Double b, Operator oper){
        return this.arithmeticCalculate(a, b, oper);
    }
}
