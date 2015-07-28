package utility;

public class ErrorMessage {
	
	boolean check = false;
	boolean mate = false;
	boolean illegalPattern = false;
	boolean wrongColorMoving = false;
	boolean wrongColorCaptured = false;
	boolean noError = true;
	
	
	public void setCheck(){
		check = true;
		noError = false;
	}
	
	public void setMate(){
		mate = true;
		noError = false;
	}
	
	public void setIllegalPattern(){
		illegalPattern = true;
		noError = false;
	}
	
	public void setWrongColorMoving(){
		wrongColorMoving = true;
		noError = false;
	}
	
	public void setWrongColorCaptured(){
		wrongColorCaptured = true;
		noError = false;
	}
	
	public boolean hasError(){
		return !noError;
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public boolean getMate(){
		return mate;
	}
	
	public boolean getIllegalPattern(){
		return illegalPattern;
	}
	
	public boolean getWrongColorMoving(){
		return wrongColorMoving;
	}
	
	public boolean getWrongColorCaptured(){
		return wrongColorCaptured;
	}
}
