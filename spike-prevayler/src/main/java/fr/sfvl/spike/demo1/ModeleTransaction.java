package fr.sfvl.spike.demo1;

import java.util.Date;

import org.prevayler.Transaction;
import org.prevayler.TransactionWithQuery;

public class ModeleTransaction {
	public static class AddPersonne implements TransactionWithQuery {

		private final Personne personne;

	    public AddPersonne(String nom, String prenom) {
	        this.personne = new Personne(nom, prenom);
	    }

		public Object executeAndQuery(Object prevalentSystem, Date executionTime)
				throws Exception {
			((Modele)prevalentSystem).addPersonne(personne);
	        return personne;
		}
	}
	
	public static class RemovePersonne implements Transaction {
		private final Personne personne;

	    public RemovePersonne(String nom, String prenom) {
	        this.personne = new Personne(nom, prenom);
	    }
		public void executeOn(Object prevalentSystem, Date executionTime) {
			((Modele)prevalentSystem).removePersonne(personne);
		}
	}
	
	public static class ClearModele implements Transaction {

		public void executeOn(Object prevalentSystem, Date executionTime) {
			((Modele)prevalentSystem).clear();
		}
	}
}
