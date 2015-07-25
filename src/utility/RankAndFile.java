package utility;
import definitions.Rank;
import definitions.File;

public class RankAndFile {
	private Rank rank;
	private File file;
	
	public RankAndFile(Rank rank, File file){
		this.rank = rank;
		this.file = file;
	}
	
	public Rank getRank(){
		return rank;
	}
	
	public File getFile(){
		return file;
	}
}
