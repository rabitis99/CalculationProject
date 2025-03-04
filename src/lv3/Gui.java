package lv3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

// GUI 클래스는 JFrame을 확장하고, ActionListener 및 KeyListener 인터페이스를 구현
public class Gui extends JFrame implements ActionListener, KeyListener {

    // 입력 필드 관련 변수
    private String textField = ""; // 연산식을 저장하는 문자열
    private JTextField jTextField = new JTextField(textField, 29); // 메인 입력 필드
    private JTextField resultField = new JTextField(textField, 3); // 최근 계산 결과 표시 필드
    private JTextArea resultsField = new JTextArea(10, 32); // 모든 결과 기록을 표시하는 텍스트 영역

    private  Calculator calulator = new Calculator(); // 계산기 로직을 수행하는 Calculator 클래스의 인스턴스

    // 창의 제목
    private String title = "사칙 연산 계산기";

    // 계산 결과 저장 리스트
    private List<String> storeList = new ArrayList<>();

    // 컨텐트 패널 선언
    private Container contentPane = getContentPane();

    // 결과값을 누적할 문자열
    private String storeListor = "";

    // 버튼 클릭 이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand(); // 클릭된 버튼의 텍스트 가져오기

        if (button.equals("AC")) { // 전체 지우기
            textField = "";
            jTextField.setText(textField);
        }
        else if (button.equals("=")) { // 계산 실행
            String yardesult = calulator.releaseYaldCalculator(textField); // 중위 → 후위 변환
            textField = calulator.releaseSolution(yardesult);// 후위 연산식 계산 결과 반환
            jTextField.setText(textField);
            if (jTextField.getText().equals("입력이 필요합니다")||jTextField.getText().equals("0으로 나눌 수 없습니다")){
                textField = "";
            }
            else{
                storeList.add(textField); // 계산 결과 저장
                resultField.setText(storeList.get(storeList.size() - 1)); // 최근 결과 표시
                textField = "";
            }
        }
        else if (button.equals("결과값")) { // 저장된 결과값 출력
            storeListor = ""; // 기존 값 초기화
            for (String result : storeList) {
                storeListor += result + "\n"; // 개행 추가
            }
            resultsField.setText(storeListor);
        }
        else if (button.equals("최근 결과 삭제")) { // 마지막 결과 삭제
            if (!storeList.isEmpty()) {
                storeList.remove(storeList.size() - 1);
                storeListor = "";
                for (String result : storeList) {
                    storeListor += result + "\n";
                }
                resultsField.setText(storeListor);
            }
        }
        else if (button.equals("C")) { // 한 글자 지우기
            if (!textField.isEmpty()) {
                textField = textField.substring(0, textField.length() - 1);
                jTextField.setText(textField);
            }
        }
        else { // 숫자 및 연산자 입력
            textField = textField.concat(button);
            jTextField.setText(textField);
        }
    }

    // 키 입력 이벤트 처리 (타이핑)
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
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // 백스페이스 처리
            if (!textField.isEmpty()) {
                textField = textField.substring(0, textField.length() - 1);
                jTextField.setText(textField);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) { // 엔터 입력 시 계산 실행
            String yardesult = calulator.releaseYaldCalculator(textField);
            textField = calulator.releaseSolution(yardesult);
            jTextField.setText(textField);
            textField = "";
        }
        e.consume(); // 기본 입력 차단
    }

    // GUI 초기 설정
    public void setGui() {
        setTitle("사칙 연산 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane.setBackground(Color.YELLOW);
        contentPane.setLayout(new FlowLayout());
        setSize(500, 500);

        contentPane.setFocusable(true);
        contentPane.requestFocus();

        // 버튼을 담을 패널
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        // 버튼 목록
        String[] buttons = {"(", ")", "%", "AC", "C",
                "7", "8", "9", "/", "결과값",
                "4", "5", "6", "*", "최근 결과 삭제",
                "1", "2", "3", "-", "=",
                ".", "0", " ", "+"};

        int row = 0, col = 0;
        for (String text : buttons) {
            JButton button = new JButton(text);
            GridBagConstraints buttonGBC = (GridBagConstraints) gridBagConstraints.clone();

            if (text.equals("=")) {
                buttonGBC.gridheight = 2; // "=" 버튼은 세로로 2칸 차지
            } else {
                buttonGBC.gridheight = 1;
            }

            buttonGBC.gridx = col;
            buttonGBC.gridy = row;
            button.addActionListener(this);
            panel.add(button, buttonGBC);

            col++;
            if (col >= 5) { // 한 줄에 5개 버튼 배치
                col = 0;
                row++;
            }
        }

        setVisible(true);
        jTextField.addKeyListener(this);
        jTextField.requestFocusInWindow();

        contentPane.add(jTextField, BorderLayout.SOUTH);
        contentPane.add(resultField, BorderLayout.SOUTH);
        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(resultsField, BorderLayout.NORTH);
        contentPane.setVisible(true);
    }

    // 창 제목 설정 메서드 오버라이드
    public void setTitle(String title) {
        this.title = title;
        super.setTitle(this.title);
    }
}
