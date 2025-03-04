package lv3;


public abstract class Calculator {
    // 연산자에 따른 사칙연산 수행
    private Double calculate(Double a, Double b, Operator oper) {
        return switch (oper) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
            case MODULO -> a % b;
            default -> 0.; // 잘못된 연산자는 기본값 0 반환
        };
    }
    public Double releaseCalculation(Double a, Double b, Operator oper){
        return this.calculate(a, b, oper);
    }
}
