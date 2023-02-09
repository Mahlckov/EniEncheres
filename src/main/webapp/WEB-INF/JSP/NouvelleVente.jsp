<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import = "fr.eni.javaee.eniencheres.messages.LecteurMessage" %> 
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/Accueil.css" rel="stylesheet">

<title>Nouvelle Vente</title>
</head>
<body>

	<jsp:include page="Menu.jsp"></jsp:include>




	<h1 class="titreNouvelleVente">Nouvelle vente</h1>



	<div class="conteneur">

		<div class="conteneurPhoto"></div>




		<div class="conteneurFormulaire">

			<form method="post">


				<div class="divParam">
					<p class="libelle">Article :</p>
					<input class="formInput" type="text" name="nomArticle"
						id="nomArticle"
						<c:if test="${!empty article }">value="${article.nomArticle}"
						onblur="if(this.value == '') 
						{ this.value= '${article.nomArticle}'; }"
							onclick="this.value = '';"</c:if>>
				</div>

				<div class="divParam">
					<p class="libelle">Description :</p>
					<input class="formInput" type="text" name="descriptionArticle"
						id="descriptionArticle"<c:if test="${!empty article }">value="${article.description}"
						onblur="if(this.value == '') 
						{ this.value= '${article.description}'; }"
							onclick="this.value = '';"</c:if>>
				</div>

				<div class="divParam">
					<label class="libelle" for="categorie-select">Catégorie :</label> <select
						class="inputCategorie" name="categorie" id="categorie-select">
						<option  value="Informatique" <c:if test="${article.noCategorie.getNoCategorie()==1}">selected</c:if>>Informatique</option>
						<option value="Ameublement"<c:if test="${article.noCategorie.getNoCategorie()==2}">selected</c:if>>Ameublement</option>
						<option value="Vetement"<c:if test="${article.noCategorie.getNoCategorie()==3}">selected</c:if>>Vêtement</option>
						<option value="Sport&Loisirs"<c:if test="${article.noCategorie.getNoCategorie()==4}">selected</c:if>>Sport&Loisirs</option>
					</select>
				</div>

				<div class="divParam">
					<p class="libelle">Photo de l'article :</p>
					<input class="formInput" type="file" name="uploadPhotoArticle"
						id="uploadPhotoArticle"
						
						>
				</div>

				<div class="divParam">
					<p class="libelle">Mise à prix :</p>
					<input class="formInput" id="miseAPrix" name="miseAPrix"
						type="number" min="0" max="1000" step="25"<c:if test="${!empty article }">value="${article.miseAprix}"
						onblur="if(this.value == '') 
						{ this.value= '${article.miseAprix}'; }"
							onclick="this.value = '';"</c:if>>
				</div>

				<div class="divParam">
					<p class="libelle">Debut de l'enchère :</p>
					<input class="formInput" type="date" name="dateDebutEnchere"
						id="dateDebutEnchere"<c:if test="${!empty article }">value="${article.dateDebutEncheres}"
						onblur="if(this.value == '') 
						{ this.value= '${article.dateDebutEncheres}'; }"
							onclick="this.value = '';"</c:if>>
				</div>

				<div class="divParam">
					<p class="libelle">Fin de l'enchère :</p>
					<input class="formInput" type="date" name="dateFinEnchere"
						id="dateFinEnchere"<c:if test="${!empty article }">value="${article.dateFinEncheres}"
						onblur="if(this.value == '') 
						{ this.value= '${article.dateFinEncheres}'; }"
							onclick="this.value = '';"</c:if>>
				</div>

				<p style="font-weight: bold;">Retrait</p>

				<div class="divRetrait">

					<div class="divParam2">
						<p class="libelle">Rue :</p>
						<input class="formInput" type="text" name="rue" id="rue" <c:if test="${!empty article }">value="${retrait.rue}"
						onblur="if(this.value == '') 
						{ this.value= '${retrait.rue}'; }"
							onclick="this.value = '';"</c:if>>
					</div>
					<div class="divParam2">
						<p class="libelle">Code postal :</p>
						<input class="formInput" type="text" name="codePostal"
							id="codePostal"<c:if test="${!empty article }">value="${retrait.code_postal}"
						onblur="if(this.value == '') 
						{ this.value= '${retrait.code_postal}'; }"
							onclick="this.value = '';"</c:if>>
					</div>
					<div class="divParam2">
						<p class="libelle">Ville :</p>
						<input class="formInput" type="text" name="ville" id="ville"<c:if test="${!empty article }">value="${retrait.ville}"
						onblur="if(this.value == '') 
						{ this.value= '${retrait.ville}'; }"
							onclick="this.value = '';"</c:if>>
					</div>

				</div>
				
				<c:if test="${!empty article }"><input type="hidden"name="articleModif"value="${article.noArticle }")'></c:if>

				<div style="height: 2em;"></div>
				<div class="buttonsDiv">


					<input class="submit" type="submit" value="Enregistrer"> 
				</div>
			</form>
<a
						href="/EniEncheres/Accueil"><button class="delete">Annuler</button></a>

				<c:if test="${!empty article }">	<a href="/EniEncheres/NouvelleVente?delete=true&noArticle=${article.noArticle}"><button
							class="delete">Annuler la vente</button></a></c:if>
		</div>
	</div>
	<c:if test="${!empty errorList}">
		<c:forEach var="a" items="${errorList}">
			<em>${LecteurMessage.getMessageErreur(a)}</em>
			<br>
		</c:forEach>
	</c:if>
</body>
</html>