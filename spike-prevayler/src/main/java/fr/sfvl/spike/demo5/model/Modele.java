package fr.sfvl.spike.demo5.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.sfvl.spike.demo5.core.CoreModele;


public class Modele extends CoreModele implements Serializable {
	List<Personne> listePersonne = new ArrayList<Personne>();
	List<Entreprise> listeEntreprise = new ArrayList<Entreprise>();

	public void addPersonne(Personne personne) {
		listePersonne.add(personne);
	}

	public List<Personne> getListePersonne() {
		return Collections.unmodifiableList(listePersonne);
	}

	public void setListePersonne(List<Personne> listePersonne) {
		this.listePersonne = listePersonne;
	}

//	public void clear() {
//		listePersonne.clear();
//		listeEntreprise.clear();
//	}

	public void removePersonne(Personne personne) {
		listePersonne.remove(personne);
		
	}

	public void addEntreprise(Entreprise entreprise) {
		listeEntreprise.add(entreprise);
	}

	public List<Entreprise> getListeEntreprise() {
		return Collections.unmodifiableList(listeEntreprise);
	}

	public void lierPersonneEntreprise(Personne personne, Entreprise entreprise) {
		Personne p = getPersonne(personne);
		Entreprise e = getEntreprise(entreprise);
		if (p != null && e != null) {
			p.addEntreprise(e);
			e.addPersonne(p);
		}
	}

	private Entreprise getEntreprise(Entreprise entrepriseCherchee) {
		return chercher(entrepriseCherchee, listeEntreprise);
	}

	private Personne getPersonne(Personne personneCherchee) {
		return chercher(personneCherchee, listePersonne);
	}


	@Override
	protected Object getField(Field field) throws IllegalArgumentException, IllegalAccessException {
		return field.get(this);
	}

	
}
