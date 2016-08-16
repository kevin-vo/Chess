package Chess;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GameDisplay extends Container {
    /** Dimensions of the window. */
    public static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 800;

    /** Size of the board inside the window. */
    public static final int BOARD_SIZE = 464;

    /** Size of the piece inside the board. */
    public static final int PIECE_SIZE = BOARD_SIZE / 8;
    
   
    GameDisplay(Game game) {
        _game = game;
    }
    
    /** Return an Image read from the resource named NAME. */
    private Image getImage(String name) {
        System.out.println("test");
        InputStream in =
            getClass().getResourceAsStream("/Chess/resources/" + name);
        try {
            return ImageIO.read(in);
        } catch (IOException excp) {
            return null;
        }
    }
    
    private void paintBoard(Graphics2D g, int x, int y) {
        g.drawImage(getImage("board.png"), x, y, BOARD_SIZE, BOARD_SIZE, null);
    }

    synchronized void paintComponent(Graphics2D g) {
        //paintBoard(g, 0, 0);
        g.setBackground(Color.black);
    }
    
    private Game _game;
}
