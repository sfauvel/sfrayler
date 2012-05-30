package fr.sfvl.spike.demo5.generated;
import java.util.Date;
import org.prevayler.TransactionWithQuery;
import fr.sfvl.spike.demo5.model.Modele;
import fr.sfvl.spike.demo5.model.Entreprise;
import fr.sfvl.spike.demo5.model.Personne;
public class ModeleTransactionGen {
public static class AddEntreprise implements TransactionWithQuery {
private final Entreprise object;
public AddEntreprise(final String arg0) {
this.object = new Entreprise(arg0);
}
public Object executeAndQuery(Object prevalentSystem, Date executionTime)
throws Exception {
((Modele) prevalentSystem).addEntreprise(object);
return object;
}
}
public static class AddPersonne implements TransactionWithQuery {
private final Personne object;
public AddPersonne(final String arg0, final String arg1) {
this.object = new Personne(arg0, arg1);
}
public Object executeAndQuery(Object prevalentSystem, Date executionTime)
throws Exception {
((Modele) prevalentSystem).addPersonne(object);
return object;
}
}
}
