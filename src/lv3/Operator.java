package lv3;

// 연산자를 Enum으로 정의하여 코드의 가독성을 높이고, 연산자 관련 기능을 캡슐화함
enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), MODULO('%');

    // 각 연산자에 해당하는 문자(symbol)를 저장하는 필드
    private final char symbol;

    // 생성자: Enum 객체가 생성될 때 symbol 값을 설정
    Operator(char symbol) {
        this.symbol = symbol;
    }

    // symbol 값을 반환하는 Getter 메서드
    public char getSymbol() {
        return symbol;
    }

    // 주어진 문자(char)가 해당하는 Operator Enum을 반환하는 메서드
    public static Operator fromChar(char ch) {
        // 모든 Operator 값을 순회하며 일치하는 symbol이 있는지 확인
        for (Operator operator : Operator.values()) {
            if (ch == operator.symbol) {
                return operator; // 일치하는 Operator 반환
            }
        }
        // 일치하는 Operator가 없을 경우 예외 발생
        throw new IllegalArgumentException("계산기에 존재하는 수식이 아닙니다.");
    }
}
