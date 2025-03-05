package lv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Stack;
import java.awt.event.KeyEvent;

public class App extends JFrame implements ActionListener, KeyListener {
    // 연산자의 우선순위를 반환하는 메서드
    static int priority(char ch) {
        return switch (ch) {
            case '%' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }

    public String textField = ""; // 계산기의 입력 값을 저장하는 문자열
    JTextField jTextField = new JTextField(textField, 24); // 사용자 입력을 표시하는 텍스트 필드

    // 생성자: GUI 설정
    public App() {
        setGui();
    }

    // 버튼 클릭 이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand(); // 클릭된 버튼의 문자열 값 가져오기

        if (button.equals("AC")) { // 초기화 버튼
            textField = "";
            jTextField.setText(textField);
            return;
        }

        if (button.equals("=")) { // 결과 계산 버튼
            String yardesult = yaldCalcultate(textField); // 중위 표기식을 후위 표기식으로 변환
            textField = solution(yardesult); // 후위 표기식을 계산하여 결과 반환
            jTextField.setText(textField);
            textField = "";
        } else { // 숫자 및 연산자 입력
            textField = textField.concat(button);
            jTextField.setText(textField);
        }
    }

    // 키보드 입력 처리 (숫자 및 연산자 입력)
    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (Character.isDigit(keyChar) || "+-*/%()".indexOf(keyChar) != -1) {
            textField = textField.concat(String.valueOf(keyChar));
            jTextField.setText(textField);
        }
        e.consume(); // 기본 입력 차단
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    // 백스페이스 및 엔터 키 처리
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // 백스페이스 처리
            if (!textField.isEmpty()) {
                textField = textField.substring(0, textField.length() - 1);
                jTextField.setText(textField);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터 키 입력 시 계산 수행
            String yardesult = yaldCalcultate(textField);
            textField = solution(yardesult);
            jTextField.setText(textField);
            textField = "";
        }
        e.consume(); // 기본 입력 차단
    }

    // GUI 설정
    public void setGui() {
        setTitle("사칙 연산 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.YELLOW);
        contentPane.setLayout(new FlowLayout());
        setSize(300, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        // 버튼 생성 및 추가
        String[] buttons = {"(", ")", "%", "AC", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            panel.add(button);
        }
        contentPane.add(panel);
        contentPane.add(jTextField, BorderLayout.NORTH);
        jTextField.addKeyListener(this);
        setVisible(true);
    }

    // 후위 표기식 계산
    public String solution(String str) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        String[] tokens = str.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) { // 숫자인 경우
                stack.push(Integer.parseInt(token));
            } else { // 연산자인 경우
                int secondInt = stack.pop();
                int firstInt = stack.pop();
                stack.push(postFix(firstInt, secondInt, token.charAt(0)));
            }
        }
        return Integer.toString(stack.pop());
    }

    // 연산 수행
    public int postFix(int a, int b, char oper) {
        return switch (oper) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            default -> 0;
        };
    }

    // 중위 표기식을 후위 표기식으로 변환
    public String yaldCalcultate(String textField) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < textField.length(); i++) {
            char token = textField.charAt(i);
            if (Character.isDigit(token)) {
                while (i < textField.length() && Character.isDigit(textField.charAt(i))) {
                    postfix.append(textField.charAt(i++));
                }
                postfix.append(" ");
                i--;
            } else if ("+-*/%".indexOf(token) != -1) {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && priority(operatorStack.peek()) >= priority(token)) {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.push(token);
            } else if (token == '(') {
                operatorStack.push(token);
            } else if (token == ')') {
                while (operatorStack.peek() != '(') {
                    postfix.append(operatorStack.pop()).append(" ");
                }
                operatorStack.pop();
            }
        }
        while (!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop()).append(" ");
        }
        return postfix.toString();
    }

    public static void main(String[] args) {
        new App();
    }
}
