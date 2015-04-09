<%--
    Document   : footer
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Pasar a URL Rewrite -->
<!-- <a href="index" class="logo"> -->
<a href="Lists.show" class="logo">
	<h1>Luettelo</h1>
</a>
<div class="search">
	<form class="form-wrapper cf" action="Lists.show">
            <input name="type" type="hidden" value="search">
	    <input name="value" type="text" placeholder="Search here..." required>
	    <button type="submit">Search</button>
	</form>
</div>

<br/>
<div class="categories">
	<!-- Pasar a URL Rewrite -->
	<!-- <a href="category/Movies" 		class="category">Movies </a>
	<a href="category/Books" 		class="category">Books </a>
	<a href="category/Series" 		class="category">Series </a>
	<a href="category/Videogames" 	class="category">Videogames </a>
	<a href="category/DIY" 			class="category">DIY </a>
	<a href="category/Miscellaneous" class="category"> Miscellaneous </a> -->
	<a href="Lists.show?type=category&value=Movies" 		class="category">Movies </a>
	<a href="Lists.show?type=category&value=Books" 		class="category">Books </a>
	<a href="Lists.show?type=category&value=Series" 		class="category">Series </a>
	<a href="Lists.show?type=category&value=Videogames" 	class="category">Videogames </a>
	<a href="Lists.show?type=category&value=DIY" 			class="category">DIY </a>
	<a href="Lists.show?type=category&value=Miscellaneous" class="category"> Miscellaneous </a>
</div>

