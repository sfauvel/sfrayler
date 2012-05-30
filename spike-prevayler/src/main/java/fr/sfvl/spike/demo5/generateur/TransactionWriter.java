package fr.sfvl.spike.demo5.generateur;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.Map.Entry;

import javax.management.ListenerNotFoundException;

import org.prevayler.Prevayler;
import org.prevayler.TransactionWithQuery;

import fr.sfvl.spike.demo5.model.Modele;
import fr.sfvl.spike.demo5.model.Personne;

public class TransactionWriter {

	private static final String ENCODING = "UTF-8";

	public void writeFile(Structure structure) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		PrintWriter out = new PrintWriter("src/main/java/fr/sfvl/spike/demo5/generated/ModeleTransactionGen.java");

		try {
			out.println("package fr.sfvl.spike.demo5.generated;");
			
			out.println("import java.util.Date;");

			out.println("import org.prevayler.TransactionWithQuery;");
			out.println("import fr.sfvl.spike.demo5.model.Modele;");
			for (Entry<String, Class<?>> liste : structure.getListeListe().entrySet()) {
				out.println("import " + liste.getValue().getName() + ";");
			}
			
			out.println("public class ModeleTransactionGen {");

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

	}

	private void writeListAccess(PrintWriter out, String listName, String objectName, Constructor constructor) {

		Class[] listeParametre = constructor.getParameterTypes();
		out.println("public static class Add" + listName + " implements TransactionWithQuery {");

		out.println("private final " + objectName + " object;");

		out.print("public Add" + listName + "(");
		String separateur = "";
		String parametre = "";
		for (int i = 0; i < listeParametre.length; i++) {
			Class parameter = listeParametre[i];
			out.print(separateur + "final " + parameter.getSimpleName() + " " + "arg" + i + "");
			parametre += separateur + "arg" + i;
			separateur = ", ";
		}
		out.println(") {");

		out.println("this.object = new " + objectName + "(" + parametre + ");");
		out.println("}");
		out.println("public Object executeAndQuery(Object prevalentSystem, Date executionTime)");
		out.println("throws Exception {");
		out.println("((Modele) prevalentSystem).add" + listName + "(object);");
		out.println("return object;");
		out.println("}");
		out.println("}");

	}
}
