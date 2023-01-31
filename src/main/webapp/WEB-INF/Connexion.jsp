<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/ENIencheres/css/style.css" rel="stylesheet">

<title>ENI Enchères</title>
</head>
<body>

<header>
<div class="header"><h1>ENI</h1><p>Enchères</p></div> 

<nav class="connect">

<a href="">Se connecter / S'inscrire</a>

</nav>

</header>



<div class="barreDeco"></div>

<div style="height:3em;" ></div>


<form method="post" action="ServletPageConnexion">
<div class="containerForm">
<label for="identifiant">Identifiant : </label> 
<input type="text" name="identifiant" id="identifiant" /> <div style="height:1em;" ></div>


<label for="motDePasse">Mot de passe : </label>
<input type="password" name="motDePasse" id="motDePasse" /> <div style="height:1em;" ></div>

</div>
<input class="submit" type="submit"  value="Connexion">



</form>

<a href="/EniEncheres/Inscription"><p class="lienCréaDeCompte">Vous n'avez pas de compte ? Cliquez ici pour s'inscrire</a> </p>


    <c:if test="${!empty messageConnexion}"> <c:out value="${messageConnexion }"></c:out> </c:if> 



</body>
</html>