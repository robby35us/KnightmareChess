package game;

import java.util.Scanner;

import utility.ErrorMessage;
import utility.MoveCompositor;
import utility.MoveInput;
import components.Board;
import components.Piece;
import components.Player;
import components.PlayerSet;
import components.Space;

import definitions.*;
import factory.PieceFactory;
import moves.ActualMove;
import moves.MoveEnPassantLeft;
import moves.MoveEnPassantRight;

public class Operations {
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	private PieceFactory factory;
	private boolean displayText;
	
	
	public Operations(boolean displayText) {
		this.displayText = displayText;
		Piece.setOps(this);
	}

	public void setupGame() {
		board = new Board();
		factory = new PieceFactory(board);
		PlayerSet [] sets = new PlayerSet[2];
		sets[0] = new PlayerSet(factory, Color.White);
		sets[1] = new PlayerSet(factory, Color.Black);
		whitePlayer = new Player(sets[0], Color.White, sets[1].getKing());
		blackPlayer = new Player(sets[1], Color.Black, sets[0].getKing());
		Setup.setupChessBoard(sets[0], sets[1], board);
	}
	
	public void setupTestGame(Board board){
		this.board = board;
	}

	public void prettyPrintBoard() {
		Space head = board.getSpace(Rank.Eight, File.A);
		Space current = head;
		System.out.println(" Rank    ----   ----   ----   ----   ----   ----   ----   ----");
		for(int i = 8; i >= 1; i--){
			if(current.getColor() == Color.White)
				System.out.printf("  %d    |  ", i);
			else {
				System.out.printf("  %d    | |", i);
			}
			for(int j = 1; j <= 8; j++){
				printPieceCode(current);
				if(current.getColor() == Color.White)
					if(current.getFile() == File.H)
						System.out.print("  |  ");
					else
						System.out.print("  | |");
				else
						System.out.print("| |  ");
				current = current.getSpaceRight();
			}
			System.out.println();
			System.out.println("         ----   ----   ----   ----   ----   ----   ----   ----");
			
			head = head.getSpaceBackward();
			current = head;
		}
		System.out.println("  File   a      b      c      d      e      f      g      h");
	}
	
	private void printPieceCode(Space current) {
		Piece piece = current.getPiece();
		if(piece == null)
			System.out.print("  ");
		else{
			String result = "";
			result += piece.getColor() == Color.White ? "W" : "B";
			switch(piece.getType()){
				case Pawn : result += "P"; break;
				case Knight : result += "N"; break;
				case Bishop : result += "B"; break;
				case Rook : result += "R"; break;
				case Queen : result += "Q"; break;
				case King : result += "K"; break;
			}
			System.out.print(result);
		}
	}

	public MoveInput getMoveInput(Color color, Scanner in, ErrorMessage message) {
		String input = null;
		if(displayText)
			System.out.println("Player " + (color.ordinal() + 1) + ", enter next move (ex. e2 e4):");
		input = in.next();
		Space init = getSpace(input);
		if(init == null){
			if(!exitCondition(input))
				message.setInvalidInput();
			return null;
		}
		input = in.next();
		Space dest = getSpace(input);
		if(dest == null){
			message.setInvalidInput();
			return null;
		}
		return new MoveInput(init, dest);
	}

	public void invalidEntryText(String input) {
		if(displayText)
			System.out.println(input + " is not a valid space identifier.");
	}

	private Space getSpace(String input) {
		if(input == null || input.length() != 2)
			return null;
		char fileInput = input.toLowerCase().charAt(0);
		char rankInput = input.charAt(1);
		if(fileInput >= 'a' && fileInput <= 'h' &&
		   rankInput >= '1' && rankInput <= '8')
			return board.getSpace(Rank.values()[rankInput - '1'], 
					              File.values()[fileInput - 'a']);
		else
			return null;
	}

	private boolean exitCondition(String input) {
		 return input.charAt(0) == 'q' || input.charAt(0) == 'Q';
	}

	public static ErrorMessage makeMove(ActualMove move, Turn turn, Operations ops, ErrorMessage message) {
		Piece moving = move.getInitialSpace().getPiece();
		Piece captured = movePiece(move, ops);
		Player opposite = turn == Turn.Player1 ? ops.blackPlayer : ops.whitePlayer;
		if(captured != null){
			if(opposite != null)
			opposite.losePiece(captured);
			captured.setSpace(null);
		}
		if(!moving.notifyKingObservers()){
			Operations.undoMove(move, captured, ops);
			if(captured != null){
				opposite = captured.getColor() == Color.White ? ops.whitePlayer : ops.blackPlayer;
				if(opposite != null)
					opposite.addPiece(captured);
				captured.setSpace(move.getDestinationSpace());
			}
			message.setCheck();
			return message;
		}
		if(moving.getType() == PieceType.Pawn && 
		   (moving.getColor() == Color.White && moving.getSpace().getRank() == Rank.Eight) ||
		   (moving.getColor() == Color.Black && moving.getSpace().getRank() == Rank.One))
			message.setPromotePawn();
		MoveCompositor.setPreviousMove(move);
		return message;
	}

	public boolean promotePawn(Piece moving, Scanner in) {
		String input = in.next();
		char selection = input.charAt(0);
		PieceType replacementType;
		switch(selection){
			case 'Q':
			case 'q':
				replacementType = PieceType.Queen;
				break;
			case 'R':
			case 'r':
				replacementType = PieceType.Rook;
				break;
			case 'K' :
			case 'k' :
				replacementType = PieceType.Knight;
				break;
			case 'B' :
			case 'b' :
				replacementType = PieceType.Bishop;
				break;
			default :
				return false;
		}
		Color color = moving.getColor();
		Player player = color == Color.White ? whitePlayer : blackPlayer;
		Piece newPiece = (factory.makePiece(replacementType, color));
		moving.getSpace().changePiece(newPiece);
		player.losePiece(moving);
		player.addPiece(newPiece);
		return true;
	}

	public static Piece movePiece(ActualMove move, Operations ops){
//		System.out.println("In movePiece");
//		ops.prettyPrintBoard();
		Piece moving = move.getInitialSpace().getPiece();
		Space capturedSpace;
		Space dest = move.getDestinationSpace();
		if(move.getClass() == MoveEnPassantRight.class || move.getClass() == MoveEnPassantLeft.class)
			capturedSpace = (moving.getColor() == Color.White) ? dest.getSpaceBackward() : dest.getSpaceForward();
		else
			capturedSpace = dest;
		Piece captured = capturedSpace.getPiece();
		capturedSpace.changePiece(null);
		move.getDestinationSpace().changePiece(moving);
		move.getInitialSpace().changePiece(null);

//		System.out.println("After movePiece - captured = " + captured);
//		ops.prettyPrintBoard();
		return captured;
	}
	
	public static void undoMove(ActualMove move, Piece captured, Operations ops){
//		System.out.println("In undoMove");
//		ops.prettyPrintBoard();
		Piece moving = move.getDestinationSpace().getPiece();
		Space capturedSpace;
		Space dest = move.getDestinationSpace();
		if(move.getClass() == MoveEnPassantRight.class || move.getClass() == MoveEnPassantLeft.class)
			capturedSpace = (moving.getColor() == Color.White) ? dest.getSpaceBackward() : dest.getSpaceForward();
		else
			capturedSpace = dest;
		move.getInitialSpace().changePiece(moving);
		dest.changePiece(null);
		capturedSpace.changePiece(captured);
//		System.out.println("After undoMove - captured = " + captured);
//		ops.prettyPrintBoard();
	}
	
	public void invalidMoveText(){
		if(displayText){
			System.out.println("The entered move is not valid.");
			System.out.println("Please try again.");
		}
	}
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn, ErrorMessage message) {
		Space init = move.getInitialSpace();
		Space dest = move.getDestinationSpace();
		Piece moving = init.getPiece();
		Piece captured = dest.getPiece();
		
		if(moving == null || moving.getColor() != Color.values()[turn.ordinal()]){
			message.setWrongColorMoving();
			return false;
		}
		if(captured != null && captured.getColor() == moving.getColor()){
			message.setWrongColorCaptured();
			return false;
		}
		return true;
	}

	public ErrorMessage checkForMate(Turn turn, ErrorMessage message) {
		Player player = turn == Turn.Player1 ? whitePlayer : blackPlayer;
		if(whitePlayer != null){
			boolean prevDisplayText = displayText;
			displayText = false;
			player.checkForMate(message);
			displayText = prevDisplayText;
		}
		return message;
	}

	public boolean displayText() {
		return displayText;
	}
}
