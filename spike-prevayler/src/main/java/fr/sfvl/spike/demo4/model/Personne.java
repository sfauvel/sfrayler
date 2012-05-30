package fr.sfvl.spike.demo4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Personne implements Serializable {
	private String nom;
	private String prenom;
	private List<Entreprise> listeEntreprise = new ArrayList<Entreprise>();

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
	Personne() {
		System.out.println("Personne.Personne() " + this);
	}
	Personne(String nom, String prenom) {
		super();
		System.out.println("Personne.Personne(String nom, String prenom) " + this);
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void addEntreprise(Entreprise e) {
		listeEntreprise.add(e);
	}

	public List<Entreprise> getListeEntreprise() {
		return Collections.unmodifiableList(listeEntreprise);
	}
	
}
