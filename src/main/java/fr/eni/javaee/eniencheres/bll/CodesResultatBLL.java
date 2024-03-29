package fr.eni.javaee.eniencheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	

	/**
	 * Echec le nom de l'article ne respecte pas les rÃ¨gles dÃ©finies
	 */
	public static final int REGLE_ARTICLE_NOM_ERREUR=20000;
	
	/**
	 * Echec la date de l'article ne respecte pas les rÃ¨gles dÃ©finies
	 */
	public static final int REGLE_DATE_ARTICLE_ERREUR=20001;
	
	/**
	 * Echec la description de l'article ne respecte pas les rÃ¨gles dÃ©finies
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR=20002;
	
	/**
	 * Echec l'Ã©tat de l'article ne respecte pas les rÃ¨gles dÃ©finies
	 */
	public static final int REGLE_ARTICLE_ETAT_ERREUR=20013;
	
	public static final int REGLE_DATEDEBUT_ARTICLE_ERREUR=20014;
	
	public static final int REGLE_DATEDEBUT_ARTICLE_PASSE=20015;

	public static final int REGLE_DATEDEFIN_ARTICLE_ERREUR=20016;

	public static final int REGLE_DATEDEFIN_ARTICLE_PASSE=20017;


	
	
	/**
	 * Echec le prix de l'article ne respecte pas les rÃ¨gles dÃ©finies
	 */
	public static final int REGLE_ARTICLE_PRIX_ERREUR= 20004;

	public static final int REGLE_ENCHERES_MONTANT_ERREUR = 20005;

	public static final int REGLE_NO_ARTICLE_NULL = 20006;

	public static final int REGLE_NO_ACHETEUR_NULL = 20007;
	
	public static final int REGLE_RETRAIT_NO_ARTICLE_ERREUR = 20008;

	public static final int REGLE_RETRAIT_RUE_ERREUR = 20009;

	public static final int REGLE_RETRAIT_CODEPOSTAL_ERREUR = 20010;

	public static final int REGLE_RETRAIT_VILLE_ERREUR = 20011;
	
	/**
	 * Echec nombre de crÃ©dits insuffisants pour enchÃ©rir
	 */
	public static final int REGLE_ENCHERES_CREDIT_ERREUR = 20012;


	
}
