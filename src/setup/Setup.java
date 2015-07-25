package setup;
import java.util.Iterator;

import components.*;
import definitions.*;


public class Setup {
	
	public static Board setupChessBoard(PlayerSet whiteSet, PlayerSet blackSet, Board board){
		placeWhitePieces(whiteSet, board);
		placeBlackPieces(blackSet, board);
		return board;
	}

	private static void placeBlackPieces(PlayerSet blackSet, Board board) {
		Space currentSpace = board.getSpace(Rank.Eight, File.A);
		Iterator<Piece> it = blackSet.iterator();		
		currentSpace.changePiece(it.next());
		for(int i = 0; i < 7; i++){
			currentSpace = currentSpace.getSpaceRight();
			currentSpace.changePiece(it.next());
		}
		currentSpace = currentSpace.getSpaceBackward();
		for(int i = 0; i < 8; i++){
			currentSpace.changePiece(it.next());
			currentSpace = currentSpace.getSpaceLeft();
		}	
	}

	private static void placeWhitePieces(PlayerSet whiteSet, Board board) {
		Space currentSpace = board.getSpace(Rank.One, File.A);
		Iterator<Piece> it = whiteSet.iterator();		
		currentSpace.changePiece(it.next());
		for(int i = 0; i < 7; i++){
			currentSpace = currentSpace.getSpaceRight();
			currentSpace.changePiece(it.next());
		}
		currentSpace = currentSpace.getSpaceForward();
		for(int i = 0; i < 8; i++){
			currentSpace.changePiece(it.next());
			currentSpace = currentSpace.getSpaceLeft();
		}
	}
	
}
