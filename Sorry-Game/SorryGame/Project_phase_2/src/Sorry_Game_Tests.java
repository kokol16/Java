import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Controller.Controller;
import model.Deck;
import model.GameBoard.Squares.EndSlideSquare;
import model.GameBoard.Squares.StartSquare;
import model.cards.Card;
import model.cards.NumberElevenCard;
import model.cards.NumberFourCard;
import model.cards.NumberTwoCard;

class Sorry_Game_Tests
{

	Controller cont=new Controller();
	

	@Test
	void MoveMethods()
	{
		
		Deck deck = cont.getDeck();
		
		Card card = new NumberElevenCard(null, "NumberElevenCard", 11);
		cont.makeMoves(card, deck, 2, deck.getRed_Pawn1(), null);
		//check if a pawn can move without card 1 or 2 from his start square
		assertEquals(-10, deck.getRed_Pawn1().getPosition());
		
		//check if the pawn will start with card 2

		 card = new NumberTwoCard(null, "NumberTwoCard", 2);
		 cont.makeMoves(card, deck, 2, deck.getRed_Pawn1(), null);
		//we see that the pawn moved in the right place
		assertEquals(11, deck.getRed_Pawn1().getPosition());
		
		//check if the pawn backwards can get to his safety zone square or to home square 
		deck.getRed_Pawn1().setPosition(12);
		card=new NumberFourCard(null, "NumberFourCard", 4);
		cont.makeMoves(card, deck, 2, deck.getRed_Pawn1(), null);
		//we see that i can't get to safety zone but it goes close enough to it
		assertEquals(2, deck.getRed_Pawn1().getPosition());
		
		//slide moves check
		 card = new NumberTwoCard(null, "NumberTwoCard", 2);
		 deck.getRed_Pawn1().setPosition(64);
		 deck.getYellow_Pawn1().setPosition(68);
		 cont.makeMoves(card, deck, 2, deck.getRed_Pawn1(), null);
		 
		 //check if the enemy yellow pawn got eaten by red pawn
		
		 assertEquals( Color.yellow, deck.getYellow_Pawn1().getCurrentSquare().getColor());
		 //check if the red pawn moved to the end of the slide
		 assertEquals( 70,deck.getRed_Pawn1().getPosition() );
		 
		 //check a swap card
		 //move the pawns from the start
		 card = new NumberTwoCard(null, "NumberTwoCard", 2);
		 cont.makeMoves(card, deck, 2, deck.getRed_Pawn2(), null);
		 cont.makeMoves(card, deck, 2, deck.getYellow_Pawn2(), null);
	
		 //swap the two pawns
		 card = new NumberElevenCard(null, "NumberElevenCard", 11);
		 cont.makeMoves(card, deck, 3, deck.getRed_Pawn2(), null);
		
	//	 assertEquals( 30,deck.getRed_Pawn1().getPosition() );
		 System.out.println(deck.getRed_Pawn2().getPosition());
		 System.out.println(deck.getYellow_Pawn2().getPosition());
		 assertEquals( 46,deck.getRed_Pawn2().getPosition() );
		 assertEquals( 11,deck.getYellow_Pawn2().getPosition() );

	} 
	
	
}
