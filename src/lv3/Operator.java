package lv3;

enum Operator {
    ADD('+'), SUBTRACT('-'), MULTIPLY('*'), DIVIDE('/'), MODULO('%');

    private final char symbol;

    Operator(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Operator fromChar(char ch){
        for(Operator operator: Operator.values()){
            if(ch == operator.symbol){
                return operator;
            }
        }throw new IllegalArgumentException("계산기에 존재하는 수식이 아닙니다.");
    }

}

