package lv3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class CalculatorUIEventHandler extends JFrame implements ActionListener, KeyListener {
    // 입력 필드 관련 변수
    String textField = ""; // 연산식을 저장하는 문자열
    JTextField jTextField = new JTextField(textField, 30); // 메인 입력 필드
    JTextField resultField = new JTextField(textField, 30); // 최근 계산 결과 표시 필드
    JTextArea resultsField = new JTextArea(5, 10); // 모든 결과 기록을 표시하는 텍스트 영역
    PostfixConverter postfixConverter = new PostfixConverter();
    ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    // 창의 제목
    String title = "사칙 연산 계산기";

    // 계산 결과 저장 리스트
    List<String> storeList = new ArrayList<>();

    // 결과값을 누적할 문자열
    String storeListor = "";


    // 버튼 클릭 이벤트 처리
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand(); // 클릭된 버튼의 텍스트 가져오기
        switch (button) {
            case "AC" -> {
                textField = "";
                jTextField.setText(textField);
                storeListor = "";
                storeList = new ArrayList<>();
                resultsField.setText("");
                resultField.setText("");
            }
            case "=" -> {
                String yaldesult = postfixConverter.releasePostfix(textField); // 중위 → 후위 변환
                textField = expressionEvaluator.releaseExpressionEvaluator(yaldesult);// 후위 연산식 계산 결과 반환
                jTextField.setText(textField);

                if (!jTextField.getText().equals("입력이 필요합니다") && !jTextField.getText().equals("0으로 나눌 수 없습니다")) {
                    storeList.add(textField); // 계산 결과 저장
                    resultField.setText(storeList.get(storeList.size() - 1)); // 최근 결과 표시
                }

                textField = "";
            }
            case "결과값" -> {
                Collections.reverse(storeList);
                storeListor = String.join("\n", storeList);
                resultsField.setText(storeListor);
                Collections.reverse(storeList);
            }
            case "최근 결과 삭제" -> {
                if (!storeList.isEmpty()) {
                    storeList.remove(storeList.size() - 1);//최근값
//                    storeList.remove(0);//맨처음 데이터 삭제
//                storeListor=storeList.stream().reduce((a,b)->a+"\n"+b).orElse("");//스트림 람다 적용
                    Collections.reverse(storeList);//리스트 역방향
                    storeListor = String.join("\n", storeList);
                    resultsField.setText(storeListor);
                    Collections.reverse(storeList);//리스트 정방향
                }
            }
            case "C" -> {
                if (!textField.isEmpty()) {
                    textField = textField.substring(0, textField.length() - 1);
                    jTextField.setText(textField);
                }
            }
            case "합계" -> {
                if (!storeList.isEmpty()) {
                    double sumList = storeList.stream().mapToDouble(Double::parseDouble).sum();
                    if (sumList==(int)sumList){
                        resultsField.setText(Integer.toString((int)sumList));
                    }else {
                        resultsField.setText(Double.toString(sumList));
                    }


                }
            }
            case "평균" -> {
                if (!storeList.isEmpty()) {
                    double avgList = storeList.stream().mapToDouble(Double::parseDouble).average().getAsDouble();
                    if (avgList==(int)avgList){
                        resultsField.setText(Integer.toString((int)avgList));
                    }else {
                        resultsField.setText(Double.toString(avgList));
                    }
                }
            }
            case "최근값" -> {
                if (!storeList.isEmpty()) {
                    textField = storeList.get(storeList.size() - 1);
                    jTextField.setText(textField);
                }
            }
            default -> {
                textField = textField.concat(button);
                jTextField.setText(textField);
            }
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
            String yaldesult = postfixConverter.releasePostfix(textField); // 중위 → 후위 변환
            textField = expressionEvaluator.releaseExpressionEvaluator(yaldesult);// 후위 연산식 계산 결과 반환
            jTextField.setText(textField);
            textField = "";
        }
        e.consume(); // 기본 입력 차단
    }
}
