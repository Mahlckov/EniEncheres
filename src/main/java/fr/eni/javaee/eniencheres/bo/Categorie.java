package fr.eni.javaee.eniencheres.bo;

public class Categorie {

	private int noCategorie;
	private String libelle;

	public Categorie() {
		super();
	}

	public Categorie(String libelle) {
		this.libelle = libelle;
		
		if(libelle.equals("Informatique")) {this.noCategorie =1;}
		if(libelle.equals("Ameublement")) {this.noCategorie =2;}
		if(libelle.equals("Vetement")) {this.noCategorie =3;}
		if(libelle.equals("Sport&Loisirs")) {this.noCategorie =4;}



		
	}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}