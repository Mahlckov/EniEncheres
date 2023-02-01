package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDate;
import java.util.List;

public class Articles {
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAprix;
	private String etatVente;
	private int noCategorie;
	private int noVendeur;
	private Retrait retrait;
	private List<Encheres> listEnchere;

	
	public Articles() {
		super();
	}
	
	public Articles(String nomArticle, String description) {
        super();
        this.nomArticle = nomArticle;
        this.description = description;
    }
	
	public Articles(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAprix, String etatVente, int noCategorie, int noVendeur) {
		super();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.etatVente = etatVente;
		this.noCategorie = noCategorie;
		this.noVendeur =noVendeur;
	}
	
	public Articles(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAprix, String etatVente, int noCategorie, int noVendeur) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.etatVente = etatVente;
		this.noCategorie = noCategorie;
		this.noVendeur =noVendeur;
	}
	
	public Articles(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAprix, String etatVente,List<Encheres> listEnchere ) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAprix = miseAprix;
		this.etatVente = etatVente;
		this.listEnchere = listEnchere;
	}
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAprix() {
		return miseAprix;
	}
	public void setMiseAprix(int miseAprix) {
		this.miseAprix = miseAprix;
	}
	
	public String getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public int getNoVendeur() {
		return noVendeur;
	}

	public void setNoVendeur(int noVendeur) {
		this.noVendeur = noVendeur;
	}
	

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public List<Encheres> getListEnchere() {
		return listEnchere;
	}

	public void setListEnchere(List<Encheres> listEnchere) {
		this.listEnchere = listEnchere;
	}
	
	
	
	

}
