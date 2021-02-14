package model.GameBoard.Squares;

import java.awt.Color;

import model.Deck;
import model.Pawn;

public class StartSlideSquare extends SlideSquare
{

	/**
	 * Constructor , initialize color of the square and his image path
	 * 
	 * @param color of the square
	 * @param image path for his image
	 */
	public StartSlideSquare(Color color, String image)
	{
		super(color, image);
	}

	/**
	 * This method will move the pawn to the end of the slide
	 * @param pawn The pawn that is on the current square
	 * @param board The board of the game
	 */
	public void movePawn(Pawn pawn, Deck board)
	{

	}

}
