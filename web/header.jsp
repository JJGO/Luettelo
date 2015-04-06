<%--
    Document   : footer
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a href="index" class="logo">
	<h1>Luettelo</h1>
</a>
<div class="search">
	<form class="form-wrapper cf" action="search">
	    <input name="q" type="text" placeholder="Search here..." required>
	    <button type="submit">Search</button>
	</form>
</div>

<br/>
<div class="categories">
	<a href="category/Movies" 		class="category">Movies </a>
	<a href="category/Books" 		class="category">Books </a>
	<a href="category/Series" 		class="category">Series </a>
	<a href="category/Videogames" 	class="category">Videogames </a>
	<a href="category/DIY" 			class="category">DIY </a>
	<a href="category/Miscellaneous" class="category"> Miscellaneous </a>
</div>

