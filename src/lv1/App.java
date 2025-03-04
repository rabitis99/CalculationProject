package lv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.Stack;
import java.awt.event.KeyEvent;

public class App extends JFrame implements ActionListener, KeyListener {
    //속성
    static int priority(char ch) {
        return switch (ch) {
            case '%' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> -1;
        };
    }
    public String textField ="";//초기에는 아무것도 보이지 않게 설정(초기 값 null 발생 예방)
    JTextField jTextField = new JTextField(textField,24);//초기값 null 발생 제한


    //생성자-매소드
    public App() {
        setGui();
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();// getActionCommand() : 이벤트를 처리한 컴포넌트(버튼)의 타이틀(문자열)을 가져오는 메서드
        if (button.equals("AC")){
            textField="";
            jTextField.setText(textField);
            return;
        }
        if (button.equals("=")) {
            String yardesult=yaldCalcultate(textField);
            textField=solution(yardesult);
            jTextField.setText(textField);
            textField="";
        }
        else {
            textField=textField.concat(button);
            jTextField.setText(textField);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        char keyChar = e.getKeyChar();

        if (Character.isDigit(keyChar) || "+-*/%()".indexOf(keyChar) != -1) {
            textField = textField.concat(String.valueOf(keyChar));
            jTextField.setText(textField);
        }
        e.consume(); // JTextField에 기본 입력이 발생하지 않도록 이벤트 차단-chatgpt

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (!textField.isEmpty()) {
                textField = textField.substring(0, textField.length() - 1);
                jTextField.setText(textField);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String yardesult = yaldCalcultate(textField);
            textField = solution(yardesult);
            jTextField.setText(textField);
            textField = "";

        }
        e.consume(); // JTextField에 기본 입력이 발생하지 않도록 이벤트 차단

    }

    public void setGui(){
        setTitle("사칙 연산 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 프레임 종료버튼 사용 시 응용 프로그램도 종료시키는 방법
        Container contentPane = getContentPane(); // 컨텐트팬 선언
        contentPane.setBackground(Color.YELLOW);// 배경색 설정
        contentPane.setLayout(new FlowLayout());// 레이아웃 설정
        setSize(300, 400);//프레임 크기조정
        contentPane.setFocusable(true);  // 컴포넌트가 포커스를 받을 수 있도록 설정
        contentPane.requestFocus();     // 컴포넌트에 포커스를 강제로 지정
        JPanel panel = new JPanel();// 버튼 패널 생성
        panel.setLayout(new GridLayout(5, 4, 5, 5));//패널 위치조정

        // 패널에 JButton값 생성하기-구글계산기(사칙연산 참조)
        String[] buttons ={"(",")","%","AC","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};//계산기에 넣을 순서
        for (String text:buttons){
            JButton button = new JButton(text);// text이름 으로 버튼생성
            button.addActionListener(this); //addActionLister확인
            panel.add(button);
        }
        contentPane.add(panel);// 페널 프레임 추가
        setVisible(true);//프레임 보이기
        jTextField.addKeyListener(this);//chatgpt사용


        contentPane.add(jTextField,BorderLayout.NORTH);
        jTextField.requestFocusInWindow();// 포커스 설정
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

    public int postFix(int a, int b, char oper) {

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

    public static void main(String[] args) {
        App app = new App();
    }
}
