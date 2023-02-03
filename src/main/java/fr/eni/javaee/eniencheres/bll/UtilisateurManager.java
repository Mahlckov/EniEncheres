package fr.eni.javaee.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.BusinessException;

import fr.eni.javaee.eniencheres.dal.UtilisateurDAO;
import fr.eni.javaee.eniencheres.dal.DAOFactory;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
 
	public List<Utilisateur> listeUtilisateur() throws BusinessException {
		return utilisateurDAO.selectAllUser();
	}

	public Utilisateur selectUser(int id) throws BusinessException {
		return utilisateurDAO.selectUserById(id);
	}

	public void insertUser(Utilisateur utilisateur) throws BusinessException {

		utilisateurDAO.insertUser(utilisateur);
	}

	public void deleteUser(int id) throws BusinessException {
		utilisateurDAO.deleteUser(id);
		
	}
	
	public Utilisateur selectUserByPseudo(String pseudo) throws BusinessException {
		return utilisateurDAO.selectUserByPseudo(pseudo);}
	
	
	public Utilisateur selectUserByMail(String mail) throws BusinessException {
		return utilisateurDAO.selectUserByMail(mail);}
	
	
	

	public List<String> verifierFormulaireInscription(HttpServletRequest request) {

		List<Utilisateur> liste = null;
		List<String> errorList = new ArrayList();

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("code_postal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		String confirmation = request.getParameter("confirmation");

		/* VERIFICATION DES CHAMPS */

		if (pseudo.isBlank()) {
			errorList.add("Le champ 'Pseudo' doit Ãªtre rempli.");
		}
		;
		if (nom.isBlank()) {
			errorList.add("Le champ 'Nom' doit Ãªtre rempli.");
		}
		;
		if (prenom.isBlank()) {
			errorList.add("Le champ 'Prenom' doit Ãªtre rempli.");
		}
		;
		if (email.isBlank()) {
			errorList.add("Le champ 'Email' doit Ãªtre rempli.");
		}
		;

		if (rue.isBlank()) {
			errorList.add("Le champ 'Rue' doit Ãªtre rempli.");
		}
		;
		if (codePostal.isBlank()) {
			errorList.add("Le champ 'Code Postal' doit Ãªtre rempli.");
		}
		;
		if (ville.isBlank()) {
			errorList.add("Le champ 'ville' doit Ãªtre rempli.");
		}
		;
		if (motDePasse.isBlank()) {
			errorList.add("Le champ 'Mot de passe' doit Ãªtre rempli.");
		}
		;
		if (confirmation.isBlank()) {
			errorList.add("Le champ 'Confirmation' doit Ãªtre rempli.");
		}
		;

		if (!motDePasse.equals(confirmation)) {
			errorList.add("Les champs 'Mot de passe' et 'Confirmation' ne correspondent pas.");
		}

		UtilisateurManager manager = new UtilisateurManager();

		/* TEST DE VALIDITE DE l'EMAIL */

		boolean emailValide = UtilisateurManager.emailIsValid(email);

		if (emailValide != true) {
			errorList.add("Le format de l'adresse email n'est pas valide.");

			/* TEST SI LE PSEUDO OU L'EMAIL SONT DEJA UTILISES */
		}

		try {
			liste = manager.listeUtilisateur();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Utilisateur utilisateur : liste) {
			String pseudoUtilisateur = utilisateur.getPseudo();
			String emailUtilisateur = utilisateur.getEmail();

			if (pseudo.equals(pseudoUtilisateur)) {

				errorList.add("Le pseudo '" + pseudo + "' est dÃ©jÃ  utilisÃ©.");
			}

			if (email.equals(emailUtilisateur)) {

				errorList.add("L'email '" + email + "' est dÃ©jÃ  utilisÃ©.");
			}

		}
		return errorList;
	}

	public boolean seConnecter(HttpServletRequest request) {
		boolean connexionAutorisee = false;
		List<Utilisateur> liste = new ArrayList<>();

		String identifiant = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motDePasse");

		UtilisateurManager manager = new UtilisateurManager();

		try {
			liste = manager.listeUtilisateur();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Utilisateur utilisateur : liste) {

			if (identifiant.contains("@")) {

				String identifiantExistant = utilisateur.getEmail();
				String motDePasseExistant = utilisateur.getMotDePasse();

				if (identifiant.equals(identifiantExistant) && motDePasse.equals(motDePasseExistant)) {

					connexionAutorisee = true;
				}

			}

			else {

				String identifiantExistant = utilisateur.getPseudo();
				String motDePasseExistant = utilisateur.getMotDePasse();

				if (identifiant.equals(identifiantExistant) && motDePasse.equals(motDePasseExistant)) {

					connexionAutorisee = true;
				}
			}
		}

		return connexionAutorisee;

	}

	/* METHODE VERIFICATION EMAIL */

	public static boolean emailIsValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

}
//	public ListeCourseManager() {
//		this.listeCourseDAO=DAOFactory.getListeCourseDAO();
//	}
//	
//	public List<ListeCourse> selectionnerListes() throws BusinessException
//	{
//		return this.listeCourseDAO.selectAll();
//	}
//
//	public ListeCourse selectionnerListe(int idListeCourse) throws BusinessException {
//		return this.listeCourseDAO.selectById(idListeCourse);
//	}
//
//	public void ajouterArticle(int idListeCourse, String nomArticle) throws BusinessException{
//		BusinessException businessException = new BusinessException();
//		this.validerNomArticle(nomArticle, businessException);
//				
//		if(!businessException.hasErreurs())
//		{
//			ListeCourse listeCourse = new ListeCourse();
//			listeCourse.setId(idListeCourse);
//			
//			Article article = new Article(nomArticle.trim());
//			listeCourse.getArticles().add(article);
//			this.listeCourseDAO.insert(listeCourse);
//		}
//		else
//		{
//			throw businessException;
//		}
//	}
//	
//	public ListeCourse ajouterListeEtArticle(String nomListe, String nomArticle) throws BusinessException {
//		BusinessException businessException = new BusinessException();
//		
//		this.validerNomListe(nomListe, businessException);
//		this.validerNomArticle(nomArticle, businessException);
//		
//		ListeCourse listeCourse=null;
//		
//		if(!businessException.hasErreurs())
//		{
//			listeCourse = new ListeCourse();
//			listeCourse.setNom(nomListe);
//		
//			Article article = new Article(nomArticle.trim());
//			listeCourse.getArticles().add(article);
//			this.listeCourseDAO.insert(listeCourse);
//		}
//		else
//		{
//			throw businessException;
//		}
//		return listeCourse;
//	}
//
//	public void supprimerArticle(int idArticle) throws BusinessException{
//		this.listeCourseDAO.deleteArticle(idArticle);
//	}
//	public void supprimerListeCourse(int idListeCourse) throws BusinessException {
//		this.listeCourseDAO.delete(idListeCourse);
//	}
//	public void cocherArticle(int idArticle) throws BusinessException
//	{
//		this.listeCourseDAO.cocherArticle(idArticle);
//	}
//	public void decocherArticle(int idArticle) throws BusinessException
//	{
//		this.listeCourseDAO.decocherArticle(idArticle);
//	}
//	public void decocherListe(int idListeCourse) throws BusinessException
//	{
//		this.listeCourseDAO.decocherListeCourse(idListeCourse);
//	}
//	
//	private void validerNomListe(String nomListe, BusinessException businessException) {
//		if(nomListe==null || nomListe.trim().length()>50)
//		{
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_LISTE_NOM_ERREUR);
//		}
//	}
//
//	private void validerNomArticle(String nomArticle, BusinessException businessException) {
//		if(nomArticle==null || nomArticle.trim().length()>50)
//		{
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
//		}
//	}
