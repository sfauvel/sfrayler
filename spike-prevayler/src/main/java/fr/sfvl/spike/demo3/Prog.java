package fr.sfvl.spike.demo3;



/**
 */
public class Prog {

	public static void main(String[] args) throws Exception {
		{
			Service service = new Service(".\\bd\\prog3");
		
			Personne p1 = service.addPersonne("Durand", "Pierre");
			Personne p2 = service.addPersonne("Martin", "Jean");
			Personne p3 = service.addPersonne("Hervé", "Luc");
	
			Entreprise e1 = service.addEntreprise("Twitter");
			Entreprise e2 = service.addEntreprise("Google");
	
			service.addPersonneToEntreprise(p1, e1);
			service.addPersonneToEntreprise(p2, e1);
		}
		{
			Service service = new Service(".\\bd\\prog3");
			afficherModele(service);
		}
		{
			Service service = new Service(".\\bd\\prog3");
			service.takeSnapshot();
		}
		{
			Service service = new Service(".\\bd\\prog3");
			afficherModele(service);
		}
		{
			Service service = new Service(".\\bd\\prog3");
			service.clearModele();
			service.takeSnapshot();
		}

	}

	private static void afficherModele(Service service) {
		for (Personne personne : service.getListePersonne()) {
			System.out.println(personne.getNom() + " " + personne.getPrenom());
			for (Entreprise entreprise : personne.getListeEntreprise()) {
				System.out.println("   " + entreprise.getNom());
			}
		}
		for (Entreprise entreprise : service.getListeEntreprise()) {
			System.out.println(entreprise.getNom());
			for (Personne personne : entreprise.getListePersonne()) {
				System.out.println("   " + personne.getNom() + " " + personne.getPrenom());
			}
		}
	}
}
