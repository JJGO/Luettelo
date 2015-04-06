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