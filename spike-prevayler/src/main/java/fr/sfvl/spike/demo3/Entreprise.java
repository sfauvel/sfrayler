package fr.sfvl.spike.demo3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entreprise implements Serializable {
	private String nom;
	private List<Personne> listePersonne = new ArrayList<Personne>();

	public Entreprise(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Entreprise) {
			Entreprise o = (Entreprise) obj;
			return isEquals(nom, o.nom);
		}
		return false;
	}

	private boolean isEquals(String s1, String s2) {
		return String.valueOf(s1).equals(String.valueOf(s2));
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addPersonne(Personne personne) {
		listePersonne.add(personne);
	}

	public List<Personne> getListePersonne() {
		return listePersonne;
	}

}
