package moveDecorators;

import definitions.Color;
import definitions.MoveType;

public class MoveEnPassantRight extends ActualMove {

	public MoveEnPassantRight(Color color){
		super(MoveType.ForwardRight.getRankOffset(),
				  MoveType.ForwardRight.getFileOffset(), 
			      color);
	}
}
