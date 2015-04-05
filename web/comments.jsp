<%-- 
    Document   : comments
    Created on : 05-abr-2015, 21:19:15
    Author     : Lucia
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Comentarios</title>
        <!-- CSS -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/search.css">
        <link rel="shortcut icon" href="images/favicon.ico">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    </head>
    <body>
        <main>
            <header id="header">
            </header>
            <div class="content">
                <div class="component main-component">
                    <div class="component-title">
                        <a href="list.html">${displayList.name}</a>
                    </div>
                    <span class="rating">${displayList.average}</span>
                    <br/>
                    <p>${displayList.description}</p>
                    <a class="component-category" href="index.html">${displayList.category}</a>
                </div>
                    <div class="component comment-component">
                        <form>
                            <div class="right-holder">
                                <textarea class="comment-box"></textarea>
                            </div>
                            <div class="right-holder">
                                 <input type="submit" value="Submit"/>
                            </div>
                        </form>
                    </div>
                    <c:forEach var="comment" items="${displayComments}">
                        <div class="component">
                            <div class="user">${comment.username}</div>
                            <div>
                                <!-- <div class="comment-rating">
                                <span id="upvote" class="arrow">▲</span>
                                <span id="downvote" class="arrow">▼</span>
                                </div> -->
                                <div>
                                    <p class="comment" id="comment">${comment.content}</p>
                                    <form id="comment-edit"style="display:none">
                                        <div class="right-holder">
                                            <textarea class="comment-edit-box" id="comment-field"></textarea>
                                        </div>
                                        <div class="right-holder">
                                             <input type="submit" value="Submit"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <c:if test="${comment.username==sessionScope.user.username}">
                                <a href="javascript:deleteComment()" style="float:right" class="complete-icon" > 
                                <img src="images/delete.png" alt="Eliminar" id="completeIcon">
                                </a>
                                <a href="javascript:editComment()" style="float:right" class="complete-icon" >
                                <img src="images/edit.png" alt="Editar" id="completeIcon">
                                </a>
                            </c:if>
                        </div>
                    </c:forEach>
            </div>
            <aside id="aside">
            </aside>
            <footer id="footer">
            </footer>
        </main>
        <!-- JS -->
        <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
        <script src="js/script.js" type="text/javascript" charset="utf-8"></script>
    </body>
</html>