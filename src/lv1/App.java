package lv1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App extends JFrame implements ActionListener {
    //생성자-매소드
    public App() {
        setGui();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();// getActionCommand() : 이벤트를 처리한 컴포넌트(버튼)의 타이틀(문자열)을 가져오는 메서드

    }
    public void setGui(){
        setTitle("사칙 연산 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 프레임 종료버튼 사용 시 응용 프로그램도 종료시키는 방법
        Container contentPane = getContentPane(); // 컨텐트팬 선언
        contentPane.setBackground(Color.YELLOW);// 배경색 설정
        contentPane.setLayout(new FlowLayout());// 레이아웃 설정
        setSize(300, 400);//프레임 크기조정

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
    }


    public static void main(String[] args) {
        new App();

    }
}