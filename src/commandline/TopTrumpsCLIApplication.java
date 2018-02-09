package commandline;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {
	private static final int MAXCARDS = 40;
	private static Cards[] deck = new Cards[MAXCARDS];

	public static Game aGame;
	private static boolean gameOver;
	private static int round, turn;
	private static boolean timer = false; //set to true before hand in
	private static logWriter log = new logWriter();
	private static boolean writeGameLogsToFile = true;
	private static int humanRounds, p1Rounds, p2Rounds, p3Rounds, p4Rounds, drawRounds; 
	private static int winner;
	private static Interaction in = new Interaction();

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {
	
		
		
		//Read file and create the deck
		createDeck();
		
/*		/*
//		 * uncomment before submission
//		 */
//		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			Collections.shuffle(Arrays.asList(deck));
			//Log the shuffled deck
			if(writeGameLogsToFile = true) {log.logShuffledDeck(deck);}
			
			
			//Get User input
			 int choice = getUserChoice();
			 
			//Play game
			if (choice ==1) {
				System.out.println("User choice was 1");
				
				//Create the game and deal the cards
				createGame();
				aGame.deal();
				
				//Log the hands dealt
				if(writeGameLogsToFile = true) {log.logHands(aGame.getAllPlayers());}
				
				gameOver = false;
				turn = 0;
				round = 0;
				humanRounds = 0;
				p1Rounds = 0;
				p2Rounds =0;
				p3Rounds = 0;
				p4Rounds =0;
				while(gameOver==false)	{
					/*
					 * Uncomment before turn in
					 */
					//Check if the human is in, if not turn timer off
					//timer = aGame.isHPin();
					
					round++;
					StringBuilder roundString = new StringBuilder("");
					System.out.println(roundString.append(String.format("--------------- Round %2d ---------------", round)).toString());
					aGame.clearActiveCards();
					
					
					/**
					 * Uses turn counter "t" to determine which player is choosing the category. 
					 * If the player is the human then they are asked to select a category to play. 
					 * Non human players use the method in AIPlayer to select and play the highest card 
					 * @param t
					 */
					//Checks if the human still has cards to play
					try {
						System.out.println(aGame.displayCard(aGame.getPlayer(0).getTopCard()) );
					} 
					//If not prints a message to say they have no card
					catch(NullPointerException n) {
						System.out.println("No card :(");
					}
//					/**
//					 * Uses turn counter to determine which player is choosing the category. 
//					 * If the player is the human then they are asked to select a category to play. 
//					 * Non human players use the method in AIPlayer to select and play the highest card 
//					 */

					for(int i = 0; i < aGame.getNumPlayers(); i++) {
						if (i == turn && turn == 0) {
							System.out.println("Please select a category to play:"
												+ "\n1 = Size, 2 = Speed, 3 = Range,"
												+ " 4 = Firepower, 5 = Cargo");
						} else if (i == turn ){
							System.out.println(String.format("Player %d is choosing a"
												+ "category to play:", i));
						}
					}
					int catChoice = 0;
					// human
					if(turn == 0) {
						catChoice = aGame.getPlayerChoice();	
					} 
					// AI
					else 
					{
						catChoice = aGame.getPlayer(turn).selectCategory(aGame.getPlayer(turn).getTopCard(), timer);
					}
					
					gameOver = aGame.playCategory(catChoice);
					
					//Log the active cards chosen category and values
					if(writeGameLogsToFile = true) {
						log.logActivePile(aGame.getActiveCards());
						log.logCategoryValues(aGame.getActiveCards(), catChoice);
					}
					
					for (int i = 0; i < aGame.getNumPlayers(); i++) {
						if (i==0) {
							System.out.println("-----HUMAN HAND --------");
							aGame.getPlayer(i).printHand();
						}
						else {
							System.out.println(String.format("-----P%d HAND --------", i));
							aGame.getPlayer(i).printHand();
						}
					}
					aGame.nullAndSort();
					
					/*
					 * Process a player winning the game
					 */
					if (gameOver) {
					
						//Print a buffer
						System.out.println("");
						System.out.println("");
						for(int i =0;i<30;i++) {
							System.out.print("-");
						}
						System.out.println("");
						
						// print the winner
						
						if(aGame.getPlayer(0).getTopCard()!=null) {
							System.out.println("Congratulations you have won!");
							if(writeGameLogsToFile = true) {log.logWinner(0);}
							//Save winner for persistent data
							winner = 0;
						}
						for (int i = 1; i < aGame.getNumPlayers(); i++) {
							if (aGame.getPlayer(i).getTopCard() != null ) {
								//Log winner
								if(writeGameLogsToFile = true) {log.logWinner(i);}
								System.out.println(String.format("Player %d has won the game!", i));
								//Save winner for persistent data
								winner=i;
							}
						}
						
						updatePersistent();

						//Print another buffer
						for(int i =0;i<30;i++) {
							System.out.print("-");
						}
						System.out.println("");
						System.out.println("");
						// gameover
					}
					
					/*
					 * Otherwise continue with the game
					 */
					else {
						int victor = aGame.doRoundCalc(catChoice);
						//update counters for persistent data collection
						updateRoundWinner(victor);
						// if not a draw
						if (victor != aGame.getNumPlayers()) {
						
						
							aGame.setDrawNo(0);
						
							System.out.println("----------Round Winning Card----------");
							System.out.println(aGame.displayCard(aGame.getActiveCard(victor)));
							System.out.println("---------------------------------------");
							aGame.getPlayer(victor).givePlayerCards(aGame.getActiveCards(), aGame.getCommunalPile());
						}
						
						String winnerString = "";
						
	
						for (int i = 0; i < aGame.getNumPlayers(); i++) {
							if (victor == 0) {
								turn = victor;
								winnerString = "You won that round!";
								aGame.comClearer();
							} else if (i == victor) {
								turn = victor;
								winnerString = String.format("Player %s won that round!", victor);
								aGame.comClearer();
							}
						}
						if (victor == aGame.getNumPlayers()) {
								//process draw
								aGame.draw();
								//Log the communal pile
								if(writeGameLogsToFile = true) {log.logCommunalPile(aGame.getCommunalPile());}
								
								winnerString = "That round was a draw!";
						}
						
						
						aGame.clearActiveCards();
						System.out.println(winnerString);
					}
					
					}
				
			}
			//Connect to database and show statistics
			if(choice ==2) {
				System.out.println("User choice was 2");  
				
				
				in.connection();
				System.out.println("The total number of games played is " + in.TotalGames());			
				System.out.println("The total number of human wins is " + in.HumanWins());
				System.out.println("The total number of AI wins is " + in.AIwins());
			    System.out.println("The average number of draws is " + in.AverageDraws());
				System.out.println("The highest number of rounds played in a single game is " + in.HighestRounds());
				in.disconnect();
				
				
			
		
			}
			//User wants to exit the game
			if(choice ==3) {
				System.out.println("Game closing");
				userWantsToQuit=true;
			}
			//No valid choice
			else {
				System.out.println("Your choice did not match any of the options");
			}
		}
	}
	
	

	public static void createGame() {
		aGame = new Game(deck);
		aGame.deal();
	}
	
	/**
	 * Creates the deck from the file containing the card data
	 */
	public static boolean createDeck() {
		BufferedReader reader;
    	Scanner in;
    	try {
    		reader = new BufferedReader(new FileReader("StarCitizenDeck.txt"));
    		in= new Scanner(reader);
    		//Line counter
    		int count =0;
    		try{
				while (in.hasNext()){
					//Reads id from file, if this corresponds to a valid class then the class is stored as activity
					String line = in.nextLine();
					Scanner lineRead = new Scanner(line);
					
					//First line contains headers which are not of use
					if (count ==0) { count++;}
					
					//While the count is less than the total number of cards read the card data from the line
					else if (count <= 41) {
						String desc = lineRead.next();
						int siz = lineRead.nextInt();
						int spe = lineRead.nextInt();
						int ran = lineRead.nextInt();
						int fir = lineRead.nextInt();
						int car = lineRead.nextInt();
						
						//Create a new card in the correct order in the deck
						deck[(count-1)] = new Cards(desc, siz, spe, ran, fir, car);
						//Increment
						count++;
						
					}
				}
	    	}
    		//Close reader and scanner and return the deck
    		finally {
    			if(writeGameLogsToFile = true) {log.logFreshDeck(deck);}
				reader.close();
				in.close();
				return true;
    		}
    	}
		//Catch input/output errors
		catch(IOException ioe) {
    		System.out.println("IOException error");
    		return false;
    	}
    			
    		
    }
    	
	
    	
    		
    
	
	/**
	 * Prints a menu of options to the user,
	 * reads user input from the options menu and returns this to the main.
	 * @return
	 */
	private static int getUserChoice() {
		//Prints instructions to user to enter a choice from the list
		System.out.println(String.format("To begin a new game, press 1 "
				+ "%nTo see past statistics press 2 %nTo exit the game press 3"));
		int choice =0;
		
		//Scanner is used to read user input and choice is returned
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		return choice;
		
	}
	
	public int setUserChoice(int onlineChoice) {
		return onlineChoice;
	}
	
	public static Cards[] getDeck() {
		return deck;
	}
	
	/**
	 * Updates statistics with the winner of each round
	 * @param winner
	 */
	private static void updateRoundWinner(int winner) {
		switch(winner) {
		case 0 : 
			humanRounds++;
			break;
		case 1 :
			p1Rounds++;
			break;
		case 2 :
			p2Rounds++;
			break;
		case 3 :
			p3Rounds++;
			break;
		case 4 :
			p4Rounds++;
			break;
		case 5 :
			drawRounds++;
			break;
		}
	}
	/**
	 * Updates the persistent data to be written to the sql table
	 */
	private static void updatePersistent() {
		//Update data for the sql queries
		in.updateSQL(humanRounds, p1Rounds, p2Rounds, p3Rounds, p4Rounds, drawRounds, round, winner);
		//Update statistics in the SQL database
		in.updateStats();
		System.out.println(""+humanRounds + p1Rounds+ p2Rounds+ p3Rounds+ p4Rounds+ drawRounds+ round+ winner);
	}
}
	


