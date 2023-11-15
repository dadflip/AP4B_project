public class ListOfPlayer {
    private Player[] list;
    private int size;

    public ListOfPlayer() {
        this.size = 0;
        this.list = new Player[6];
    }

    public int getSize() {
        return this.size;
    }

    public Player getPlayer(int index) {
        return this.list[index];
    }

    public void addPlayer(Player player) {
        this.list[this.size] = player;
        this.size++;
    }

    public void reset() {
        for (int i = 0; i < this.size; i++) {
            this.list[i].reset();
        }
    }

    public void resetScore() {
        for (int i = 0; i < this.size; i++) {
            this.list[i].resetScore();
        }
    }

    public void resetTurn() {
        for (int i = 0; i < this.size; i++) {
            this.list[i].resetTurn();
        }
    }

    public void addScore(int index, int score) {
        this.list[index].addScore(score);
    }

    public void addTurn(int index) {
        this.list[index].addTurn();
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < this.size; i++) {
            result += this.list[i].toString() + "\n";
        }
        return result;
    }
}
