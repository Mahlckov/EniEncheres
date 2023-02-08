<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<header>
<div class="header"><a class="lienAccueil" href="/EniEncheres/Accueil"><h2>ENI</h2><h3>Enchères</h3></a></div>

<nav class="connect">

<!--SI UTILISATEUR CONNECTE   --> 



    <c:if test="${ !empty sessionScope.noUtilisateur}">
    
    <div class="menuConnecte">
    
    <a href="/EniEncheres/Accueil"><div class="rubrique">Enchères</div></a>
    <a href="/EniEncheres/NouvelleVente"><div class="rubrique">Vendre un article</div></a>
    <a href="/EniEncheres/MonProfil"><div class="rubrique">Mon profil</div></a>
    <a href="/EniEncheres/Deconnexion"><div class="rubrique">Deconnexion</div></a>
    
    
    
    
    
    
    </div>
    
    
    </c:if>

<!--SI UTILISATEUR CONNECTE   --> 


    <c:if test="${ empty sessionScope.noUtilisateur}">


<a href="/EniEncheres/Connexion">Se connecter / S'inscrire</a>


</c:if>
</nav>

</header>

<div class="barreDeco"></div>


</body>
</html>