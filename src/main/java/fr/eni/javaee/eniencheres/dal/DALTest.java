package fr.eni.javaee.eniencheres.dal;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import fr.eni.javaee.eniencheres.BusinessException;
import fr.eni.javaee.eniencheres.bll.ArticleManager;
import fr.eni.javaee.eniencheres.bo.Articles;

public class DALTest {

	public static void main(String[] args) throws BusinessException{
		ArticleManager article = new ArticleManager();
		
		System.out.println("******************************************");
		System.out.println("***** Affichage de tous les articles *****");
		System.out.println("******************************************");
		
		List<Articles> mesArticles;
		mesArticles = article.selectionnerListArticles();
		for(Articles currentArticle : mesArticles) {
			System.out.println(currentArticle);
		}
		System.out.println();
	
//	
//		Articles a1 = article.selectionnerArticle(1);
//		Articles a2 = article.selectionnerArticle(2);
//
//		System.out.println("******************************************");
//		System.out.println("Affichage des articles d'id 1 et 2");
//		System.out.println("******************************************");
//		System.out.println(a1);
//		System.out.println(a2);
//		System.out.println();
	
//		System.out.println("******************************************");
//		System.out.println("Insertion d'un article");
//		System.out.println("******************************************");
//	
//		Articles articleAInserer = new Articles("Porte","bois",LocalDate.of(2023, Month.JANUARY, 31), LocalDate.of(2023, Month.MARCH, 31), 100, 200, "vendu");
//		article.ajouterArticle(articleAInserer);
//		System.out.println("L'article inséré est : ");
//		System.out.println(articleAInserer);
//		System.out.println();
//		
//		System.out.println("******************************************");
//		System.out.println("Modification d'un article");
//		System.out.println("******************************************");
//		System.out.println("Article avant modification : ");
//		
//		Articles articleAModifier = article.selectionnerArticle(3);
//		System.out.println(articleAModifier);
//		System.out.println();
//		
//		articleAModifier.setNomArticle("Bateau");
//		articleAModifier.setDescription("moche");		
//		articleAModifier.setDateDebutEncheres(LocalDate.of(2021, Month.JANUARY, 31));
//		articleAModifier.setDateFinEncheres(LocalDate.of(2023, Month.MARCH, 31));
//		articleAModifier.setMiseAprix(10000);
//		articleAModifier.setPrixVente(0);
//		articleAModifier.setEtatVente("A vendre");
//
//		article.miseAjourArticle(articleAModifier);
//		
//		System.out.println("Article apres modification : ");
//		System.out.println(articleAModifier);
//		System.out.println();
//		
//		System.out.println("******************************************");
//		System.out.println("Suppression d'un article");
//		System.out.println("******************************************");
//		article.supprimerArticle(articleAModifier.getNoArticle());
//		// article.delete(a1.get());
		
		
		
		
		
	}
}
