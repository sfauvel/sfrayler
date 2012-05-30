package fr.sfvl.spike.demo1;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import fr.sfvl.spike.demo1.ModeleTransaction.*;

/**
 * Exemple sur un usage basique de prevalyer.
 */
public class Prog {

	public static void main(String[] args) throws Exception {
		Prevayler prevayler = PrevaylerFactory.createPrevayler(new Modele(),
				".\\bd\\prog1");
		Modele modele = (Modele) prevayler.prevalentSystem();

		Personne p1 = (Personne) prevayler.execute(new AddPersonne("Durand", "Pierre"));
		Personne p2 = (Personne) prevayler.execute(new AddPersonne("Martin", "Jean"));
		Personne p3 = (Personne) prevayler.execute(new AddPersonne("Hervé", "Luc"));

		prevayler.execute(new RemovePersonne("Martin", "Jean"));

		for (Personne personne : modele.getListePersonne()) {
			System.out.println(personne.getNom() + " " + personne.getPrenom());
		}

		prevayler.execute(new ClearModele());
		prevayler.takeSnapshot();
	}
}
