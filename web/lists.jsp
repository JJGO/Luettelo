<%--
    Document   : lists
    Created on : 04-abr-2015, 19:24:36
    Author     : Lucia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Luettelo</title>
        <!-- CSS -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/search.css">
        <link rel="shortcut icon" href="images/favicon.ico">
    </head>
    <body onLoad="javascript:showLogin();">
        <main>
            <header id="header">
                <%@include file="header.jsp" %>
            </header>
            <div class="content">
                <c:forEach var="list" items="${displayLists}">
                    <div class="component ${list.subscribed ? 'subscribed' : ''}"}>
                        <!-- TO DO: Change all href's -->
                        <div class="component-title">
                            <a href="list.html">
                                ${list.name}
                            </a>
                        </div>
                        <span class="component-rating component-rating-index">${list.average}</span>
                        <br/>
                        <a class="component-comments" href="comments.html">Comments (${list.comments})</a>
                        <a class="component-category" href="lists.html">${list.category}</a>
                    </div>
                </c:forEach>
            </div>
            <aside id="aside">
                <%@include file="aside.jsp" %>
            </aside>
            <footer id="footer">
                <%@include file="footer.jsp" %>
            </footer>
        </main>
        <script src="js/script.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
