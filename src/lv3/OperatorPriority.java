package lv3;

public abstract class OperatorPriority {
    // 연산자의 우선순위를 반환하는 정적 메서드
    private int priority(char ch) {
        return switch (ch) {
            case '%' -> 3;       // 나머지 연산의 우선순위
            case '*', '/' -> 2;  // 곱셈과 나눗셈의 우선순위
            case '+', '-' -> 1;  // 덧셈과 뺄셈의 우선순위
            default -> -1;       // 기타 문자 (예: 숫자, 공백 등)
        };
    }
    public int releasPriority(char ch){
        return this.priority(ch);
    }
}
