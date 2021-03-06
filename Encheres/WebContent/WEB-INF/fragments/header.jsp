<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<fmt:setBundle basename="fr.eni.ecole.messages.header" var="r"/>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<title><fmt:message key="msg.nav_title" bundle="${r}"></fmt:message> - ${ title } </title>
		<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath }/js/popper.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/moment-with-locales.js"></script>
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/theme/css/style.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/theme/bootstrap-4.1.3/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/theme/fontawesome/css/fontawesome.min.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/theme/fontawesome/css/solid.min.css">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <a class="navbar-brand" href="${pageContext.request.contextPath }/"><fmt:message key="msg.nav_title" bundle="${r}"></fmt:message></a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">
		    <ul class="navbar-nav mr-auto">
		      <li class="nav-item"></li>
		    </ul>
		    <ul class="navbar-nav justify-content-end">
		      <c:if test="${no_utilisateur != null}">
		      	<li class ="nav-item">
		      		<a class="nav-link" href="${pageContext.request.contextPath }/"><fmt:message key="msg.nav_auctions" bundle="${r}"></fmt:message><span class="sr-only">(current)</span></a>
		      	</li>
		      	<li class ="nav-item ">
		      		<a class="nav-link" href="Sell"><fmt:message key="msg.nav_sell" bundle="${r}"></fmt:message></a>
		      	</li>
		      	<li class ="nav-item ">
		      		<a class="nav-link" href="Profil"><fmt:message key="msg.nav_profile" bundle="${r}"></fmt:message></a>
		      	</li>
		      	<li class ="nav-item ">
		      		<a class="nav-link" href="./Deconnexion"><fmt:message key="msg.nav_disconnection" bundle="${r}"></fmt:message></a>
		      	</li>
		      </c:if>
		      <c:if test="${no_utilisateur == null}">
			      <li class ="nav-item">
			      	<a class="nav-link" href="./Login"><fmt:message key="msg.nav_connection_subscription" bundle="${r}"></fmt:message><span class="sr-only">(current)</span></a>
			      </li>
		      </c:if>
		    </ul>
		  </div>
		</nav>
		
	