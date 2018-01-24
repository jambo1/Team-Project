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
	private int turn;
	private Cards[] deck = new Cards[MAXCARDS];
	private Cards[] communalPile = new Cards[MAXCARDS];
	private Cards[]  activeCards = new Cards[NUMPLAYERS];
	private AIPlayer hp = new AIPlayer();
	private AIPlayer p1 = new AIPlayer();
	private AIPlayer p2 = new AIPlayer();
	private AIPlayer p3 = new AIPlayer();
	private AIPlayer p4 = new AIPlayer();
	private String winnerString;
	private boolean gameOver = false;
	
	//this is the basic running order of the game - NEED TO UTILISE GAME OVER
	public Game(Cards[] cards) {
		deck = cards;
		turn=0;
		deal();
		while(gameOver==false)	{
		playRound(turn);
		turn++;
		}
	}
	
	//deals the cards, human player starts with same card all the time atm, might be shuffle error
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
	
	//player of round chosen by turn counter t, based on initial value and then divisibility by no of players
	//Always prints human card to screen first, then prints result of either human choice or computer choice
	private void playRound(int t)	{
		System.out.println(displayCard(hp.getTopCard()));
		if(t==0||t%5==0)	{
			System.out.println("Please select a category to play:");
			System.out.println("1 = Size, 2 = Speed, 3 = Range, 4 = Firepower, 5 = Cargo");
			System.out.println(playCategory(getUserChoice()));
		}
		else if(t==1||t%5==1)	{
			System.out.println("Player 1 is choosing a category to play:");
			System.out.println(playCategory(p1.selectCategory(p1.getTopCard())));
		}
		else if(t==2||t%5==2)	{
			System.out.println("Player 2 is choosing a category to play:");
			System.out.println(playCategory(p2.selectCategory(p2.getTopCard())));
		}
		else if(t==3||t%5==3)	{
			System.out.println("Player 3 is choosing a category to play:");
			System.out.println(playCategory(p3.selectCategory(p3.getTopCard())));			
		}
		else if(t==4||t%5==4)	{
			System.out.println("Player 4 is choosing a category to play:");
			System.out.println(playCategory(p4.selectCategory(p4.getTopCard())));
		}
		else	{
			System.out.println("play round is fucked");
		}
	}
	
	//method to make wee card on screen, can def be polished if we have time
	private String displayCard(Cards c)	{
		int i = 0;
		while(hp.getPlayerHand()[i]!=null) {
			System.out.println(hp.getPlayerHand()[i].getDescription());
			i++;
		}
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
	
	//creates active card array, then zeroes top card in each hand so in following round the remaining cards are pushed up
	//(using method in AIPlayer) categories are enumerated in same order as on card (desc=0,size=1 etc.)
	private String playCategory(int c)	{
		int victor=0, bestValue=0;
		int[] cardValArray = new int[NUMPLAYERS];
		activeCards[0] = hp.getTopCard();
		activeCards[1] = p1.getTopCard();
		activeCards[2] = p2.getTopCard();
		activeCards[3] = p3.getTopCard();
		activeCards[4] = p4.getTopCard();
		
		hp.nullTopCard();
		p1.nullTopCard();
		p2.nullTopCard();
		p3.nullTopCard();
		p4.nullTopCard();
		
		if(c==1)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getSize();
				if(bestValue<activeCards[i].getSize())	{
					bestValue = activeCards[i].getSize();
					victor = i;
				}
			}
		}
		if(c==2)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getSpeed();
				if(bestValue<activeCards[i].getSpeed())	{
					bestValue = activeCards[i].getSpeed();
					victor = i;
				}
			}
		}
		if(c==3)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getRange();
				if(bestValue<activeCards[i].getRange())	{
					bestValue = activeCards[i].getRange();
					victor = i;
				}
			}
		}
		if(c==4)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getFirepower();
				if(bestValue<activeCards[i].getFirepower())	{
					bestValue = activeCards[i].getFirepower();
					victor = i;
				}
			}
		}
		if(c==5)	{
			for(int i=0; i<NUMPLAYERS; i++)	{
				cardValArray[i] = activeCards[i].getCargo();
				if(bestValue<activeCards[i].getCargo())	{
					bestValue = activeCards[i].getCargo();
					victor = i;
				}
			}
		}
		//tests for draw bases
		Arrays.sort(cardValArray);
		boolean draw = false;
		if(cardValArray[NUMPLAYERS-1]==cardValArray[NUMPLAYERS-2])	{
			draw = true;
		}
		return takePile(victor, draw);
	}
	
	//scans for user input, error if not always scanning for some reason (ie. if it gets closed)
	private int getUserChoice() {
		int choice = 0;
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
		//sc.close();
		return choice;
	}
	
	//creates winner string and allocates communal and active cards to winner
	private String takePile(int v, boolean d)	{
		if(d==true)	{
			int comStart =0;
			while(communalPile[comStart]!=null)	{comStart++;}
			for(int i=0;i<NUMPLAYERS;i++)	{
				communalPile[comStart] = activeCards[i];
			}
			winnerString = "That round was a draw!";
		}
		else	{
			if(v==0)	{
				hp.givePlayerCards(activeCards, communalPile);
				winnerString = "You won that round!";
			}
			else if(v==1)	{
				p1.givePlayerCards(activeCards, communalPile);
				winnerString = "Player 1 won that round!";
			}
			else if(v==2)	{
				p2.givePlayerCards(activeCards, communalPile);
				winnerString = "Player 2 won that round!";
			}
			else if(v==3)	{
				p3.givePlayerCards(activeCards, communalPile);
				winnerString = "Player 3 won that round!";
			}
			else if(v==4)	{
				p4.givePlayerCards(activeCards, communalPile);
				winnerString = "Player 4 won that round!";
			}
		}
		return winnerString;
	}
}