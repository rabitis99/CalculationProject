package lv3;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// GUI 클래스는 JFrame을 확장하고, ActionListener 및 KeyListener 인터페이스를 구현
public class CalculatorUI extends CalculatorUIEventHandler {

    public CalculatorUI(){
        setCalculatorUI();
    }

    // GUI 초기 설정
    private void setCalculatorUI() {
        // 컨텐트 패널 선언
        Container contentPane = getContentPane();
        setTitle("사칙 연산 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout());
        contentPane.setFocusable(true);
        contentPane.requestFocus();

        // 입력 필드 패널 생성

        jTextField.setEditable(false); // 편집 불가능
        jTextField.addKeyListener(this);
        jTextField.setHorizontalAlignment(JTextField.RIGHT);
        jTextField.setFont(new Font("Times", Font.BOLD, 50));
        jTextField.setBorder(new LineBorder(Color.gray, 0));
        contentPane.add(jTextField,BorderLayout.NORTH);


        resultField.setEditable(false);
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setFont(new Font("Times", Font.BOLD, 50));
        contentPane.add(jTextField,BorderLayout.NORTH);

        // 결과 표시 영역 설정
        resultsField.setFont(new Font("Times", Font.BOLD, 30)); // JTextArea 폰트 설정
        resultsField.setEditable(true);
        resultsField.setLineWrap(true);
        resultsField.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultsField);
        contentPane.add(scrollPane, BorderLayout.SOUTH);

        // 버튼을 담을 패널
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);

        // 버튼 목록
        String[] buttons = {"(", ")", "%", "AC", "C",
                "7", "8", "9", "/", "결과값",
                "4", "5", "6", "*", "최근 결과 삭제",
                "1", "2", "3", "-", "=",
                ".", "0", " ", "+",
                "", "합계", "", "평균", "", "최근값"};

        int row = 0, col = 0;
        for (String text : buttons) {
            JButton button = new JButton(text);
            GridBagConstraints buttonGBC = (GridBagConstraints) gridBagConstraints.clone();

            if (text.equals("=")) {
                buttonGBC.gridheight = 2; // "=" 버튼은 세로로 2칸 차지
            } else {
                buttonGBC.gridheight = 1;
            }
            if (text.equals("합계") || text.equals("평균")) {
                buttonGBC.gridwidth = 2;
            } else {
                buttonGBC.gridwidth = 1;
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

        contentPane.add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // 창 제목 설정 메서드 오버라이드
    public void setTitle(String title) {
        this.title = title;
        super.setTitle(this.title);
    }
}
