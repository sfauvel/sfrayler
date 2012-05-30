package fr.sfvl.spike.demo4;

import java.lang.reflect.Method;

import fr.sfvl.spike.demo4.model.Entreprise;
import fr.sfvl.spike.demo4.model.Personne;
import fr.sfvl.spike.demo4.model.Service;



/**
 * Exemple en supprimant la possibilité de modifier en dehors des transactions.
 */
public class Prog {

	private static final String BD_PROG = ".\\bd\\prog4";

	public static void main(String[] args) throws Throwable {
		
		{
			Service service = new Service(BD_PROG);
		
	
			Personne p1 = service.addPersonne("Durand", "Pierre");
			Personne p2 = service.addPersonne("Martin", "Jean");
			Personne p3 = service.addPersonne("Hervé", "Luc");

			Entreprise e1 = service.addEntreprise("Twitter");
			Entreprise e2 = service.addEntreprise("Google");
	
			service.addPersonneToEntreprise(p1, e1);
			service.addPersonneToEntreprise(p2, e1);

			checkNoSetter(Personne.class);
			checkNoSetter(Entreprise.class);
			
			try {
				p1.getListeEntreprise().clear();
				throw new Throwable("Une exception aurait du être levée");
			} catch (Exception e) {
				
			}
		}
		{
			System.out.println("Reconstruction à partir des transactions");
			Service service = new Service(BD_PROG);
			afficherModele(service);
		}
		{
			System.out.println("Take snapshot");
			Service service = new Service(BD_PROG);
			service.takeSnapshot();
		}
		{
			System.out.println("Reconstruction à partir du snapshot");
			Service service = new Service(BD_PROG);
			afficherModele(service);
		}
		{
			System.out.println("Clear model");
			Service service = new Service(BD_PROG);
			service.clearModele();
			service.takeSnapshot();
		}
	}

	private static void checkNoSetter(Class clazz) throws Exception {
		for (Method method : clazz.getMethods()) {
			if (method.getName().startsWith("set")) {
				throw new Exception("Il ne doit pas y avoir de setter:" + method.getName());
			}
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
