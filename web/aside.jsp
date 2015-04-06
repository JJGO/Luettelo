<%--
    Document   : aside
    Created on : 06-abr-2015, 01:48:36
    Author     : JJ
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.List"%>
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
                <form action="Login.auth" method="post">
                    <div >
                        <input name="username" type="text"required autocomplete="off" placeholder="Username" />
                    </div>
                    <div >
                        <input name="password" type="password"required autocomplete="off" placeholder="Password"/>
                    </div>
                    <span id="loginError">${loginError}</span>
                    <!-- <p class="forgot"><a href="#">Forgot Password?</a></p> -->
                    <div  style="margin-top:10px">
                        <button id="btnLogIn" type="submit" class="botonAside" ><strong>LOG IN</strong></button>
                    </div>
                </form>
            </div>

            <div class="panelFormulario" id="signupPanel" style="display:none">
                <div class="mensajeFormulario">Sign Up for Free</div>
                <form action="Register.auth" method="post" onsubmit="javascript:return validate();">
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

        <form action="user/${sessionScope.user.username}" style="text-align: center">
        <button id="btnCreated" type="submit" class="botonAside" ><strong>${sessionScope.user.username}'s LISTS</strong></button>
        </form>

        <form action="subscribed" style="text-align: center">
        <button id="btnSubscribed" type="submit" class="botonAside" ><strong>SUBSCRIBED LISTS</strong></button>
        </form>
        <hr/>
        <form action="newList.html" style="text-align: center">
        <button id="btnNewList" type="submit" class="botonAside" ><strong>CREATE A NEW LIST</strong></button>
        </form>
        <hr/>
</c:if>

<div class="trendingL">
    <form action="index" style="text-align: center">
        <button id="btnTlists" type="submit" class="botonAside" ><strong>TRENDING LISTS</strong></button>
        </form>
    <table>
        <c:forEach var="list" items="${defaultLists}">
            <tr>
                <td> ${list.average} </td>
                <td> <a href="items/${list.id}/${list.url}"> ${list.name} </a> </td>
            </tr>
        </c:forEach>
    </table>
</div>





