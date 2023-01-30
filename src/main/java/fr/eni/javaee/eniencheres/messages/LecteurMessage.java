package fr.eni.javaee.eniencheres.messages;

import java.util.ResourceBundle;

/**
 * Cette classe permet de lire le contenu du fichier messages_erreur.properties
 * @author Administrator
 *
 */
public class LecteurMessage {
	private static ResourceBundle rb; //objet qui permet d'afficher du contenu .properties
	
	static
	{
		try
		{
			rb = ResourceBundle.getBundle("fr.eni.javaee.gestionlistescourses.messages.messages_erreur");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @param code
	 * @return
	 */
	public static  String getMessageErreur(int code)
	{
		String message="";
		try
		{
			if(rb!=null)
			{
				//récupération du texte lié à la clé de notre rb
				//on cast en String notre code int
				message = rb.getString(String.valueOf(code));
			}
			else
			{
				//Si rb=null= on a pas réussi à lire le fichier
				message="Problème à la lecture du fichier contenant les messages";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		}
		return message;
	}
}