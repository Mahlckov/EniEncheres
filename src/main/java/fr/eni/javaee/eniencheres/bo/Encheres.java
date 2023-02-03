package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDate;

public class Encheres implements Comparable <Encheres> {
	private LocalDate date_enchere ;
	private int montant_enchere ;
	private Articles noArticle;
	private Utilisateur noAcheteur;
	
	public Encheres() {
		super();
	}
	

	
	public Encheres(Utilisateur noAcheteur,Articles noArticle,LocalDate date_enchere, int montant_enchere) {
		super();
		this.noAcheteur = noAcheteur;
		this.noArticle = noArticle;
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
		
	}



	public Articles getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(Articles noArticle) {
		this.noArticle = noArticle;
	}



	public Utilisateur getNoAcheteur() {
		return noAcheteur;
	}



	public void setNoAcheteur(Utilisateur noAcheteur) {
		this.noAcheteur = noAcheteur;
	}



	public LocalDate getDate_enchere() {
		return date_enchere;
	}
	public void setDate_enchere(LocalDate date_enchere) {
		this.date_enchere = date_enchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}

	@Override
	public int compareTo(Encheres encheres) {
	if(encheres.getMontant_enchere()==this.montant_enchere)	{
		return 0;
	}
		if(encheres.getMontant_enchere()<this.montant_enchere) {
			return -1;
		}
		else return 1;
		
	}
	
	
	
	
}
