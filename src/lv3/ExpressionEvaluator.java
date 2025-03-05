package lv3;

import java.util.EmptyStackException;
import java.util.Stack;

// 후위 표기법(Reverse Polish Notation, RPN) 계산 수행
public class ExpressionEvaluator extends ArithmeticCalculator {
    private String evaluate(String str) {
        Stack<Double> stack = new Stack<>();
        stack.push(0.0); // 음수 연산 방지용 (0을 미리 넣어둠)

        String[] tokens = str.split(" "); // 공백을 기준으로 후위 표기법 토큰 분리

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) { // 공부
                stack.push(Double.parseDouble(token));
            }
            else { // 연산자인 경우
                Double firstInt;
                Double secondInt;
                try {
                    secondInt = stack.pop();
                    firstInt = stack.pop();
                    stack.push(releaseArithmeticCalculator(firstInt, secondInt, Operator.fromChar(token.charAt(0)))); // 연산 결과를 스택에 다시 푸시
                } catch (EmptyStackException e) {
                    return "입력이 필요합니다";
                } catch (ArithmeticException e) {
                    return "0으로 나눌 수 없습니다";
                }
            }
            double res= stack.pop();
            if ()
        }
        return Double.toString(stack.pop()); // 최종 결과 반환
    }
    // 사칙연산을 수행하는 메서드 (외부에서 호출할 수 있는 인터페이스 역할)
    public String releaseExpressionEvaluator (String solution) {
        return this.evaluate(solution); // private 메서드 호출
    }

}

