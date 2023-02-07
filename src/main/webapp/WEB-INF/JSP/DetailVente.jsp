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


<h1>Detail vente</h1>

<div class="conteneur">

<div class="conteneurPhoto"></div>




<div class="conteneurFormulaire">

<div class="divParam">${article.nomArticle}</div>
<div class="divParam"><p class="champs">Description :</p><p class="remplissageChamps">${article.description}</p></div>
<div class="divParam"><p class="champs">Catégorie : </p><p class="remplissageChamps">${article.noCategorie.getLibelle()}</p></div>
<c:if test="${article.noAcheteur == null}">
<div class="divParam"><p class="champs">Meilleure offre : </p><p class="remplissageChamps">Aucune offre pour le moment</p></div>

</c:if>

<c:if test="${article.noAcheteur != null}">
<div class="divParam"><p class="champs">Meilleure offre : </p><p class="remplissageChamps">${article.prixVente} points par ${article.getNoAcheteur().getPseudo()}</p></div>

</c:if>

<div class="divParam"><p class="champs">Mise à prix : </p><p class="remplissageChamps">${article.miseAprix} points </p></div>
<div class="divParam"><p class="champs">Date de fin d'enchère : </p><p class="remplissageChamps">${article.dateFinEncheres}</p></div>
<div class="divParam"><p class="champs">Retrait : </p><p class="remplissageChamps">${retrait.rue}<br>${retrait.code_postal}<br>${retrait.ville}</p></div>
<div class="divParam"><p class="champs">Vendeur :</p><p class="remplissageChamps">${article.getNoVendeur().getPseudo()}</p></div>
<form method="post">
<input type="hidden" name = "noArticle" value="${article.noArticle}" >
<div class="divParam"><p class="champs">Ma proposition :</p><input class="remplissageChamps" id="propositionEnchere" name="propositionEnchere" type="number" min="${article.prixVente}+10" max="10000" step="25"></div>
<input type="submit" value="Enchérir">
	
</form>
</div>
</div>

</body>

</html>