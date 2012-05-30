package fr.sfvl.spike.demo6;

import java.util.Date;

import org.prevayler.Transaction;
import org.prevayler.TransactionWithQuery;

public class ModeleTransaction {
	
	public static abstract class GenericTransactionWithQuery<M, T> implements TransactionWithQuery {
		public final T objet;
		
		 public GenericTransactionWithQuery(T objet) {
			 this.objet = objet;
		 }
		public final Object executeAndQuery(Object prevalentSystem, Date executionTime)
				throws Exception {
	        genericExecuteAndQuery((M)prevalentSystem, executionTime);
	        return objet;
		}
		
		public abstract void genericExecuteAndQuery(M prevalentSystem, Date executionTime) throws Exception;
		
	}
	
	public static class AddPersonne extends GenericTransactionWithQuery<Modele, Personne> {
		public static AddPersonne query(String nom, String prenom) {
			return new AddPersonne(new Personne(nom, prenom));
		}
	    public AddPersonne(Personne objet) {
			super(objet);
		}

		public void genericExecuteAndQuery(Modele prevalentSystem, Date executionTime)
				throws Exception {
			prevalentSystem.addPersonne(objet);
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
