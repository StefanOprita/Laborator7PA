package Board;

public class Token {
    private int first;
    private int second;
    private int value;

    public Token(int first, int second, int value) {
        this.first = first;
        this.second = second;
        this.value = value;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
