package fr.sfvl.spike.demo2;



/**
 * Exemple avec un usage simplifié en masquant la technique.
 */
public class Prog {

	public static void main(String[] args) throws Exception {
		Service service = new Service(".\\bd\\prog2");
	
		Personne p1 = service.addPersonne("Durand", "Pierre");
		Personne p2 = service.addPersonne("Martin", "Jean");
		Personne p3 = service.addPersonne("Hervé", "Luc");

		service.removePersonne("Martin", "Jean");

		for (Personne personne : service.getListePersonne()) {
			System.out.println(personne.getNom() + " " + personne.getPrenom());
		}

		service.clearModele();
		service.takeSnapshot();
	}
}
