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
        try {
          TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e) {
          System.err.println("Timer interrupted.");
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
       */
      public void givePlayerCards(Cards[] ap, Cards[] cp)	{
    	  int comCount = 0, activeCount = 0;
    	 int p=0,l=0,m=0;
    	  while(p<40) {
    		  System.out.println(cp[p] + "-------com");
    		  p++;
    	  }
    	  while(l<5) {
    		  if(ap[l]==null)	{
    			  System.out.println(l + "null--------act");
    		  }
    		  else {
    		  System.out.println(ap[l].getDescription() + "--------act");
    		  }
    		  l++;
    	  }
    	  for(int i=0; i<hand.length; i++)	{
    		  
    		  if(activeCount>4)	{break;}
    		  
    		  if(hand[i]==null&&cp[comCount]!=null)	{
    			  hand[i]=cp[comCount];
    			  comCount++;
    			  }
    		  else if(hand[i]==null&&ap[activeCount]!=null)	{
    			  hand[i] = ap[activeCount];
    			  activeCount++;
    		  }
    		  else if(hand[i]==null&&cp[comCount]==null&&ap[activeCount]==null)	{
    			  ;
    		  }
    	  }
    	  while(m<5)	{
    		  ap[m]=null;
    		  m++;
    	  }
      }

      public void nullTopCard()	{hand[0]=null;}
      
      public Cards getTopCard()	{return hand[0];}
      public Cards[] getPlayerHand() {return hand;}
      
      public void printHand() {
    	  int i = 0;
    	  while(getPlayerHand()[i]!=null&&i<40) {
    		  System.out.println(getPlayerHand()[i].getDescription());
    		  i++;
    	  }
      }

    }
