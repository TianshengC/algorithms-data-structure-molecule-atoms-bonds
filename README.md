# **Atom and Molecular Simulation in Java**

This project provides a simulation of atoms and molecules using Java. The primary objective is to represent atoms, their bonds, and the construction of molecules using tree structures.

## **Components**

### **1. Atom**
The `Atom` class represents an individual atom. Each atom has:

- **Element**: Denotes the type of atom (e.g., H for Hydrogen, C for Carbon).
- **List of bonds**: Connections to other atoms.
- **Valency**: Represents the atom's combining power.

The class also provides a static map, `VALENCY_MAP`, which contains the valencies for various elements. The atom's valency is used to determine how many bonds it can form.

### **2. Bond**
The `Bond` class represents a bond between two atoms. Each bond has:

- **Child**: The atom it's connected to.
- **Weight**: Represents the strength of the bond (single, double, or triple bond).

### **3. TreeMolecule**
The `TreeMolecule` class represents a molecule using a tree structure. Each molecule has:

- **First atom**: The starting point of the molecule.

The class provides methods to:
- Add bonds between atoms
- Check if an atom is part of the molecule
- Generate the molecule's SMILES string and structural formula.

## **Usage**

1. Create atoms using the `Atom` class.
2. Construct molecules using the `TreeMolecule` class.
3. Add bonds between atoms using the `addBond` method.
4. Check if an atom is part of a molecule using the `contains` method.
5. Generate the SMILES string and structural formula of the molecule using the `smilesString` and `structuralFormula` methods respectively.

## **Note**

This is a basic simulation and might not cover all the intricacies of molecular chemistry. It's designed for educational purposes and to provide a foundation for further development. This work is inspired by the Algorithms and Data Structure coursework.

