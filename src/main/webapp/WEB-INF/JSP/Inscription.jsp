<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/EniEncheres/css/Accueil.css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>

<jsp:include page="Menu.jsp"></jsp:include>


<h1>Mon inscription</h1>
<div class="conteneur">

<div class="conteneurPhoto"></div>




<div class="conteneurFormulaire">

<form method="post" >
<div class="divParam"><p class="libelle">Pseudo : </p> 
<input class="formInput" type="text" name="pseudo" id="pseudo" /></div> 

<div class="divParam"><p class="libelle">Nom :  </p>  
<input class="formInput" type="text" name="nom" id="nom" /> </div>

<div class="divParam"><p class="libelle">Prenom :  </p>  
<input class="formInput" type="text" name="prenom" id="prenom" /> </div>

<div class="divParam"><p class="libelle">Email :  </p>  
<input class="formInput" type="text" name="email" id="email" /> </div>

<div class="divParam"><p class="libelle">Telephone :  </p>  
<input class="formInput" type="text" name="telephone" id="telephone" /></div>

<div class="divParam"><p class="libelle">Rue :  </p>  
<input class="formInput" type="text" name="rue" id="rue" /></div> 

<div class="divParam"><p class="libelle">Code postal :</p> 
<input class="formInput" type="text" name="code_postal" id="code_postal" /></div>

<div class="divParam"><p class="libelle">Ville :  </p>  
<input class="formInput" type="text" name="ville" id="ville" /> </div>


<div class="divParam"><p class="libelle">Mot de passe :  </p> 
<input class="formInput" type="password" name="motDePasse" id="motDePasse" /></div>

<div class="divParam"><p class="libelle">Confirmation :  </p> 
<input class="formInput" type="password" name="confirmation" id="confirmation" /></div> 

<input class="submit" type="submit"  value="Créer">


</form>

<div class="containerButton">




<a class="annuler" href="/EniEncheres/Accueil"><button class="annuler"value="Annuler">Annuler</button></a>

</div>

    <c:if test="${ !empty errorList}"> 
    
    <c:forEach items="${ errorList}" var="errorList"> <em>${errorList} </em>   </c:forEach>
    
    
     </c:if>

</div></div>

</body>
</html>