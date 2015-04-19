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
                    <input name="username" type="text" required autocomplete="off" placeholder="Username" />
                </div>
                <div>
                    <input name="password" type="password" required autocomplete="off" placeholder="Password"/>
                </div>
                <span id="loginError">${loginError}</span>
                <div style="margin-top:10px">
                    <button id="btnLogIn" type="button" class="botonAside" onclick="location.href='javascript:loginJSON();'"><strong>LOG IN</strong></button>
                </div>
            </div>

            <div class="panelFormulario" id="signupPanel" style="display:none">
                <div class="mensajeFormulario">Sign Up for Free</div>
                <form action="Register.auth" method="post" onsubmit="javascript:return validateSignUp();">
                    <div >
                        <input id="username" name="username" type="text"required autocomplete="off" placeholder="Username" />
                    </div>
                    <span id="errorUsername"></span>
                    <div >
                        <input id="email" name="email" type="email"required autocomplete="off" placeholder="Email Adress" />
                    </div>
                    <span id="errorEmail"></span>
                    <div >
                        <input id="password" name="password" type="password"required autocomplete="off" placeholder="Password"/>
                    </div>
                    <span id="errorPassword"></span>
                    <div >
                        <input id="rpassword" type="password"required autocomplete="off" placeholder="Retype Password"/>
                    </div>
                    <span id="errorRpassword"></span>
                    <div style="margin-top:10px">
                        <button id="btnSignUp" type="submit" class="botonAside" ><strong>GET STARTED</strong></button>
                    </div>
                </form>
            </div>
    </div>
</c:if>

<c:if test="${not empty sessionScope.user}">
     <div class="mensajeFormulario" style="text-transform: capitalize">Hi  ${sessionScope.user.username}</div>
     <a href="Logout.auth"> Logout </a>
        <!-- <div onClick="user/${sessionScope.user.username}" style="text-align: center"> -->
        <div style="text-align: center">
            <button onclick="window.location.href='Lists.show?type=user&value=${sessionScope.user.username}'" id="btnCreated" class="botonAside" ><strong>${fn:toUpperCase(sessionScope.user.username)}'S LISTS</strong></button>
        </div>


    <!-- <div onClick="window.location.href='subscribed" style="text-align: center"> -->
    <div style="text-align: center">
        <button  onclick="window.location.href='Lists.show?type=subscribed'" id="btnSubscribed" class="botonAside" ><strong>SUBSCRIBED LISTS</strong></button>
    </div>
    <hr/>
    <div style="text-align: center">
        <button onclick="window.location.href='AddList.do'" id="btnNewList" class="botonAside" ><strong>CREATE A NEW LIST</strong></button>
    </div>
        <hr/>
</c:if>

<div class="trendingL">
    <div style="text-align: center">
        <button onclick="window.location.href='Lists.show'" id="btnTlists" class="botonAside" ><strong>TRENDING LISTS</strong></button>
    </div>
    <table>
        <c:forEach var="list" items="${trendingLists}">
            <tr>
                <td> ${list.average} </td>
                <!-- Pasar a URL Rewrite -->
                <!-- <td> <a href="items/${list.id}/${list.url}"> ${list.name} </a> </td> -->
                <td> <a href="Items.show?listId=${list.id}"> ${list.name} </a> </td>
            </tr>
        </c:forEach>
    </table>
</div>





