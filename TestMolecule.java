import java.util.ArrayList;

public class TestMolecule3 {

    // Helper methods
    public static ArrayList<Atom> atomList(int len, String type) {
        ArrayList<Atom> theAtoms = new ArrayList<Atom>();
        for (int i = 0; i < len; i++) theAtoms.add(new Atom(type));
        return theAtoms;
    }

    public static Molecule createMolecule(String name, ArrayList<Atom> hydrogenAtoms, ArrayList<Atom> carbonAtoms, ArrayList<Atom> sulfurAtoms, ArrayList<Atom> chlorineAtoms, ArrayList<Atom> oxygenAtoms) {
        Molecule mol = new TreeMolecule(carbonAtoms.get(0)); // All molecules start with a carbon atom

        if (name.equals("chloroform")) {
            mol.addBond(carbonAtoms.get(0), hydrogenAtoms.get(0), 1);
            mol.addBond(carbonAtoms.get(0), chlorineAtoms.get(0), 1);
            mol.addBond(carbonAtoms.get(0), chlorineAtoms.get(1), 1);
            mol.addBond(carbonAtoms.get(0), chlorineAtoms.get(2), 1);
            return mol;
        }

        if (name.equals("sulfuric acid")) {
            mol.addBond(sulfurAtoms.get(0), oxygenAtoms.get(0), 2);
            mol.addBond(sulfurAtoms.get(0), oxygenAtoms.get(1), 2);
            mol.addBond(sulfurAtoms.get(0), oxygenAtoms.get(2), 1);
            mol.addBond(sulfurAtoms.get(0), oxygenAtoms.get(3), 1);
            mol.addBond(oxygenAtoms.get(2), hydrogenAtoms.get(0), 1);
            mol.addBond(oxygenAtoms.get(3), hydrogenAtoms.get(1), 1);
            return mol;
        }

        return null;
    }

    public static void main(String[] args) {
        ArrayList<Atom> hydrogenAtoms = atomList(10, "H");
        ArrayList<Atom> carbonAtoms = atomList(10, "C");
        ArrayList<Atom> sulfurAtoms = atomList(10, "S");
        ArrayList<Atom> chlorineAtoms = atomList(10, "Cl");
        ArrayList<Atom> oxygenAtoms = atomList(10, "O");

        String name = "sulfuric acid";
        Molecule mol = createMolecule(name, hydrogenAtoms, carbonAtoms, sulfurAtoms, chlorineAtoms, oxygenAtoms);
        System.out.println(name + " has smiles string " + mol.smilesString());
        System.out.println(name + " and structure " + mol.structuralFormula());

        boolean containsAtom = mol.contains(oxygenAtoms.get(2));
        String output = "";
        if (containsAtom) output += " contains ";
        else output += " doesn't contain ";
        output += "the atom in question";
        System.out.println(name + "" + output);

        int a = sulfurAtoms.get(0).numAvailableBond();
        System.out.println("" + a);
    }
}

