package fr.sfvl.spike.demo5.generated;
import java.util.List;
import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;
import fr.sfvl.spike.demo5.model.Modele;
import fr.sfvl.spike.demo5.model.Entreprise;
import fr.sfvl.spike.demo5.model.Personne;
public class ServiceGen {
protected Prevayler prevayler;
protected Modele modele;
public ServiceGen(String bd) throws Exception {
prevayler = PrevaylerFactory.createPrevayler(new Modele(), bd);
modele = (Modele) prevayler.prevalentSystem();
}
public Entreprise addEntreprise(final String arg0)
throws Exception {
return (Entreprise) prevayler.execute(new ModeleTransactionGen.AddEntreprise(arg0));
}
public List<Entreprise> getListeEntreprise() {
	return modele.getListeEntreprise();
}
public Personne addPersonne(final String arg0, final String arg1)
throws Exception {
return (Personne) prevayler.execute(new ModeleTransactionGen.AddPersonne(arg0, arg1));
}
public List<Personne> getListePersonne() {
	return modele.getListePersonne();
}
}
