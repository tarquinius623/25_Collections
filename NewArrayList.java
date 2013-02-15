public class NewArrayList {
	
	private Object[] a;
	private int maxLength;
	
	public NewArrayList(int l){
		a = new Object[l];
		maxLength = l;
	}
	
	public int size(){
		int size = 0;
		for (int i = 0; i < maxLength - 1; i++) {
			if (a[i] != null) {
				size++;
			}
		}
		return size;
	}
	
	public boolean add(Object e) {
		for (int i = 0; i < maxLength - 1; i++) {
			if (a[i] == null) {
				a[i] = e;
				return true;
			}
		}
		Object[] b = new Object[maxLength*10];
		for (int i = 0; i < maxLength - 1; i++) {
			b[i] = a[i];
		}
		a = b;
		add(e);
		return false;
	}
	
	public Object get(int index) {
		return a[index];
	}
	
	public void set(int index, Object value) {
		a[index] = value;
	}
	
	public void remove(int index) {
		for (int i = index; i < maxLength - 1; i++) {
			a[i] = a[i + 1];
		}
	}
	
	public void addCollection(NewArrayList z) {
		for (int i = 0; i < z.size(); i++) {
			if (z.get(i) != null) {
				add(z.get(i));
			}
		}
	}
}