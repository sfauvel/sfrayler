package fr.sfvl.spike.demo5.model;

import fr.sfvl.spike.demo5.generated.ServiceGen;
import fr.sfvl.spike.demo5.model.ModeleTransaction.ClearModele;
import fr.sfvl.spike.demo5.model.ModeleTransaction.LierPersonneEntreprise;
import fr.sfvl.spike.demo5.model.ModeleTransaction.RemovePersonne;


public class Service extends ServiceGen {

	public Service(String bd) throws Exception {
		super(bd);
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


	public void addPersonneToEntreprise(final Personne personne, final Entreprise entreprise) {
		prevayler.execute(new LierPersonneEntreprise(personne, entreprise));
	}

}
