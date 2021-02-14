package model;

import java.awt.Color;

import model.GameBoard.Squares.Square;

/**
 * 
 * @author kokol
 *
 */
public class Pawn
{
		private Color color;//color of the pawn
		private Square currentSquare;//square that the pawn is on
		private boolean available=true;//if the pawn is available ,so if the pawn reached the end or not
		private int position=-10;//the position of the current pawn
		private int id ;//id is 1 for first pawn and 2 for second pawn
		public Pawn(int id)
		{
			this.id=id;
		}
		/**
		 * Accessor
		 * @return the id of the current pawn
		 */
		public int getId()
		{
			return id;
		}
		/**
		 * Transformer
		 * @param color The color of the current pawn
		 */
		public void setColor(Color color)
		{
			this.color = color;
		}

		/**
		 * Transformer
		 * This method update the square that the pawn is on
		 * @param currentSquare  The square the the pawn is on
		 */
		public void setCurrentSquare(Square currentSquare)
		{
			this.currentSquare = currentSquare;
		}

		/**
		 * Accessor
		 * This method Gives us the color of the current pawn
		 * @return The color of the pawn
		 */
		public Color getColor()
		{
			return color;
		}
	
		/**
		 * accessor
		 * This method returns the position of the current pawn
		 * @return The integer that describes the position of the pawn
		 */
		public int getPosition()
		{
			return position;
		}
	
		/**
		 * transformer
		 * This method set the position of the current pawn
		 * @param position an integer which describes the square that the pawn will be moved
		 */
		public void setPosition(int position)
		{
			this.position = position;
		}
	
	
		/**
		 * accessor
		 * This method gives us the information if the current pawn has reached the end 
		 * @return If the current pawn is available
		 */
		public boolean isAvailable()
		{
			return available;
		}
	
	
		/**
		 * accessor
		 * This method is used for recognizing the square that the pawn stands
		 * @return The square of the current pawn
		 */
		public Square getCurrentSquare()
		{
			return currentSquare;
		}

		/**
		 * Transformer
		 * This method set if the pawn is available or not
		 * @param available if the pawns is available or not
		 */
		public void setAvailable(boolean available)
		{
			this.available = available;
		}
		
		
		
		
		

}
