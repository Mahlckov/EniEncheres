<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css_venteGagnee.css" rel="stylesheet">

<title>Vous avez remporté la vente</title>
</head>
<body>
	<h1 class="titreListeEnchere">Détail vente</h1>
	<div class="conteneur">
	<div>${article.nomArticle}</div>
	<div>Description : ${article.description}</div>
	<div>Catégorie : ${article.noCategorie.libelle}</div>
	<div>Meilleure offre : ${article.listEnchere.get(0).montant_enchere}</div>
	<div>Mise à prix : ${article.miseAprix}</div>
	<div>Fin de l'enchère : ${article.dateFinEncheres}</div>
	<div>Retrait : ${article.retrait.rue}</div>
	<div>          ${article.retrait.code_postal} ${article.retrait.ville}</div>
	
	<div class="conteneurPhoto"></div>
	
	<div><a href="/EniEncheres/Accueil"><button class="annuler">Back</button></a></div>
</div>
</body>
</html>