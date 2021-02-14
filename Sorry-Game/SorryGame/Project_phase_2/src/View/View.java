package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import model.Pawn;
import model.cards.Card;

/**
 * 
 * @author George Kokolakis
 *
 *         This class Displays the Game board You will see a Demo code
 */

public class View extends JPanel
{
	static View mainPanel = new View();// The main panel
	static JFrame frame = new JFrame("Sorry Game");// The frame
	static JLabel positions[] = new JLabel[72];// position of each squares
	static JButton cardButton = new JButton("sorry card");// button for the card1
	static JButton cardButton2 = new JButton("card");// button for the second card
	static JButton pawn1_red = new JButton();
	static JButton pawn2_red = new JButton();
	static JButton pawn1_yel = new JButton();
	static JButton pawn2_yel = new JButton();
	static JLabel start_yellow;
	static JLabel start_red;
	static JTextArea infoBox = new JTextArea();
	static JButton fold_button = new JButton();
	
	/**
	 * Accessor
	 * @return the fold button
	 */
	public JButton getFold_button()
	{
		return fold_button;
	}
	
	
	/**
	 * Accessor
	 * @return the card button
	 */
	public static JButton getCardButton()
	{
		return cardButton;
	}
	/**
	 * Accessor
	 * @return the first red pawn button
	 */
	public static JButton getPawn1_red()
	{
		return pawn1_red;
	}
	/**
	 * Accessor
	 * @return the second red pawn button
	 */
	
	public static JButton getPawn2_red()
	{
		return pawn2_red;
	}
	/**
	 * Accessor
	 * @return the first yellow pawn button
	 */
	public static JButton getPawn1_yel()
	{
		return pawn1_yel;
	}
	/**
	 * Accessor
	 * @return the second yellow pawn button
	 */
	public static JButton getPawn2_yel()
	{
		return pawn2_yel;
	}
	/**
	 * Accessor
	 * @return the second card's button
	 */
	public static JButton getCardButton2()
	{
		return cardButton2;
	}
	/**
	 * This method dispays 2 text fields where the user can insert how much blocks 
	 * he wants to move each pawn when number seven card is actiaved
	 * @return
	 */
	public int[] ChooseNumOfBlocks()
	{
		 	JPanel myPanel = new JPanel();
		    int [] choices = new int[2];
		 	JTextField field1 = new JTextField(5);
		    JTextField field2 = new JTextField(5);
		    myPanel.add(field1);
		    myPanel.add(field2);
		    JOptionPane.showMessageDialog(null, myPanel);
		    //System.out.println( Integer.parseInt(field1.getText()) );
		    //System.out.println( Integer.parseInt(field2.getText()) );
		    choices[0]=Integer.parseInt(field1.getText());
		    choices[1]=Integer.parseInt(field2.getText());
		    return choices;
	}

	/**
	 * update the card that the player draw
	 * @param card Last card drawn
	 */
	public static void updateCard(Card card)
	{
		int imageWidth = 120, imageHeight = 200;
		Image newimage2 = new ImageIcon(mainPanel.getClass().getResource(card.getImage())).getImage();
		Image image2 = newimage2.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);

		cardButton2.setIcon(new ImageIcon(image2));
		mainPanel.revalidate();
		mainPanel.repaint();
		
		
	
	}

	
	
	
	
	
	/**
	 * This method updates the pawn position on board
	 * 
	 * @param pawn     The pawn that will be moved
	 * @param position The position that the pawn will move
	 */
	public static void updatePawn(Pawn pawn,Pawn pawn2,Pawn pawn3,Pawn pawn4)
	{
		
		if(pawn2.getPosition()==9)
		{
			pawn2_red.setBounds(50, 0, 35, 40); 
		}
		if(pawn4.getPosition()==44)
		{
			pawn2_yel.setBounds(50, 0, 35, 40); 
		}
		
		if (pawn2.getPosition()!=-10) 
		{
			pawn2_red.setBounds(0, 0, 35, 40); 
		}
		if (pawn4.getPosition()!=-10) 
		{
			pawn2_yel.setBounds(0, 0, 35, 40); 
			
		}
		
		if(pawn.getPosition()!=-10) 
		{
			positions[Math.floorMod(pawn.getPosition(), 72)].add(pawn1_red,0);
			
		}
		else 
		{
			start_red.add(pawn1_red);
		}
				
		if(pawn2.getPosition()!=-10)
		{
			positions[Math.floorMod(pawn2.getPosition(), 72)].add(pawn2_red,0);
			
		}
		else
		{
			pawn2_red.setBounds(50, 0, 35, 40); 
			start_red.add(pawn2_red);
		}
			
				
			
		
		if(pawn3.getPosition()!=-10) 
		{
			positions[Math.floorMod(pawn3.getPosition(), 72)].add(pawn1_yel, 0);
			
		}
		else 
		{
			start_yellow.add(pawn1_yel);
		}
			
		if(pawn4.getPosition()!=-10) 
		{
			
			positions[Math.floorMod(pawn4.getPosition(), 72)].add(pawn2_yel, 0);
		}
		else
		{
			pawn2_yel.setBounds(50, 0, 35, 40); 
			start_yellow.add(pawn2_yel);
		}			
				
			
		

		mainPanel.revalidate();
		mainPanel.repaint();

	}

	public View()
	{
	
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// right rectangle
		g.drawRect(580, 0, 600, 800);
		g.setColor(Color.darkGray);
		g.fillRect(580, 0, 600, 800);

		int x = 40;
		int y = 45;
		int width = 595 - 2 * x;
		int height = 585;

		// whole left rectangle
		g.drawRect(x - 40, y - 50, 595, 800);
		g.setColor(Color.black);
		g.fillRect(x - 40, y - 50, 595, 800);

		// left rectangle
		g.drawRect(x, y, width, height);
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}

	/**
	 * @pre Both players pawn aren't available This method will display The player
	 *      that won
	 * @param msg The message that will be displayed
	 * @post Message Displayed
	 */
	public void winMessage(String msg)
	{
		JOptionPane.showMessageDialog(frame, msg);
		System.out.println(msg);
	}

	/**
	 * This method will update the info box with informations about whose round is ,
	 * cards left
	 * 
	 * @param msg The message that will be displayed
	 */
	public static void updateInfoBox(String msg)
	{
		// Text area info box
		infoBox.setBounds(750, 500, 300, 170);
		infoBox.setText(msg);
		infoBox.setEditable(false);
	}

	/**
	 * This method creates listener for the buttons which are the card and pawns
	 */
	public void init_Listeners()
	{

	}

	/**
	 * 
	 * This method creates the components for our graphics ,images, buttons, text
	 */
	public static void init_Components()
	{
		//init pawns
		pawn1_red.setBounds(0, 0, 35, 40);
		pawn2_red.setBounds(50, 0, 35, 40);
		pawn1_yel.setBounds(0, 0, 35, 40);
		pawn2_yel.setBounds(50, 0, 35, 40);

		start_red.add(pawn1_red,0);
		start_red.add(pawn2_red);
		start_yellow.add(pawn1_yel);
		start_yellow.add(pawn2_yel);
		
		//styling 
		/*	pawn1_red.setFocusPainted(false);
			pawn1_red.setMargin(new Insets(0, 0, 0, 0));
			pawn1_red.setBorderPainted(false);
			pawn1_red.setOpaque(false);
		
			pawn2_red.setFocusPainted(false);
			pawn2_red.setMargin(new Insets(0, 0, 0, 0));
			pawn2_red.setBorderPainted(false);
			pawn2_red.setOpaque(false);
			
			pawn1_yel.setFocusPainted(false);
			pawn1_yel.setMargin(new Insets(0, 0, 0, 0));
			pawn1_yel.setBorderPainted(false);
			pawn1_yel.setOpaque(false);
		//	pawn1_yel.setBorder(null);
			pawn2_yel.setFocusPainted(false);
			pawn2_yel.setMargin(new Insets(0, 0, 0, 0));
			pawn2_yel.setBorderPainted(false);
			pawn2_yel.setOpaque(false);
		 */
		
		//set pawn images
		Image red_pawn_1 = new ImageIcon(mainPanel.getClass().getResource("/images/pawns/redPawn1.png")).getImage();
		Image red_pawn_1_icon = red_pawn_1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		pawn1_red.setIcon(new ImageIcon(red_pawn_1_icon));

		Image red_pawn_2 = new ImageIcon(mainPanel.getClass().getResource("/images/pawns/redPawn2.png")).getImage();
		Image red_pawn_2_icon = red_pawn_2.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		pawn2_red.setIcon(new ImageIcon(red_pawn_2_icon));
		
		Image yel_pawn_1 = new ImageIcon(mainPanel.getClass().getResource("/images/pawns/yellowPawn1.png")).getImage();
		Image yel_pawn_1_icon = yel_pawn_1.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		pawn1_yel.setIcon(new ImageIcon(yel_pawn_1_icon));
		
		Image yel_pawn_2 = new ImageIcon(mainPanel.getClass().getResource("/images/pawns/yellowPawn2.png")).getImage();
		Image yel_pawn_2_icon = yel_pawn_2.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		pawn2_yel.setIcon(new ImageIcon(yel_pawn_2_icon));
				
		
	
		
		
		
		
		
		int buttonX = 750, buttonY = 100, buttonWidth = 120, buttonHeight = 200;
		int imageWidth = 120, imageHeight = 200;
		// buttons for the cards
		cardButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
		cardButton2.setBounds(buttonX + 150, buttonY, buttonWidth, buttonHeight);

		// images for the buttons
		Image newimage = new ImageIcon(mainPanel.getClass().getResource("/images/cards/backCard.png")).getImage();
		Image image = newimage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);

		cardButton.setIcon(new ImageIcon(image));
		// Image image = new ImageIcon("/src/resources/card2.png").getImage();

		// fold button
		fold_button.setFont(new Font("Ink Free", Font.BOLD, 30));
		fold_button.setText("FOLD");
		fold_button.setForeground(Color.black);
		fold_button.setBounds(750, 370, 300, 50);
		fold_button.setBackground(Color.white);

		// Sorry logo
		JLabel sorryLogo = new JLabel();
		imageWidth = 200;
		imageHeight = 100;
		sorryLogo.setBounds(200, 280, imageWidth, imageHeight);
		Image logo = new ImageIcon(mainPanel.getClass().getResource("/images/sorryImage.png")).getImage();
		Image sorry_image = logo.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);

		

		sorryLogo.setIcon(new ImageIcon(sorry_image));

		mainPanel.add(fold_button);
		mainPanel.add(sorryLogo);
		mainPanel.add(cardButton);
		mainPanel.add(cardButton2);
		mainPanel.add(infoBox);
		frame.setSize(1200, 800);
		frame.getContentPane().add(mainPanel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	/**
	 * This method displays a option panel ,which giving the user the options for
	 * his next move
	 * 
	 * @param choices Array of strings that contains the moves that the player can
	 *                make
	 * @return The choice that the user chose
	 */
	public static int  askUser(String[] choices)
	{
		String s = (String) JOptionPane.showInputDialog(null, "make your choice", "Try GUI", JOptionPane.PLAIN_MESSAGE,
				null, choices, choices[0]);
		int number =Integer.parseInt(s.substring(0, 1) );
		return number ;
	}

	/**
	 * This method places the squares to the panel
	 */
	public static void init_Squares()
	{
		int index = 0;

		int x = 2, y = 2, width = 35, height = 40;

		mainPanel.setLayout(null);
		// labels up

		for (int i = 0; i < 22; i++)
		{

			if (i == 4)
			{

				int xx = x - 37, yy = y + 42;

				for (int k = 4; k < 11; k++)
				{
					if (k != 9)
					{

						positions[k] = new JLabel(String.valueOf(k + 1), SwingConstants.CENTER);
						positions[k].setOpaque(true);
						positions[k].setBounds(xx, yy, width, height);
						positions[k].setBackground(Color.red);
					}
					yy += 42;
					if (k == 9)// draw home label
					{
						yy -= 42;
						xx -= 30;
						String sText = "<html>HOME <br/> " + (k + 1) + " </html>";
						positions[k] = new JLabel(sText, SwingConstants.CENTER);
						Border b = BorderFactory.createLineBorder(Color.red, 3);
						positions[k].setBorder(b);
						positions[k].setOpaque(true);
						positions[k].setBounds(xx, yy, 90, 80);
						positions[k].setBackground(Color.white);
					}

					mainPanel.add(positions[k]);

					i = k;
				}
				i--;

			} else
			{
				positions[i] = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);

				positions[i].setOpaque(true);
				positions[i].setBounds(x, y, width, height);
				positions[i].setBackground(Color.white);
				x += 37;

				mainPanel.add(positions[i]);
			}

		}

		// labels right side
		x = 557;
		y = 45;
		width = 35;
		height = 40;
		for (int i = 0; i < 15; i++)

		{
			positions[i + 22] = new JLabel(String.valueOf(i + 23), SwingConstants.CENTER);

			positions[i + 22].setOpaque(true);
			positions[i + 22].setBounds(x, y, width, height);
			positions[i + 22].setBackground(Color.white);
			y += 42;

			mainPanel.add(positions[i + 22]);
		}

		// bottom side
		x = 557 - 37;
		y = 633;
		width = 35;
		height = 40;
		for (int i = 38; i < 60; i++)
		{

			if (i == 40)
			{
				// make home for yellow player
				int xx = x + 37, yy = y;
				for (int k = 40; k < 46; k++)
				{

					yy -= 42;
					if (k == 45)// draw home label
					{
						yy -= 42;
						xx -= 35;
						String sText = "<html>HOME <br/> " + (k) + " </html>";
						positions[k - 1] = new JLabel(sText, SwingConstants.CENTER);
						Border b = BorderFactory.createLineBorder(Color.yellow, 3);
						positions[k - 1].setBorder(b);
						positions[k - 1].setOpaque(true);
						positions[k - 1].setBounds(xx, yy, 90, 80);

						positions[k - 1].setBackground(Color.white);

						i = k;
					} else
					{
						positions[k - 1] = new JLabel(String.valueOf(k), SwingConstants.CENTER);
						positions[k - 1].setOpaque(true);
						positions[k - 1].setBounds(xx, yy, width, height);
						positions[k - 1].setBackground(Color.yellow);
					}

					mainPanel.add(positions[k - 1]);

				}
				// i++;
			} else
			{
				positions[i - 1] = new JLabel(String.valueOf(i), SwingConstants.CENTER);

				positions[i - 1].setOpaque(true);
				positions[i - 1].setBounds(x, y, width, height);
				positions[i - 1].setBackground(Color.white);
				x -= 37;

				mainPanel.add(positions[i - 1]);
			}
			/*
			 * if ((46 - i) == 36)// draw start box { int xx = x + 10; int yy = y - 37 * 2;
			 * label[i] = new JLabel("START", SwingConstants.CENTER);
			 * label[i].setVerticalAlignment(JLabel.TOP);
			 * 
			 * label[i].setOpaque(true); label[i].setBounds(xx, yy, 80, 70);
			 * label[i].setBackground(Color.yellow); mainPanel.add(label[i]); i++; }
			 */

		}

		// left side

		x = 2;
		y = 45;
		width = 35;
		height = 40;
		for (int i = 0; i < 15; i++)
		{
			positions[72 - i - 1] = new JLabel(String.valueOf(72 - i), SwingConstants.CENTER);

			positions[72 - i - 1].setOpaque(true);
			positions[72 - i - 1].setBounds(x, y, width, height);
			positions[72 - i - 1].setBackground(Color.white);
			y += 42;

			mainPanel.add(positions[72 - i - 1]);

		}

		// start  squares

		
		start_red = new JLabel("START", SwingConstants.CENTER);
		start_red.setHorizontalTextPosition(JLabel.CENTER);
		start_red.setVerticalTextPosition(JLabel.BOTTOM);
		start_red.setOpaque(true);
		start_red.setBounds(width*5-10, height+7, 90, 80);
		start_red.setBackground(Color.white);
		Border b = BorderFactory.createLineBorder(Color.red, 3);
		start_red.setBorder(b);
		
		mainPanel.add(start_red);
		
		start_yellow = new JLabel("START", SwingConstants.CENTER);
		start_yellow.setHorizontalTextPosition(JLabel.CENTER);
		start_yellow.setVerticalTextPosition(JLabel.BOTTOM);
		start_yellow.setOpaque(true);
		start_yellow.setBounds(380, 547, 90, 80);
		start_yellow.setBackground(Color.white);
		Border c = BorderFactory.createLineBorder(Color.yellow, 3);
		start_yellow.setBorder(c);
		
		mainPanel.add(start_yellow);
	}

	public static void init_Squares_image()
	{
		ArrayList<Image> sqaure_Images = new ArrayList<>();
		// first red slide
		for (int i = 0; i < 3; i++)
		{
			sqaure_Images
					.add(new ImageIcon(mainPanel.getClass().getResource("/images/slides/redSlide" + (i + 1) + ".png"))
							.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		}
		int k = 0;
		for (int i = 1; i < 5; i++)
		{
			if (i == 1)
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			} else if (i == 4)
			{
				k++;
				positions[i + 6].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else
			{
				if (i == 2 || i == 3)
				{

					positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				}

				else
				{
					positions[i + 6].setIcon(new ImageIcon(sqaure_Images.get(k)));
				}

			}

		}

		// second red slide
		k = 0;
		for (int i = 15; i < 20; i++)
		{
			if (i == 15)
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 19)
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}
		// first blue slide

		for (int i = 0; i < 3; i++)
		{
			sqaure_Images
					.add(new ImageIcon(mainPanel.getClass().getResource("/images/slides/blueSlide" + (i + 1) + ".png"))
							.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING));
		}

		k = 3;
		for (int i = 22; i < 26; i++)
		{
			if (i == 22)// start slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 25)// end slide
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else // middle slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}
		// second blue slide

		k = 3;

		for (int i = 30; i < 35; i++)
		{
			if (i == 30)// start slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 34)// end slide
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else // middle slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}

		// first yellow slide
		for (int i = 0; i < 3; i++)
		{
			sqaure_Images.add(
					new ImageIcon(mainPanel.getClass().getResource("/images/slides/yellowSlide" + (i + 1) + ".png"))
							.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING));
		}
		k = 6;

		for (int i = 37; i < 41; i++)
		{
			if (i == 37)
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			} else if (i == 40)
			{
				k++;
				positions[i + 6].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else
			{
				if (i == 38)
				{

					positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				}

				else
				{
					positions[i + 6].setIcon(new ImageIcon(sqaure_Images.get(k)));
				}

			}
		}

		// second yellow slide
		k = 6;
		for (int i = 51; i < 56; i++)
		{
			if (i == 51)// start slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 55)// end slide
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else // middle slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}

		// first green slide
		// second yellow slide
		for (int i = 0; i < 3; i++)
		{
			sqaure_Images
					.add(new ImageIcon(mainPanel.getClass().getResource("/images/slides/greenSlide" + (i + 1) + ".png"))
							.getImage().getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING));
		}

		k = 9;
		for (int i = 58; i < 62; i++)
		{
			if (i == 58)// start slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 61)// end slide
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else // middle slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}

		k = 9;
		for (int i = 66; i < 71; i++)
		{
			if (i == 66)// start slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
				k++;
			}

			else if (i == 70)// end slide
			{
				k++;
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			} else // middle slide
			{
				positions[i].setIcon(new ImageIcon(sqaure_Images.get(k)));
			}

		}

	}
}
