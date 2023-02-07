<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/Accueil.css" rel="stylesheet">

<title>ENI Enchères</title>
</head>
<body>

<header>
<div class="header"><h1>ENI</h1><p>Enchères</p></div> 

<nav class="connect">




    <c:if test="${ !empty sessionScope.noUtilisateur}">
    
    <div class="menuConnecte">
    
    <a href="/EniEncheres/Accueil"><div class="rubrique">Enchères</div></a>
    <a href="/EniEncheres/NouvelleVente"><div class="rubrique">Vendre un article</div></a>
    <a href="/EniEncheres/MonProfil"><div class="rubrique">Mon profil</div></a>
     <a href="/EniEncheres/Deconnexion"><div class="rubrique">Deconnexion</div></a>
    
    
    
    
    
    
    </div>
    
    
    </c:if>


    <c:if test="${ empty sessionScope.noUtilisateur}">


<a href="/EniEncheres/Connexion">Se connecter / S'inscrire</a>


</c:if>
</nav>

</header>




<div class="barreDeco"></div>

<h1 class="titreListeEnchere">Liste des enchères</h1>

<p class="filtre">Filtres : </p>


<label for="search">:</label>
<input class="searchBar" type="search" id="search" name="q"> <input type="submit" value="Rechercher">


<div class="categories">


<label for="categorie-select">Catégorie :</label>

<select class="inputCategorie" name="categorie" id="categorie-select">
    <option value="toutes">Toutes</option>
    <option value="informatique">Informatique</option>
    <option value="ameublement">Ameublement</option>
    <option value="vetement">Vêtement</option>
    <option value="sport&loisirs">Spider</option>
</select>
   
</input>
</div>

<div class="conteneurListeEnchere">


<c:forEach var="a" items="${listeArticleEnCours}"> 

<div class="conteneurArticle"> 

<div class="conteneurPhoto"></div>
<div class="conteneurDescriptionArticle">

<a href="/EniEncheres/DetailVente?id=${a.noArticle}"><p class="descriptionArticle"><c:out value="${a.getNomArticle()}"></c:out> <p></a>

<p class="descriptionArticle">Prix : <c:out value="${a.getPrixVente()}"> points</c:out> <p>

<p class="descriptionArticle">Fin de l'enchère : <c:out value="${a.getDateFinEncheres()}"></c:out> <p>

<p class="descriptionArticle">Vendeur : 
	<a href="/EniEncheres/MonProfil?noVendeur=${a.getNoVendeur().getPseudo()}">
		<c:out value="${a.getNoVendeur().getPseudo()}"> 
		</c:out> 
	</a>
<p>




</div>

</div>

</c:forEach>


</div>


</body>
</html>





