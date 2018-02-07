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
	private Interaction in;
	

	public TopTrumpsCLIApplication(Interaction inter) {
		
		
		this.in=inter; 
		in.connection();
		
	}

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {
	
		
		
		//Read file and create the deck, then shuffle it
		createDeck();
		Collections.shuffle(Arrays.asList(deck));
		
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		//if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			Collections.shuffle(Arrays.asList(deck));
			//Get User input
			 int choice = getUserChoice();
			 
			//Play game
			if (choice ==1) {
				System.out.println("User choice was 1");
				Game aGame = new Game(deck);
			}
			//Connect to database and show statistics
			if(choice ==2) {
				System.out.println("User choice was 2");  
				
				Interaction in = new Interaction();
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
    		try {
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
}
	


