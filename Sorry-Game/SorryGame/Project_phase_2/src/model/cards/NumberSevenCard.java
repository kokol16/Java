package model.cards;

import java.awt.Color;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.StartSquare;

public class NumberSevenCard extends NumberCard
{
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	
	public String[] getChoices()
	{
		return choices;
	}
	
	/**
	 * Constructor 
	 * @param image Path of the image
	 * @param name Name of card
	 * @param value  of card
	 */
	public NumberSevenCard(String image , String name, int value  )
	{

		super(image, name, value );
		choices=new String[2];
		choices[0]="1. Choose how 7 blocks for your 2 pawns";
		choices[1]="2. move pawn by seven ";
	}
	
	
	/*
	 * This method makes the moves that the user chose
	 * @param pawn1 The first pawn of the player
	 * @param pawn2	The second pawn of the player
	 * @param move1 The number of blocks that  pawn1 will move
	 * @param move2 The number of blocks that  pawn2 will move
	 * @return if the moves was successful
	 */
	public boolean ChosenMove(Pawn pawn1,Pawn pawn2,int move1,int move2,Deck  deck)
	{
		boolean result1=false;
		boolean result2=false;
		SimpleNumberCard numbCard=new SimpleNumberCard(null, null, 0);
		if(pawn1.getPosition()> pawn2.getPosition())
		{	
			result1=numbCard.move_By_Blocks(pawn1, deck, move1);
			result2=numbCard.move_By_Blocks(pawn2, deck, move2);
			
		}
		else 
		{
			result1= numbCard.move_By_Blocks(pawn2, deck, move2);
			 result2=numbCard.move_By_Blocks(pawn1, deck, move1);
			
		}
		if(result1 || result2) {return true;}
		return false;
		
	}
	
	/**
	 * This method moves the pawn by one square ,it checks if the move is valid and set the new position for the current pawn
	 * @param pawn
	 * @param deck
	 * @return true if the move is valid
	 */
	public boolean   moveByOSeven(Pawn pawn,Deck deck)
	{
		int possibleMove=pawn.getPosition()+7;
		possibleMove=Math.floorMod(possibleMove, 72);
		if ( !check.isPawnOnStartPosition(pawn)&&
				!check.IsEnemyHome(pawn, possibleMove, deck) && !check.IsEnemySafetyZone(pawn, possibleMove, deck) 
		&& !check.IsEnemyStartSquare(pawn, possibleMove, deck) && !check.IsTwoSamePawnsOnSquare(pawn, possibleMove, deck) )
		{
			if(check.IsEnemyPawnOnSquare(pawn, possibleMove, deck))
			{
				deck.getSquares().get(possibleMove).getCurr_pawn().setPosition(-10);//move the enemy pawn to his start square
				deck.getSquares().get(possibleMove).getCurr_pawn().setCurrentSquare(new StartSquare(deck.getSquares().get(possibleMove).getCurr_pawn().getColor(), null));//move the enemy pawn to his start square
				
			}
			pawn.getCurrentSquare().setCurr_pawn(null);
			
			update_Pawn_Current_Square(pawn, possibleMove, deck);
			
			set_Squares_Pawn(pawn, possibleMove, deck);
			pawn.setPosition(possibleMove);
			return true;
			
		}
		return false;
	}
	@Override
	public void movePawn(Pawn pawn,Pawn pawn2, Deck  deck,int choice,int numOfBlocks1,int numOfBlocks2)
	{
		//check if numofBlocks is negative and  throw error
		//check if the pawn is at the start block and throw error
		boolean moveResult=false;
			
		switch (choice)
		{
		case 1:
			moveResult=ChosenMove(pawn,pawn2, numOfBlocks1, numOfBlocks2, deck);
			break;
		case 2:
			moveResult=moveByOSeven(pawn, deck);
			break;
		default:
			break;
		}
		if(moveResult==false && choice==1)
		{
			moveByOSeven(pawn, deck);
		}
		
		
		
	}



	
	@Override
	public String toString()
	{

		return "Seven Card";
	}
}
