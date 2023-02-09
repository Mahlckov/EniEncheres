package fr.eni.javaee.eniencheres.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.eniencheres.bo.Encheres;
import fr.eni.javaee.eniencheres.bo.Utilisateur;
import fr.eni.javaee.eniencheres.BusinessException;

import fr.eni.javaee.eniencheres.dal.UtilisateurDAO;
import fr.eni.javaee.eniencheres.dal.CodesResultatDAL;
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
	
	public void updateUser(Utilisateur utilisateur) throws BusinessException{
		utilisateurDAO.updateUser(utilisateur);
	}
	
	public List<String> verifierFormulaireModification(HttpServletRequest request) {
		List<Utilisateur> liste = null;
		List<String> errorList = new ArrayList();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("newPassword");
		String confirmation = request.getParameter("confirmPassword");
		
		/* VERIFICATION DES CHAMPS */
		if (pseudo.trim().length()>30) {
			errorList.add("Le champ 'Pseudo' ne peut dépasser 30 caractères.");
		}
		
		if (nom.trim().length()>30) {
			errorList.add("Le champ 'Nom' ne peut dépasser 30 caractères.");
		}
		if (prenom.trim().length()>30) {
			errorList.add("Le champ 'Prénom' ne peut dépasser 30 caractères.");
		}
		if (email.trim().length()>70) {
			errorList.add("Le champ 'Email' ne peut dépasser 70 caractères.");
		}
		if (telephone !=null && telephone.trim().length()!=10) {
			errorList.add("Le champ 'Téléphone' saisi n'est pas conforme.");
		}
		if (rue.trim().length()>30) {
			errorList.add("Le champ 'Rue' ne peut dépasser 30 caractères.");
		}
		if (codePostal.length()!=5) {
			errorList.add("Le champ 'Code Postal' saisi n'est pas conforme.");
		}
		if (ville.trim().length()>30) {
			errorList.add("Le champ 'Ville' ne peut dépasser 30 caractères.");
		}
		if (motDePasse.trim().length()>30) {
			errorList.add("Le champ 'Mot de passe' ne peut dépasser 30 caractères.");
		}
		
		/* TEST DIFFERENCE ENTRE MDP ET CONFIRMATION */
		if (!motDePasse.equals(confirmation)) {
			errorList.add("Les champs 'Mot de passe' et 'Confirmation' ne correspondent pas.");
		}
		

		/* TEST DE VALIDITE DE l'EMAIL */
		boolean emailValide = UtilisateurManager.emailIsValid(email);

		if (emailValide != true) {
			errorList.add("Le format de l'adresse email n'est pas valide.");
			try {
				liste = utilisateurManager.listeUtilisateur();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* SI EMAIL VALIDE TEST SI PSEUDO ET EMAIL DEJA UTILISES */
			for (Utilisateur utilisateur : liste) {
				String pseudoUtilisateur = utilisateur.getPseudo();
				String emailUtilisateur = utilisateur.getEmail();

				if (pseudo.equals(pseudoUtilisateur)) {

					errorList.add("Le pseudo '" + pseudo + "' est déjà utilisé.");
				}

				if (email.equals(emailUtilisateur)) {

					errorList.add("L'email '" + email + "' est déjà utilisé.");
				}
			}
		}
		return errorList;
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
			errorList.add("Le champ 'Pseudo' doit être rempli.");
		}
		else if (pseudo.trim().length()>30) {
			errorList.add("Le champ 'Pseudo' ne peut dépasser 30 caractères.");
		};
		
		if (nom.isBlank()) {
			errorList.add("Le champ 'Nom' doit être rempli.");
		}
		else if (nom.trim().length()>30) {
			errorList.add("Le champ 'Nom' ne peut dépasser 30 caractères.");
		}
		;
		
		if (prenom.isBlank()) {
			errorList.add("Le champ 'Prenom' doit être rempli.");
		}
		else if (prenom.trim().length()>30) {
			errorList.add("Le champ 'Prénom' ne peut dépasser 30 caractères.");
		}
		;
		
		if (email.isBlank()) {
			errorList.add("Le champ 'Email' doit être rempli.");
		}
		else if (email.trim().length()>70) {
			errorList.add("Le champ 'Email' ne peut dépasser 70 caractères.");
		};

		if (rue.isBlank()) {
			errorList.add("Le champ 'Rue' doit être rempli.");
		}
		else if (rue.trim().length()>30) {
			errorList.add("Le champ 'Rue' ne peut dépasser 30 caractères.");
		}
		;
		if (codePostal.isBlank()) {
			errorList.add("Le champ 'Code Postal' doit être rempli.");
		}
		else if (codePostal.length()!=5) {
			errorList.add("Le champ 'Code Postal' saisi n'est pas conforme.");
		}
		;
		if (ville.isBlank()) {
			errorList.add("Le champ 'ville' doit être rempli.");
		}
		else if (ville.trim().length()>30) {
			errorList.add("Le champ 'Ville' ne peut dépasser 30 caractères.");
		}
		;
		if (motDePasse.isBlank()) {
			errorList.add("Le champ 'Mot de passe' doit être rempli.");
		}
		else if (motDePasse.trim().length()>30) {
			errorList.add("Le champ 'Mot de passe' ne peut dépasser 30 caractères.");
		}
		;
		if (confirmation.isBlank()) {
			errorList.add("Le champ 'Confirmation' doit être rempli.");
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
		}

		/* TEST DE VALIDITE DU PSEUDO */	
		boolean pseudoValide = UtilisateurManager.pseudoIsValid(pseudo);

		if (pseudoValide != true) {
			errorList.add("Le format du pseudo n'est pas valide, il ne doit contenir que des caractères alphanumériques.");
		}
		
		/* TEST SI LE PSEUDO OU L'EMAIL SONT DEJA UTILISES */


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

				errorList.add("Le pseudo '" + pseudo + "' est déjà utilisé.");
			}

			if (email.equals(emailUtilisateur)) {

				errorList.add("L'email '" + email + "' est déjà utilisé.");
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
	
	/* METHODE VERIFICATION PSEUDO */
	public static boolean pseudoIsValid (String pseudo) {
	    if (pseudo != null && pseudo.matches("^[a-zA-Z0-9]+$")) {
	      return true;
	    } else {
	      return false;
	    }
	  }
	
	public void retirerCredit (int credit, int noUtilisateur, Encheres encheres) throws BusinessException {
		String errorList = null;
		UtilisateurManager userManager = new UtilisateurManager();
		Utilisateur user = userManager.selectUser(noUtilisateur);
		Encheres enchere =  encheres;
		
			if(user.getCredit() >= enchere.getMontant_enchere()) {
				int montantRetire = user.getCredit() - enchere.getMontant_enchere();
					user.setCredit(montantRetire);
					userManager.updateUser(user);
				}
	}
	
	public void restituerCredit  (int credit, int noUtilisateur) throws BusinessException {
		UtilisateurManager userManager = new UtilisateurManager();
		Utilisateur user = userManager.selectUser(noUtilisateur);
		
		user.setCredit(user.getCredit() + credit);
		userManager.updateUser(user);
	}
	
	
	public boolean seConnecterAvecCookie(String id,String mdp) {
		boolean connexionAutorisee = false;
		List<Utilisateur> liste = new ArrayList<>();

		String identifiant = id;
		String motDePasse = mdp;

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

}
