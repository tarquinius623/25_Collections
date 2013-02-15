public class Tester {
	
	public static void main(String[] args){
		NewArrayList n = new NewArrayList(100);
		System.out.println("Size: "+n.size());
		System.out.println("");
		
		n.add(1);
		System.out.println("Size: "+n.size());
		System.out.println("0: "+n.get(0));
		System.out.println("");
		
		n.add(4);
		System.out.println("Size: "+n.size());
		System.out.println("0: "+n.get(0));
		System.out.println("1: "+n.get(1));
		System.out.println("");
		
		n.set(0, 2);
		System.out.println("Size: "+n.size());
		System.out.println("0: "+n.get(0));
		System.out.println("1: "+n.get(1));
		System.out.println("");
		
		n.remove(0);
		System.out.println("Size: "+n.size());
		System.out.println("0: "+n.get(0));
		System.out.println("1: "+n.get(1));
		System.out.println("");
		
		NewArrayList o = new NewArrayList(10);
		System.out.println("Old Size: "+o.size());
		o.addCollection(n);
		System.out.println("New Size: "+o.size());
		System.out.println("");
		
		for (int i = 0; i < 13; i++) {
			n.add(2);
			System.out.println(i+": "+n.get(i));
		}

		
		
	}
	
}