<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/style.css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>

<header>
<div class="header"><h1>ENI</h1><p>Enchères</p></div> 


</header>



<div class="barreDeco"></div>

<div style="height:3em;" ></div>



<form method="post" action="ServletPageInscription">
<label for="pseudo">Pseudo : </label> 
<input type="text" name="pseudo" id="pseudo" /> <div style="height:1em;" ></div>

<label for="nom">Nom : </label> 
<input type="text" name="nom" id="nom" /> <div style="height:1em;" ></div>

<label for="prenom">Prenom : </label> 
<input type="text" name="prenom" id="prenom" /> <div style="height:1em;" ></div>

<label for="email">Email : </label> 
<input type="text" name="email" id="email" /> <div style="height:1em;" ></div>

<label for="telephone">Telephone : </label> 
<input type="text" name="telephone" id="telephone" /> <div style="height:1em;" ></div>

<label for="rue">Rue : </label> 
<input type="text" name="rue" id="rue" /> <div style="height:1em;" ></div>

<label for="code_postal">Code postal : </label> 
<input type="text" name="code_postal" id="code_postal" /> <div style="height:1em;" ></div>

<label for="ville">Ville : </label> 
<input type="text" name="ville" id="ville" /> <div style="height:1em;" ></div>


<label for="motdepasse">Mot de passe : </label>
<input type="password" name="motDePasse" id="motDePasse" /> <div style="height:1em;" ></div>

<label for="confirmation">Confirmation : </label> 
<input type="password" name="confirmation" id="confirmation" /> <div style="height:1em;" ></div>

<input class="submit" type="submit"  value="Créer">


</form>

<div class="containerButton">




<a class="annuler" href="/EniEncheres/Accueil"><button class="annuler"value="Annuler">Annuler</button></a>

</div>

    <c:if test="${ !empty errorList}"> 
    
    <c:forEach items="${ errorList}" var="errorList"> <p>${errorList } </p>   </c:forEach>
    
    
     </c:if>



</body>
</html>