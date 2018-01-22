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

      /**
       * Constructor taking @param deckSize as input
       */
      public AIPlayer(int deckSize) {
          hand = new Cards[deckSize];

      }

      // // Not sure if necessary?
      // public Cards getCard(int q) {
      //   return hand[q];
      // }

      // Gets the top card.
      public Cards getTopCard() {
        while(hand[0] == null) {
          sortCards();
        }
        Cards topCard = hand[0];
        //topCard = hand[0];
        return topCard;
      }

      // Method that puts the cards of the hand in the first n slots of the hand
      private void sortCards() {
        for(int i = 0; i < hand.length; i++) {
          if(i+1 != hand.length) {
            hand[i] = hand[i+1];
          }
          else {
            hand[i] = null;
          }
        }
      }

      // Method that gets the index for the best category for a card.
      private int selectCategory(Cards topCard) {
        // I'm not entirely sure how this TimeUnit thing works, or if it has any drawbacks.
        TimeUnit.SECONDS.sleep(3);
        int bestCategory;
        int bestValue = 0;
        int [] values =  {topCard.getSize,
                          topCard.getSpeed,
                          topCard.getRange,
                          topCard.getFirepower,
                          topCard.getCargo};

        for (int i = 0; i < values; i++) {
          if (values[i] > bestValue) {
            bestValue = values[i];
            bestCategory = i + 1;
          }
        }
        return bestCategory;

      }

      }
