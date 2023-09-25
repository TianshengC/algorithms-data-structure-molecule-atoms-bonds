import java.util.ArrayList;
public class TreeMolecule implements Molecule {

	private Atom first;

	//add constructor, implemented methods and any helper methods you require

	// TreeMolecule constructor
	public TreeMolecule(Atom atom){
		this.first = atom;
	}

	// getter and setter
	public Atom getFirst() {
		return first;
	}

	public void setFirst(Atom first) {
		this.first = first;
	}

	/**This method is used to connect two atoms. A1 is the parent atom. A2 is the child atom.
	 * They both remove the strength number of hydrogen bonds which will be replaced with a new bond.
	 * A2 will lose the strength number of child bonds after the connection.
	 *
	 * @param a1 parent atom
	 * @param a2 child atom
	 * @param strength the number of hydrogen bond to be replaced.
	 * @return true if adding action is valid. Otherwise, false.
	 */
	@Override
	public boolean addBond(Atom a1, Atom a2, int strength) {
		if(a1.numAvailableBond() >= strength && a2.numAvailableBond() >= strength){ //check whether enough hydrogen bonds for connection
			a1.removeHydrogenBond(strength);//both atoms remove the strength number of hydrogen bonds
			a2.removeHydrogenBond(strength);
			Bond bondAdd = new Bond(a2, strength); //create a new bond
			a1.getBonds().add(bondAdd);//add this new bond to a1
			return true;
		}else{
			return false;
		}
	}

	/** This method is used to identify whether a actual atom is included in the molecule.
	 *
	 * @param a the atom to be checked
	 * @return true if the atom exists in the molecule. Otherwise, false.
	 *
	 */
	@Override
	public boolean contains(Atom a) {
		if(this.first == a){ // if first atom is atom a, return true.
			return true;
		}else{
			if(this.first.hasValidChild()) { //if first atom has valid Child atoms, then check the child atoms recursively.
				for (TreeMolecule temp : this.first.getValidChildTreeMoleculeList()) { // get the TreeMolecule of children.
					if (temp.contains(a)) { // check whether child TreeMolecule has this atom, if have return true.
						return true;
					}
				}
			}
			return false; // if atom a is not found, return false.
		}
	}

	/**This method aims to print the Smiles String of the TreeMolecule.
	 *
	 * @return the smiles string of this TreeMolecule.
	 *
	 */
	@Override
	public String smilesString() {
		StringBuilder smilesString = new StringBuilder();

		// Add the first atom to the smiles string
		smilesString.append(this.first.toStringSmilesString());

		// Check if the first atom has valid children
		if (this.first.hasValidChild()) {
			// Get the list of valid child TreeMolecules and size
			ArrayList<TreeMolecule> validChildList = this.first.getValidChildTreeMoleculeList();
			int childListSize = validChildList.size();

			// Append the child molecules' structural formulas
			if (childListSize == 1) { // if only one child, append it directly.
				smilesString.append(validChildList.get(0).smilesString());
			} else if (childListSize >= 2) { // if two or more children, append () and smile string.
				for (TreeMolecule temp : validChildList) {
					smilesString.append("(").append(temp.smilesString()).append(")");
				}
			}
		}
		return smilesString.toString();
	}

	/**This method aims to print the Smiles String of the TreeMolecule.
	 *
	 * @return the smiles string of this TreeMolecule.
	 *
	 */
	@Override
	public String structuralFormula() {
		StringBuilder structureFormula = new StringBuilder();

		// Add the first atom to the formula
		structureFormula.append(this.first.toStringStructuralFormula());

		// Check if the first atom has valid children
		if (this.first.hasValidChild()) {
			// Get the list of valid child TreeMolecules and size
			ArrayList<TreeMolecule> validChildList = this.first.getValidChildTreeMoleculeList();
			int childListSize = validChildList.size();

			// Append the child molecules' structural formulas
			if (childListSize == 1) {
				structureFormula.append(validChildList.get(0).structuralFormula());
			} else if (childListSize >= 2) { // if branch
				for (TreeMolecule temp : validChildList) {
					structureFormula.append("(").append(temp.structuralFormula()).append(")");
				}
			}
		}
		return structureFormula.toString();
	}

}
