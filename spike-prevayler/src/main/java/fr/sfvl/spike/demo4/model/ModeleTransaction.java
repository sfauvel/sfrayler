package fr.sfvl.spike.demo4.model;

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
			((Modele) prevalentSystem).addPersonne(personne);
			return personne;
		}
	}

	public static class RemovePersonne implements Transaction {
		private final Personne personne;

		public RemovePersonne(String nom, String prenom) {
			this.personne = new Personne(nom, prenom);
		}

		public void executeOn(Object prevalentSystem, Date executionTime) {
			((Modele) prevalentSystem).removePersonne(personne);

		}

	}

	public static class AddEntreprise implements TransactionWithQuery {

		private static final long serialVersionUID = 9117948659583091943L;
		private final Entreprise entreprise;

		public AddEntreprise(String nom) {
			this.entreprise = new Entreprise(nom);
		}

		public Object executeAndQuery(Object prevalentSystem, Date executionTime)
				throws Exception {
			((Modele) prevalentSystem).addEntreprise(entreprise);
			return entreprise;
		}
	}

	public static class LierPersonneEntreprise implements Transaction {

		private static final long serialVersionUID = -3168821494706032538L;
		private final Personne personne;
		private final Entreprise entreprise;

		public LierPersonneEntreprise(final Personne personne,
				final Entreprise entreprise) {
			this.personne = personne;
			this.entreprise = entreprise;
		}

		public void executeOn(Object prevalentSystem, Date executionTime) {
			((Modele) prevalentSystem).lierPersonneEntreprise(personne, entreprise);

		}

	}

	public static class ClearModele implements Transaction {

		public void executeOn(Object prevalentSystem, Date executionTime) {
			((Modele) prevalentSystem).clear();

		}

	}
	
}
