public class Dice {
    private int value;

    public Dice() {
        this.value = 0;
    }

    public int getValue() {
        return this.value;
    }

    public void roll() {
        this.value = (int) (Math.random() * 6) + 1;
    }
}
