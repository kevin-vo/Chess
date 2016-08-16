package Chess;

/**
 * Where human inputs and the AI element is implemented.
 * @author Kevin Vo
 *
 */
public class Player {
    /** Creates a player of type HUMAN or AI with side BLACK or WHITE. */
    Player(String type, String side) {
        _type = type;
        _side = side;
    }
    
    /** Gets the player type. */
    String getType() {
        return _type;
    }
    
    /** Gets the player side. */
    String getColor() {
        return _side;
    }

    /** Player type. */
    private String _type;
    
    /** Player side. */
    private String _side;
}
