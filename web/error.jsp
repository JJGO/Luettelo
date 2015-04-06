<%--
    Document   : error
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
                Oops! An error has ocurred. We are sorry for th unconvenience
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
