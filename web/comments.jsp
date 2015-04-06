<%--
    Document   : comments
    Created on : 05-abr-2015, 21:19:15
    Author     : Lucia
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="component main-component">
    <span class="component-title">
        <a href="Items.show?listId=${list.id}">${displayList.name}</a>
        <a class="user-inline" href="Lists.show?type=user&value=${displayList.username}">${displayList.username}</a>
    </span>
    <span class="component-rating component-rating-index">${displayList.average}</span>
    <br/><br/>
    <p>${displayList.description}</p>
    <!--<span class="component-comments">Comments (${displayList.comments})</span>-->
    <span class="component-comments">Comments</span>
    <a class="component-category" href="Lists.show?type=category&value=${list.category}">${displayList.category}</a>
</div>
<div class="component comment-component">
    <form onsubmit="javascript:addComment()">
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
        <div class="user"><a href="Lists.show?type=user&value=${comment.username}">${comment.username}</a></div>
        <div>
            <!-- <div class="comment-rating">
            <span id="upvote" class="arrow">▲</span>
            <span id="downvote" class="arrow">▼</span>
            </div> -->
            <div>
                <p class="comment" id="comment_${comment.id}">${comment.content}</p>
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
            <div style="float:right">
                <a href="javascript:editComment(${comment.id})" class="complete-icon" >
                <img src="images/edit.png" alt="Editar" id="completeIcon">
                </a>
                <a href="javascript:deleteComment(${comment.id})" class="complete-icon" >
                <img src="images/delete.png" alt="Eliminar" id="completeIcon">
                </a>
            </div>
        </c:if>
    </div>
</c:forEach>