<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/NouvelleVente.css" rel="stylesheet">

<title>Nouvelle Vente</title>
</head>
<body>



<h1 class="titreNouvelleVente">Nouvelle vente</h1>


<div class="conteneur">

<div class="conteneurPhoto"></div>




<div class="conteneurFormulaire">

<form method="post">


<div class="divParam"><p class="libelle">Article :</p>  <input type="text" name="nomArticle" id="nomArticle"> </div>

<div class="divParam"><p class="libelle">Description :</p> <input type="text" name="descriptionArticle" id="descriptionArticle"></div>

<div class="divParam"><label class="libelle"for="categorie-select">Catégorie :</label>

<select class="inputCategorie" name="categorie" id="categorie-select">
    <option value="Informatique">Informatique</option>
    <option value="Ameublement">Ameublement</option>
    <option value="Vêtement">Vêtement</option> 
    <option value="Sport&Loisirs">Sport&Loisirs</option>
</select></div>

<div class="divParam"><p class="libelle">Photo de l'article : </p> <input type="file" name="uploadPhotoArticle" id="uploadPhotoArticle"></div>

<div class="divParam"><p class="libelle">Mise à prix : </p> <input id="miseAPrix" name="miseAPrix" type="number" min="0" max="1000" step="25"></div>

<div class="divParam"><p class="libelle">Debut de l'enchère :</p> <input type="date" name="dateDebutEnchere" id="dateDebutEnchere"></div>

<div class="divParam"><p class="libelle">Fin de l'enchère :</p>  <input type="date" name="dateFinEnchere" id="dateFinEnchere"></div>

<p style="font-weight:bold;	">Retrait</p>

<div class="divRetrait">

<div class="divParam2"><p class="libelle">Rue :</p>  <input type="text" name="rue" id="rue"> </div>
<div class="divParam2"><p class="libelle">Code postal :</p>  <input type="text" name="codePostal" id="codePostal"> </div>
<div class="divParam2"><p class="libelle">Ville :</p>  <input type="text" name="ville" id="ville"> </div>




</div>

<input class="submit"type="submit" value="Enregistrer" >
</form>



<a href="/EniEncheres/Accueil"><button class="annuler">Annuler</button></a>

</div>
</div>
</body>
</html>