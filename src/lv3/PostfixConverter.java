package lv3;

import java.util.Stack;

public class PostfixConverter extends OperatorPriority {
    // 중위 표현식을 후위 표현식으로 변환하는 메서드 (외부 인터페이스 역할)
    public String releasePostfix(String yaldCalculator) {
        return this.makePostifix(yaldCalculator); // private 메서드 호출
    }
    // 중위 표기법을 후위 표기법으로 변환하는 메서드
    private String makePostifix(String textField) {
        StringBuilder postfix = new StringBuilder(); // 변환된 후위 표기법 문자열
        Stack<Character> operatorStack = new Stack<>(); // 연산자를 저장할 스택

        for (int i = 0; i < textField.length(); i++) {
            char token = textField.charAt(i);

            if (Character.isDigit(token) || token == '.') { // 숫자 또는 소수점인지 확인

                while (i < textField.length()) {
                    char currentChar = textField.charAt(i);

                    if (Character.isDigit(currentChar)) { // 숫자인 경우
                        postfix.append(currentChar);
                    } else if (currentChar == '.') { // 소수점이나오는 경우
                        postfix.append(currentChar);
                    } else {
                        break; // 숫자 또는 유효한 소수점이 아닐 경우 종료
                    }
                    i++;
                }
                postfix.append(" "); // 숫자 구분을 위한 공백 추가
                i--; // for 루프에서 추가 증가를 방지
            }

            else if (token == '+' || token == '-' || token == '*' || token == '/' || token == '%') { // 연산자인 경우
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && releasPriority(operatorStack.peek()) >= releasPriority(token)) {
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
