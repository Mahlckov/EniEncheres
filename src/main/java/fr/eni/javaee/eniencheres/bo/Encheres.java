package fr.eni.javaee.eniencheres.bo;

import java.time.LocalDate;
import java.util.Date;

public class Encheres {
	private LocalDate date_enchere ;
	private int montant_enchere ;
	
	
	public Encheres() {
		super();
	}
	
	public Encheres(LocalDate date_enchere, int montant_enchere) {
		super();
		this.date_enchere = date_enchere;
		this.montant_enchere = montant_enchere;
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
	
	
	
	
}
