package fr.sfvl.spike.demo5.generateur;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map.Entry;

import javax.management.ListenerNotFoundException;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import fr.sfvl.spike.demo5.generated.ModeleTransactionGen;
import fr.sfvl.spike.demo5.model.Modele;
import fr.sfvl.spike.demo5.model.ModeleTransaction;
import fr.sfvl.spike.demo5.model.Personne;

public class ServiceWriter {

	private static final String ENCODING = "UTF-8";

	public void writeFile(Structure structure) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		PrintWriter out = new PrintWriter("src/main/java/fr/sfvl/spike/demo5/generated/ServiceGen.java");

		try {
			out.println("package fr.sfvl.spike.demo5.generated;");

			out.println("import java.util.List;");

			out.println("import org.prevayler.Prevayler;");
			out.println("import org.prevayler.PrevaylerFactory;");
			out.println("import fr.sfvl.spike.demo5.model.Modele;");
			for (Entry<String, Class<?>> liste : structure.getListeListe().entrySet()) {
				out.println("import " + liste.getValue().getName() + ";");
			}
			out.println("public class ServiceGen {");

			out.println("protected Prevayler prevayler;");
			out.println("protected Modele modele;");

			out.println("public ServiceGen(String bd) throws Exception {");
			out.println("prevayler = PrevaylerFactory.createPrevayler(new Modele(), bd);");
			out.println("modele = (Modele) prevayler.prevalentSystem();");
			out.println("}");

			for (Entry<String, Class<?>> liste : structure.getListeListe().entrySet()) {
				String listName = liste.getKey().replaceFirst("liste", "");
				writeListAccess(out, listName, liste.getValue());
			}
			out.println("}");
		} finally {
			out.close();
		}
	}

	private void writeListAccess(PrintWriter out, String listName, Class<?> objectName) {
		Constructor<?>[] listeConstructor = objectName.getConstructors();
		for (Constructor<?> constructor : listeConstructor) {
			writeListAccess(out, listName, objectName.getSimpleName(), constructor);
		}

		out.println("public List<" + objectName.getSimpleName() + "> getListe" + listName + "() {");
		out.println("	return modele.getListe" + listName + "();");
		out.println("}");

	}

	private void writeListAccess(PrintWriter out, String listName, String objectName, Constructor constructor) {
		Class[] listeParametre = constructor.getParameterTypes();
		out.print("public " + objectName + " add" + listName + "(");
		String separateur = "";
		String parametre = "";
		for (int i = 0; i < listeParametre.length; i++) {
			Class parameter = listeParametre[i];
			out.print(separateur + "final " + parameter.getSimpleName() + " " + "arg" + i + "");
			parametre += separateur + "arg" + i;
			separateur = ", ";
		}
		out.println(")");

		out.println("throws Exception {");
		out.println("return (" + objectName + ") prevayler.execute(new ModeleTransactionGen.Add" + listName + "(" + parametre + "));");
		out.println("}");
	}
}
