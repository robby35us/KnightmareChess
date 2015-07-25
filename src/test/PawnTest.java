package test;

import org.junit.Test;

import utility.PieceInfo;

import java.io.IOException;

import components.Board;

import definitions.Color;
import definitions.File;
import definitions.PieceType;
import definitions.Rank;
import game.*;



public class PawnTest {
	boolean showDisplay = false;
	
	@Test
	public void testPawnMoveAndCapture() {
		PieceInfo[] beginningState = new PieceInfo[6];
		beginningState[0] = new PieceInfo(PieceType.Pawn, Color.White, Rank.Two, File.A);
		beginningState[1] = new PieceInfo(PieceType.Pawn, Color.White, Rank.Two, File.B);
		beginningState[2] = new PieceInfo(PieceType.Pawn, Color.White, Rank.Two, File.H);
		beginningState[3] = new PieceInfo(PieceType.Pawn, Color.Black, Rank.Seven, File.A);
		beginningState[4] = new PieceInfo(PieceType.Pawn, Color.Black, Rank.Seven, File.G);
		beginningState[5] = new PieceInfo(PieceType.Pawn, Color.Black, Rank.Seven, File.H);
		Board board = TestUtility.makeBoard(beginningState);
		TestIO tio = new TestIO(board, showDisplay);
		tio.setMoveInput("h2 h4 g7 g5 h4 g5 h7 h6 g5 h6 a7 a5 b2 b4 a5 b4 a2 a3 b4 a3 q");
		try {
			Start.playGame(tio);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PieceInfo[] endState = new PieceInfo[2];
		endState[0] = new PieceInfo(PieceType.Pawn, Color.White, Rank.Six, File.H);
		endState[1] = new PieceInfo(PieceType.Pawn, Color.Black, Rank.Three, File.A);
		TestUtility.verifyBoardState(board, endState);
	}
}
