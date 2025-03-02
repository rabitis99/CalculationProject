package lv2;

import java.util.Stack;

public class Calulator<T> {
    private static int priority(char ch) {
        return switch (ch) {
            case '%' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    public String solution(String str) {

        int firstInt;
        int secondInt;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);//음수끝
        String[] tokens = str.split(" ");

        for(String token : tokens) {
            // 연산식이면
            if(token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            }
            else {
                secondInt = stack.pop();
                firstInt = stack.pop();
                stack.push(postFix(firstInt, secondInt, token.charAt(0)));
            }
        }
        int res = stack.pop();
        String result=Integer.toString(res);
        return result;

    }
    private int postFix(int a, int b, char oper) {

        if(oper == '+') {
            return a+b;
        }else if(oper == '-'){
            return a-b;
        }else if(oper == '*'){
            return a*b;
        }else if(oper == '/'){
            return a/b;
        }else if(oper == '%'){
            return a%b;
        }else {
            return 0;
        }
    }
    public String yaldCalcultate(String textField) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < textField.length(); i++) {
            char token = textField.charAt(i);
            if (Character.isDigit(token)) {
                while (i < textField.length() && Character.isDigit(textField.charAt(i))) {
                    postfix.append(textField.charAt(i));
                    i++;//chatGpt이용
                }
                postfix.append(" "); // 숫자 구분을 위해 공백 추가
                i--; // for 루프에서 추가 증가를 방지
            }
            else if (token == '+' || token == '-' || token == '*' || token == '/'|| token == '%') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && priority(operatorStack.peek())>=priority(token) ) {
                    postfix.append(operatorStack.pop());
                    postfix.append(" ");
                }
                operatorStack.push(token);
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop());
                    postfix.append(" ");
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");// 이게 왜 될까?
        }
        return postfix.toString();
    }
}
