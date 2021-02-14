package Controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import View.View;
import model.Deck;
import model.MovesChecker;
import model.Pawn;
import model.Player;
import model.GameBoard.Squares.EndSlideSquare;
import model.GameBoard.Squares.HomeSquare;
import model.GameBoard.Squares.SafetyZoneSquare;
import model.GameBoard.Squares.Square;
import model.GameBoard.Squares.StartSlideSquare;
import model.GameBoard.Squares.StartSquare;
import model.cards.Card;
import model.cards.NumberCard;

public class Controller
{
	private final Deck deck;
	private final View view;
	private Player player1;// player 1
	private Player player2;// player 2
	int turn=1;// 1 player 1 is playing , 2 player 2 is playing
	boolean cardHasBeenDrawn = false;//This variable informs as if  the new card has been drawn
	boolean Fold=false;//This variable infroms us if the fold button can be activated
	//These variables informs as which pawns has been pressed by the player
	boolean red1_pressed=false;
	boolean red2_pressed=false;
	boolean yel1_pressed=false;
	boolean yel2_pressed=false;
	private boolean canChangeCard=true;//This variable informs us if we can allow user to change the card
	private boolean Fold_clicked=true;//This variables checks if we can press the fold button
	private boolean move_succeded=false;//This variables informs us if the move for the pawn has been succeded
	
	/**
	 * Accessor
	 * @return deck of the game
	 */
	public Deck getDeck()
	{
		return deck;
	}

	/**
	 * Constructor
	 * calls the methods for initalizing the game and the graphics
	 */
	public Controller()
	{
		this.view = new View();
	//	View view = new View();
		view.init_Squares();
		view.init_Components();
		view.init_Squares_image();
		// view.ChooseNumOfBlocks();
		deck = new Deck();
		init_Game(deck);
		player1 = new Player("George", deck.getRed_Pawn1(), deck.getRed_Pawn2(), Color.red);
		player2 = new Player("Manos", deck.getYellow_Pawn1(), deck.getYellow_Pawn2(), Color.yellow);
		System.out.println(player1);
		System.out.println(player2);
		System.out.println(player1.getPawn1().getPosition());
		System.out.println(player1.getPawn2().getCurrentSquare());
		System.out.println(player2.getPawn1().getColor());
	

	}

	public static void main(String[] args)
	{
		Controller cont = new Controller();
	}

	/**
	 * This method will check if there is a winner
	 * 
	 * @return if we found a winner
	 */
	public boolean foundWinner()
	{
		if (player1.hasFinished() || player2.hasFinished())
		{
			return true;
		}
		return false;
	}

	/**
	 *  @pre pawn must be available
	 * This method will call the move methods and the update pawn
	 * @post The pawn is moves
	 * @param card card that has been drawned
	 * @param deck of the game
	 * @param choice that the user made
	 * @param pawn that will be moved
	 * @param enemyPawn in case there's a special card
	 */
	public void makeMoves(Card card, Deck deck, int choice, Pawn pawn, Pawn enemyPawn)
	{
		
		if(!pawn.isAvailable()) {return ;}
		switch (card.getName())
		{
		case "NumberOneCard":

			card.movePawn(pawn, deck, choice);
			System.out.println(card.getName());
			break;

		case "NumberTwoCard":
			int prev_pos=pawn.getPosition();//check if the move has succeded so we let the player play again
			card.movePawn(pawn, deck, choice);
			System.out.println(card.getName());
			if(pawn.getColor()==Color.red && prev_pos != pawn.getPosition())//change the turn after card 2 has been played
			{
				ChangeTurnByMe(2);
				
			}
			else {
				ChangeTurnByMe(1);
				
			}
			break;

		case "NumberFourCard":
			card.movePawn(pawn, deck, choice);
			System.out.println(card.getName());
			break;

		case "NumberSevenCard":
			if (choice == 1)
			{

				int[] numOfBlocks = new int[2];
				numOfBlocks = view.ChooseNumOfBlocks();
				while ((numOfBlocks[0] + numOfBlocks[1]) > 7)// while user input is invalid repeat asking
				{
					numOfBlocks = view.ChooseNumOfBlocks();
				}
				if (pawn.getColor() == player1.getColor())
				{
					if (player1.getPawn1() != pawn)
					{
						card.movePawn(pawn, player1.getPawn1(), deck, choice, numOfBlocks[0], numOfBlocks[1]);

					} else
					{
						card.movePawn(pawn, player1.getPawn2(), deck, choice, numOfBlocks[0], numOfBlocks[1]);

					}
				} else
				{
					if (player2.getPawn1() != pawn)
					{
						card.movePawn(pawn, player2.getPawn1(), deck, choice, numOfBlocks[0], numOfBlocks[1]);

					} else
					{
						card.movePawn(pawn, player2.getPawn2(), deck, choice, numOfBlocks[0], numOfBlocks[1]);

					}
				}
			}
			else {
				card.movePawn(pawn, player1.getPawn1(), deck, choice, 0,0);

			}

			break;
		case "NumberTenCard":
			card.movePawn(pawn, deck, choice);
			System.out.println(card.getName());
			break;
		case "NumberElevenCard":

			card.movePawn(pawn, enemyPawn, deck, choice);

			System.out.println(card.getName());
			break;

		case "SorryCard":
			card.movePawn(pawn, enemyPawn, deck, choice);
			System.out.println(card.getName());
			break;

		default:
			System.out.println("card's value is :" + ((NumberCard) card).getValue());
			System.out.println(((NumberCard) card).getName());
			card.movePawn(pawn, deck, ((NumberCard) card).getValue());
			break;

		}
	
		
		//check if a pawn is on a start slide square  and move it to the end of it
		if( (player1.getPawn1().getCurrentSquare() instanceof StartSlideSquare)  && 
				(player1.getPawn1().getColor()!= player1.getPawn1().getCurrentSquare().getColor() ) )
		{
			moveToTheEndOfTheSlide(deck.getSquares(), player1.getPawn1());
		}
		
		if( (player1.getPawn2().getCurrentSquare() instanceof StartSlideSquare)  && 
				(player1.getPawn2().getColor()!= player1.getPawn2().getCurrentSquare().getColor() ) )
		{
			moveToTheEndOfTheSlide(deck.getSquares(), player1.getPawn2());
		}
		if( (player2.getPawn2().getCurrentSquare() instanceof StartSlideSquare)  && 
				(player2.getPawn2().getColor()!= player2.getPawn2().getCurrentSquare().getColor() ) )
		{
			moveToTheEndOfTheSlide(deck.getSquares(), player2.getPawn2());
		}
		
		if( (player2.getPawn1().getCurrentSquare() instanceof StartSlideSquare)  && 
				(player2.getPawn1().getColor()!= player2.getPawn1().getCurrentSquare().getColor() ) )
		{
			moveToTheEndOfTheSlide(deck.getSquares(), player2.getPawn1());
		}
		
		if(player1.getPawn1().getCurrentSquare() instanceof HomeSquare)
		{
			player1.getPawn1().setAvailable(false);
			System.out.println(" red pawn1 is no longer available");
		}
		 if(player1.getPawn2().getCurrentSquare() instanceof HomeSquare) 
		{
			player1.getPawn2().setAvailable(false);
			System.out.println(" red pawn2 is no longer available");
		}
		 if (player2.getPawn1().getCurrentSquare() instanceof HomeSquare)
		{
			player2.getPawn1().setAvailable(false);
			System.out.println("yellow pawn1 is no longer available");
		}
		 if (player2.getPawn2().getCurrentSquare() instanceof HomeSquare)
		{
			player2.getPawn2().setAvailable(false);
			System.out.println("yellow pawn2 is no longer available");
		}
	
	}

	/**
	 * @pre Move has been made or fold button activated
	 * This method change the turn when the move has been done or the fold button has been pressed
	 * @post change the turn 
	 */
	public void changeTurn()
	{
		if(turn==1)
		{
			ChangeTurnByMe(2);
			view.updateInfoBox("turn player : " + getTurn()+" Color : Yellow");
			}
		else {
			ChangeTurnByMe(1);
			view.updateInfoBox("turn player : " + getTurn()+" Color : Red");
		}
	}
	
	/**
	 * Transformer
	 * This method is used for changing the turn as i want , not by the regular algorithm
	 * @param turn 
	 */
	public void ChangeTurnByMe(int turn)
	{
		this.turn=turn;
	}
	
	/**
	 * @pre pawn is on start slide square && there's no same pawn on the end slide square
	 * This method moves the pawn that is on start slide square to the end slide square 
	 * @param squares of the board
	 * @param pawn that is on start slide square and must be moved
	 * @post moves the pawn to the end slide square
	 */
	public void moveToTheEndOfTheSlide(ArrayList<Square> squares,Pawn pawn)
	{
		MovesChecker checker=new MovesChecker();
		if(squares.get(Math.floorMod(pawn.getPosition()+4, 72))  instanceof EndSlideSquare &&
				checker.IsTwoSamePawnsOnSquare(pawn, Math.floorMod(pawn.getPosition()+4, 72), deck)														)
		{			
			System.out.println("same pawn on the end of the slide");	//check if there is another same pawn in the end of the slide
			return ;
		}
		else if(squares.get(Math.floorMod(pawn.getPosition()+3, 72))  instanceof EndSlideSquare &&
				checker.IsTwoSamePawnsOnSquare(pawn, Math.floorMod(pawn.getPosition()+3, 72), deck ) )	
		{
			System.out.println("same pawn on the end of the slide");	//check if there is another same pawn in the end of the slide
			return ;

		}
		else if(squares.get(Math.floorMod(pawn.getPosition()+9, 72))  instanceof EndSlideSquare &&
				checker.IsTwoSamePawnsOnSquare(pawn, Math.floorMod(pawn.getPosition()+9, 72), deck ))//for slides that are near to  safety zone 
		{
			System.out.println("same pawn on the end of the slide");	//check if there is another same pawn in the end of the slide
			return ;
		}
		int index =pawn.getPosition();
		int steps=0;
		//loop while end slide square and search if there's any enemy pawn across it
		while ( ! ( squares.get(index) instanceof EndSlideSquare ) ) 
		{
			Pawn pawnToBeEaten=squares.get(index).getCurr_pawn();
			if (pawnToBeEaten !=null && !(squares.get(index) instanceof SafetyZoneSquare ) &&
				!(squares.get(index) instanceof HomeSquare )		)
			{
				if(pawn.getColor()!=pawnToBeEaten.getColor() )//if there's an enemy pawn send it on his start square
				{
					pawnToBeEaten.setPosition(-10);
					pawnToBeEaten.setCurrentSquare(new StartSlideSquare(pawnToBeEaten.getColor(), null));;
				
					//squares.get(index).setCurr_pawn(null);
					
					
				}
			}
			steps++;
			index++;
		}
		Pawn pawnToBeEaten=squares.get(index).getCurr_pawn();
		if (pawnToBeEaten !=null)//check also if there's an enemy pawn on the end slide square
		{
			if(pawn.getColor()!=pawnToBeEaten.getColor() )
			{
				pawnToBeEaten.setPosition(-10);
				pawnToBeEaten.setCurrentSquare(new StartSlideSquare(pawnToBeEaten.getColor(), null));;
				//possibly add setcurrpawn for new start slide square
				//squares.get(index).setCurr_pawn(null);
					
				
			}
		}
		deck.getSquares().get(pawn.getPosition()).setCurr_pawn(null);//previous square of pawn
		
		pawn.setCurrentSquare(squares.get(pawn.getPosition()+steps));
		int position =pawn.getPosition()+steps;
		squares.get(position).setCurr_pawn(pawn);
		pawn.setPosition(pawn.getPosition()+steps);//update pawns position
		System.out.println("position is : "+position+ " has pawn : "+squares.get(position).getCurr_pawn()!=null);
		System.out.println("===================");
	}
	
	
	
	/**
	 * Accessor
	 * @return whose turn is 
	 */
	public int getTurn()
	{
		return turn;
	}
	/**
	 * Transformer 
	 * @param turn whose turn will be
	 */
	public void setTurn(int turn)
	{
		this.turn = turn;
	}

	/**
	 * This method will initialize the cards , squares , table ,pawns and will set the listener for the card  ,mouse,and fold button
	 */
	public void init_Game(Deck deck)
	{

		setListeners();
		deck.init_Cards();
		deck.init_Squares();
		deck.init_Table();
		deck.init_Pawns();
	}

	/**
	 * This method will set the listener for the cards and Fold button
	 */
	public void setListeners()
	{
		view.getCardButton().addMouseListener(new CardListener());

		view.getPawn1_red().addMouseListener(new PawnListener());
		view.getPawn2_red().addMouseListener(new PawnListener());
		view.getPawn1_yel().addMouseListener(new PawnListener());
		view.getPawn2_yel().addMouseListener(new PawnListener());
		view.getFold_button().addMouseListener(new FoldButtonListener());
	}
	/**
	 * This class implements a mouse listener for the fold button
	 * 
	 * @author kokol
	 *
	 */
	private class FoldButtonListener implements MouseListener
	{

		/**
		 * if fold button gets available then we set the variable fold_clicked to true ,allowing the user to press the fold button
		 */
		@Override
		public void mouseClicked(MouseEvent arg0)
		{
			if(Fold==true  )
			{
				Fold_clicked =true;
			//	changeTurn();
			//	canChangeCard=true;
				
		//		Fold=false;
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	/**
	 * 
	 * @author kokol
	 *
	 */
	private class PawnListener implements MouseListener
	{
		
		/**
		 * This method calls the make move method for making the moves for the current pawns
		 * it also calls the update method to update the pawns on the board
		 * and checks if there is a winner
		 */
		@Override
		public void mouseClicked(MouseEvent e)
		{
			JButton but = ((JButton) e.getSource());
			if (SwingUtilities.isLeftMouseButton(e))
			{
				 if(!canChangeCard)
				{	
					int prev_pos_player1_pawn1=player1.getPawn1().getPosition(); 
					int prev_pos_player1_pawn2=player1.getPawn2().getPosition(); 
					int prev_pos_player2_pawn1=player2.getPawn1().getPosition(); 
					int prev_pos_player2_pawn2=player2.getPawn2().getPosition(); 
					System.out.println(deck.getCurrentCard().getName());
					String[] questions = deck.getCurrentCard().getChoices();
					if (questions != null)
					{
						//System.out.println(choice);
						if (but == view.getPawn1_red() && turn==1 )
						{
							int choice = view.askUser(questions);
							makeMoves(deck.getCurrentCard(), deck, choice, deck.getRed_Pawn1(), null);
							view.updatePawn(deck.getRed_Pawn1(), deck.getRed_Pawn2(), deck.getYellow_Pawn1(),
									deck.getYellow_Pawn2());
							red1_pressed=true;
					//		cardHasBeenDrawn = false;
						} else if (but == view.getPawn2_red()&& turn==1)
						{
							int choice = view.askUser(questions);

							makeMoves(deck.getCurrentCard(), deck, choice, deck.getRed_Pawn2(), null);
							view.updatePawn(deck.getRed_Pawn1(), deck.getRed_Pawn2(), deck.getYellow_Pawn1(),
									deck.getYellow_Pawn2());
							red2_pressed=true;
					//		cardHasBeenDrawn = false;
						} else if (but == view.getPawn1_yel()&& turn==2)
						{
							int choice = view.askUser(questions);

							makeMoves(deck.getCurrentCard(), deck, choice, deck.getYellow_Pawn1(), null);
							view.updatePawn(deck.getRed_Pawn1(), deck.getRed_Pawn2(), deck.getYellow_Pawn1(),
									deck.getYellow_Pawn2());
						//	cardHasBeenDrawn = false;
							yel1_pressed=true;
						} else if (but == view.getPawn2_yel()&& turn==2)
						{
							int choice = view.askUser(questions);

							makeMoves(deck.getCurrentCard(), deck, choice, deck.getYellow_Pawn2(), null);
							view.updatePawn(deck.getRed_Pawn1(), deck.getRed_Pawn2(), deck.getYellow_Pawn1(),
									deck.getYellow_Pawn2());
							yel2_pressed=true;
					//		cardHasBeenDrawn = false;
						} 
						if(foundWinner())
						{
							if(player1.hasFinished())
							{
								
								view.winMessage("player 1 with color red won!!!");
							}
							else 
							{
								view.winMessage("player 2 with color yellow won!!!");
							}
						}
						
						//we check if a pawn has been move , so that means that the move has been completed and the turn can change
						
						if(prev_pos_player1_pawn1!=player1.getPawn1().getPosition()
							||prev_pos_player1_pawn2!=player1.getPawn2().getPosition()
							||prev_pos_player2_pawn1!=player2.getPawn1().getPosition()
							||prev_pos_player2_pawn2!=player2.getPawn2().getPosition())
						{
							move_succeded=true;
							canChangeCard=true;
						}
						
						// we check if the user has tried to make a move with both his pawns , so now we allow him to press the fold button
						if(  (red1_pressed && red2_pressed) || (yel1_pressed && yel2_pressed ) )
						{
							red1_pressed=false;
							red2_pressed=false;
							yel1_pressed=false;
							yel2_pressed=false;
							//canChangeCard=true;
							//cardHasBeenDrawn = false;
							Fold=true;
							canChangeCard=true;
							System.out.println("you can now fold");
						}
					}

				}

				
			
			}

		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

	}

	/**
	 * This class will handle when we draw a new card
	 * 
	 * @author kokol
	 *
	 */
	private class CardListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			JButton but = ((JButton) e.getSource());
			//System.out.println(but);
			 if(Fold_clicked || move_succeded  ) //prevent someone from changing the card without the fold button is activated or the move has been done      !cardHasBeenDrawn
			// a move
			{

				if (SwingUtilities.isLeftMouseButton(e))
				{
					//cardHasBeenDrawn = true;
					move_succeded=false;
					Fold=false;
					Fold_clicked=false;
					canChangeCard=false;
					view.updateCard(deck.getNewCard());
				
					changeTurn();

				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			// TODO Auto-generated method stub

		}

	}

}
