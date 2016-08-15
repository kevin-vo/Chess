package Chess;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {

    @Test
    public void simplePawnTest() {
       Pawn p = new Pawn("white", 6, 0);
       Pawn p2 = new Pawn("black", 2, 2);
       boolean result = p.isLegal(5, 1);
       assertFalse(result);
       result = p.isLegal(5, 0);
       assertTrue(result);
       result = p.isLegal(4, 0);
       assertTrue(result);
       
       result = p2.isLegal(1, 2);
       assertFalse(result);
       
       result = p2.isLegal(3, 2);
       assertTrue(result);
    }
    
    @Test
    public void simpleBishopTest() {
        Bishop b = new Bishop("white", 4, 4);
        assertFalse(b.isLegal(4,  4));
        assertTrue(b.isLegal(7,  7));
        assertTrue(b.isLegal(3, 5));
        assertFalse(b.isLegal(8, 8));
        assertTrue(b.isLegal(7,  1));
        assertFalse(b.isLegal(4,  5));
    }
    
    @Test
    public void simpleRookTest() {
        Rook r = new Rook("black", 3, 3);
        assertFalse(r.isLegal(4,  4));
        assertTrue(r.isLegal(2,  3));
        assertTrue(r.isLegal(3, 7));
        assertTrue(r.isLegal(0, 3));
    }

    @Test
    public void simpleQueenTest() {
        Queen q = new Queen("black", 0, 1);
        assertTrue(q.isLegal(0, 0));
        assertTrue(q.isLegal(0, 7));
        assertTrue(q.isLegal(1, 0));
        assertTrue(q.isLegal(7, 1));
        assertTrue(q.isLegal(6, 7));
        assertFalse(q.isLegal(0, 1));
    }
    
    @Test
    public void simpleKingTest() {
        King k = new King("white", 2, 2);
        assertTrue(k.isLegal(1, 1));
        assertTrue(k.isLegal(2, 1));
        assertTrue(k.isLegal(3, 3));
        assertFalse(k.isLegal(0, 0));
        assertFalse(k.isLegal(0, 2));
    }
    
    @Test
    public void simpleKnightTest() {
        Knight k = new Knight("white", 7, 1);
        assertTrue(k.isLegal(5, 0));
        assertTrue(k.isLegal(5, 2));
        assertTrue(k.isLegal(6, 3));
        assertFalse(k.isLegal(5, 3));
    }
    
    @Test
    public void blockedPawnTest() {
        Board board = new Board();
        Pawn p = new Pawn("white", 5, 1);
        board.set(p, 5, 1);
        assertTrue(board.getPiece(6, 1).isBlocked(5, 1, board));
        assertFalse(board.getPiece(6, 2).isBlocked(5, 2, board));
    }
    
    @Test
    public void blockedBishopTest() {
        Board board = new Board();
        Bishop b = new Bishop("white", 4, 1);
        board.set(b, 4, 2);
        assertTrue(b.isBlocked(6, 0, board));
        assertFalse(b.isBlocked(5, 1, board));
        assertTrue(board.getPiece(7, 2).isBlocked(5, 0, board));
        assertFalse(b.isBlocked(2, 4, board));
    }
    
    @Test
    public void blockedRookTest() {
        Board board = new Board();
        Rook r = new Rook("black", 3, 0);
        board.set(r, 3, 0);
        assertTrue(r.isBlocked(1, 0, board));
        assertFalse(r.isBlocked(3, 7, board));
        assertFalse(r.isBlocked(6, 0, board));
        assertTrue(r.isBlocked(7, 0, board));
    }
    
    @Test
    public void blockedQueenTest() {
        Board board = new Board();
        Queen q = new Queen("black", 3, 2);
        board.set(q, 3, 2);
        assertTrue(q.isBlocked(1, 0, board));
        assertTrue(q.isBlocked(1, 2, board));
        assertFalse(q.isBlocked(2, 1, board));
        assertFalse(q.isBlocked(2, 2, board));
        
    }
    
    @Test
    public void randomTest() {
        Board board = new Board();
        board.makeMoveNaive(7, 3, 3, 1);
        assertTrue(board.getPiece(3, 1).isBlocked(0, 4, board));
        assertFalse(((King) board.getKing("black")).inCheck(board));
        
    }
    
    @Test
    public void movesTest() {
        Board board = new Board();
        //board.printBoard();
        assertTrue(board.makeMove(6, 3, 4, 3));
        //board.printBoard();
        assertFalse(board.makeMove(4, 3, 3, 3));
        assertTrue(board.makeMove(1, 0, 3, 0));
        //board.printBoard();
        assertTrue(board.makeMove(7, 3, 6, 3));
        board.setTurn();
        //System.out.println(board.getTurn());
        //board.printBoard();
        assertTrue(board.makeMove(6, 3, 3, 0));
        //board.printBoard();
//        System.out.printf("Queen is at %d, %d.\n", board.getPiece(3, 0).getRow(),
//                board.getPiece(3, 0).getCol());
        assertTrue(board.makeMove(1, 1, 2, 1));
        board.makeMove(7, 6, 5, 7);
        assertFalse(board.makeMove(2, 1, 4, 1));
        //board.printBoard();
        assertTrue(board.makeMove(2, 1, 3, 0));
        //board.printBoard();
        assertFalse(board.makeMove(6, 0, 5, 1));
        assertTrue(board.makeMove(6, 0, 4, 0));
        //board.printBoard();
        assertFalse(board.makeMove(3, 0, 4, 0));
    }
    
    @Test
    public void kingCheckTest() {
        Board board = new Board();
        King check = new King("white", 5, 0);
        King notCheck = new King("white", 5, 1);
        Empty empty = new Empty("empty", 1, 0);
        board.set(check, 5, 0);
        board.set(notCheck, 5, 1);
        board.set(empty, 1, 0);
        //board.printBoard();
        assertTrue(check.inCheck(board));
        assertFalse(notCheck.inCheck(board));
    }
    
    @Test
    public void boardCopyTest() {
        Board board = new Board();
        Board board2 = new Board();
        board2.copyBoard(board);
        //board.printBoard();
        
        board2.makeMove(6, 0, 4, 0);
        //board2.printBoard();
        
        Piece pawn1 = board.getPiece(6, 0);
        Piece pawn2 = board2.getPiece(4, 0);
        
        assertTrue(pawn1.getRow() != pawn2.getRow());
        
        Piece queen1 = board.getPiece(7, 4);
        Piece queen2 = board2.getPiece(7, 4);
        
        assertTrue(queen1.getValue() == queen2.getValue());
        //System.out.println(queen2.getValue());
        
        
        Board board3 = new Board();
        board3.makeMove(6, 1, 5, 1);
        //board3.printBoard();
        
        Board board4 = new Board();
        board4.copyBoard(board3);
        //board4.printBoard();
        
        
        
    }
    
   @Test
    public void doNotCheckOwnKingTest() {
        Board board = new Board();
        board.makeMoveNaive(7, 5, 6, 4);
        board.makeMoveNaive(0, 0, 2, 4);
        //board.printBoard();
        Board copy = new Board();
        copy.copyBoard(board);
        //copy.printBoard();
        assertFalse(board.makeMove(6, 4, 5, 3));
        //System.out.println(((King) board.getKing("white")).inCheck(board));
    }
   
   @Test
   public void moveOutOfCheckTest() {
       Board board = new Board();
       //board.printBoard();
       board.makeMoveNaive(7, 4, 5, 3);
       board.makeMoveNaive(0, 0, 2, 3);
       board.makeMoveNaive(7, 2, 5, 2);
       board.makeMoveNaive(1, 0, 2, 0);
       //board.printBoard();
       assertFalse(board.makeMove(6, 0, 4, 0));
       assertTrue(board.makeMove(5, 3, 5, 4));
       //board.printBoard();
       assertTrue(board.makeMove(2, 3, 2, 4));
       //board.printBoard();
       assertFalse(board.makeMove(7, 4, 7, 2));
       assertTrue(board.makeMove(5, 2, 3, 4));
       //board.printBoard();
   }
   
   @Test
   public void checkmateTest() {
       Board board = new Board();
       assertFalse(board.isCheckmate("black"));
       //board.printBoard();
       board.makeMove(6, 4, 4, 4);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(0, 1, 2, 0);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(7, 3, 3, 7);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(1, 1, 2, 1);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(7, 5, 4, 2);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(2, 0, 4, 1);
       //board.printBoard();
       assertFalse(board.isCheckmate("black"));
       
       board.makeMove(3, 7, 1, 5);
       //board.printBoard();
       assertTrue(board.isCheckmate("black"));
       
   }
   
   @Test
   public void castlingTest() {
       Board board = new Board();
       board.printBoard();
       board.makeMove(6, 1, 4, 1);
       board.makeMove(1, 6, 2, 6);
       board.makeMove(7, 2, 5, 0);
       board.makeMove(0, 5, 2, 7);
       board.makeMove(6, 2, 4, 2);
       board.makeMove(0, 6, 2, 5);
       board.makeMove(7, 3, 5, 1);
       board.makeMove(1, 4, 3, 4);
       board.makeMove(7, 1, 5, 2);
       assertTrue(board.makeMove(0, 4, 0, 6));
       assertFalse(board.makeMove(7, 4, 7, 6));
       assertTrue(board.makeMove(7, 4, 7, 2));
       board.printBoard();
   }
   
   @Test
   public void castlingTest2() {
       Board board = new Board();
       board.printBoard();
       board.makeMove(6, 1, 4, 1);
       board.makeMove(1, 6, 2, 6);
       board.makeMove(7, 2, 5, 0);
       board.makeMove(0, 5, 2, 7);
       board.makeMove(6, 2, 4, 2);
       board.makeMove(0, 6, 2, 5);
       board.makeMove(7, 3, 5, 1);
       board.makeMove(1, 4, 3, 4);
       board.makeMove(7, 1, 5, 2);
       board.printBoard();
   }
}
