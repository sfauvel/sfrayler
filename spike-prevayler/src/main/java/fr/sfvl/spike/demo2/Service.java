package fr.sfvl.spike.demo2;

import java.util.List;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import fr.sfvl.spike.demo2.ModeleTransaction.AddPersonne;
import fr.sfvl.spike.demo2.ModeleTransaction.ClearModele;
import fr.sfvl.spike.demo2.ModeleTransaction.RemovePersonne;


public class Service {
	
	Prevayler prevayler;
	Modele modele;
	
	public Service(String bd) throws Exception {
		prevayler = PrevaylerFactory.createPrevayler(new Modele(), bd);
		modele = (Modele) prevayler.prevalentSystem();
	}
	public Personne addPersonne(final String nom, final String prenom) throws Exception {
		return (Personne) prevayler.execute(new AddPersonne(nom, prenom));
	}
	
	public void removePersonne(final String nom, final String prenom) {
		prevayler.execute(new RemovePersonne(nom, prenom));
	}
	
	public void clearModele() {
		prevayler.execute(new ClearModele());
	}

	public void takeSnapshot() throws Exception {
		prevayler.takeSnapshot();
	}
	public List<Personne> getListePersonne() {
		return modele.getListePersonne();
	}
}
