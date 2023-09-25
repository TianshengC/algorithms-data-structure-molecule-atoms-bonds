
//a bond is a pair (Atom, weight) 
public class Bond {


	private Atom child;
	private int weight;
	
	//add constructor, getters setters

	// Bond constructor
	public Bond (Atom c, int w){
		this.child = c;
		this.weight = w;
	}

	//getter and setter
	public Atom getChild() {
		return child;
	}

	public void setChild(Atom child) {
		this.child = child;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}


}
