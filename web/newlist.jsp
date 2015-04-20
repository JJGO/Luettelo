<%--
    Document   : footer
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="component">
    <div class="component-title">
        Create a new list
    </div>
    <br/><br/>
    <form action="/addList" method="POST" id="newListForm">
        <table>
            <tr>
                <td>Name</td>
                <td><input class="rounded-component textbox" type="text" name="name"></td>
            </tr>
            <tr>
                <td>Category</td>
                <td><select name="category" class="rounded-component multiplechoice" form="newListForm">
                  <option value="Movies">Movies</option>
                  <option value="Books">Books</option>
                  <option value="Series">Series</option>
                  <option value="Videogames">Videogames</option>
                  <option value="DIY">DIY</option>
                  <option value="Miscellaneous">Miscellaneous</option>
                </select></td>
            </tr>
            <tr>
                <td>Description</td>
                <td></td>
            </tr>
        </table>
        <textarea name="description" form="newListForm" class="rounded-component comment-box add-description-list"></textarea>
        <br/><br/>
            <input type="submit" value="Submit" class="my-button">
    </form>
</div>
