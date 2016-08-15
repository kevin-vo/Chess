package Chess;

import static org.junit.Assert.*;

/**
 * Represents the super class piece.
 * 
 * @author Kevin Vo
 *
 */
public class Piece {
    /** Constructs piece with initial position (R, C). */
    Piece(String color, int r, int c) {
        updatePosition(r, c);
        assertTrue(color.equals("white") || color.equals("black") || color.equals("empty"));
        _color = color;
        if (color.equals("white")) {
            _opposite = "black";
        }
        if (color.equals("black")) {
            _opposite = "white";
        }
    }

    /** Gets row position. */
    int getRow() {
        return R;
    }

    /** Gets column position. */
    int getCol() {
        return C;
    }

    /** Updates position to ROW and COLUMN. */
    void updatePosition(int row, int column) {
        R = row;
        C = column;
    }

    /** Determines if move to x, y is possible and within bounds of board. 
     *  Does not account for blocked pieces or captures!. */
    boolean isLegal(int x, int y) {
        return false;
    }
    
    /** Determines if move to x, y is blocked. */
    boolean isBlocked(int x, int y, Board board) {
        return false;
    }
    
    /** Gets the point value. */
    int getValue() {
        return _value;
    }
    
    /** Returns distance of two integers. */
    int diff(int x, int y) {
        return Math.abs(x - y);
    }
    
    /** Returns the color of piece. */
    String getColor() {
        return _color;
    }
    
    /** Returns the color on the other side of this piece. */
    String getOpposite() {
        return _opposite;
    }
    
    /** Returns the name of the piece. */
    String getName() {
        return _name;
    }
    
    /** Returns a copy of the piece. */
    Piece copy() {
        return new Piece(getColor(), getRow(), getCol());
    }
    
    /** Returns true if piece has moved. */
    boolean hasMoved() {
        return _hasMoved;
    }
    
    /** Updates _hasMoved to true if piece has moved. */
    void updateHasMoved() {
        _hasMoved = true;
    }
    
    private boolean _hasMoved;

    /** Row in board. */
    private int R;

    /** Column in board. */
    private int C;
    
    /** Relative point value of the piece. */
    protected int _value;
    
    /** What side the piece is in. */
    protected String _color;
    
    /** The opposite side of the piece. */
    protected String _opposite;
    
    /** Name of the piece. */
    protected String _name = ".";
    
}
