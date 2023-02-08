<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon Profil</title>
</head>
<body>

	<h1>Profil de ${user.getPseudo()}</h1>
	<div>Pseudo : ${user.getPseudo()}</div>
	<div>Nom : ${user.nom}</div>
	<div>Prénom : ${user.prenom}</div>
	<div>Email : ${user.email}</div>
	<div>Téléphone : ${user.telephone}</div>
	<div>Rue : ${user.rue}</div>
	<div>Code Postal : ${user.codePostal}</div>
	<div>Ville : ${user.ville}</div>
	<c:if test="${user.getNoUtilisateur() == sessionScope.noUtilisateur && empty Modifier}">
		<div>
			<a href="/EniEncheres/MonProfil?Modifier=true"><input type="button" value="Modifier"/></a> <!-- redirige vers la servlet avec parametre Modifier -->
		</div>
</c:if>

<c:if test="${!empty Modifier}">
	<form method="post">    
	    <label for="pseudo">Pseudo :</label>
	    <input type="text" id="pseudo" name="pseudo" value="${user.getPseudo()}" onblur="if(this.value == '') { this.value= '${user.getPseudo()}'; }" onclick="this.value = '';"/>
	    
	    <label for="nom">Nom :</label>
	    <input type="text"  id="nom" name="nom" value="${user.nom}" onblur="if(this.value == '') { this.value= '${user.nom}'; }" onclick="this.value = '';"/><br>
	    
	    <label for="prenom">Prénom :</label>
	    <input type="text" id="prenom" name="prenom" value="${user.getPrenom()}"onblur="if(this.value == '') { this.value= '${user.prenom}'; }" onclick="this.value = '';"/>
	    
	    <label for="email">Email :</label>
	    <input type="text" id="email" name="email" value="${user.getEmail()}" onblur="if(this.value == '') { this.value= '${user.email}'; }" onclick="this.value = '';"/><br>
	    
	    <label for="telephone">Téléphone :</label>
	    <input type="tel" id="telephone" name="telephone" value="${user.getTelephone()}" onblur="if(this.value == '') { this.value= '${user.telephone}'; }" onclick="this.value = '';"/>
	    
	    <label for="rue">Rue :</label>
	    <input type="text" id="rue" name="rue" value="${user.getRue()}" onblur="if(this.value == '') { this.value= '${user.rue}'; }" onclick="this.value = '';"/><br>
	        
	    <label for="codePostal">Code Postal :</label>
	    <input type="text" id="codePostal" name="codePostal" value="${user.getCodePostal()}" onblur="if(this.value == '') { this.value= '${user.codePostal}'; }" onclick="this.value = '';"/>
	    
	    <label for="ville">Ville :</label>
	    <input type="text" id="ville" name="ville" value="${user.getVille()}" onblur="if(this.value == '') { this.value= '${user.ville}'; }" onclick="this.value = '';"/><br>
	    
	    <label for="currentPassword">Mot de passe actuel :</label>
	    <input type="password" id="currentPassword" name="currentPassword" value="${user.getMotDePasse()}"  onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }" onclick="this.value = '';"/><br>
	    
	    <label for="newPassword">Nouveau mot de passe :</label>
	    <input type="password" id="newPassword" name="newPassword" value="${user.getMotDePasse()}" onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }" onclick="this.value = '';"/>
	
	    <label for="confirmPassword">Confirmation :</label>
	    <input type="password" id="confirmPassword" name="confirmPassword" value="${user.getMotDePasse()}" onblur="if(this.value == '') { this.value= '${user.motDePasse}'; }" onclick="this.value = '';"/><br>
	    
	    <label for="credit">Credit : ${user.getCredit()} points</label>    
	
	    <div>
	    	<input type="submit" value="confirmer" class="confirm">
		</div>
	</form>
		<div>	
		    <a href="/EniEncheres/MonProfil?delete=true"><button class="delete">Supprimer mon compte</button></a>
		</div>
</c:if>
<c:if test="${!empty errorList}">
	<c:forEach var="a" items="${errorList}">
		<c:out value="${a}"></c:out><br>
	</c:forEach>
</c:if>
</body>
</html>