<%--
    Document   : luettelo
    Created on : 06-abr-2015, 16:48:15
    Author     : JJ
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="Create lists and share them with the world.">
        <meta name="keywords" content="list, item, comment, Luettelo, movies, books, best, miscellaneous">
        <title>${title}</title>
        <!-- CSS -->
        <link rel="stylesheet" href="/css/style.min.css">
        <link rel="shortcut icon" href="/images/favicon.ico">
    </head>
    <body>
        <main>
            <header id="header">
                <c:import url="/header.jsp" charEncoding="UTF-8" />
            </header>
            <div id="content" class="content">
                <c:import url="/${content}.jsp" charEncoding="UTF-8" />
            </div>
            <aside id="aside">
                <c:import url="/aside.jsp" charEncoding="UTF-8" />
            </aside>
            <footer id="footer">
                <c:import url="/footer.jsp" charEncoding="UTF-8" />
            </footer>
        </main>
        <script src="/js/script.min.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>