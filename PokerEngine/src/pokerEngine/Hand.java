package pokerEngine;

import java.util.ArrayList;
import java.util.Collections;

public class Hand extends Deck {
	// Create a new list of cards to use as the had.
	private ArrayList<Card> hand = new ArrayList<Card>();

	// Method to draw a hand from the deck.
	public void drawAHand(Deck deckForHand){
		//Enacts the draw function 5 times.
		for(int i = 1; i<=5; i++){
			Card c1 = deckForHand.Draw();
			hand.add(c1);
		}
	}
	// Method to print the hand out
	public void showHand(){
		//Loops through the hand and converts the number to the name of the card
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank() < 11)
				System.out.print(hand.get(i).getRank() + " of ");
			else if (hand.get(i).getRank() == 11)
				System.out.print("Jack of ");
			else if (hand.get(i).getRank() == 12)
				System.out.print("Queen of ");
			else if (hand.get(i).getRank() == 13)
				System.out.print("King of ");
			else if (hand.get(i).getRank() == 14)
				System.out.print("Ace of ");
			
			if(hand.get(i).getSuit() == 0)
				System.out.println("Spades");
			else if (hand.get(i).getSuit() == 1)
				System.out.println("Clubs");
			else if (hand.get(i).getSuit() == 2)
				System.out.println("Hearts");
			else if (hand.get(i).getSuit() == 3)
				System.out.println("Diamonds");
		}
	}
	
	public static int judgeHand(Hand judged) {
		// First initialize a bunch of flags about the different hand outcomes.
		boolean threePair = false;
		boolean onePair = false;
		boolean twoPair = false;
		boolean fullHouse = false;
		boolean royalFlush = false;
		boolean fourKind = false;
		boolean flush = false;
		boolean straight = false;
		boolean straightFlush = false;
		// Initialize a number of pair for use later
		int numberPair = 0;
		// Create two array lists that determine the ranks and suites in the hand.
		ArrayList<Integer> Ranks = new ArrayList<Integer>();
		ArrayList<Integer> suits = new ArrayList<Integer>();
		
		// Populate the two arrayLists used to determine hands.
		for (int i = 0; i < judged.hand.size(); i++) {
			Ranks.add(judged.hand.get(i).getRank());
			suits.add(judged.hand.get(i).getSuit());
			// Sort ranks to make it usable later on.
			Collections.sort(Ranks);
		}
		
		// Loops through to see if they have a royal Flush first checking for all of the appropriate cards for a royal flush
		if((Ranks.contains(11) && (Ranks.contains(12)))) {
			if((Ranks.contains(13) && (Ranks.contains(14)))) {
				if(Ranks.contains(10)) {
					// If there is a 10, jack, queen, king, ace then check if they are of the same rank
					int n = 0;
					// Loops through to check for all of the same suits.
					for(int i = 0; i < judged.hand.size(); i++) {
					if(suits.get(0) == suits.get(i)) {
						n++;
					}		
					}
					// If the suits are the same then set the royalFlush flag to true
					if(n == 5){
						royalFlush = true;
					}
				}
			}
		}
		
		// This loops through the hand to check for duplicates
		for(int i = 0; i < judged.hand.size(); i++) {
			// Each time reset n to 0 since you're checking for duplicates of the same card
			int n = 0;
			// Compare the i-th card to all of the cards
			for(int j = 0; j < judged.hand.size(); j++) {
				if(judged.hand.get(i).getRank() == judged.hand.get(j).getRank())
					n++;
			}
			// If n == 3 then there are 3 of a kind 
			if(n == 3){
				threePair = true;
				//int PairOf = judged.hand.get(i).getRank();
			// If n == 2 then there are 2, and one pair gets added to the counter
			} else if(n == 2){
				onePair = true;
				numberPair++;
			// if n == 4 there are four of a kind.
			} else if(n == 4) {
				fourKind = true;
			}
			// If number pair == 4 (each pair ups the counter twice since the second card of each pair
			// also ups numberPair) then there are two pairs.
			if(numberPair == 4) {
				twoPair = true;
			}
		}
		
		// If the conditions are ture for a fullhouse flip that switch
		if(threePair == true && onePair == true)
			fullHouse = true;
		//Reinitialize the counter back to 0 for the next flag
		int n =0;
		// Loops through comparing each suit to the first to ensure they are all the same
		for(int i = 0; i < judged.hand.size(); i++) {
			if(suits.get(0) == suits.get(i)) {
				n++;
			}		
			}
		// if n++ was triggered 5 times (once for each card) then you have a flush on hand.
		if(n == 5){
				flush = true; 
			}
		// Reset n = 0 for the next counter.
		n = 0;
		// Loop through all of the cards
		for(int i = 0; i < judged.hand.size(); i++) {
		// Each card is represented by an integer then each next card should be one more than the card before it
			if(judged.hand.get(i).getRank() == judged.hand.get(0).getRank() + i)
				n++;
		}
		// If n++ was triggered 5 times then the hand is a straight.
		if(n==5) {
			straight = true;
		}
		// If straight and flush are true then it is a straight flush
		if(straight == true && flush == true)
			straightFlush = true;
		
		// Return a score based off of the hands ranking.
		if(royalFlush == true)
			return 10;
		else if (straightFlush == true)
			return 9;
		else if (fourKind == true)
			return 8;
		else if (fullHouse == true)
			return 7;
		else if (flush == true)
			return 6;
		else if (straight == true)
			return 5;
		else if (threePair == true)
			return 4;
		else if(twoPair == true)
			return 3;
		else if(onePair == true)
			return 2;
		else
			return 0;
}
	
	public static void judgeHand(Hand[] hands) {
		int currentWinner = 0;
		// Initialized to -1 so the first score of 0 won't trigger a tie breaker
		int highScore = -1;
		for (int i = 0; i < hands.length; i++){
			// Show hands for the TAs sake so they know engine is working
			System.out.println("Player "  + (i + 1) + "'s hand:");
			hands[i].showHand();
			int score = Hand.judgeHand(hands[i]);
			System.out.println("Score: " + score + "\n");
			if (score > highScore){
				currentWinner = i;
				highScore = score;
			// If there is a tie then compare the highest card of each hand unless its a full house which is handeled below
			} else if((score == highScore) && (score != 7)) {
				// Create a list of the two ranks
				ArrayList<Integer> ranks1 = new ArrayList<Integer>();
				ArrayList<Integer> ranks2 = new ArrayList<Integer>();
				// Populate the two arrayLists used to determine hands.
					for(int k = 0; k < 5; k++){
						ranks1.add(hands[i].hand.get(k).getRank());
						ranks2.add(hands[currentWinner].hand.get(k).getRank());
					}
				// Sort the ranks so the highest value is first
				Collections.sort(ranks1);
				Collections.sort(ranks2);
				// Compare the last card in each hand.
				if(ranks1.get(ranks1.size() - 1) > ranks2.get(ranks2.size() - 1))
					currentWinner = i;
				}
			// If the tie is full house
			else if((score == highScore) && (score == 7)) {
				ArrayList<Integer> ranks1 = new ArrayList<Integer>();
				ArrayList<Integer> ranks2 = new ArrayList<Integer>();
				
				// Populate the two arrayLists used to determine hands.
					for(int k = 0; k < 5; k++){
						ranks1.add(hands[i].hand.get(k).getRank());
						ranks2.add(hands[currentWinner].hand.get(k).getRank());
					}
					Collections.sort(ranks1);
					Collections.sort(ranks2);
				if(ranks1.get(2) > ranks2.get(2))
					currentWinner = i;
			}
			}
		// Print out the winner and their hand to prove engien is working for TAs
		System.out.println("The winner is player " + (currentWinner +1));
		System.out.println("With a winning hand of: ");
		hands[currentWinner].showHand();
		}
	}

