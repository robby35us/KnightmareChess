package moves;

import definitions.Color;
import definitions.MoveType;

public class MoveEnPassantLeft extends ActualMove {

	public MoveEnPassantLeft(Color color){
		super(MoveType.ForwardLeft.getRankOffset(),
				  MoveType.ForwardLeft.getFileOffset(), 
			      color);
	}
}
