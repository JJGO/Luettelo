<%--
    Document   : aside
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.List"%>
<%@page import="dominio.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${empty sessionScope.user}">
    <div class="formulario">
        <div class="opcion">
                <a id="btnShowLogin" href="javascript:showLogin();" class="botonFormulario">Log In</a>
                <a id="btnShowSignUp" href="javascript:showSignUp();" class="botonFormulario" style="background:#bf3535">Sign Up</a>
            </div>

            <div class="panelFormulario" id="loginPanel">
                <div class="mensajeFormulario">Welcome Back!</div>
                <div>
                    <input name="username" id="username_login" type="text" required autocomplete="off" placeholder="Username" />
                </div>
                <div>
                    <input name="password" id="password_login" type="password" required autocomplete="off" placeholder="Password"/>
                </div>
                <span id="loginError">${loginError}</span>
                <div style="margin-top:10px">
                    <button id="btnLogIn" type="button"  onclick="location.href='javascript:login();'"><strong>LOG IN</strong></button>
                </div>
            </div>

            <div class="panelFormulario" id="signupPanel" style="display:none">
                <div class="mensajeFormulario">Sign Up for Free</div>
                <div>
                    <input id="username" name="username" type="text"required autocomplete="off" placeholder="Username" />
                </div>
                <span id="errorUsername"></span>
                <div>
                    <input id="email" name="email" type="email"required autocomplete="off" placeholder="Email Adress" />
                </div>
                <span id="errorEmail"></span>
                <div>
                    <input id="password" name="password" type="password"required autocomplete="off" placeholder="Password"/>
                </div>
                <span id="errorPassword"></span>
                <div>
                    <input id="rpassword" type="password"required autocomplete="off" placeholder="Retype Password"/>
                </div>
                <span id="errorRpassword"></span>
                <div class="botonAside" style="margin-top:10px">
                    <button id="btnSignUp" type="button"  onclick="location.href='javascript:validateSignUp();'"><strong>GET STARTED</strong></button>
                </div>
            </div>
    </div>
</c:if>

<c:if test="${not empty sessionScope.user}">
     <div class="mensajeFormulario">Hi  ${sessionScope.user.username}</div>
     <a href="javascript:logout();"> Logout </a>
        <div class="botonAside">
            <button onclick="window.location.href='/user/${sessionScope.user.username}'" id="btnCreated"  ><strong>${fn:toUpperCase(sessionScope.user.username)}'S LISTS</strong></button>
        </div>


    <div class="botonAside">
        <button  onclick="window.location.href='/subscriptions'" id="btnSubscribed"  ><strong>SUBSCRIBED LISTS</strong></button>
    </div>
    <hr/>
    <div class="botonAside">
        <button onclick="window.location.href='/addList'" id="btnNewList"  ><strong>CREATE A NEW LIST</strong></button>
    </div>
        <hr/>
</c:if>

<div class="visitedLists">
    <div class="botonAside">
        <button onclick="window.location.href='/visited'" id="btnTlists"  ><strong>VISITED LISTS</strong></button>
    </div>
    <table>
        <c:forEach var="list" items="${visitedLists}">
            <tr>
                <td> ${list.average} </td>
                <td> <a href="/items/${list.id}/${list.url}"> ${list.name} </a> </td>
            </tr>
        </c:forEach>
    </table>
</div>





