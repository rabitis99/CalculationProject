package lv2;

import java.util.Stack;
// 후위 표기법(Reverse Polish Notation, RPN) 계산 수행
public class Solution extends Calculator {
    private String solute(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 음수 연산 방지용 (0을 미리 넣어둠)

        String[] tokens = str.split(" "); // 공백을 기준으로 후위 표기법 토큰 분리

        for (String token : tokens) {
            if (token.matches("\\d+")) { // 숫자인 경우
                stack.push(Integer.parseInt(token));
            } else { // 연산자인 경우
                int secondInt = stack.pop();
                int firstInt = stack.pop();
                stack.push(releaseCalculation(firstInt, secondInt, token.charAt(0))); // 연산 결과를 스택에 다시 푸시
            }
        }
        return Integer.toString(stack.pop()); // 최종 결과 반환
    }
    // 사칙연산을 수행하는 메서드 (외부에서 호출할 수 있는 인터페이스 역할)
    public String releaseSolution(String solution) {
        return this.solute(solution); // private 메서드 호출
    }

}

