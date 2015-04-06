<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dominio.List"%>
<!DOCTYPE html>
<div class="component main-component">
    <span class="component-title">
        <a href="Items.show?listId=${displayList.id}">${displayList.name}</a>
        <a class="user-inline" href="Lists.show?type=user&value=${displayList.username}">${displayList.username}</a>
    </span>
    <c:if test="${displayList.username==sessionScope.user.username}">
        <a href="javascript:deleteList())" style="float:right" class="check-icon" >
            <img src="images/delete.png" alt="Eliminar" id="deleteList">
        </a>
        <a href="javascript:editList()" style="float:right" class="check-icon" >
            <img src="images/edit.png" alt="Editar" id="editList">
        </a>
    </c:if>
    <span class="component-rating">${displayList.average}</span>
    <br/>
    <c:if test="${not empty sessionScope.user.username}">
        <c:if test="${not displayList.subscribed}">
            <a href="javascript:subscribeList())" class="subscribe-icon" onmouseover="hoverSubscribe()" onmouseout="unhoverSubscribe()">
                <img src="images/subscribe.png" alt="Subscribe" id="subscribeIcon">
            </a>
        </c:if>
        <c:if test="${displayList.subscribed}">
            <a href="javascript:unsubscribeList())" class="subscribe-icon" onmouseover="hoverUnsubscribe()" onmouseout="unhoverUnsubscribe()">
                <img src="images/subscribed.png" alt="Subscribe" id="subscribeIcon">
            </a>
        </c:if>
    </c:if>
    <c:if test="${displayList.username==sessionScope.user.username}">
        <div class="list-margin component-title" style="display:none" id="list-edit">
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
                <input type="submit" value="Submit" onclick="javascript:commitEditList())">
        </div>
    </c:if>
    <p class="component-description" id="list-description">${displayList.description}</p>
    <br/>
    <a class="component-comments" id="list-comments" href="Comments.show?listId=${displayList.id}">Comments (${displayList.comments})</a>
    <a class="component-category" id="list-category" href="Lists.show?type=category&value=${displayList.category}">${displayList.category}</a>
    <p style="display:none" id="listId">${displayList.id}</p>
</div>

