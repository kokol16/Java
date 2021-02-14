package model;

import java.awt.Color;
/**
 * 
 * @author kokol
 *
 */
public class Player
{
	private Color color;//color of the player
	private String name;//name of the player
	private Pawn pawn1;//his first pawn
	private Pawn pawn2;//his  second pawn
	private boolean canPlay;//if it's his turn or not
	private boolean finished;//if the player has finished the game
	/**
	 * constructor
	 * @param name of the player
	 * @param pawn1 first pawn of the player
	 * @param pawn2 second pawn of the player
	 */
	public Player(String name , Pawn pawn1,Pawn pawn2,Color color )
	{
		setColor(color);
		setName(name);
		setPawn1(pawn1);
		setPawn2(pawn2);
	}
	/**
	 * Observer
	 * check if it's players turn
	 * @return if is the current player turn 
	 */
	public boolean isMyTurn()
	{
		return canPlay;
	}
	
	/**
	 * Observer
	 * This method check's if the player has finished the game 
	 * @return if the player has finished 
	 */
	public boolean hasFinished()
	{
		if( pawn1.isAvailable() || pawn2.isAvailable())
		{
			finished=false;
		}else 
		{
			
			finished=true;
		}
		return finished;
	}
	
	/**
	 * @pre The enemy player finished his turn
	 * Transformer
	 * @param canPlay if the player can play
	 * @post the player plays
	 */
	public void setCanPlay(boolean canPlay)
	{
		this.canPlay = canPlay;
	}
	/**
	 * Name of the player
	 */
	@Override
	public String toString()
	{
		return ("My name is: "+name);
	}

	/**
	 * accessor
	 * @return the color of the player
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * transformer
	 * @param color the color that the player will have
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	
	/**
	 * accessor
	 * @return Name of the player
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * transformer
	 * @param name the name that the player will ahve
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * accessor
	 * @return the first pawn of the player
	 */
	public Pawn getPawn1()
	{
		return pawn1;
	}

	/**
	 * transformer
	 * @param pawn1 the first pawn that player will have
	 */
	public void setPawn1(Pawn pawn1)
	{
		this.pawn1 = pawn1;
	}

	/**
	 * accessor
	 * @return the second pawn of the player
	 */
	public Pawn getPawn2()
	{
		return pawn2;
	}

	/**
	 * transformer
	 * @param pawn2 the second pawn that the player will have
	 */
	public void setPawn2(Pawn pawn2)
	{
		this.pawn2 = pawn2;
	}
	  
	
}
