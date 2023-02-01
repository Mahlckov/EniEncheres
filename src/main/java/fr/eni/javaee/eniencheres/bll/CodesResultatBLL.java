package fr.eni.javaee.eniencheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	

	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_NOM_ERREUR=20000;
	
	/**
	 * Echec la date de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_DATE_ARTICLE_ERREUR=20001;
	
	/**
	 * Echec la description de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR=20002;
	
	/**
	 * Echec l'état de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_ETAT_ERREUR=20003;
	
	/**
	 * Echec le prix de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_PRIX_ERREUR= 20004;
	
}
