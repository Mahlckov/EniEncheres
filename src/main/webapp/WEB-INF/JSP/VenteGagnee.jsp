<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/NouvelleVente.css" rel="stylesheet">


<title>Detail Vente</title>
</head>
<body>
<div class="conteneur">
	<c:if test="${user.getNoUtilisateur() == sessionScope.noUtilisateur}">
		<h1 class="titreListeEnchere">Vous avez remporté la vente</h1>
		<div>${article.nomArticle}</div>
		<div>Description : ${article.description}</div>
		<div>Catégorie : ${article.noCategorie.libelle}</div>
		<div>Meilleure offre : ${article.listEnchere.get(0).montant_enchere}</div>
		<div>Mise à prix : ${article.miseAprix}</div>
		<div>Fin de l'enchère : ${article.dateFinEncheres}</div>
		<div>Retrait : ${article.retrait.rue}</div>
		<div>          ${article.retrait.code_postal} ${article.retrait.ville}</div>
		<div>Vendeur : ${article.getNoVendeur()} </div>
		<div>Tel : ${article.utilisateur.getTelephone()} </div>
		<div><a href="/EniEncheres/Accueil"><button class="annuler">Back</button></a></div>
		
	</c:if>	
	
	<c:if test="${user.getNoUtilisateur() != sessionScope.noUtilisateur}">
		<h1 class="titreListeEnchere">${user.getPseudo()} a remporté la vente</h1>
		<div>${article.nomArticle}</div>
		<div>Description : ${article.description}</div>
		<div>Meilleure offre : ${article.listEnchere.get(0).montant_enchere}</div>
		<div>Mise à prix : ${article.miseAprix} points</div>
		<div>Fin de l'enchère : ${article.dateFinEncheres}</div>
		<div>Retrait : ${article.retrait.rue}</div>
		<div>          ${article.retrait.code_postal} ${article.retrait.ville}</div>
		<div>Vendeur : ${article.getNoVendeur()} </div>
		<div><a href="/EniEncheres/Accueil"><button class="annuler">Retrait effectué</button></a></div>
	</c:if>	
	
		<div class="conteneurPhoto"></div>
		
		
</div>
</body>
</html>