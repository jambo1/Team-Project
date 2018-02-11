package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.*;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private Cards[] deck;
	private Game aGame;
	private boolean gameOver;
	private int round, turn;
	private int finalVictor;
	private int numberOfPlayers;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		
		
		
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		/*
		
		 */
		//
		
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	/**
	 * This method is called whenever user presses 'Play Round'. Pretty much reasembles the 
	 * command line game logic but without any system.out. This funciton @returns who won
	 * the round. 
	 * @param category
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/playCategory")
	public int playCategory(@QueryParam("Category") int category) throws IOException {
		int victor = 10;
		round++;
		if (turn == 0) {
			// do nothing, use QueryParam since it's the users choice
		} else {
			category = aGame.getPlayer(turn).selectCategory(aGame.getPlayer(turn).getTopCard(), false);
		}
		gameOver = aGame.playCategory(category);
		aGame.nullAndSort();
		if (gameOver) {
			for (int i = 0; i < aGame.getNumPlayers(); i++) {
				if (aGame.getPlayer(i).getTopCard() != null ) {
					finalVictor = i;
				}
			}
			
		}
		else {
			victor = aGame.doRoundCalc(category);
			if (victor != aGame.getNumPlayers()) {
				aGame.setDrawNo(0);
				aGame.getPlayer(victor).givePlayerCards(aGame.getActiveCards(), aGame.getCommunalPile());
				
				turn = victor;
				aGame.comClearer();
				
			}
			else {
				aGame.draw();
			}
			aGame.clearActiveCards();

		}

		System.out.println(victor);
		return victor;
	}
	
	
	/**
	 * Method called when isGameOver has returned true. This method @returns who won the game
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/gameIsOver")
	public int gameIsOver()  throws IOException {
		return finalVictor;
	}
	
	/**
	 * This method is called to check wether or not the game is over.
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/isGameOver")
	public int isGameOver() throws IOException {
		
		if (gameOver) {
			return 1;
		} else {
			return 0;
		}
	}
	/**
	 * This method is called when user press 'Start Game'. it creates a new game and 
	 * set all necessary parameters to their default values. Doesn't need to return anything.
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/startGame")
	public String startGame() throws IOException {
		createGame();
		gameOver = false;
		turn = 0;
		round = 0;
		return "game started";
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getNumberOfPlayers")
	public int getNumberOfPlayers() throws IOException {
		return aGame.getNumPlayers();
	}
	
	/**
	 * This funciton is called whenever a user presses a button on the home screen.
	 * This function can be deleted i think -----------------------------------
	 * @param selection
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/selection")
	public String getSelection(@QueryParam("Selection") int selection) throws IOException {
		return "" + selection + " was chosen";
	}
	
	/**
	 * This function is meant to be called when the user starts the game. A way to set the 
	 * amount of players for the game. Does not need to return anything.
	 * @param numberOfPlayers
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/numberOfPlayers")
	public String setNumberOfPlayers(@QueryParam("numberOfPlayers") int numberOfPlayers) throws IOException {
		this.numberOfPlayers = numberOfPlayers;
		//Game.setNUMPLAYERS = numberOfPlayers;
		//System.out.println("You choose " + numberOfPlayers + " players. Good luck!");
		return "You choose " + numberOfPlayers + " players. Good luck!";
	}
	
	/**
	 * This method has been replaced by getCardDescription and getCardValues
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/displayCard")
	public String displayCard() throws IOException {
		String cardThings = aGame.getPlayer(0).getTopCard().getDescription();
		return cardThings; 
	}
	
	/**
	 * This method returns the Description of a certain players current top card. 
	 * @param player
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getCardDescription")
	public String getCardDescription(@QueryParam("player") int player) throws IOException {
		System.out.println(aGame.getPlayer(player).getTopCard().getDescription());
		return aGame.getPlayer(player).getTopCard().getDescription();
	}
	
	@GET
	@Path("/getCardSize")
	public String getCardSize(@QueryParam("player") int player) throws IOException {
	
		return "" + aGame.getPlayer(player).getTopCard().getSize();
	}
	
	@GET
	@Path("/getCardSpeed")
	public String getCardSpeed(@QueryParam("player") int player) throws IOException {

		return "" + aGame.getPlayer(player).getTopCard().getSpeed();
	}
	
	@GET
	@Path("/getCardRange")
	public String getCardRange(@QueryParam("player") int player) throws IOException {

		return "" + aGame.getPlayer(player).getTopCard().getRange();
	}
	@GET
	@Path("/getCardFirepower")
	public String getCardFirepower(@QueryParam("player") int player) throws IOException {

		return "" + aGame.getPlayer(player).getTopCard().getFirepower();
	}
	@GET
	@Path("/getCardCargo")
	public String getCardCargo(@QueryParam("player") int player) throws IOException {

		return "" + aGame.getPlayer(player).getTopCard().getCargo();
	}
	
	/**
	 * This method returns a string with all values of a certain players current top card.
	 * @param player
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/getCardValues")
	public String getCardValues(@QueryParam("player") int player)  throws IOException {
		
		StringBuilder sb = new StringBuilder("");
		sb.append(String.format("%26s\r\n\r\n", aGame.getPlayer(player).getTopCard().getDescription()));
		sb.append(String.format("Size: %20d\r\n", aGame.getPlayer(player).getTopCard().getSize()));
		sb.append(String.format("Speed: %19d\r\n", aGame.getPlayer(player).getTopCard().getSpeed()));
		sb.append(String.format("Range: %19d\r\n",aGame.getPlayer(player).getTopCard().getRange()));
		sb.append(String.format("Firepower: %15d\r\n",aGame.getPlayer(player).getTopCard().getFirepower()));
		sb.append(String.format("Cargo: %19d\r\n",aGame.getPlayer(player).getTopCard().getCargo()));
		
		return sb.toString();
	}
	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Let's");
		listOfWords.add("Play!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	

	/**
	 * This method creates a deck, shuffles the deck and initialises a new game with said deck.
	 * 
	 * @return
	 */
	public boolean createGame() {
		try {
		TopTrumpsCLIApplication.createDeck();
		
		deck = TopTrumpsCLIApplication.getDeck();
		Collections.shuffle(Arrays.asList(deck));
		aGame = new Game(deck, numberOfPlayers);
		aGame.deal();
		
		
		}
		catch(NullPointerException e) { 
			System.out.println("NullP"); 
		}
		
		return true;
	}
	
	
	
	
}
