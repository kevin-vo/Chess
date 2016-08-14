package Chess;

/**
 * Represents a King piece.
 * 
 * @author Kevin Vo
 *
 */
public class King extends Piece{
    King(String color, int r, int c) {
        super(color, r, c);
        _value = Integer.MAX_VALUE;
        _name = (color == "white") ? "K" : "k";
    }
    
    boolean isLegal(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || (getRow() == x && getCol() == y)) {
            return false;
        }
        return (diff(getRow(), x) <= 1 && diff(getCol(), y) <= 1);
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        } else {
            return false;
        }
    }
    
    boolean inCheck(Board board) {
        String kColor = getColor();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = board.getPiece(r, c);
                if (p.getColor().equals(kColor) || p.getColor().equals("empty")) {
                    continue;
                } else {
                    if (board.isValid(r, c, getRow(), getCol())) {
                        return true;
                    }
                }
            }
        }
        return false;
        
    }
    
    King copy() {
        return new King(getColor(), getRow(), getCol());
    }
    
}
