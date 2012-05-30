package fr.sfvl.spike.demo6;

import fr.sfvl.spike.demo6.ModeleTransaction.AddPersonne;
/**
 * Exemple avec un usage simplifié en masquant la technique.
 */
public class Prog {

	public static void main(String[] args) throws Exception {
		Service service = new Service(".\\bd\\prog6");
	
		Personne p1 = service.execute(AddPersonne.query("Durand", "Pierre"));
		Personne p2 = service.execute(AddPersonne.query("Martin", "Jean"));
		Personne p3 = service.execute(AddPersonne.query("Hervé", "Luc"));

		service.removePersonne("Martin", "Jean");

		for (Personne personne : service.getListePersonne()) {
			System.out.println(personne.getNom() + " " + personne.getPrenom());
		}

		service.clearModele();
		service.takeSnapshot();
	}
}
