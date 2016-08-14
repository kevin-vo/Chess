package Chess;

/**
 * Represents a Knight piece.
 * 
 * @author Kevin Vo
 *
 */
public class Knight extends Piece {
    Knight(String color, int r, int c) {
        super(color, r, c);
        _value = 3;
        _name = (color == "white") ? "N" : "n";
    }
    
    boolean isLegal(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || (getRow() == x && getCol() == y)) {
            return false;
        }
        if (diff(getRow(), x) == 2) {
            return diff(getCol(), y) == 1;
        }
        if (diff(getCol(), y) == 2) {
            return diff(getRow(), x) == 1;
        }
        return false;
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        } else {
            return false;
        }
    }
    
    Knight copy() {
        return new Knight(getColor(), getRow(), getCol());
    }
    
}
