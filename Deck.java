import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	private Card addingCard;
	private String[] suitArray;
	
	public Deck() {
		cards = new ArrayList<Card>();
		suitArray = new String[4];
		suitArray[0] = "a";
		suitArray[1] = "b";
		suitArray[2] = "c";
		suitArray[3] = "d";
		for (int i = 0; i < 13; i++) {
			for (int i1 = 0; i1 < 4; i1++){
				addingCard = new Card(i+1, suitArray[i1]);
				cards.add(addingCard);
			}
		}
	}
	
	public void shuffle(){
		Card shufflingCard;
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		Random r = new Random();
		int randomCardNumber;
		for (int i2 = 0; i2 < 51; i2++) {
			randomCardNumber = 0;
			while(randomCardNumber == 0){
				randomCardNumber = r.nextInt(cards.size());
			}
			shufflingCard = cards.get(randomCardNumber);
			cards.remove(randomCardNumber);
			shuffledDeck.add(shufflingCard);
		}
		shufflingCard = cards.get(0);
		cards.remove(0);
		shuffledDeck.add(shufflingCard);
		cards = shuffledDeck;
	}
	
	public Card get(int i){
		return cards.get(i);
	}
	
	public Card remove(int i){
		return cards.remove(i);
	}
	
	public void print() {
		for (int i = 0; i < cards.size(); i++){
			System.out.println(cards.get(i));			
		}
		System.out.println("");
		System.out.println(cards.size());
	}
	
	public int getSize() {
		return cards.size();
	}
}