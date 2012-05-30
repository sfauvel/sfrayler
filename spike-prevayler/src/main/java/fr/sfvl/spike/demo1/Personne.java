package fr.sfvl.spike.demo1;

import java.io.Serializable;

public class Personne implements Serializable {
	private String nom;
	private String prenom;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Personne) {
			Personne o = (Personne) obj;
			return isEquals(nom, o.nom) && isEquals(prenom, o.prenom);
		}
		return false;
	}

	private boolean isEquals(String s1, String s2) {
		return String.valueOf(s1).equals(String.valueOf(s2));
	}

	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
