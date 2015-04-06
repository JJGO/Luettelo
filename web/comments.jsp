<%--
    Document   : comments
    Created on : 05-abr-2015, 21:19:15
    Author     : Lucia
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Comment"%>
<%@page import="dominio.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/displayList.jsp" charEncoding="UTF-8" />
<c:if test="${not empty sessionScope.user}">
    <div class="component comment-component">
        <form onsubmit="javascript:addComment()">
            <div class="right-holder">
                <textarea class="comment-box" id="comment-field"></textarea>
            </div>
            <div class="right-holder">
                 <input type="submit" value="Submit"/>
            </div>
        </form>
    </div>
</c:if>

<c:forEach var="comment" items="${displayComments}">
    <div class="component">
        <div class="user"><a href="Lists.show?type=user&value=${comment.username}">${comment.username}</a></div>
        <div>
            <!-- <div class="comment-rating">
            <span id="upvote" class="arrow">▲</span>
            <span id="downvote" class="arrow">▼</span>
            </div> -->
            <div>
                <p class="comment" id="comment-${comment.id}">${comment.content}</p>
                <c:if test="${comment.username==sessionScope.user.username}">
                    <div id="comment-edit-${comment.id}"style="display:none">
                        <div class="right-holder">
                            <textarea class="comment-edit-box" id="comment-field-${comment.id}"></textarea>
                        </div>
                        <div class="right-holder">
                             <input type="submit" value="Submit"  onclick="javascript:commitEditComment(${comment.id})"/>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${comment.username==sessionScope.user.username}">
            <div style="float:right">
                <a href="javascript:editComment(${comment.id})" class="check-icon" >
                <img src="images/edit.png" alt="Edit Comment" id="editCommentIcon">
                </a>
                <a href="javascript:removeComment(${comment.id})" class="check-icon" >
                <img src="images/delete.png" alt="Remove Comment" id="removeCommentIcon">
                </a>
            </div>
        </c:if>
    </div>
</c:forEach>