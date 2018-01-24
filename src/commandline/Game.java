package commandline;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
	/*
	 * Instance variables for the deck and communal pile which could potentially hold all of the cards in the game
	 * and the active pile which will have at most the one card for each of the five players.
	 * The active cards pile is set to this limit to simplify comparing the cards in play.
	 */
	private final int MAXCARDS = 40;
	private final int NUMPLAYERS = 5;
	private Cards[] deck = new Cards[MAXCARDS];
	private Cards[] communalPile = new Cards[MAXCARDS];
	private Cards[]  activeCards = new Cards[NUMPLAYERS];
	private AIPlayer hp,p1,p2,p3,p4;
	private String winnerString;
	
	public Game(Cards[] cards) {
		deck = cards;
		deal();
		
	}
	
	private void deal()	{
		int cardCount = 0, playerCount=0;
		while(cardCount<MAXCARDS)	{
			hp.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p1.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p2.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p3.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			p4.getPlayerHand()[playerCount] = deck[cardCount];
			cardCount++;
			playerCount++;
		}
	}
	
	private void playRound()	{
		System.out.println(displayCard(hp.getTopCard()));
		System.out.println("Player, please select a category to play:");
		System.out.println("1 = Size, 2 = Speed, 3 = Range, 4 = Firepower, 5 = Cargo");
		int cat = getUserChoice();
		
	}
	
	private String displayCard(Cards c)	{
		StringBuilder dSBuild = new StringBuilder("");
		dSBuild.append("______________________________\r\n"); //30
		dSBuild.append(String.format("| %26s |\r\n", c.getDescription()));
		dSBuild.append(String.format("| Size: %20d |\r\n", c.getSize()));
		dSBuild.append(String.format("| Speed: %19d |\r\n", c.getSpeed()));
		dSBuild.append(String.format("| Range: %19d |\r\n", c.getRange()));
		dSBuild.append(String.format("| Firepower: %15d |\r\n", c.getFirepower()));
		dSBuild.append(String.format("| Cargo: %19d |\r\n", c.getCargo()));
		dSBuild.append("______________________________"); //30
		return dSBuild.toString();
	}
	
	private String playCategory(int c)	{
		int victor=0, bestValue=0, drawInteger=0;
		int[] cardValArray = new int[NUMPLAYERS];
		activeCards[0] = hp.getTopCard();
		activeCards[1] = p1.getTopCard();
		activeCards[2] = p2.getTopCard();
		activeCards[3] = p3.getTopCard();
		activeCards[4] = p4.getTopCard();
		
		if(c==1)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getSize();
				if(bestValue<activeCards[i].getSize())	{
					bestValue = activeCards[i].getSize();
					victor = i;
					drawInteger++;
				}
			}
		}
		if(c==2)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getSpeed();
				if(bestValue<activeCards[i].getSpeed())	{
					bestValue = activeCards[i].getSpeed();
					victor = i;
					drawInteger++;
				}
			}
		}
		if(c==3)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getRange();
				if(bestValue<activeCards[i].getRange())	{
					bestValue = activeCards[i].getRange();
					victor = i;
					drawInteger++;
				}
			}
		}
		if(c==4)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getFirepower();
				if(bestValue<activeCards[i].getFirepower())	{
					bestValue = activeCards[i].getFirepower();
					victor = i;
					drawInteger++;
				}
			}
		}
		if(c==5)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getCargo();
				if(bestValue<activeCards[i].getCargo())	{
					bestValue = activeCards[i].getCargo();
					victor = i;
					drawInteger++;
				}
			}
		}
		Arrays.sort(cardValArray);
		boolean draw = false;
		if(cardValArray[NUMPLAYERS-1]==cardValArray[NUMPLAYERS-2])	{
			draw = true;
		}
		divideSpoils(victor, draw);
		return winnerString;
	}
	
	private int getUserChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		sc.close();
		return choice;
	}
	
	private void divideSpoils(int v, boolean d)	{
		if(d==true)	{
			int comStart =0;
			while(communalPile[comStart]!=null)	{comStart++;}
			for(int i=0;i<NUMPLAYERS;i++)	{
				communalPile[comStart] = activeCards[i];
			}
		}
		else	{
			if(v==0)	{
				hp;
			}
			else if(v==1)	{
				p1;
			}
			else if(v==2)	{
				p2;
			}
			else if(v==3)	{
				p3;
			}
			else if(v==4)	{
				p4;
			}
		}
	}
}