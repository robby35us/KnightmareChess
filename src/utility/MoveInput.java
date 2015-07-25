package utility;

import components.Space;

public class MoveInput {
	private Space init;
	private Space dest;
	
	public MoveInput(Space init, Space dest){
		this.init = init;
		this.dest = dest;
	}
	
	public Space getInit(){
		return init;
	}
	
	public Space getDest(){
		return dest;
	}
}
