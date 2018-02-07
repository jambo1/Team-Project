<<<<<<< HEAD
package commandline;

import java.io.*;
import java.util.Scanner;

/**
 * Class to print the logs for commandline version of the game
 * @author Jamie
 *
 */
public class logWriter {
	 private String outputFileName = "toptrumps.log";
	 private BufferedWriter writer; 
	 private final int NUMCARDS = 40;
	 private final String SEPARATOR = String.format("%n------------------%n");
	 private final String TAB = "    ";
	

	/**
	 * Constructor
	 */
	public logWriter() {
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.close();
		}
		catch(IOException ioe){
			
		}
	}
	
	/**
	 * Print the contents of the complete deck when read from file and constructed
	 * @param deck
	 */
	public void logFreshDeck(Cards[] deck) {
		try{
			try{
				//Create a fresh file with filewriter with false signifying overwrite current file
				FileWriter fw = new FileWriter(outputFileName, false);
				writer = new BufferedWriter(fw);
				
				//Use a StringBuilder to build the contents of the cards into a printable format
				StringBuilder sb = new StringBuilder();
				//Loop through the deck and get the description of each card, separated by a tab
				for(int i=0;i<NUMCARDS;i++) {
					sb.append(String.format(deck[i].getDescription() + TAB));
				}
				//Write the card information and print the separator
				writer.write(sb.toString());
				writer.write(SEPARATOR);
			}
			//Close writer
			finally {
			writer.close();
			}
		}
		//If IOException do nothing
		catch(IOException ioe) {
		}
				
		
		
	}
	
	/**
	 * Print the contents of the complete deck when it has been shuffled
	 * @param deck
	 */
	public void logShuffledDeck(Cards[] deck) {
		try{
			try {
				/*
				 * Create a FileWriter using true to signify that the file should be 
				 * appended to and BufferedWriter to actually write to the file
				 */
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				//Stringbuilder to store the card information
				StringBuilder sb = new StringBuilder();
				//Loop through the deck and store each cards description in the stringbuilder, separated by a tab
				for(int i=0;i<NUMCARDS;i++) {
					sb.append(String.format(deck[i].getDescription() + TAB));
				}
				//Write the stringbuilder to file
				writer.write(sb.toString());
			}
		
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//If IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the user and computer's hands. User hand is always in hands[0]
	 */
	public void logHands(Cards[][] hands) {
		//Stringbuilder to store the information
		StringBuilder sb = new StringBuilder();
		try {
			try {
				//FileWriter, true to signify append to file and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Loop through and print the user hand
				sb.append(String.format("%nUser's hand%n"));
				//Print each card separated by a TAB
				for(int i=0;i<hands[0].length;i++) {
					sb.append(String.format(hands[0][i].getDescription()+TAB));
				}
				
				//Print each of the computer hands
				for(int i=1;i<hands.length;i++) {
					//Append the current player
					sb.append(String.format("%nPlayer "+ i+"'s hand%n"));
					//Add each card separated by a TAB
					for(int j=0;j<hands[i].length;j++) {
						sb.append(String.format(hands[i][j].getDescription()+TAB));
					}
				}
				
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the communal pile
	 * @param communists
	 */
	public void logCommunalPile(Cards[] communists) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Stringbuilder to handle the information
				StringBuilder sb = new StringBuilder();
				//Print header stating communal pile
				sb.append(String.format("%nCommunal pile%n"));
				
				//Loop through the communal pile and print the card description separated by a tab
				for(int i=0;i<communists.length;i++) {
					sb.append(String.format(communists[i].getDescription() + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the active cards
	 * @param active
	 */
	public void logActivePile(Cards[] active) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//SB to hold the information
				StringBuilder sb = new StringBuilder();
				//Print header
				sb.append(String.format("%nCurrent Cards in Play%n"));
				
				//Loop through the active cards and print their descriptions
				for(int i=0;i<active.length;i++) {
					sb.append(String.format(active[i].getDescription() + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the chosen category in a round and the values each of the cards has for that category
	 * @param cardValArray
	 * @param c
	 */
	public void logCategoryValues(int[] cardValArray, int c) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Stringbuilder to hold information
				StringBuilder sb = new StringBuilder();
				sb.append(String.format("%nChosen Category and Values%n"));
				
				//Switch to determine what the chosen category was and to append it to sb
				switch (c){
					case 1 : sb.append("Chosen category was Size" );
							break;
					case 2 : sb.append("Chosen category was Speed" );
							break;
					case 3 : sb.append("Chosen category was Rango" );
							break;
					case 4 : sb.append("Chosen category was Firepower" );
							break;
					case 5 : sb.append("Chosen category was Cargo" );
							break;
				}
				
				//Append the values for the category
				for(int i=0;i<cardValArray.length;i++) {
					sb.append(String.format(""+cardValArray[i] + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch (IOException ioe) {}
	}
	
	/**
	 * Stores the winner of the game to the log file
	 * @param player
	 */
	public void logWinner(int player) {
		//Stringbuilder to hold data to be writen
		StringBuilder sb = new StringBuilder();
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Switch to handle which player has won
				switch (player) {
					case 0 : sb.append("The human user won the game");
							break;
					case 1 : sb.append("Computer player 1 won the game");
							break;
					case 2 : sb.append("Computer player 2 won the game");
							break;
					case 3 : sb.append("Computer player 3 won the game");
							break;
					case 4 : sb.append("Computer player 4 won the game");
							break;
				}
			}
			//Write to file close writer
			finally {
					writer.write(sb.toString());
					writer.close();
				}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
}
=======
package commandline;

import java.io.*;
import java.util.Scanner;

/**
 * Class to print the logs for commandline version of the game
 * @author Jamie
 *
 */
public class logWriter {
	 private String outputFileName = "toptrumps.log";
	 private BufferedWriter writer; 
	 private final int NUMCARDS = 40;
	 private final String SEPARATOR = String.format("%n------------------%n");
	 private final String TAB = "    ";
	

	/**
	 * Constructor
	 */
	public logWriter() {
		try {
			writer = new BufferedWriter(new FileWriter(outputFileName));
			writer.close();
		}
		catch(IOException ioe){
			
		}
	}
	
	/**
	 * Print the contents of the complete deck when read from file and constructed
	 * @param deck
	 */
	public void logFreshDeck(Cards[] deck) {
		try{
			try{
				//Create a fresh file with filewriter with false signifying overwrite current file
				FileWriter fw = new FileWriter(outputFileName, false);
				writer = new BufferedWriter(fw);
				
				//Use a StringBuilder to build the contents of the cards into a printable format
				StringBuilder sb = new StringBuilder();
				//Loop through the deck and get the description of each card, separated by a tab
				for(int i=0;i<NUMCARDS;i++) {
					sb.append(String.format(deck[i].getDescription() + TAB));
				}
				//Write the card information and print the separator
				writer.write(sb.toString());
				writer.write(SEPARATOR);
			}
			//Close writer
			finally {
			writer.close();
			}
		}
		//If IOException do nothing
		catch(IOException ioe) {
		}
				
		
		
	}
	
	/**
	 * Print the contents of the complete deck when it has been shuffled
	 * @param deck
	 */
	public void logShuffledDeck(Cards[] deck) {
		try{
			try {
				/*
				 * Create a FileWriter using true to signify that the file should be 
				 * appended to and BufferedWriter to actually write to the file
				 */
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				//Stringbuilder to store the card information
				StringBuilder sb = new StringBuilder();
				//Loop through the deck and store each cards description in the stringbuilder, separated by a tab
				for(int i=0;i<NUMCARDS;i++) {
					sb.append(String.format(deck[i].getDescription() + TAB));
				}
				//Write the stringbuilder to file
				writer.write(sb.toString());
			}
		
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//If IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the user and computer's hands. User hand is always in hands[0]
	 */
	public void logHands(Cards[][] hands) {
		//Stringbuilder to store the information
		StringBuilder sb = new StringBuilder();
		try {
			try {
				//FileWriter, true to signify append to file and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Loop through and print the user hand
				sb.append(String.format("%nUser's hand%n"));
				//Print each card separated by a TAB
				for(int i=0;i<hands[0].length;i++) {
					sb.append(String.format(hands[0][i].getDescription()+TAB));
				}
				
				//Print each of the computer hands
				for(int i=1;i<hands.length;i++) {
					//Append the current player
					sb.append(String.format("%nPlayer "+ i+"'s hand%n"));
					//Add each card separated by a TAB
					for(int j=0;j<hands[i].length;j++) {
						sb.append(String.format(hands[i][j].getDescription()+TAB));
					}
				}
				
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the communal pile
	 * @param communists
	 */
	public void logCommunalPile(Cards[] communists) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Stringbuilder to handle the information
				StringBuilder sb = new StringBuilder();
				//Print header stating communal pile
				sb.append(String.format("%nCommunal pile%n"));
				
				//Loop through the communal pile and print the card description separated by a tab
				for(int i=0;i<communists.length;i++) {
					sb.append(String.format(communists[i].getDescription() + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the active cards
	 * @param active
	 */
	public void logActivePile(Cards[] active) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//SB to hold the information
				StringBuilder sb = new StringBuilder();
				//Print header
				sb.append(String.format("%nCurrent Cards in Play%n"));
				
				//Loop through the active cards and print their descriptions
				for(int i=0;i<active.length;i++) {
					sb.append(String.format(active[i].getDescription() + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
	
	/**
	 * Prints the chosen category in a round and the values each of the cards has for that category
	 * @param cardValArray
	 * @param c
	 */
	public void logCategoryValues(int[] cardValArray, int c) {
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Stringbuilder to hold information
				StringBuilder sb = new StringBuilder();
				sb.append(String.format("%nChosen Category and Values%n"));
				
				//Switch to determine what the chosen category was and to append it to sb
				switch (c){
					case 1 : sb.append("Chosen category was Size" );
							break;
					case 2 : sb.append("Chosen category was Speed" );
							break;
					case 3 : sb.append("Chosen category was Rango" );
							break;
					case 4 : sb.append("Chosen category was Firepower" );
							break;
					case 5 : sb.append("Chosen category was Cargo" );
							break;
				}
				
				//Append the values for the category
				for(int i=0;i<cardValArray.length;i++) {
					sb.append(String.format(""+cardValArray[i] + TAB));
				}
				//Write to file
				writer.write(sb.toString());
			}
			finally {
				//Print the separator and close the writer
				writer.write(SEPARATOR);
				writer.close();
			}
		}
		//Catch IOE do nothing
		catch (IOException ioe) {}
	}
	
	/**
	 * Stores the winner of the game to the log file
	 * @param player
	 */
	public void logWinner(int player) {
		//Stringbuilder to hold data to be writen
		StringBuilder sb = new StringBuilder();
		try{
			try {
				//FileWriter with true to signify append and bufferedwriter for efficiency
				FileWriter fw = new FileWriter(outputFileName, true);
				writer = new BufferedWriter(fw);
				
				//Switch to handle which player has won
				switch (player) {
					case 0 : sb.append("The human user won the game");
							break;
					case 1 : sb.append("Computer player 1 won the game");
							break;
					case 2 : sb.append("Computer player 2 won the game");
							break;
					case 3 : sb.append("Computer player 3 won the game");
							break;
					case 4 : sb.append("Computer player 4 won the game");
							break;
				}
			}
			//Write to file close writer
			finally {
					writer.write(sb.toString());
					writer.close();
				}
		}
		//Catch IOE do nothing
		catch(IOException ioe) {}
	}
}
>>>>>>> f11c55a012fb3f148887cca5703203c90556464e
	