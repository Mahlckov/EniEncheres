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

<jsp:include page="Menu.jsp"></jsp:include>


<div style="height:3em;" ></div>

<form method="post">

		<div class="conteneur">


			<div class="centrageProfil"></div>


			<div class="conteneurProfil">

<div class="divParamID">
						<p class="libelle">Identifiant : </p> 
<input class="formInput" type="text" name="identifiant" id="identifiant" /> </div>


<div class="divParamID">
						<p class="libelle">Mot de passe : </p>
<input class="formInput" type="password" name="motDePasse" id="motDePasse" /></div> 

<input class="submitConnexion" type="submit"  value="Connexion">

</div> </div>
</form>


<div class="centrageLienInscription">
<a href="/EniEncheres/Inscription"><p class="lienCréaDeCompte">Vous n'avez pas de compte ? Cliquez ici pour s'inscrire</a> </p> </div>


    <c:if test="${!empty messageConnexion}"> <c:out value="${messageConnexion }"></c:out> </c:if> 



</body>
</html>