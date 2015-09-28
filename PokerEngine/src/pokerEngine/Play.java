package pokerEngine;

public class Play {

	public static void main(String[] args) {
		// Initialize the number of players
		int NumberOfPlayers = 2;
		// Create a new deck
		Deck deck1 = new Deck();
		// Makes an array of the hand type.
		Hand[] toBeJudged;
		toBeJudged = new Hand[NumberOfPlayers];
		
		// Draw a hand for each player
		for(int i = 0; i < NumberOfPlayers; i++){
			Hand tempHand = new Hand();
			tempHand.drawAHand(deck1);
			toBeJudged[i] = tempHand;
		}
		
		// Judge the array of hands and prints results to show TAs the engine is working.
		Hand.judgeHand(toBeJudged);
	}

}
