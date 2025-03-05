package lv3;

import javax.swing.*;
import java.awt.*;

// GUI 클래스는 JFrame을 확장하고, ActionListener 및 KeyListener 인터페이스를 구현
public class CalculatorUI extends CalculatorUIEventHandler {

    // GUI 초기 설정
    public void setCalculatorUI() {
        // 컨텐트 패널 선언
        Container contentPane = getContentPane();
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
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);

        // 버튼 목록
        String[] buttons = {"(", ")", "%", "AC", "C",
                "7", "8", "9", "/", "결과값",
                "4", "5", "6", "*", "최근 결과 삭제",
                "1", "2", "3", "-", "=",
                ".", "0", " ", "+",
                "","합계","" ,"평균","","최근값"};

        int row = 0, col = 0;
        for (String text : buttons) {
            JButton button = new JButton(text);
            GridBagConstraints buttonGBC = (GridBagConstraints) gridBagConstraints.clone();

            if (text.equals("=")) {
                buttonGBC.gridheight = 2; // "=" 버튼은 세로로 2칸 차지
            } else {
                buttonGBC.gridheight = 1;
            }
            if ((text.equals("합계")||text.equals("평균")))
                buttonGBC.gridwidth=2;
            else {
                buttonGBC.gridwidth=1;
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
