package com.sintie.amoba;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BoardTest {

    private Board board;
    private Configuration mockConfig;

    @Before
    public void setUp() {
        // Mock Configuration
        mockConfig = Mockito.mock(Configuration.class);
        when(mockConfig.rows).thenReturn(5);
        when(mockConfig.cols).thenReturn(5);

        board = new Board(mockConfig);
    }

    @Test
    public void testInitBoard() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                assertTrue(board.isValidMove(row, col));
            }
        }
    }

    @Test
    public void testIsValidMoveAndPlaceMark() {
        assertTrue(board.isValidMove(0, 0));
        board.placeMark(0, 0, 'X');
        assertFalse(board.isValidMove(0, 0));
    }

    @Test
    public void testCheckWinHorizontal() {
        char symbol = 'X';
        board.placeMark(2, 1, symbol);
        board.placeMark(2, 2, symbol);
        board.placeMark(2, 3, symbol);

        assertTrue(board.checkWin(2, 2, symbol));
    }

    @Test
    public void testCheckWinVertical() {
        char symbol = 'O';
        board.placeMark(0, 0, symbol);
        board.placeMark(1, 0, symbol);
        board.placeMark(2, 0, symbol);

        assertTrue(board.checkWin(1, 0, symbol));
    }

    @Test
    public void testCheckWinDiagonal1() { // \
        char symbol = 'X';
        board.placeMark(0, 0, symbol);
        board.placeMark(1, 1, symbol);
        board.placeMark(2, 2, symbol);

        assertTrue(board.checkWin(1, 1, symbol));
    }

    @Test
    public void testCheckWinDiagonal2() { // /
        char symbol = 'O';
        board.placeMark(2, 0, symbol);
        board.placeMark(1, 1, symbol);
        board.placeMark(0, 2, symbol);

        assertTrue(board.checkWin(1, 1, symbol));
    }

    @Test
    public void testIsBoardFull() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                board.placeMark(row, col, 'X');
            }
        }
        assertTrue(board.isBoardFull());
    }

    @Test
    public void testBoardNotFullInitially() {
        assertFalse(board.isBoardFull());
    }

    @Test
    public void testPrintBoardOutput() {
        // Capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        board.printBoard();

        String output = outContent.toString();
        assertTrue(output.contains("|   |")); // empty cells
        assertTrue(output.contains("a"));     // column labels

        System.setOut(System.out); // reset System.out
    }

}
