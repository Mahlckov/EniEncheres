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
<h1>Mon Profil</h1>
<<<<<<< HEAD
<div>${sessionScope.pseudo}</div>
<div>${sessionScope.nom}</div>
=======
<div>${user.pseudo}</div>
<div>${user.nom}</div>
>>>>>>> branch 'main' of https://github.com/Mahlckov/EniEncheres.git
<div>${user.prenom}</div>
<div>${user.email}</div>
<div>${user.telephone}</div>
<div>${user.rue}</div>
<div>${user.codePostal}</div>
<div>${user.ville}</div>
<div>
<a href="<%=request.getContextPath()%>/Profile"><input type="button" value="Modifier"/></a>
</div>

</body>
</html>