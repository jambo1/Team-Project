package commandline;

import java.util.concurrent.TimeUnit;
  /**
    *
    *
    *
    *
    */

    public class AIPlayer {
      /*
       * Instance variables for the AIPlayer
       */
      private Cards[] hand;
      private final int DECKSIZE = 40;
      private final int NUMPLAYERS =5;
      private boolean timer = true;

      /**
       * Constructor taking @param deckSize as input
       */
      public AIPlayer() {
          hand = new Cards[DECKSIZE];
          }

      // // Not sure if necessary?
      // public Cards getCard(int q) {
      //   return hand[q];
      // }

      // Gets the top card.
/*      public Cards getTopCard() {
        while(hand[0] == null) {
          sortCards();
          if(hand[0]==null)	{
        	System.out.println("Player out?");
        	break;
          }
        }
        Cards topCard = hand[0];
        //topCard = hand[0];
        return topCard;
      }
*/
      // Method that puts the cards of the hand in the first n slots of the hand
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

      // Method that gets the index for the best category for a card.
      public int selectCategory(Cards topCard) {
        // I'm not entirely sure how this TimeUnit thing works, or if it has any drawbacks.
        if(timer ==true){
        	try {
	          TimeUnit.SECONDS.sleep(3);
	        }
	        catch (InterruptedException e) {
	          System.err.println("Timer interrupted.");
	        }
        }
        int bestCategory = 0;
        int bestValue = 0;
        int [] values =  {topCard.getSize(),
                          topCard.getSpeed(),
                    topCard.getRange(),
                          topCard.getFirepower(),
                          topCard.getCargo()
                          };

        for (int i = 0; i < values.length; i++) {
          if (values[i] > bestValue) {
            bestValue = values[i];
            bestCategory = i + 1;
          }
        }
        return bestCategory;

      }
      //THIS IS WHERE ALL OUR PROBLEMS LIE
      /*
       * Now starts searching for empty hand slots in array element[1], instead of [0], because when hand is
       * empty card will be inserted into slot 2, but getTopCard() will automatically shunt it up to slot [0]
       * ap = active pile, cp = communal pile
       */
      public void givePlayerCards(Cards[] ap, Cards[] cp)	{
    	 int comCount = 0, activeCount = 0;
    	 int p=0,l=0;
    	  while(p<40) {
    		  if(cp[p]!=null) {
    		  System.out.println(cp[p].getDescription() + "-------com");
    		  }
    		  p++;
    	  }
    	  while(l<5&&ap[l]!=null) {
    		  System.out.println(ap[l].getDescription() + "--------act");
    		  l++;
    	  }
    	  for(int i=0; i<hand.length; i++)	{
    		  //break if activecount is 5
    		  if(activeCount>4)	{break;}
    		  //Add the communal pile cards first
    		  if(hand[i]==null&&cp[comCount]!=null)	{
    			  hand[i]=cp[comCount];
    			  comCount++;
    			  }
    		  //Add the active pile cards second
    		  else if(hand[i]==null&&ap[activeCount]!=null)	{
    			  hand[i] = ap[activeCount];
    			  activeCount++;
    		  }
    		  //Else increment active count to account for when a player is put out which leaves empty slots in the active pile
    		  else if(hand[i]==null&&ap[activeCount]==null){
    			  activeCount++;
    		  }
    		  else {
    			  ;
    		  }
    		  
    	  }
      }
      
      /**
       * Sets the top card in the hand to null
       */
      public void nullTopCard()	{hand[0]=null;}
      
      /**
       * Returns the top card
       * @return
       */
      public Cards getTopCard()	{return hand[0];}
      /**
       * Returns the platers hand
       * @return
       */
      public Cards[] getPlayerHand() {return hand;}
      
      /**
       * Prints the players hand
       */
      public void printHand() {
    	  int i = 0;
    	  while(getPlayerHand()[i]!=null&&i<40) {
    		  System.out.println(getPlayerHand()[i].getDescription());
    		  i++;
    	  }
      }
      
      public void turnOffTimer() {timer = false;}

    }
