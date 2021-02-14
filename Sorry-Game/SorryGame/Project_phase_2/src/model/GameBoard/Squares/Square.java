package model.GameBoard.Squares;

import java.awt.Color;

import model.Pawn;

/**
 * 
 * @author George Kokolakis
 *
 */
public abstract class Square
{
	private Color color;	//color of the current square
	
	private Pawn curr_pawn; //variable for getting the pawn that is in the square
	private String image ;//path of the image for the current square	
	

	
	
	

	/**
	 * Transformer
	 * This method  set the pawn to the current square 
	 * @param curr_pawn  The pawn that will be into the square
	 */

	public void setCurr_pawn(Pawn curr_pawn)
	{
		this.curr_pawn = curr_pawn;
	}


	
	/**
	 * accessors
	 * This method gives us the pawn on the current square
	 * @return the pawn on square
	 */


	public Pawn getCurr_pawn()
	{
		return curr_pawn;
	}




	/**
	 * Constructor  , set the color of square and the image path
	 * @param color of the square 
	 */
	public Square(Color color,String image )
	{
		setColor(color);
		setImage(image);
		
	}
	
	
	
	/**
	 * Accessor
	 * @return The path of the image for the current square
	 */
	
	public String getImage()
	{
		return image;
	}


	/**
	 * Transformer
	 * @param image the path of square's image
	 */
	public void setImage(String image)
	{
		this.image = image;
	}



	/**
	 * Observer
	 * This method check's if the current square has a pawn on it
	 */
	public  boolean hasPawn()
	{
		return (curr_pawn!=null);
	}
	
	/**
	 * Transformer
	 * @param color  The color of the current square
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}
	/**
	 * Accessor
	 * @return the color of the current square
	 */
	public Color getColor()
	{
		return color;
	}

	
}
