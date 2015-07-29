package test;

import java.io.IOException;

import utility.ErrorMessage;

import game.ConsoleIO;
import game.Start;

public class Test{

	public static void main(String[] args) throws IOException{
		ErrorMessage message = Start.playGame(new ConsoleIO());
		System.out.println(message);
	}
}
