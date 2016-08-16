package Chess;

import java.awt.*;
import javax.swing.*;

public class GuiGame extends JFrame {
    GuiGame(Game game) {
        super();
        setTitle("pogChess");
        setSize(1280, 720);
        GameDisplay display = new GameDisplay(game);
        _game = game;
        _display = display;
        Label lb = new Label("Enter ID");
        add(lb);
        add(display);
        _display.repaint();
        
    }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        Game game = new Game();
        GuiGame gui = new GuiGame(game);
        gui.show();
    }
    
    private Game _game;
    
    private GameDisplay _display;
}
