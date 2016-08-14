package Chess;

/**
 * Represents a Queen piece.
 * 
 * @author Kevin Vo
 *
 */
public class Queen extends Piece{
    Queen(String color, int r, int c) {
        super(color, r, c);
        _value = 9;
        _name = (color == "white") ? "Q" : "q";
    }
    
    boolean isLegal(int x, int y) {
        Bishop copy = new Bishop(getColor(), getRow(), getCol());
        Rook copy2 = new Rook(getColor(), getRow(), getCol());
        return copy.isLegal(x, y) || copy2.isLegal(x, y);
    }
    
    boolean isBlocked(int x, int y, Board board) {
        if (board.getPiece(x, y).getColor().equals(this.getColor())) {
            return true;
        }
        if (x == getRow() || y == getCol()) {
            Rook r = new Rook(getColor(), getRow(), getCol());
            return r.isBlocked(x, y, board);
        } else {
            Bishop b = new Bishop(getColor(), getRow(), getCol());
            return b.isBlocked(x, y, board);
        }

    }
    
    Queen copy() {
        return new Queen(getColor(), getRow(), getCol());
    }
}
