package gameboy.gamepack.racinggame.data.vo;

public class Position {

    private int position;

    public Position() { }

    public Position(int position) {
        validationPosition(position);
        this.position = position;
    }

    private void validationPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("0미만의 포지션 생성: " + position);
        }
    }

    public void add() {
        position += 1;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return this.getClass().getTypeName().hashCode() + position;
    }
}
