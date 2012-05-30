package fr.sfvl.spike.demo5.generateur;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.sfvl.spike.demo5.model.Modele;

public class Generateur {
	
	
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Generateur generateur = new Generateur();
		generateur.genererModele(Modele.class);
	}
	
	private void genererModele(Class<Modele> model) throws UnsupportedEncodingException, FileNotFoundException, IOException {

		Structure structure = new Structure();
		
		Field[] listeField = model.getDeclaredFields();
		for (Field field : listeField) {
			System.out.println(field.getName());
			if (field.getType().isAssignableFrom(List.class)) {
				Class<? extends Object> fieldArgClass = getListObjectType(field);
				System.out.println(fieldArgClass.getName());
				System.out.println(fieldArgClass.getSimpleName());
				structure.getListeListe().put(field.getName(), fieldArgClass);
			}

		}
		

		new ServiceWriter().writeFile(structure);
		new TransactionWriter().writeFile(structure);
		
	}

	private Class<? extends Object> getListObjectType(Field field) {
		Type genericFieldType = field.getGenericType();
		
		if(genericFieldType instanceof ParameterizedType){
		    ParameterizedType aType = (ParameterizedType) genericFieldType;
		    Type[] fieldArgTypes = aType.getActualTypeArguments();
		    for(Type fieldArgType : fieldArgTypes){
		        return (Class) fieldArgType;
		    }
		}
		return null;
	}

}
