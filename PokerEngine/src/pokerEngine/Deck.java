package pokerEngine;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	// Initialize an array List of type Card to be the deck.
	private ArrayList<Card> Deck = new ArrayList<Card>();
	
	// Constructor that loops through the types and creates one of each. Then shuffles.
	public Deck () {
		for (int a = 2; a < 15; a++) {
			for(int b = 0; b < 4; b++ ) {
				Card c = new Card(a,b);
				Deck.add(c);
	
			}
		}
		// Calls the shuffleDeck method
		shuffleDeck();
	}
	
	// Method to draw a card from the deck.
	public Card Draw() {
		Card temp = this.Deck.get(0);
		this.Deck.remove(0);
		return temp;
	}
	
	// Simple method to return the number of cards remaining
	public int Remaining() {
		return this.Deck.size();
	}
	
	//Simple function that uses the collections set to shuffle the deckl
	public void shuffleDeck(){
		Collections.shuffle(this.Deck);
	}

	
}
