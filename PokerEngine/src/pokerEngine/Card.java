package pokerEngine;

public class Card {
	// Initialize the two variables associated with an instance of a card
	private int rank;
	private int suit;
	
	//Constructs the Card class
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	// Non public getters for use later in the program
	protected int getRank() {
		return rank;
	}
	protected int getSuit() {
		return suit;
	}
}
