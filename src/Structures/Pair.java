package Structures;

public class Pair<FirstType,SecondType> {
    private FirstType fist;
    private SecondType second;

    public Pair(FirstType fist, SecondType second) {
        this.fist = fist;
        this.second = second;
    }

    public FirstType getFist() {
        return fist;
    }

    public void setFist(FirstType fist) {
        this.fist = fist;
    }

    public SecondType getSecond() {
        return second;
    }

    public void setSecond(SecondType second) {
        this.second = second;
    }
}
