public class Card {
	private int number;
	private String suit;
	
	public Card (int n, String s){
		number = n;
		suit = s;
	}
	
	public int getNumber(){
		return number;
	}
	
	public void setNumber(int n){
		number = n;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public void setString(String s){
		suit = s;
	}
	
	public String toString(){
		return number + suit;
	}
}