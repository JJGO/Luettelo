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
                        <div class="component-title">
                        <!-- Pasar a URL Rewrite -->
                            <!-- <a href="items/${list.id}/${list.url}">
                                ${list.name}
                            </a> -->
                            <a href="Items.show?listId=${list.id}">
                                ${list.name}
                            </a>
                            <!-- <a class="user-inline" href="user/${displayList.username}">${displayList.username}</a> -->
                            <a class="user-inline" href="Lists.show?type=user&value=${list.username}">${list.username}</a>
                        </div>

                        <span class="component-rating component-rating-index">${list.average}</span>
                        <br/>
                        <!-- <a class="component-comments" hhref="comments/${list.id}/${list.url}">Comments (${list.comments})</a> -->
                        <a class="component-comments" href="Comments.show?listId=${list.id}">Comments (${list.comments})</a>
                        <!-- <a class="component-category" href="category/{list.category}">${list.category}</a> -->
                        <a class="component-category" href="Lists.show?type=category&value=${list.category}">${list.category}</a>
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
