import java.util.*;
import java.io.*;

public class PokerSimulator {
	
	private Deck d;
	private ArrayList<Card> hand;
	
	public static void main(String[] args){
		PokerSimulator p = new PokerSimulator();
	}
	
	public PokerSimulator(){
		Deck d = new Deck();
		int counter = 0;
		//d.print();
		d.shuffle();
		//System.out.println("");
		//d.print();
		hand = new ArrayList<Card>();
		hand = play(d, counter, hand);
		/*hand.set(0, new Card(10, "a"));
		hand.set(1, new Card(11, "a"));
		hand.set(2, new Card(12, "a"));
		hand.set(3, new Card(13, "a"));
		hand.set(4, new Card(1, "a"));*/
		System.out.println(score(hand));
	}
	
	public ArrayList<Card> play(Deck d, int counter, ArrayList<Card> hand){
	//Code for Buffered Reader borrowed from http://alvinalexander.com/java/edu/pj/pj010005
		if (counter == 0){
			draw(5, d, hand);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("How many cards do you want to keep (all, some, none)? ");
			String prompt = null;
			try {
				prompt = br.readLine();
		    } catch (IOException ioe) {
		    	System.out.println("IO error trying to read your input!");
		    	System.exit(1);
		    }
			if (prompt.equals("all")) {
				System.out.println("All cards kept.");
				System.out.println("");
			} else if (prompt.equals("none")) {
				System.out.println("No cards kept.");
				System.out.println("");
				hand = new ArrayList<Card>();
				draw(5, d, hand);
				counter++;
				play(d, counter, hand);
			} else if (prompt.equals("some")) {
				System.out.println("Which cards would you like to discard (i.e., '3a,5d,4b)? ");
				String keepers = null;
				try {
					keepers = br.readLine();
			    } catch (IOException ioe) {
			    	System.out.println("IO error trying to read your input!");
			    	System.exit(1);
			    }
				discardSome(keepers, d, hand);
				counter++;
				play(d, counter, hand);
			}
		}
		if (counter == 1) {
			//System.out.println(d.getSize());
		}
		return hand;
	}
	
	public void draw(int num, Deck d, ArrayList<Card> h) {
		for (int i = 0; i < num; i++) {
			Card currentCard = d.remove(i);
			h.add(currentCard);
		}
		System.out.println(h);
	}
	
	public void discardSome(String s, Deck d, ArrayList<Card> h){
		String delims = "[,]";
		String[] sArray = s.split(delims);
		for (int index = 0; index < sArray.length; index++){
			for (int i2 = 0; i2 < h.size(); i2++){
				Card checkingCard = h.get(i2);
				if (sArray[index].equals(checkingCard.toString())) {
					h.remove(i2);
					break;
				}
			}
		}
		draw(sArray.length, d, h);
	}
	
	public String score(ArrayList<Card> h) {
		System.out.println("");
		System.out.println("Scoring Now!");
		
		//Sort consecutively
		System.out.println("");
		System.out.println("Sorting now!");
		System.out.println("");
		int swapCounter = 1;
		while (swapCounter != 0){
			swapCounter = 0;
			for (int index = 0; index < h.size() - 1; index++){
				Card checkingCard = h.get(index);
				Integer compare1 = checkingCard.getNumber();
				Card checkedCard = h.get(index+1);
				Integer compare2 = checkedCard.getNumber();
				if (compare1 > compare2){
					h.set(index,checkedCard);
					h.set(index+1, checkingCard);
					swapCounter++;
				}
			}
		}
		System.out.println(h);
		
		//Check Flush	
		Iterator i = h.iterator();
		Card checkingCard = (Card) i.next();
		String flushCheck = checkingCard.getSuit();
		int flushCounter = 0;
		int royalCounter = 0;
		while (i.hasNext()){
			Card checkedCard = (Card) i.next();
			if (flushCheck.equals(checkedCard.getSuit())){
				flushCounter++;
			}
		}
		if (flushCounter == 4) {
			i = h.iterator();
			int straightCounter = 0;
			checkingCard = (Card) i.next();
			Integer straightCheck = checkingCard.getNumber();
			while (i.hasNext()){
				Card checkedCard = (Card) i.next();
				if (checkingCard.getNumber() == 1 && checkedCard.getNumber() == 10){
					straightCounter++;
					straightCheck = 10;
					h.set(0, new Card(14, checkingCard.getSuit()));
				} else {
					if (straightCheck == (checkedCard.getNumber() - 1)){
						straightCounter++;
					} else {
						break;
					}
					straightCheck++;
				}
			}
			if (straightCounter == 4){
				i = h.iterator();
				while (i.hasNext()){
					Card royalCheck = (Card) i.next();
					if (royalCheck.getNumber() >= 10){
						royalCounter++;
					}
				}
				if (royalCounter == 5) {
					return "Royal Flush!";
				} else {
					return "Straight Flush!";
				}
			} else {
				return "Flush!";
			}
		}
		
		//Straight check
		i = h.iterator();
		int straightCounter = 0;
		checkingCard = (Card) i.next();
		Integer straightCheck = checkingCard.getNumber();
		while (i.hasNext()){
			Card checkedCard = (Card) i.next();
			if (checkingCard.getNumber() == 1 && checkedCard.getNumber() == 10){
				straightCounter++;
				straightCheck = 10;
			} else {
				if (straightCheck == (checkedCard.getNumber() - 1)){
					straightCounter++;
				} else {
					break;
				}
				straightCheck++;
			}
		}
		if (straightCounter == 4){
			return "Straight!";
		}
		//Pair check

		int counter = 0;
		int pairCounter = 0;
		int threeCounter = 0;
		
			for (int index = 0; index < h.size() - 1; index++){
				counter = 0;
				checkingCard = h.get(index);
				Integer check = checkingCard.getNumber();
				for (int index2 = index + 1; index2 < h.size(); index2++){
					Card checkedCard = h.get(index2);
					if (check == checkedCard.getNumber()){
						counter++;
					}
				}
				if (counter == 3){
					return "Four of a kind!";
				} else if (counter == 2){
					index = index + 2;
					threeCounter++;
				} else if (counter == 2 && pairCounter == 1){
					return "Full House!";
				} else if (counter == 1) {
					pairCounter++;
				}
			}
			
		if (pairCounter == 2){
			return "Two pair!";
		}  else if (pairCounter == 1 && threeCounter == 1){
			return "Full House!";
		} else if (threeCounter == 1){
			return "Three of a kind!";
		} else if (pairCounter == 1) {
			return "One pair!";
		}
		return "No pair!";
	}
	
}