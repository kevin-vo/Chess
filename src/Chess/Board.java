package Chess;

/**
 * Represents the board of chess.
 * 
 * @author Kevin Vo
 *
 */
public class Board {
    /** Initializes a board. */
    Board() {
        initialize();
        _turn = "white";
    }

    /** Sets up the initial pieces on the board. */
    void initialize() {
        Piece BR1, BKN1, BB1, BK, BQ, BB2, BKN2, BR2,
              BP1, BP2, BP3, BP4, BP5, BP6, BP7, BP8,
              WP1, WP2, WP3, WP4, WP5, WP6, WP7, WP8,
              WR1, WKN1, WB1, WK, WQ, WB2, WKN2, WR2, EMP;
        
        BR1 = new Rook("black", 0, 0);
        BKN1 = new Knight("black", 0, 1);
        BB1 = new Bishop("black", 0, 2);
        BK = new King("black", 0, 4);
        BQ = new Queen("black", 0, 3);
        BB2 = new Bishop("black", 0, 5);
        BKN2 = new Knight("black", 0, 6);
        BR2 = new Rook("black", 0, 7);
        BP1 = new Pawn("black", 1, 0);
        BP2 = new Pawn("black", 1, 1);
        BP3 = new Pawn("black", 1, 2);
        BP4 = new Pawn("black", 1, 3);
        BP5 = new Pawn("black", 1, 4);
        BP6 = new Pawn("black", 1, 5);
        BP7 = new Pawn("black", 1, 6);
        BP8 = new Pawn("black", 1, 7);
        WP1 = new Pawn("white", 6, 0);
        WP2 = new Pawn("white", 6, 1);
        WP3 = new Pawn("white", 6, 2);
        WP4 = new Pawn("white", 6, 3);
        WP5 = new Pawn("white", 6, 4);
        WP6 = new Pawn("white", 6, 5);
        WP7 = new Pawn("white", 6, 6);
        WP8 = new Pawn("white", 6, 7);
        WR1 = new Rook("white", 7, 0);
        WKN1 = new Knight("white", 7, 1);
        WB1 = new Bishop("white", 7, 2);
        WK = new King("white", 7, 4);
        WQ = new Queen("white", 7, 3);
        WB2 = new Bishop("white", 7, 5);
        WKN2 = new Knight("white", 7, 6);
        WR2 = new Rook("white", 7, 7);
        EMP = new Empty("empty", 0, 0);
        Piece[][] start = {{BR1, BKN1, BB1, BQ, BK, BB2, BKN2, BR2},
                           {BP1, BP2, BP3, BP4, BP5, BP6, BP7, BP8},
                           {EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
                           {EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
                           {EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
                           {EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
                           {WP1, WP2, WP3, WP4, WP5, WP6, WP7, WP8},
                           {WR1, WKN1, WB1, WQ, WK, WB2, WKN2, WR2}
                          };
        _board = start;
        _whiteKing = WK;
        _blackKing = BK;
    }
    
    /** Returns the array of the chess board. */
    Piece[][] getBoard() {
        return _board;
    }
    
    /** Copies contents of BOARD to this.*/
    void copyBoard(Board board) {
        _turn = board.getTurn();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece copy = board.getPiece(r, c).copy();
                if (copy.getName().equals("K")) {
                    this._whiteKing = copy;
                }
                if (copy.getName().equals("k")) {
                    this._blackKing = copy;
                }
                this.set(copy, r, c);
            }
        }
    }
    
    /** Sets piece P at r, c. */
    void set(Piece p, int r, int c) {
        _board[r][c] = p;
        p.updatePosition(r, c);
    }
    
    /** Attempts to make a move from piece at r1, c1 to r2, c2.
     *  Returns True if successful and false otherwise.
     */
    boolean makeMove(int r1, int c1, int r2, int c2) {
        if (!getPiece(r1, c1).getColor().equals(_turn)) {
            return false;
        }
        if (isValid(r1, c1, r2, c2)) {
            set(getPiece(r1, c1), r2, c2);
            set(new Empty("empty", r1, c1), r1, c1);
            setTurn();
            return true;
        }
        return false;
    }
    
    /** Makes a move regardless of whether it is a valid move or not. 
     *  Ignores the turn. Does not change the turn. */
    void makeMoveNaive(int r1, int c1, int r2, int c2) {
        set(getPiece(r1, c1), r2, c2);
        set(new Empty("empty", r1, c1), r1, c1);
    }
    
    /** Determines whether move is a valid move. Takes into account
     *  blocked pieces. */
    boolean isValid(int r1, int c1, int r2, int c2) {
        Piece p = getPiece(r1, c1);
        String pn = p.getName();
        boolean step1; //valid move
        boolean step2; //does not check own king
        if (pn.equals("p") || pn.equals("P")) {
            Pawn pawn = new Pawn(p.getColor(), r1, c1);
            step1 = pawn.isLegal(r2, c2, this) && !p.isBlocked(r2, c2, this);
            if (!step1) {
                return false;
            } else {
                Board copy = new Board();
                copy.copyBoard(this);
                String turn = copy.getTurn();
                copy.makeMoveNaive(r1, c1, r2, c2);
                step2 = !((King) copy.getKing(turn)).inCheck(copy);
                return step1 && step2;

            }
        } else {
            step1 = p.isLegal(r2, c2) && !p.isBlocked(r2, c2, this);
            if (!step1) {
                return false;
            } else {
                Board copy = new Board();
                copy.copyBoard(this);
                String turn = copy.getTurn();
                copy.makeMoveNaive(r1, c1, r2, c2);
                step2 = !((King) copy.getKing(turn)).inCheck(copy);
                return step1 && step2;
            }
        }
    }
    
    /** Returns true if COLOR King is in checkmate. */
    boolean isCheckmate(String color) {
        for (int r1 = 0; r1 < 8; r1++) {
            for (int c1 = 0; c1 < 8; c1++) {
                if (!getPiece(r1, c1).getColor().equals(color)) {
                    continue;
                }
                for (int r2 = 0; r2 < 8; r2++) {
                    for (int c2 = 0; c2 < 8; c2++) {
                        if (isValid(r1, c1, r2, c2)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    /** Gets piece at r, c. */
    Piece getPiece(int r, int c) {
        return _board[r][c];
    }
    
    /** Gets the turn at the moment. */
    String getTurn() {
        return _turn;
    }
    
    /** Sets opposing turn. */
    void setTurn() {
        if (_turn == "white") {
            _turn = "black";
        } else {
            _turn = "white";
        }
    }
    
    /** Prints the current board. */
    void printBoard() {
        System.out.println("  0 1 2 3 4 5 6 7 ");
        for (int counter = 0; counter < 8; counter++) {
            System.out.printf("%d ", counter);
            for (int i = 0; i < 8; i++) {
                System.out.print(getPiece(counter, i).getName() + " ");
            }
            System.out.println();
        }
        System.out.println("__________________");
        
    }
    
    /** Gets the king of COLOR. */
    Piece getKing(String color) {
        if (color.equals("white")) {
            return _whiteKing;
        } else if (color.equals("black")) {
            return _blackKing;
        }
        return null;
    }
    
    /** Gets the king in the side of the current turn. */
    Piece getKing() {
        return getKing(getTurn());
    }

    /** Data structure for board. */
    private Piece[][] _board;
    
    /** Represents whether it is white's turn to move or black's. */
    private String _turn;
    
    /** Represents the black king. */
    private Piece _blackKing;
    
    /** Represents the white king. */
    private Piece _whiteKing;
}
