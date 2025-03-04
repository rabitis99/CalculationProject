package lv2;

import java.util.Stack;

// 사칙연산을 수행하는 계산기 클래스
public class Calculator {

    // 연산자의 우선순위를 반환하는 정적 메서드
    private static int priority(char ch) {
        return switch (ch) {
            case '%' -> 3;       // 나머지 연산의 우선순위
            case '*', '/' -> 2;  // 곱셈과 나눗셈의 우선순위
            case '+', '-' -> 1;  // 덧셈과 뺄셈의 우선순위
            default -> -1;       // 기타 문자 (예: 숫자, 공백 등)
        };
    }

    // 사칙연산을 수행하는 메서드 (외부에서 호출할 수 있는 인터페이스 역할)
    public String releaseSolution(String solution) {
        return this.solution(solution); // private 메서드 호출
    }

    // 중위 표현식을 후위 표현식으로 변환하는 메서드 (외부 인터페이스 역할)
    public String releaseYaldCalculator(String yaldCalculator) {
        return this.yaldCalcultate(yaldCalculator); // private 메서드 호출
    }

    // 후위 표기법(Reverse Polish Notation, RPN) 계산 수행
    private String solution(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // 음수 연산 방지용 (0을 미리 넣어둠)

        String[] tokens = str.split(" "); // 공백을 기준으로 후위 표기법 토큰 분리

        for (String token : tokens) {
            if (token.matches("\\d+")) { // 숫자인 경우
                stack.push(Integer.parseInt(token));
            } else { // 연산자인 경우
                int secondInt = stack.pop();
                int firstInt = stack.pop();
                stack.push(postFix(firstInt, secondInt, token.charAt(0))); // 연산 결과를 스택에 다시 푸시
            }
        }
        return Integer.toString(stack.pop()); // 최종 결과 반환
    }

    // 연산자에 따른 사칙연산 수행
    private int postFix(int a, int b, char oper) {
        return switch (oper) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            default -> 0; // 잘못된 연산자는 기본값 0 반환
        };
    }

    // 중위 표기법을 후위 표기법으로 변환하는 메서드
    private String yaldCalcultate(String textField) {
        StringBuilder postfix = new StringBuilder(); // 변환된 후위 표기법 문자열
        Stack<Character> operatorStack = new Stack<>(); // 연산자를 저장할 스택

        for (int i = 0; i < textField.length(); i++) {
            char token = textField.charAt(i);

            if (Character.isDigit(token)) { // 숫자인 경우
                while (i < textField.length() && Character.isDigit(textField.charAt(i))) {
                    postfix.append(textField.charAt(i));
                    i++;
                }
                postfix.append(" "); // 숫자 구분을 위한 공백 추가
                i--; // for 루프에서 추가 증가를 방지
            }
            else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '%') { // 연산자인 경우
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && priority(operatorStack.peek()) >= priority(token)) {
                    postfix.append(operatorStack.pop()).append(" "); // 스택에서 연산자를 꺼내 후위 표기법에 추가
                }
                operatorStack.push(token); // 현재 연산자를 스택에 추가
            }
            else if (token == '(') { // 여는 괄호인 경우
                operatorStack.push(token);
            }
            else if (token == ')') { // 닫는 괄호인 경우
                while (operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop()).append(" "); // 여는 괄호 전까지 스택의 연산자를 후위 표기법에 추가
                }
                operatorStack.pop(); // 여는 괄호 '(' 제거
            }
        }

        // 스택에 남아 있는 연산자를 후위 표기법에 추가
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");
        }

        return postfix.toString(); // 후위 표기법 반환
    }
}
