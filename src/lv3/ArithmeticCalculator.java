package lv3;


public class ArithmeticCalculator<T extends Number> {
    // 연산자에 따른 사칙연산 수행
    private Double arithmeticCalculate(T a, T b, Operator oper) {
        return switch (oper) {
            case ADD -> a.doubleValue() + b.doubleValue();
            case SUBTRACT -> a.doubleValue() - b.doubleValue();
            case MULTIPLY -> a.doubleValue() * b.doubleValue();
            case DIVIDE -> {
                if (b.doubleValue() == 0) throw new ArithmeticException("0으로 나눌 수 없습니다.");
                yield a.doubleValue() / b.doubleValue();
            }
            case MODULO -> {
                if (b.doubleValue() == 0) throw new ArithmeticException("0에는 나머지가 없습니다.");
                yield a.doubleValue() % b.doubleValue();
            }

        };
    }

    public Double releaseArithmeticCalculator(T a, T b, Operator oper) {
        return this.arithmeticCalculate(a, b, oper);
    }
}

