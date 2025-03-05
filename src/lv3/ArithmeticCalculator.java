package lv3;


public abstract class ArithmeticCalculator {
    // 연산자에 따른 사칙연산 수행
    public double arithmeticCalculate(double a, double b, Operator oper) {
        double result = switch (oper) {
            case ADD-> a.doubleValue() + b.doubleValue();
            case SUBTRACT -> a.doubleValue() - b.doubleValue();
            case MULTIPLY -> a.doubleValue() * b.doubleValue();
            case DIVIDE -> a.doubleValue() / b.doubleValue();
            case MODULO -> a.doubleValue() % b.doubleValue();
            default -> 0.0;
        };
        // 정수형이면 Integer 반환, 아니면 Double 반환
        T t = (T) (Double.valueOf(result) == result ? Integer.valueOf((int) result) : Double.valueOf(result));
        return t;
    }


    public Double releaseArithmeticCalculator(T a, T b, Operator oper){
        return  arithmeticCalculate(a, b, oper);
    }
}
