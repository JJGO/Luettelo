<%--
    Document   : lists
    Created on : 04-abr-2015, 19:24:36
    Author     : Lucia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.List"%>
<%@page import="dominio.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="queryMessage">
    <h4> ${queryMessage} </h4>
    <c:if test="${empty displayLists}">
        <p>There are no lists that match this criteria</p>
    </c:if>
</div>
<c:forEach var="list" items="${displayLists}">
    <div class="component ${list.subscribed ? 'subscribed' : ''}"}>
        <div class="component-title">
            <a href="/items/${list.id}/${list.url}">
                ${list.name}
            </a>
            <a class="user-inline" href="/user/${list.username}">${list.username}</a>
        </div>

        <span class="component-rating component-rating-index">${list.average}</span>
        <br/>
        <a class="component-comments" href="/comments/${list.id}/${list.url}">Comments (${list.comments})</a>
        <a class="component-category" href="/category/${list.category}">${list.category}</a>
    </div>
</c:forEach>
