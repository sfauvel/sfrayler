package fr.sfvl.spike.prevalyer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import fr.sfvl.spike.demo3.Entreprise;
import fr.sfvl.spike.demo3.Personne;

public class TestCopie {
	@Test
	public void testCopie() throws IOException, ClassNotFoundException {
		Personne p1 = new Personne("Durand", "Paul");
		Entreprise e1 = new Entreprise("Google");
		p1.addEntreprise(e1);
		voirPersonne(p1);
		voirEntreprise(e1);
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(stream);

		out.writeObject(p1);
		out.writeObject(e1);

		ObjectInputStream in = new ObjectInputStream(
				new ByteArrayInputStream(stream.toByteArray()));

		Personne p1Copie = (Personne) in.readObject();
		Entreprise e1Copie = (Entreprise) in.readObject();
		voirPersonne(p1Copie);
		voirEntreprise(e1Copie);
	}
	
	private void voirEntreprise(Entreprise entreprise) {
		System.out.println(entreprise);
		System.out.println(entreprise.getNom());
	}

	private void voirPersonne(Personne personne) {
		System.out.println(personne);
		System.out.println(personne.getNom() + " " + personne.getPrenom());
		for (Entreprise entreprise : personne.getListeEntreprise()) {
			System.out.println("   " + entreprise);
			System.out.println("   " + entreprise.getNom());
		}
		
	}
}
