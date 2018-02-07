package commandline;

import java.util.concurrent.TimeUnit;
 	/**
 	 * Class for players, holds their personal hand of cards and performs operations on this during gameplay
 	 *
 	 */
///MORE TESTS

	public class AIPlayer {
		/*
		 * Instance variables for the AIPlayer
		 */
		private Cards[] hand;
		private final int DECKSIZE = 40;

		/**
		 * Constructor taking @param deckSize as input
		 */
		public AIPlayer() {
			hand = new Cards[DECKSIZE];
		}
		
		/**
		 * Moves all cards in the players hand down one position to process when the top card has been removed
		 * from their hand. 
		 */
		public void sortCards() {
			if(hand[0]==null&&hand[1]!=null)	{
				for(int i = 0; i < hand.length; i++) {
					if(i+1 != hand.length) {
						hand[i] = hand[i+1];
					}
					else	{
						hand[i] = null;
					}
				}
			}
			else	{
				;
			}
		}

		/**
		 * Returns the index of the best category in the card given to the method.
		 * Used to calculate which category the AI players should use
		 * @param topCard
		 * @return
		 */
		public int selectCategory(Cards topCard, boolean timer) {
		//If the human player is still in the game a timer is used between moves to enhance gameplay
		if(timer == true) {
	    	try {
	          TimeUnit.SECONDS.sleep(3);
	        }
	        catch (InterruptedException e) {
	          System.err.println("Timer interrupted.");
	        }
		}
        
		int bestCategory = 0;
        int bestValue = 0;
        //Gets each of the values of the categories on the card
        int [] values =  {topCard.getSize(),
        		topCard.getSpeed(),
        		topCard.getRange(),
        		topCard.getFirepower(),
        		topCard.getCargo()
        };
        //Loops through the values and finds the best
        for (int i = 0; i < values.length; i++) {
        	if (values[i] > bestValue) {
        		bestValue = values[i];
        		bestCategory = i + 1;
        	}
        }
        //Returns the highest number category
        return bestCategory;
		}
		
		/**
		 * Gives the winning player the cards they have won at the end of the round
		 * ap = active pile
		 * cp = communal pile
		 * @param ap
		 * @param cp
		 */
		public void givePlayerCards(Cards[] ap, Cards[] cp)	{
/*			int p=0,l=0,m=0;
			while(p<40) {
				System.out.println(cp[p] + "-------com");
				p++;
			}
			while(l<5) {
				if(ap[l]==null)	{
					//System.out.println(l + "null--------act");
				}
				else {
					//System.out.println(ap[l].getDescription() + "--------act");
				}
				l++;
			}
*/
			//Gives the player the communal cards in available slots
			for(int i=0; i<hand.length; i++)	{
				if(hand[i]==null)	{
					for(int j=0; j<DECKSIZE; j++)	{
						if(cp[j]!=null)	{
							hand[i]=cp[j];
							cp[j]=null;
							break;
						}
					}
				}
				//Gives the player the cards from the active pile in available slots
				if(hand[i]==null)	{
					for(int j=0; j<5; j++)	{
						if(ap[j]!=null)	{
							hand[i]=ap[j];
							ap[j]=null;
							break;
						}
					}
				}
			}
		}
		
		//Sets the top card in the players hand to null
		public void nullTopCard()	{hand[0]=null;}
		
		//Returns the top card in the players hand
		public Cards getTopCard()	{return hand[0];}
		
		//Returns the entire players hand
		public Cards[] getPlayerHand() {return hand;}
      
		//Prints the players hand
		public void printHand() {
			int i = 0;
			while(i<40&&getPlayerHand()[i]!=null) {
				System.out.println(getPlayerHand()[i].getDescription());
				i++;
			}
		}  
	}
