package fr.sfvl.spike.demo1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Modele implements Serializable {
	List<Personne> listePersonne = new ArrayList<Personne>();

	public void addPersonne(Personne personne) {
		listePersonne.add(personne);
	}

	public List<Personne> getListePersonne() {
		return Collections.unmodifiableList(listePersonne);
	}

	public void setListePersonne(List<Personne> listePersonne) {
		this.listePersonne = listePersonne;
	}

	public void clear() {
		listePersonne.clear();
	}

	public void removePersonne(Personne personne) {
		listePersonne.remove(personne);
		
	}

	
}
