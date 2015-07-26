package components;
import definitions.File;
import definitions.Rank;

/* Board.java
 * The board class represents the chess board.
 * A board is simply a 2-D array of space objects.
 * A board object is not aware of the Piece class 
 * or of what pieces are on it. It only knows about
 * the spaces it contains, and how to return one of
 * it's Space objects.
 */
public class Board {
	
	// The 2-D Space array that makes up a Board object.
	private Space[][] spaces;
	
	/*
	 * The constructor initializes the Space array using the enums
	 * Rank and File. The size of the board is dictated by the number
	 * of values in these enums.
	 */
	public Board(){
		spaces = new Space[Rank.values().length][File.values().length];
		for(Rank r : Rank.values())
			for(File f : File.values())
				spaces[r.ordinal()][f.ordinal()] = new Space(r,f, this);
	}
	
	/*
	 * This function finds another space(the next space) on the board in relation to
	 * a given space (initialSpace) using the rankOffset and fileOffset. It returns 
	 * null if there is no such space. 
	 */
	public Space getNextSpace(int rankOffset, int fileOffset, Space initialSpace){
		int newRank = initialSpace.getRank().ordinal() + rankOffset;
		int newFile = initialSpace.getFile().ordinal() + fileOffset;
		if(newRank >= 0 && newRank < Rank.values().length &&
		   newFile >= 0 && newFile < File.values().length)
			return spaces[newRank][newFile];
		else{
			// No such space exists!!!
			return null;
		}
	}
	
	/*
	 * Returns the Space on the Board at the given coordinates.
	 */
	public Space getSpace(Rank rank, File file){
		return spaces[rank.ordinal()][file.ordinal()];
	}
}
