<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/Accueil.css" rel="stylesheet">
<title>Detail Vente</title>
</head>
<body>

<jsp:include page="Menu.jsp"></jsp:include>


			<c:if test="${article.getEtatVente()=='EN COURS'}">
				<h1>Detail de la vente</h1>
			</c:if>
			<c:if test="${article.etatVente == 'TERMINEE'}">
				<c:if
					test="${article.getNoAcheteur().getNoUtilisateur() == sessionScope.noUtilisateur}">

					<h1>Vous avez remporté la vente</h1>
				</c:if>
				<c:if
					test="${article.getNoAcheteur().getNoUtilisateur() != sessionScope.noUtilisateur}">
					<h1>${utilisateurGagnant.getPseudo()} a remporté la vente</h1>

				</c:if>
			</c:if>
			<div style="height:2em;"></div>

	<div class="conteneur">

		<div class="conteneurPhoto"></div>

		<div class="conteneurFormulaire">


			<div class="divParam" style="font-weight:bold;">${article.nomArticle}</div>
			<div class="divParam">
				<p class="champs">Description :</p>
				<p class="remplissageChamps">${article.description}</p>
			</div>

			<c:if test="${article.getEtatVente()=='EN COURS'}">
				<div class="divParam">
					<p class="champs">Catégorie :</p>
					<p class="remplissageChamps">${article.noCategorie.getLibelle()}</p>
				</div>
			</c:if>

			<c:if test="${article.noAcheteur == null}">
				<div class="divParam">
					<p class="champs">Meilleure offre :</p>
					<p class="remplissageChamps">Aucune offre pour le moment</p>
				</div>
			</c:if>

			<c:if test="${article.noAcheteur != null}">
				<div class="divParam">
					<p class="champs">Meilleure offre :</p>
					<p class="remplissageChamps">${article.prixVente} points par
						${article.getNoAcheteur().getPseudo()}</p>
				</div>
			</c:if>

			<div class="divParam">
				<p class="champs">Mise à prix :</p>
				<p class="remplissageChamps">${article.miseAprix} points</p>
			</div>

			<div class="divParam">
				<p class="champs">Date de fin d'enchère :</p>
				<p class="remplissageChamps">${article.dateFinEncheres}</p>
			</div>

			<div class="divParam">
				<p class="champs">Retrait :</p>
				<p class="remplissageChamps">${retrait.rue}<br>${retrait.code_postal}<br>${retrait.ville}</p>
			</div>

			<div class="divParam">
				<p class="champs">Vendeur :</p>
				<a
					href="/EniEncheres/MonProfil?noVendeur=${article.getNoVendeur().getPseudo()}">
					<p class="remplissageChamps">${article.getNoVendeur().getPseudo()}</p>
				</a>
			</div>
			<c:if
				test="${article.getEtatVente()=='TERMINEE'&& article.getNoAcheteur().getNoUtilisateur() == sessionScope.noUtilisateur}">
				<div>Tel : ${article.getNoVendeur().getTelephone()}</div>
			</c:if>
			<c:if test="${article.getEtatVente()=='EN COURS'}">
				<form method="post">
					<input type="hidden" name="noArticle" value="${article.noArticle}">
					<div class="divParam">
						<p class="champs">Ma proposition :</p>
						<input class="formProfil" class="remplissageChamps" id="propositionEnchere"
							name="propositionEnchere" type="number"
							min="${article.prixVente}+10" max="10000" step="25">
					</div>
					<input type="submit" value="Enchérir">
				</form>

			</c:if>
		</div>
	</div>

</body>

</html>