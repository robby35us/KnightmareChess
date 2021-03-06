package game;
import java.util.Iterator;
import components.*;
import definitions.*;

/*
 * This class holds a set of instruction to set up
 * the pieces on a standard chess board. 
 */
public class Setup {
	
	/*
	 * Takes the two given PlayerSets and places the contained pieces on the provided
	 * Board object. Assumes that each set contains the standard 16 chess pieces. Also,
	 * assumes the each set is organized by first row, from File A to File H, 
	 * and then the eight pawns.
	 */
	public static Board setupChessBoard(PlayerSet whiteSet, PlayerSet blackSet, Board board){
		placeWhitePieces(whiteSet, board);
		placeBlackPieces(blackSet, board);
		return board;
	}

	/*
	 * Sets the black pieces on the board
	 */
	private static void placeBlackPieces(PlayerSet blackSet, Board board) {
		Space currentSpace = board.getSpace(Rank.Eight, File.A);
		Iterator<Piece> it = blackSet.iterator();
		
		// set the "first" row(Rank 8) of black pieces from left to right
		currentSpace.changePiece(it.next());
		for(int i = 0; i < 7; i++){
			currentSpace = currentSpace.getSpaceRight();
			currentSpace.changePiece(it.next());
		}
		currentSpace = currentSpace.getSpaceBackward();
		
		// set the "second" row(Rank 7) of black pieces from right to left
		for(int i = 0; i < 8; i++){
			currentSpace.changePiece(it.next());
			currentSpace = currentSpace.getSpaceLeft();
		}	
	}

	/*
	 * Sets the white Pieces on the board
	 */
	private static void placeWhitePieces(PlayerSet whiteSet, Board board) {
		Space currentSpace = board.getSpace(Rank.One, File.A);
		Iterator<Piece> it = whiteSet.iterator();
		
		// set the "first" row (Rank 1) of white pieces left to right
		currentSpace.changePiece(it.next());
		for(int i = 0; i < 7; i++){
			currentSpace = currentSpace.getSpaceRight();
			currentSpace.changePiece(it.next());
		}
		currentSpace = currentSpace.getSpaceForward();
		
		// set the "second" row ("Rank 2) of white pieces from right to left
		for(int i = 0; i < 8; i++){
			currentSpace.changePiece(it.next());
			currentSpace = currentSpace.getSpaceLeft();
		}
	}
}
