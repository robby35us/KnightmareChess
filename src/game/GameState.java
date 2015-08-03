package game;

import java.util.ArrayList;
import java.util.Scanner;

import utility.ErrorMessage;
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

public class GameState {
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	private PieceFactory factory;
	private ArrayList<ErrorMessage> messages;
	private ActualMove prevMove;
	
	
	// use this constructor for a regular game
	public GameState() {
		this(null);
		setupGame(); // sets the Board 
	}

	// use this constructor for a test game using irregular initial board state
	public GameState(Board board) {
		Piece.setGameState(this);
		this.board = board;
		this.messages = new ArrayList<ErrorMessage>();
	}

	public void setupGame() {
		board = new Board();
		factory = new PieceFactory(board, this);
		PlayerSet [] sets = new PlayerSet[2];
		sets[0] = new PlayerSet(factory, Color.White);
		sets[1] = new PlayerSet(factory, Color.Black);
		whitePlayer = new Player(sets[0], Color.White, sets[1].getKing());
		blackPlayer = new Player(sets[1], Color.Black, sets[0].getKing());
		Setup.setupChessBoard(sets[0], sets[1], board);
	}

	public MoveInput getMoveInput(Color color, Scanner in, ErrorMessage message) {
		String input = null;
		input = in.next();
		Space init = getSpace(input);
		if(init == null){
			if(!exitCondition(input)){
				message.setInvalidInput();
			}
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

	public ErrorMessage makeMove(ActualMove move, Turn turn, ErrorMessage message) {
		Piece moving = move.getInitialSpace().getPiece();
		Piece captured = movePiece(move);
		Player opposite = turn == Turn.Player1 ? blackPlayer : whitePlayer;
		if(captured != null){
			if(opposite != null)
			opposite.losePiece(captured);
			captured.setSpace(null);
		}
		if(!moving.notifyKingObservers()){
			undoMove(move, captured);
			if(captured != null){
				opposite = captured.getColor() == Color.White ? whitePlayer : blackPlayer;
				if(opposite != null)
					opposite.addPiece(captured);
				captured.setSpace(move.getDestinationSpace());
			}
			message.setCheck();
			return message;
		}
		if(moving.getType() == PieceType.Pawn && 
		   (moving.getColor() == Color.White && moving.getSpace().getRank() == Rank.Eight) ||
		   (moving.getColor() == Color.Black && moving.getSpace().getRank() == Rank.One)){
			message.setPromotePawn();
		}
		setPreviousMove(move);
		return message;
	}

	public ArrayList<ErrorMessage> getMessages() {
		return messages;
	}
	
	public PieceType getPawnPromotionType(Scanner in){
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
				return null;
		}
		return replacementType;
	}

	public boolean promotePawn(Piece moving, PieceType promotionType) {
		Color color = moving.getColor();
		Player player = color == Color.White ? whitePlayer : blackPlayer;
		Piece newPiece = (factory.makePiece(promotionType, color));
		moving.getSpace().changePiece(newPiece);
		player.losePiece(moving);
		player.addPiece(newPiece);
		return true;
	}

	public static Piece movePiece(ActualMove move){
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
	
	public static void undoMove(ActualMove move, Piece captured){
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

	// NOTE: THIS METHOD DOESN"T USE THE MESSAGE PARAMETER
	public ErrorMessage checkForMate(Turn turn, ErrorMessage message) {
		Player player = turn == Turn.Player1 ? whitePlayer : blackPlayer;
		if(whitePlayer != null){
			player.checkForMate(message);
		}
		return message;
	}

	public Board getBoard() {
		return board;
	}
	
	public ActualMove getPreviousMove(){
		return prevMove;
	}
	
	public void setPreviousMove(ActualMove prevMove){
		this.prevMove = prevMove;
	}
}
