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

<!--SI UTILISATEUR CONNECTE   --> 



    <c:if test="${ !empty sessionScope.noUtilisateur}">
    
    <div class="menuConnecte">
    
    <a href="/EniEncheres/Accueil"><div class="rubrique">Enchères</div></a>
    <a href="/EniEncheres/NouvelleVente"><div class="rubrique">Vendre un article</div></a>
    <a href="/EniEncheres/MonProfil"><div class="rubrique">Mon profil</div></a>
    <a href="/EniEncheres/Deconnexion"><div class="rubrique">Deconnexion</div></a>
    
    
    
    
    
    
    </div>
    
    
    </c:if>

<!--SI UTILISATEUR CONNECTE   --> 


    <c:if test="${ empty sessionScope.noUtilisateur}">


<a href="/EniEncheres/Connexion">Se connecter / S'inscrire</a>


</c:if>
</nav>

</header>




<div class="barreDeco"></div>

<h1 class="titreListeEnchere">Liste des enchères</h1>

<p class="filtre">Filtres : </p>

<!--PARAMETRE DE FILTRAGE PAR RECHERCHE   --> 


<form method="get">

<label for="search">:</label>
<input class="searchBar" type="search" id="search" name="search"> <input type="submit" value="Rechercher">

<!--PARAMETRE DE FILTRAGE CATEGORIE   --> 


<div class="categories">


<label for="categorie-select">Catégorie :</label>

<select class="inputCategorie" name="categorie" id="categorie-select">
    <option value="Toutes"<c:if test="${categorie=='Toutes'}">selected</c:if>>Toutes</option>
   <option value="Informatique" <c:if test="${categorie=='Informatique'}">selected</c:if>>Informatique</option>
    <option value="Ameublement"<c:if test="${categorie=='Ameublement'}">selected</c:if>>Ameublement</option>
    <option value="Vêtement"<c:if test="${categorie=='Vêtement'}">selected</c:if>>Vêtement</option>
    <option value="Sport&Loisirs"<c:if test="${categorie=='Sport&Loisirs'}">selected</c:if>>Sport&Loisirs</option>
</select>
</input>


<!--PARAMETRE DE FILTRAGE A COCHER   --> 

</div>
<div class="surContainerACocher">
<div class="containerACocher">
 <div class="aCocher">
    <input type="checkbox" id="EO" name="encheresOuvertes" value="true">
    <label for="EO">Enchères ouvertes</label>
  </div>
  
   <div class="aCocher">
    <input type="checkbox" id="EO" name="mesEnchereEnCours" value="true">
    <label for="EO">Mes enchères en cours</label>
  </div>
  
     <div class="aCocher">
    <input type="checkbox" id="EO" name="mesEnchereRemportees" value="true">
    <label for="EO">Mes enchères remportées</label>
  </div>
</div>

<div class="containerACocher">
 <div class="aCocher">
    <input type="checkbox" id="VO" name="mesVentesEnCours" value="true">
    <label for="EO">Mes ventes en cours</label>
  </div>
  
   <div class="aCocher">
    <input type="checkbox" id="VO" name="ventesNonDebutees" value="true">
    <label for="EO">Ventes non débutées</label>
  </div>
  
     <div class="aCocher">
    <input type="checkbox" id="VO" name="ventesTerminees" value="true">
    <label for="EO">Ventes terminées</label>
  </div>
</div>
</div>
   </form>

<!--CREATION DES CONTENEURS D'ARTICLE   --> 


<div class="conteneurListeEnchere">


<c:forEach var="a" items="${listeArticleEnCours}"> 

<div class="conteneurArticle"> 

<div class="conteneurPhoto"></div>
<div class="conteneurDescriptionArticle">

<a href="/EniEncheres/DetailVente?id=${a.noArticle}"><p class="descriptionArticle"><c:out value="${a.getNomArticle()}"></c:out> <p></a>

<p class="descriptionArticle">Prix : <c:out value="${a.getPrixVente()}"> points</c:out> <p>

<p class="descriptionArticle">Fin de l'enchère : <c:out value="${a.getDateFinEncheres()}"></c:out> <p>

<p class="descriptionArticle">Vendeur : <c:out value="${a.getNoVendeur().getNom()}"></c:out> <p>




</div>

</div>

</c:forEach>


</div>


</body>
</html>





