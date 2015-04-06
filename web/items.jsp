<%--
    Document   : items
    Created on : 06-abr-2015, 2:52:36
    Author     : Lucia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List items</title>
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
            <div class="content">
                <div class="component main-component">
                    <div class="component-title" id="list-title">${displayList.name}</div>
                    <c:if test="${displayList.username==sessionScope.user.username}">
                        <a href="javascript:deleteList()" style="float:right" class="complete-icon" >
                            <img src="images/delete.png" alt="Eliminar" id="deleteList">
                        </a>
                        <a href="javascript:editList()" style="float:right" class="complete-icon" >
                            <img src="images/edit.png" alt="Editar" id="editList">
                        </a>
                    </c:if>
                    <span class="component-rating">${displayList.average}</span>
                    <br/>
                    <c:if test="${not empty sessionScope.user.username}">
                        <a href="javascript:suscribe()" class="suscribe-icon" onmouseover="hoverSuscribe()" onmouseout="unhoverSuscribe()">
                            <img src="images/suscribe.png" alt="Suscribirse" id="suscribeIcon">
                        </a>
                    </c:if>
                    <c:if test="${displayList.username==sessionScope.user.username}">
                        <form action="" class="list-margin component-title" style="display:none" id="list-edit">
                            <table>
                                <tr>
                                    <td>Name</td>
                                    <td><input type="text" name="list-name" id="ltitle-field" class="edit-field"></td>
                                </tr>
                                <tr>
                                    <td>Category</td>
                                    <td><input type="text" name="list-url" id="lcategory-field" class="edit-field"></td>
                                </tr>
                                <tr>
                                    <td>Description</td>
                                    <td><input type="text" name="list-description" id="ldescription-field" class="edit-field"></td>
                                </tr>
                            </table>
                            <br/>
                                <input type="submit" value="Submit">
                        </form>
                    </c:if>
                    <p class="component-description" id="list-description">${displayList.description}</p>
                    <br/>
                    <a class="component-comments" id="list-comments" href="comments.html">Comments (${displayList.comments})</a>
                    <a class="component-category" id="list-category" href="index.html">${displayList.category}</a>
                </div>
                    <div class="component">
                        <div class="component-title">
                            Add a new item to this list
                        </div>
                        <br/>
                        <br/>
                        <form action="">
                            <table>
                                <tr>
                                    <td>Name</td>
                                    <td><input type="text" name="item-name"class="edit-field"></td>
                                </tr>
                                <tr>
                                    <td>URL</td>
                                    <td><input type="text" name="item-url"class="edit-field"></td>
                                </tr>
                            </table>
                            <br/>
                                <input type="submit" value="Submit">
                        </form>
                    </div>
                    <c:forEach var="item" items="${displayItems}">
                        <div class="component">
                            <!--  -->
                            <c:set var="subscription" value="1"/>
                            <c:if test="${subscription==1}">
                            <!--  -->
                                <a href="javascript:complete()"  class="complete-icon" >
                                    <img src="images/complete.png" alt="Completar" id="completeIcon">
                                </a>
                            </c:if>
                            <div class="component-title" id="component-title" >
                                <a id="element" href="${item.url}">${item.name}</a>
                            </div>
                            <form action="" class="component-title" style="display:none" id="component-edit">
                                <table>
                                    <tr>
                                        <td>Name</td>
                                        <td><input type="text" name="item-name" id="name-field" class="edit-field"></td>
                                    </tr>
                                    <tr>
                                        <td>URL</td>
                                        <td><input type="text" name="item-url" id="url-field" class="edit-field"></td>
                                    </tr>
                                </table>
                                <br/>
                                    <input type="submit" value="Submit">
                            </form>
                            <c:if test="${displayList.username==sessionScope.user.username}">
                                <a href="javascript:deleteItem()" style="float:right" class="complete-icon" >
                                    <img src="images/delete.png" alt="Eliminar" id="deleteIcon">
                                </a>
                                <a href="javascript:editItem()" style="float:right" class="complete-icon" >
                                    <img src="images/edit.png" alt="Editar" id="editIcon">
                                </a>
                            </c:if>
                            <span class="component-rating">92</span>
                            <!--  -->
                            <c:if test="${subscription}">
                            <!--  -->
                                <br/>
                                <div class="rating" id="rating">
                                    <span>☆</span><span>☆</span><span>☆</span><span>☆</span><span>☆</span>
                                </div>
                            </c:if>
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
        <!-- JS -->
        <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
        <script src="js/script.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>
