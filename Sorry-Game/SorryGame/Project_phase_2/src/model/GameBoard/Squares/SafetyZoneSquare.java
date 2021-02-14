package model.GameBoard.Squares;

import java.awt.Color;

public class SafetyZoneSquare extends Square
{

	

	/**
	 * Constructor , initialize color of the square and his image path
	 * @param color of the square
	 * @param image path for his image
	 */

	public SafetyZoneSquare(Color color, String image)
	{
		super(color, image);
	}

	@Override
	public boolean hasPawn()
	{
		// TODO Auto-generated method stub
		return false;
	}

}
