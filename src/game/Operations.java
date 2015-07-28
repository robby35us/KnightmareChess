package game;

import java.util.Scanner;

import setup.Setup;
import utility.ErrorMessage;
import utility.MoveInput;
import components.Board;
import components.Piece;
import components.PlayerSet;
import components.Space;

import definitions.*;
import factory.PieceFactory;
import moveDecorators.ActualMove;

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
		
		// how can we get rid of this code without everything breaking?!
		factory = new PieceFactory(board);
		PlayerSet [] sets = new PlayerSet[2];
		sets[0] = new PlayerSet(factory, Color.White);
		sets[1] = new PlayerSet(factory, Color.Black);
		whitePlayer = new Player(sets[0], Color.White, sets[1].getKing());
		blackPlayer = new Player(sets[1], Color.Black, sets[0].getKing());
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

	public MoveInput getMoveInput(Color color, Scanner in) {
		String input = null;
		while(input == null){
			if(displayText)
				System.out.println("Player " + (color.ordinal() + 1) + ", enter next move (ex. e2 e4):");
			input = in.next();
			Space init = getSpace(input);
			if(init == null){
				if(exitCondition(input))
					return null;
				prettyPrintBoard();
				invalidEntryText(input);
				continue;
			}
			input = in.next();
			Space dest = getSpace(input);
			if(dest == null){
				prettyPrintBoard();
				invalidEntryText(input);
				continue;
			}
			return new MoveInput(init, dest);
		}
		return null;
	}

	private void invalidEntryText(String input) {
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

	public static ErrorMessage makeMove(ActualMove move, Turn turn, Operations ops) {
		Piece moving = move.getInitialSpace().getPiece();
		Piece captured = movePiece(move, ops);
		Player opposite = turn == Turn.Player1 ? ops.blackPlayer : ops.whitePlayer;
		if(captured != null){
			opposite.losePiece(captured);
			captured.setSpace(null);
		}
		if(!moving.notifyKingObservers()){
			Operations.undoMove(move, captured, ops);
			if(captured != null){
				(captured.getColor() == Color.White ? ops.whitePlayer : ops.blackPlayer).addPiece(captured);
				captured.setSpace(move.getDestinationSpace());
			}
			ErrorMessage result = new ErrorMessage();
			result.setMate();
			return result;
		}
		 //check the opposite player for mate
		return new ErrorMessage();
	}

	public static Piece movePiece(ActualMove move, Operations ops){
		Piece moving = move.getInitialSpace().getPiece();
		Piece captured = move.getDestinationSpace().getPiece();
		move.getDestinationSpace().changePiece(moving);
		move.getInitialSpace().changePiece(null);
		return captured;
	}
	
	public static void undoMove(ActualMove move, Piece captured, Operations ops){
		move.getInitialSpace().changePiece(move.getDestinationSpace().getPiece());
		move.getDestinationSpace().changePiece(captured);
	}
	
	public void invalidMoveText(){
		if(displayText){
			System.out.println("The entered move is not valid.");
			System.out.println("Please try again.");
		}
	}
	public boolean meetsUniversalConstraints(ActualMove move, Turn turn) {
		Space init = move.getInitialSpace();
		Space dest = move.getDestinationSpace();
		Piece moving = init.getPiece();
		Piece captured = dest.getPiece();
		
		if(moving == null || moving.getColor() != Color.values()[turn.ordinal()]){
			invalidMoveText();
			return false;
		}
		if(captured != null && captured.getColor() == moving.getColor()){
			invalidMoveText();
			return false;
		}
		return true;
	}

	public boolean checkForMate(Turn turn) {
		Player player = turn == Turn.Player1 ? whitePlayer : blackPlayer;
		return !player.checkForMate().getMate();
	}
}
