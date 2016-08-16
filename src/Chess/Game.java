package Chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles the playing of the chess game.
 * @author Kevin Vo
 *
 */
public class Game {
    /** Game constructor. */
    Game() {
        _players = new Player[2];
        _players[0] = new Player("human", "white");
        _players[1] = new Player("human", "black");
        _playerIndex = 0;
    }
    
    /** Executes play time. 
     * @throws IOException */
    void play() throws IOException {
        Board board = new Board();
        board.printBoard();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String move;
        while (!board.isGameOver()) {
            if (board.isCheckmate("white")) {
                System.out.println("Black wins!");
                break;
            }
            if (board.isCheckmate("black")) {
                System.out.println("White wins!");
                break;
            }
            System.out.println("It is " + board.getTurn() + "'s turn to move.");
            System.out.print("> ");
            if (_players[_playerIndex].getColor().equals(board.getTurn())) {
                move = input.readLine();
                if (_players[_playerIndex].getType().equals("human")) {
                    if (move.equalsIgnoreCase("help")) {
                        System.out.println("Enter command in format 'from row# column# to row# to column#.");
                        System.out.println("Example: '6 3 4 3' to move pawn up two spaces.");
                    }
                    String[] split = move.split(" ");
                    if (split.length != 4) {
                        System.out.println("Invalid move. Type 'help' for help.");
                    } else {
                        int r1 = Integer.parseInt(split[0]);
                        int c1 = Integer.parseInt(split[1]);
                        int r2 = Integer.parseInt(split[2]);
                        int c2 = Integer.parseInt(split[3]);
                        if (!board.makeMove(r1, c1, r2, c2)) {
                            System.out.println("Invalid move.");
                        } else {
                            updateIndex();
                            board.printBoard();
                        }
                    }
                }
            } else {
                updateIndex();
                
            }
        }
    }
    
    /** Switches player index from 0 to 1 and vice versa. */
    private void updateIndex() {
        _playerIndex = (_playerIndex == 1) ? 0 : 1;
    }
    
    /** Array representing two players. */
    private Player[] _players;
    
    /** Turn counter. */
    private int _playerIndex;
}
