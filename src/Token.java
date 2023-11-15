public class Token {
    private int value;

    public Token() {
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
    }
}
