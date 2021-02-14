package model.cards;

import java.awt.Color;
import java.util.ArrayList;

import model.Deck;
import model.Pawn;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.Square;
import model.GameBoard.Squares.StartSquare;

public class SorryCard extends Card
{
	private Pawn enemyPawn;//Enemy pawn
	
	private String [] choices;//the choices that the player can make for moving the pawn according the current card
	
	public String[] getChoices()
	{
		return choices;
	}
	
	/**
	 * Transformer 
	 * @param enemyPawn The enemy's player pawn
	 */
	public void setEnemyPawn(Pawn enemyPawn)
	{
		this.enemyPawn = enemyPawn;
	}



	/**
	 * Constructor 
	 * @param image Path of the image
	 * @param name Name of card
	 */
	public SorryCard(String image, String name)
	{
		super(image, name);
		choices = new String[2];
		choices[0]="1. Take a pawn from your start position and swap it with first enemypawn";
		choices[1]="2. Take a pawn from your start position and swap it with second enemypawn";
	}

	/**
	 * This method swaps the two pawns
	 * @param pawn that will be swapped 
	 * @param enemyPawn that will be swapped
	 * @param deck of the game
	 * @param choice that the user made
	 * @return if the swap was completed
	 */
	public boolean swapPawns(Pawn pawn,Pawn enemyPawn, Deck deck,int choice)
	{
		if( ( pawn.getCurrentSquare() instanceof StartSquare )  &&
				! (enemyPawn.getCurrentSquare() instanceof StartSquare) &&
				
				!(enemyPawn.getCurrentSquare() instanceof HomeSquare)  &&
			
				!(enemyPawn.getCurrentSquare() instanceof SafetyZoneSquare))
		
			{
				System.out.println("sorry card activated");
				pawn.setPosition(enemyPawn.getPosition());
				pawn.setCurrentSquare(enemyPawn.getCurrentSquare());
				enemyPawn.getCurrentSquare().setCurr_pawn(pawn);
				
				enemyPawn.setPosition(-10);
				Square Start =new StartSquare(enemyPawn.getColor(), null);
				Start.setCurr_pawn(enemyPawn);
				enemyPawn.setCurrentSquare(Start);
				return true;
				
				
			}
		return false;
	}

	public void movePawn(Pawn pawn,Pawn enemyPawn, Deck deck,int choice)
	{
		int whichCalled;
		boolean result=false;
		if(choice==1 )
		{
			if( pawn.getColor()==Color.red)
			{
				result=swapPawns(pawn, deck.getYellow_Pawn1(), deck, choice);
				whichCalled=0;
			}
			else 
			{
				System.out.println("change yellow pawn 1 with red pawn 1");
				result=swapPawns(pawn, deck.getRed_Pawn1(), deck, choice);
				whichCalled=1;
			}
		}
		else 
		{
			if( pawn.getColor()==Color.red)
			{
				result=swapPawns(pawn, deck.getYellow_Pawn2(), deck, choice);
				whichCalled=2;
				
			}
			else {
				result=swapPawns(pawn, deck.getRed_Pawn2(), deck, choice);
				whichCalled=3;
			}
		}
		
		
		//if result is false then check if the other move can be done
		if(result ==false && whichCalled==0)
		{
			swapPawns(pawn, deck.getYellow_Pawn2(), deck, choice);
		}
		else if(result=false && whichCalled==1)
		{
			swapPawns(pawn, deck.getRed_Pawn2(), deck, choice);
		}
		else if( result==false && whichCalled==2)
		{
			swapPawns(pawn, deck.getYellow_Pawn1(), deck, choice);
		}
		else if(result==false && whichCalled==3) 
		{
			swapPawns(pawn, deck.getRed_Pawn1(), deck, choice);
		}
		
	
	
	}
	
	
	/**
	 * Accessorr 
	 * This method  Gets the enemy pawn
	 */
	public void  GetEnemyPawn()
	{
		this.enemyPawn=enemyPawn;
	}

	

	@Override
	public String toString()
	{
		
		return "Sorry Card";
	}



	@Override
	public void movePawn(Pawn pawn, Deck Deck, int choice)
	{
		// TODO Auto-generated method stub
		
	}

}
