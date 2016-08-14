package Chess;

/**
 * Represents a pawn piece.
 * 
 * @author Kevin Vo
 *
 */
public class Pawn extends Piece {
    Pawn(String color, int r, int c) {
        super(color, r, c);
        _value = 1;
        _name = (color == "white") ? "P" : "p";
    }
    
    boolean isLegal(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        if (diff(getCol(), y) != 0) {
            return false;
        }
        if (getColor().equals("white")) {
            if (getRow() - x == 1) {
                return true;
            }
            if (getRow() == 6) {
                if (getRow() - x == 2) {
                    return true;
                }
            }
            
        } else {
            if (getRow() - x == -1) {
                return true;
            }
            if (getRow() == 1) {
                if (getRow() - x == -2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean isLegal(int x, int y, Board board) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return false;
        }
        if (diff(getCol(), y) > 1) {
            return false;
        }
        if (getColor().equals("white")) {
            if (getRow() - x == 1) {
                if ((diff(getCol(), y) == 0)) {
                    return true;
                } else {
                    if (board.getPiece(x, y).getColor().equals(this.getOpposite())) {
                        return true;
                    }
                }
            }
            if (getRow() == 6) {
                if (getRow() - x == 2) {
                    return diff(getCol(), y) == 0;
                }
            }
            
        } else {
            if (getRow() - x == -1) {
                if ((diff(getCol(), y) == 0)) {
                    return true;
                } else {
                    if (!board.getPiece(x, y).getColor().equals(this.getColor())) {
                        return true;
                    }
                }
            }
            if (getRow() == 1) {
                if (getRow() - x == -2) {
                    return diff(getCol(), y) == 0;
                }
            }
        }
        return false;
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        } else if (getCol() == y &&
                board.getPiece(x, y).getColor().equals(this.getOpposite())) {
            return true;
        } else {
            return false;
        }
    }
    
    Pawn copy() {
        return new Pawn(getColor(), getRow(), getCol());
    }
    
}
