import java.util.Stack;

public class HanoiManager {

    private int moves;
    private int numDiscos;
    private int minMoves;
    private Stack<String> movesHistory;
    private Stack<String> towerA;
    private Stack<String> towerB;
    private Stack<String> towerC;

    public HanoiManager() {
        moves = 0;
        numDiscos = 0;
        movesHistory = new Stack<>();
        towerA = new Stack<>();
        towerB = new Stack<>();
        towerC = new Stack<>();
    }

    public int getMovesCount() {
        return moves;
    }

    public void setMovesCount(int moves) {
        this.moves = moves;
    }

    public int getDiscCount() {
        return numDiscos;
    }

    public void setDiscCount(int numDiscos) {
        this.numDiscos = numDiscos;
    }

    public int getMinMoves() {
        return minMoves;
    }

    public void setMinMoves() {
        this.minMoves = ((int) Math.pow(2, numDiscos)) - 1;
    }

    public Stack<String> getMovesStack() {
        return movesHistory;
    }

    public Stack<String> getTowerA() {
        return towerA;
    }

    public Stack<String> getTowerB() {
        return towerB;
    }

    public Stack<String> getTowerC() {
        return towerC;
    }

}
