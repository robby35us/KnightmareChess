package game;

import java.util.ArrayList;
import utility.ErrorMessage;
import components.*;
import definitions.*;
import factory.PieceFactory;
import moves.*;

/*
 * As advertised, this class keeps track of the game state. It holds
 * the Board, two Player objects, a PieceFactory for making new pieces,
 * a list of ErrorMessages and tracks the previous move. It also
 * provides various operations and methods that involve changing the
 * game state.
 */
public class GameState {
	private Board board;
	private Player whitePlayer;
	private Player blackPlayer;
	private PieceFactory factory;
	private ArrayList<ErrorMessage> messages;
	private ActualMove prevMove;
	
	
	/*
	 * This constructor sets up a regular game, and initializes the Board
	 * with regular piece placement, as well as both Player objects.
	 */
	public GameState() {
		this(null);
		setupGameState(); // sets the Board 
	}
	
	/*
	 * This constructor is used for testing with a irregular board. It 
	 * does not place pieces on the given Board or create Player objects.
	 */
	public GameState(Board board) {
		Piece.setGameState(this);
		this.board = board;
		this.messages = new ArrayList<ErrorMessage>();
	}

	/*
	 * Does the work of setting up the board state for a regular game.
	 * Initializes the Players, their PlayerSet's, the Board, and the
	 * factory.
	 */
	public void setupGameState() {
		board = new Board();
		factory = new PieceFactory(board, this);
		
		PlayerSet [] sets = new PlayerSet[2];
		sets[0] = new PlayerSet(factory, Color.White);
		sets[1] = new PlayerSet(factory, Color.Black);
		
		whitePlayer = new Player(sets[0], Color.White, sets[1].getKing());
		blackPlayer = new Player(sets[1], Color.Black, sets[0].getKing());
		
		Setup.setupChessBoard(sets[0], sets[1], board);
	}

	

	/*
	 * Moves the piece to it's new positions, using the information provided
	 * in move and turn to get the initial and destination location and for
	 * which player should lose the captured piece.
	 */
	public ErrorMessage makeMove(ActualMove move, Turn turn, ErrorMessage message) {
		Piece moving = move.getInitialSpace().getPiece();
		
		// Complete the move
		Piece captured = movePiece(move);
		Player opposite = turn == Turn.Player1 ? blackPlayer : whitePlayer;
		
		// remove the captured piece, if any, from the board
		if(captured != null){
			if(opposite != null)
			opposite.losePiece(captured);
			captured.setSpace(null);
		}
		
		// verify that this move didn't place it's own king in check
		if(!moving.notifyKingObservers()){
			
			// reset everything to how it was before the move.
			Space capturedSpace = undoMove(move, captured);
			if(captured != null){
				opposite = captured.getColor() == Color.White ? whitePlayer : blackPlayer;
				if(opposite != null)
					opposite.addPiece(captured);
				
				captured.setSpace(capturedSpace);
			}
			
			// return that this move would have placed the king in check
			message.setCheck();
			return message;
		}
		
		// check for pawn promotion
		if(moving.getType() == PieceType.Pawn && 
		   (moving.getColor() == Color.White && moving.getSpace().getRank() == Rank.Eight) ||
		   (moving.getColor() == Color.Black && moving.getSpace().getRank() == Rank.One)){
			message.setPromotePawn();
		}
		
		setPreviousMove(move);
		return message;
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

		return captured;
	}
	
	public static Space undoMove(ActualMove move, Piece captured){
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
		return capturedSpace;
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
			player.checkForMate(message);
		}
		return message;
	}

	// public getters
	public Board getBoard() {
		return board;
	}
	

	public ArrayList<ErrorMessage> getMessages() {
		return messages;
	}
	
	public ActualMove getPreviousMove(){
		return prevMove;
	}
	
	// public setters
	public void setPreviousMove(ActualMove prevMove){
		this.prevMove = prevMove;
	}
}
