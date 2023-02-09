<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/Accueil.css" rel="stylesheet">

<title>Mon Profil</title>
</head>
<body>
	<jsp:include page="Menu.jsp"></jsp:include>

	<c:if test="${user.noUtilisateur == sessionScope.noUtilisateur }">
		<h1>Mon profil</h1>
	</c:if>

	<c:if test="${user.noUtilisateur != sessionScope.noUtilisateur }">

		<h1>Profil de ${user.getPseudo()}</h1>
	</c:if>

	<c:if test="${empty Modifier}">

		<div class="conteneur">


			<div class="centrageProfil"></div>


			<div class="conteneurProfil">

				<div class="divParamProfil">
					<p class="libelle">Pseudo :</p>
					<p class="info">${user.getPseudo()}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Nom :</p>
					<p class="info">${user.nom}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Prenom :</p>
					<p class="info">${user.prenom}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Email :</p>
					<p class="info">${user.email}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Tel :</p>
					<p class="info">${user.telephone}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Rue :</p>
					<p class="info">${user.rue}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Code postal :</p>
					<p class="info">${user.codePostal}</p>
				</div>
				<div class="divParamProfil">
					<p class="libelle">Ville :</p>
					<p class="info">${user.ville}</p>
				</div>
				<c:if
					test="${user.getNoUtilisateur() == sessionScope.noUtilisateur && empty Modifier}">
					<div class="divParamProfil">
						<div>
							<a href="/EniEncheres/MonProfil?Modifier=true"><input
								type="button" value="Modifier" /></a>
							<!-- redirige vers la servlet avec parametre Modifier -->
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</c:if>

	<c:if test="${!empty Modifier}">

		<div class="conteneur">


			<div class="centrageProfil"></div>


			<div class="conteneurProfil">

				<form method="post">
					<div class="divParamProfil">
						<p class="libelle">Pseudo :</p>
						<input class="formInput" type="text" id="pseudo" name="pseudo"
							value="${user.getPseudo()}"
							onblur="if(this.value == '') { this.value= '${user.getPseudo()}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Nom :</p>
						<input class="formInput" type="text" id="nom" name="nom"
							value="${user.nom}"
							onblur="if(this.value == '') { this.value= '${user.nom}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Prenom</p>
						<input class="formInput" type="text" id="prenom" name="prenom"
							value="${user.getPrenom()}"
							onblur="if(this.value == '') { this.value= '${user.prenom}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Email :</p>
						<input class="formInput" type="text" id="email" name="email"
							value="${user.getEmail()}"
							onblur="if(this.value == '') { this.value= '${user.email}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Tel :</p>
						<input class="formInput" type="tel" id="telephone"
							name="telephone" value="${user.getTelephone()}"
							onblur="if(this.value == '') { this.value= '${user.telephone}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Rue :</p>
						<input class="formInput" type="text" id="rue" name="rue"
							value="${user.getRue()}"
							onblur="if(this.value == '') { this.value= '${user.rue}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Code postal :</p>
						<input class="formInput" type="text" id="codePostal"
							name="codePostal" value="${user.getCodePostal()}"
							onblur="if(this.value == '') { this.value= '${user.codePostal}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Ville :</p>
						<input class="formInput" type="text" id="ville" name="ville"
							value="${user.getVille()}"
							onblur="if(this.value == '') { this.value= '${user.ville}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Mot de passe actuel :</p>
						<input class="formInput" type="password" id="currentPassword"
							name="currentPassword" value="${user.getMotDePasse()}"
							onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Nouveau mot de passe :</p>
						<input class="formInput" type="password" id="newPassword"
							name="newPassword" value="${user.getMotDePasse()}"
							onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Confirmation :</p>
						<input class="formInput" type="password" id="confirmPassword"
							name="confirmPassword" value="${user.getMotDePasse()}"
							onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }"
							onclick="this.value = '';" />
					</div>

					<div class="divParamProfil">
						<p class="libelle">Credit :</p>
						<p class="info">${user.getCredit()} points</p>
					</div>

					<div class="buttonsDiv">
						<input class="submit" type="submit" value="confirmer">

						<a href="/EniEncheres/MonProfil?delete=true"><button
								class="delete">Supprimer mon compte</button></a>

					</div>
				</form>

			</div>
		</div>

	</c:if>
	<c:if test="${!empty errorList}">
		<c:forEach var="a" items="${errorList}">
			<em>${a}</em>
			<br>
		</c:forEach>
	</c:if>
</body>
</html>