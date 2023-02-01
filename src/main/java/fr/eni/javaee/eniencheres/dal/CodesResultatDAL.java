package fr.eni.javaee.eniencheres.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {
	
	/**
	 * Echec général quand tentative d'ajouter un objet null
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec accès BDD
	 */
	public static final int ACCES_BDD_KO=10002;
	
	/**
	 * Echec de la lecture d'une liste d'articles
	 */
	public static final int LECTURE_LISTE_ARTICLES_ECHEC = 10003;
	
	/**
	 * Echec de la lecture d'un article
	 */
	public static final int LECTURE_ARTICLE_ECHEC = 10004;
	
	/**
	 * Article inexistant
	 */
	public static final int LECTURE_ARTICLE_INEXISTANT = 10005;
	
	/**
	 * Erreur à la suppression d'un article
	 */
	public static final int SUPPRESSION_ARTICLE_ERREUR = 10006;
	
	/**
	 * Erreur à la modification d'un article
	 */
	public static final int UPDATE_ARTICLE_ERREUR = 10007;
	
	/**
	 * Echec de la lecture des listes d'utilisateurs
	 */
	public static final int LECTURE_LISTES_ECHEC = 10008;
	
	/**
	 * Echec de la lecture des infos d'un utilisateur
	 */
	public static final int LECTURE_UTILISATEUR_ECHEC = 10009;
	
	
//	/**
//	 * Erreur à la suppression d'une liste
//	 */
//	public static final int SUPPRESSION_LISTE_ERREUR = 10006;
//	/**
//	 * Erreur au cochage d'un article
//	 */
//	public static final int COCHE_ARTICLE_ERREUR = 10007;
//	/**
//	 * Erreur au décochage d'un article
//	 */
//	public static final int DECOCHE_ARTICLE_ERREUR = 10008;
//	/**
//	 * Erreur au décochage de tous les articles d'une liste
//	 */
//	public static final int DECOCHE_ARTICLES_ERREUR = 10009;
}












