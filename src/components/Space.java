/* Space.java
 * A space object represents a single space on the
 * chess board. It holds it's rank and file information
 * and it's color. It also holds information about which 
 * piece, if any, is occupying it. It contains a reference
 * to the board object and some functions to get adjacent 
 * spaces.
 */

package components;
import definitions.Color;
import definitions.File;
import definitions.MoveType;
import definitions.Rank;


public class Space {
	private Rank rank;
	private File file;
	private Color color;
	private Piece piece;
	private Board board;
	
	public Space (Rank rank, File file, Board board){
		this.rank = rank;
		this.file = file;
		this.board = board;
		color = ((rank.ordinal() + file.ordinal()) & 1) == 0 ? Color.White : Color.Black;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public File getFile(){
		return file;
	}
	
	public boolean changePiece(Piece newPiece){
		boolean wasPrevPiece = piece != null;
		piece = newPiece;
		return wasPrevPiece;
	}
	
	public boolean hasPiece(){
		return piece != null;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Space getSpaceLeft(){
		return board.getNextSpace(MoveType.Left.getRankOffset(), 
				                  MoveType.Left.getFileOffset(),this);
	}
	
	public Space getSpaceRight(){
		return board.getNextSpace(MoveType.Right.getRankOffset(), 
                				  MoveType.Right.getFileOffset(),this);
	}
	
	public Space getSpaceForward(){
		return board.getNextSpace(MoveType.Forward.getRankOffset(), 
                				  MoveType.Forward.getFileOffset(),this);
	}
	
	public Space getSpaceBackward(){
		return board.getNextSpace(MoveType.Backward.getRankOffset(), 
                				  MoveType.Backward.getFileOffset(),this);
	}
}
