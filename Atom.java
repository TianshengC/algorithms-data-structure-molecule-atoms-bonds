import java.util.*;

public class Atom  {

	private String element;
	private List<Bond> bonds = new ArrayList<Bond>();//the bonds to child atoms
	private int valency;
	

        //this map has been included to look up valencies
        
        private static final Map<String, Integer> VALENCY_MAP = createMap();
        
	private static Map<String, Integer> createMap() {
		Map<String, Integer> result = new HashMap<>();
		result.put("H", 1);// hydrogen
		result.put("C", 4);// carbon
		result.put("B", 3);// boron
		result.put("N", 3);// nitrogen
		result.put("O", 2);// oxygen
		result.put("F", 1);// fluorine
		result.put("P", 3);// phosphorous
		result.put("S", 2);// sulphur
		result.put("Cl", 1);// fluorine
		result.put("Br", 1);// bromine

		return Collections.unmodifiableMap(result);
	}

	// all of the Atom constructors and methods

	// This presents a hydrogen bond which will be used in the constructor of atom.
	// It is static because it is shared by all atom instances.
	public static Bond bondDefault = new Bond(new Atom("H"), 1);

	/** The Atom constructor take the name of element as a parameter. Then set the valency and add the valency number
	 * of hydrogen bond into the bond list.
	 *
	 * @param element the name of the elements.
	 */
	public Atom(String element){
		this.element = element;
		this.valency = VALENCY_MAP.get(this.element);
		if(!element.equals("H")){ //It is mentioned in the coursework that the hydrogen atom bond list is empty
			for(int i=0; i<this.valency; i++) { //add hydrogen bond into the bond list
				this.bonds.add(bondDefault);
			}
		}
	}

	//getter and setter
	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public List<Bond> getBonds() {
		return bonds;
	}

	public void setBonds(List<Bond> bonds) {
		this.bonds = bonds;
	}

	public int getValency() {
		return valency;
	}

	public void setValency(int valency) {
		this.valency = valency;
	}

	//Helper methods below

	/**This method helps to find the number of hydrogen bonds in an atom.
	 *
	 * @return the number of hydrogen bonds in an atom.
	 * @see TreeMolecule
	 *
	 */
	public int numAvailableBond() {
		int numAvailableBond = 0;
		for (Bond bond : this.bonds) {
			if (bond.getChild().getElement().equals("H")) {
				numAvailableBond++;
			}
		}
		return numAvailableBond;
	}

	/**This method checks whether the child atoms in bonds are not hydrogen only.
	 *
	 * @return true = the child atoms have other elements except hydrogen.
	 * @see TreeMolecule
	 *
	 */
	public boolean hasValidChild(){
		for(Bond bond: this.getBonds()){
			if(!bond.getChild().getElement().equals("H")){
				return true;
			}
		}
		return false;
	}

	/**This method is used to get TreeMolecule List of bond children (exclude H element).
	 *
	 * @return TreeMolecule List of bond children (exclude H element).
	 * @see TreeMolecule
	 */
	public ArrayList<TreeMolecule> getValidChildTreeMoleculeList(){
		ArrayList<TreeMolecule> list = new ArrayList<>();
		if(this.hasValidChild()){
			for(Bond bond: this.getBonds()){ // set the children (exclude H) to a TreeMolecule List
				if(!bond.getChild().getElement().equals("H")){
					TreeMolecule temp = new TreeMolecule(bond.getChild());
					list.add(temp);
				}
			}
		}
		return list;
	}

	/**This method removed the number strength of hydrogen bonds from this atom.
	 *
	 * @param strength stands for the number of hydrogen bonds to be removed
	 * @see TreeMolecule
	 *
	 * Because it is not allowed to remove an item from the ArrayList you're iterating over,
	 * Iterator is used for remove function only.
	 */
	public void removeHydrogenBond(int strength) {
		int numBondsRemoved = 0; //As a calculator to record the total number of hydrogen bond removed
		Iterator<Bond> bondIterator = this.bonds.iterator();
		while (bondIterator.hasNext()) { //whether next item is available
			Bond bond = bondIterator.next(); //to get the next bond
			if (bond.getChild().getElement().equals("H")) { //if next bond is "H", remove it.
				bondIterator.remove();
				numBondsRemoved++;
				if (numBondsRemoved == strength) {//If enough bond removed, then break.
					break;
				}
			}
		}
	}

	/**This method try to get the Smiles String of single atom.
	 *
	 * @return Smiles String of single atom.
	 * @see TreeMolecule
	 *
	 */
	public String toStringSmilesString() {
		StringBuilder out = new StringBuilder(this.element);

		//Calculate the total weight of children bonds
		int numTotalBondWeight = 0;
		for (Bond bond : bonds) {
			numTotalBondWeight += bond.getWeight();
		}

		// Calculate the number of other bonds.
		int numOtherBonds = this.valency - numTotalBondWeight;

		// Prepend bond symbols to the output string based on the number of other bonds.
		if (numOtherBonds == 2) {
			out.insert(0, "=");
		} else if (numOtherBonds == 3) {
			out.insert(0, "#");
		}

		return out.toString();
	}


	/**This method try to get the Structural Formula of single atom.
	 *
	 * @return Structural Formula of single atom.
	 * @see TreeMolecule
	 *
	 */
	public String toStringStructuralFormula() {
		StringBuilder out = new StringBuilder(this.element);
		int numHydrogenBonds = 0;

		// Count the number of hydrogen bonds.
		for (Bond bond : bonds) {
			if (bond.getChild().getElement().equals("H")) {
				numHydrogenBonds++;
			}
		}

		// Append hydrogen bonds to the output string.
		if (numHydrogenBonds == 1) {
			out.append("H");
		} else if (numHydrogenBonds >= 2) {
			out.append("H").append(numHydrogenBonds);
		}

		//Calculate the total weight of children bonds
		int numTotalBondWeight = 0;
		for (Bond bond : bonds) {
			numTotalBondWeight += bond.getWeight();
		}

		// Calculate the number of other bonds.
		int numOtherBonds = this.valency - numTotalBondWeight;

		// Prepend bond symbols to the output string based on the number of other bonds.
		if (numOtherBonds == 2) {
			out.insert(0, "=");
		} else if (numOtherBonds == 3) {
			out.insert(0, "#");
		}

		return out.toString();
	}

}
