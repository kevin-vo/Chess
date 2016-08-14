package Chess;

/**
 * Represents a Bishop piece.
 * 
 * @author Kevin Vo
 *
 */
public class Bishop extends Piece{
    Bishop(String color, int r, int c) {
        super(color, r, c);
        _value = 3;
        _name = (color == "white") ? "B" : "b";
    }
    
    boolean isLegal(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || (getRow() == x && getCol() == y)) {
            return false;
        }
        if (diff(getRow(), x) == diff(getCol(), y)) {
            return true;
        } else {
            return false;
        }
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        } else {
            int slope = (y - getCol()) / (x - getRow());
            int start = getRow();
            int end = x;
            int b = y - slope * x;
            if (getRow() < x) {
                for (int i = start + 1; i <= end; i++) {
                    int j = slope * i + b;
                    if (board.getPiece(i, j).getColor().equals(this.getColor())) {
                        return true;
                    }
                    if (board.getPiece(i, j).getColor().equals(this.getOpposite())) {
                        return i != x;
                    }
                    
                }
            } else {
                for (int i = start - 1; i >= end; i--) {
                    int j = slope * i + b;
                    if (board.getPiece(i, j).getColor().equals(this.getColor())) {
                        return true;
                    }
                    if (board.getPiece(i, j).getColor().equals(this.getOpposite())) {
                        return i != x;
                    }
                }
            }
        }
        return false;
    }
    
    Bishop copy() {
        return new Bishop(getColor(), getRow(), getCol());
    }
    
}
