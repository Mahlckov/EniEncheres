<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						id="nomArticle">
				</div>

				<div class="divParam">
					<p class="libelle">Description :</p>
					<input class="formInput" type="text" name="descriptionArticle"
						id="descriptionArticle">
				</div>

				<div class="divParam">
					<label class="libelle" for="categorie-select">Catégorie :</label> <select
						class="inputCategorie" name="categorie" id="categorie-select">
						<option value="Informatique">Informatique</option>
						<option value="Ameublement">Ameublement</option>
						<option value="Vetement">Vêtement</option>
						<option value="Sport&Loisirs">Sport&Loisirs</option>
					</select>
				</div>

				<div class="divParam">
					<p class="libelle">Photo de l'article :</p>
					<input class="formInput" type="file" name="uploadPhotoArticle"
						id="uploadPhotoArticle">
				</div>

				<div class="divParam">
					<p class="libelle">Mise à prix :</p>
					<input class="formInput" id="miseAPrix" name="miseAPrix"
						type="number" min="0" max="1000" step="25">
				</div>

				<div class="divParam">
					<p class="libelle">Debut de l'enchère :</p>
					<input class="formInput" type="date" name="dateDebutEnchere"
						id="dateDebutEnchere">
				</div>

				<div class="divParam">
					<p class="libelle">Fin de l'enchère :</p>
					<input class="formInput" type="date" name="dateFinEnchere"
						id="dateFinEnchere">
				</div>

				<p style="font-weight: bold;">Retrait</p>

				<div class="divRetrait">

					<div class="divParam2">
						<p class="libelle">Rue :</p>
						<input class="formInput" type="text" name="rue" id="rue">
					</div>
					<div class="divParam2">
						<p class="libelle">Code postal :</p>
						<input class="formInput" type="text" name="codePostal"
							id="codePostal">
					</div>
					<div class="divParam2">
						<p class="libelle">Ville :</p>
						<input class="formInput" type="text" name="ville" id="ville">
					</div>

				</div>
				
				<div style="height:2em;"></div>
				<div class="buttonsDiv">

					<input class="submit" type="submit" value="Enregistrer"> <a
						href="/EniEncheres/Accueil"><button class="delete">Annuler</button></a>

				</div>
			</form>

		</div>
	</div>
</body>
</html>