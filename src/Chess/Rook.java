package Chess;

/**
 * Represents a Rook piece.
 * 
 * @author Kevin Vo
 *
 */
public class Rook extends Piece{
    Rook(String color, int r, int c) {
        super(color, r, c);
        _value = 5;
        _name = (color == "white") ? "R" : "r";
    }
    
    boolean isLegal(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || (getRow() == x && getCol() == y)) {
            return false;
        }
        if (getRow() == x || getCol() == y) {
            return true;
        } else {
            return false;
        }
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        } else {
            if (getRow() - x == 0) {
                if (getCol() < y) {
                    for (int i = getCol() + 1; i <= y; i++) {
                        if (board.getPiece(x, i).getColor().equals(this.getColor())) {
                            return true;
                        }
                        if (board.getPiece(x, i).getColor().equals(this.getOpposite())) {
                            return i != y;
                        }
                        
                    }
                } else {
                    for (int i = getCol() - 1; i >= y; i--) {
                        if (board.getPiece(x, i).getColor().equals(this.getColor())) {
                            return true;
                        }
                        if (board.getPiece(x, i).getColor().equals(this.getOpposite())) {
                            return i != y;
                        }
                    }
                }
            } else {
                if (getRow() < x) {
                    for (int i = getRow() + 1; i <= x; i++) {
                        if (board.getPiece(i, y).getColor().equals(this.getColor())) {
                            return true;
                        }
                        if (board.getPiece(i, y).getColor().equals(this.getOpposite())) {
                            return i != x;
                        }
                        
                    }
                } else {
                    for (int i = getRow() - 1; i >= x; i--) {
                        if (board.getPiece(i, y).getColor().equals(this.getColor())) {
                            return true;
                        }
                        if (board.getPiece(i, y).getColor().equals(this.getOpposite())) {
                            return i != x;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    Rook copy() {
        return new Rook(getColor(), getRow(), getCol());
    }
    
    
}
