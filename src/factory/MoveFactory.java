package factory;
import definitions.*;
import moves.*;

public class MoveFactory {

	public ActualMove makeMove(MoveType type, Color color){
		ActualMove move = null;
		switch(type){
			case EnPassantLeft : move = new MoveEnPassantLeft(color); break;
			case EnPassantRight : move = new MoveEnPassantRight(color); break;
			case Forward : move = new MoveForward(color); break;
			case ForwardLeft : move = new MoveForwardLeft(color); break;
			case ForwardRight : move = new MoveForwardRight(color); break;
			case ForwardTwo : move = new MoveForwardTwo(color); break;
			case LForwardLeft : move = new MoveLForwardLeft(color); break;
			case LForwardRight : move = new MoveLForwardRight(color); break;
			case LRightForward : move = new MoveLRightForward(color); break;
			case LRightBackward : move = new MoveLRightBackward(color); break; 
			case LBackwardRight : move = new MoveLBackwardRight(color); break;
			case LBackwardLeft : move = new MoveLBackwardLeft(color); break;
			case LLeftBackward : move = new MoveLLeftBackward(color); break;
			case LLeftForward : move = new MoveLLeftForward(color); break;
			case BackwardRight : move = new MoveBackwardRight(color); break;
			case BackwardLeft : move = new MoveBackwardLeft(color); break;
			case Right : move = new MoveRight(color); break;
			case Backward : move = new MoveBackward(color); break;
			case Left : move = new MoveLeft(color); break;
			case ReverseKingSideCastle : move = new MoveReverseKingSideCastle(color); break;
			case ReverseQueenSideCastle : move = new MoveReverseQueenSideCastle(color); break; 
			case KingSideCastle : move = new MoveKingSideCastle(color); break;
			case QueenSideCastle : move = new MoveQueenSideCastle(color); break;
		}
		return move;
	}
}
