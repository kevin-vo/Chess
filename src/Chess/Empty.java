package Chess;

/**
 * Represents an empty slot in the board.
 * 
 * @author Kevin Vo
 *
 */
public class Empty extends Piece {
    /** Constructs empty piece at R, C. */
    Empty(String color, int r, int c) {
        super(color, r, c);
    }

}
