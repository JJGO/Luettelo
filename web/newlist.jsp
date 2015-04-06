<%--
    Document   : footer
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Luettelo</title>
    <!-- CSS -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="shortcut icon" href="images/favicon.ico">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body>
    <main>
        <header id="header">
            <%@include file="header.jsp" %>
        </header>
        <c:if test="${not empty sessionScope.user}">
            <div class="content">
                <div class="component">
                    <div class="component-title">
                        Create a new list
                    </div>
                    <br/><br/>
                    <form action="AddList.do" method="POST" id="newListForm">
                        <table>
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="name"></td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td><input type="text" name="category"></td>
                            </tr>
                            <tr>
                                <td>Description</td>
                                <td></td>
                            </tr>
                        </table>
                        <textarea name="description" form="newListForm" class="comment-box"></textarea>
                        <br/><br/>
                        <!-- <div class="component-title">
                            Add the first item to your list
                        </div>
                        <br/><br/>
                        <table>
                            <tr>
                                <td>Name</td>
                                <td><input type="text" name="item-name"></td>
                            </tr>
                            <tr>
                                <td>URL</td>
                                <td><input type="text" name="item-url"></td>
                            </tr>
                        </table>
                        <br/><br/> -->
                            <input type="submit" value="Submit">
                    </form>
                </div>
            </div>
        </c:if>
        <aside id="aside">
            <%@include file="aside.jsp" %>
        </aside>
        <footer id="footer">
            <%@include file="footer.jsp" %>
        </footer>
    </main>
    <!-- JS -->
    <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
    <script src="js/script.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>