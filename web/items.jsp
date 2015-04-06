<%--
    Document   : items
    Created on : 06-abr-2015, 2:52:36
    Author     : Lucia
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Item"%>
<%@page import="dominio.User"%>
<%@page import="dominio.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:import url="/displayList.jsp" charEncoding="UTF-8" />
<c:if test="${displayList.username==sessionScope.user.username}">
    <div class="component">
        <div class="component-title">
            Add a new item to this list
        </div>
        <br/>
        <br/>
        <div >
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="item-name" id="item-name" class="edit-field"></td>
                </tr>
                <tr>
                    <td>URL</td>
                    <td><input type="text" name="item-url" id="item-url" class="edit-field"></td>
                </tr>
            </table>
            <br/>
                <input type="submit" value="Submit" onclick="addItem(${displayList.id})" >
        </div>
    </div>
</c:if>
<c:forEach var="item" items="${displayItems}">
    <div class="component">
        <c:if test="${displayList.subscribed}">
            <c:if test="${empty item.rating}">
                <a href="javascript:checkItem(${item.id})"  class="check-icon" onmouseover="hoverCheck(${item.id})" onmouseout="unhoverCheck(${item.id})">
                    <img src="images/check.png" alt="Check" id="checkIcon-${item.id}">
                </a>
            </c:if>

            <c:if test="${not empty item.rating}">
                <a href="javascript:uncheckItem(${item.id})"  class="check-icon" onmouseover="hoverUncheck(${item.id})" onmouseout="unhoverUncheck(${item.id})">
                    <img src="images/checked.png" alt="Uncheck" id="checkIcon-${item.id}">
                </a>
            </c:if>
        </c:if>
        <div class="component-title" id="item-title-${item.id}" >
            <a id="item-url-${item.id}" href="${item.url}">${item.name}</a>
        </div>
        <c:if test="${displayList.username==sessionScope.user.username}">
        <div class="component-title" style="display:none" id="item-edit-${item.id}">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" id="name-field-${item.id}"  class="edit-field"></td>
                </tr>
                <tr>
                    <td>URL</td>
                    <td><input type="text" id="url-field-${item.id}"  class="edit-field"></td>
                </tr>
            </table>
            <br/>
                <input type="submit" value="Submit" onclick="javascript:commitEditItem(${item.id})">
        </div>
            <a href="javascript:removeItem(${item.id})" style="float:right" class="check-icon" >
                <img src="images/delete.png" alt="Eliminar" id="deleteIcon">
            </a>
            <a href="javascript:editItem(${item.id})" style="float:right" class="check-icon" >
                <img src="images/edit.png" alt="Editar" id="editIcon">
            </a>
        </c:if>
        <span class="component-rating">${item.average}</span>
        <br/>
        <c:if test="${not empty item.rating}">
            <div class="rating" id="rating">
                <c:forEach var="i" begin="${item.rating+1}" end="5">
                    <span onclick="javascript:rateItem(${item.id},${5-i})">&#x2606;</span>
                </c:forEach>
                <c:forEach var="i" begin="1" end="${item.rating}">
                    <span onclick="javascript:rateItem(${item.id},${5-i})">&#x2605;</span>
                </c:forEach>
            </div>
        </c:if>
    </div>
</c:forEach>