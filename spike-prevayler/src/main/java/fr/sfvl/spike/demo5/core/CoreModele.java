package fr.sfvl.spike.demo5.core;

import java.lang.reflect.Field;
import java.util.List;

public abstract class CoreModele {
	protected <T> T chercher(T objetCherche, List<T> liste) {
		int index = liste.indexOf(objetCherche);
		if (index != -1) {
			return liste.get(index);
		}
		return null; 
	}
	
	public void clear() {
		Field[] listeField = getClass().getDeclaredFields();
		for (Field field : listeField) {
			if (field.getType().isAssignableFrom(List.class)) {
				try {
					List liste = (List) getField(field);
					liste.clear();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	protected abstract Object getField(Field field) throws IllegalArgumentException, IllegalAccessException;
}
