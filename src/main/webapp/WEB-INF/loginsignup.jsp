<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:generic>

    <jsp:attribute name="header">
         QuickByg
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <h3 class="mt-5" style="color: #1B4386">Login / Signup</h3>
        <br>
        <div class="container container-fluid" style="background-color: #F8F8F8">
            <div class="row">

                <!--            TODO: Create inline rows that dont cut off text with input box og beautify! -->

                <div class="col">
                    <h3>Login med eksiterende konto</h3>
                    <form name="login" action="${pageContext.request.contextPath}/fc/logincommand"  method="POST">
                        <!--                    <div class="row mb-3">-->
                        <label class="col-sm-1 col-form-label" for="email" id="loginemail">Email</label>
                        <div class="col-sm-4">
                            <input class="form-control" type="text" name="loginemail" placeholder="someone@nowhere.com">
                        </div>
                        <!--                    </div>-->
                        <!--                    <div class="row mb-3">-->
                        <label class="col-sm-1 col-form-label" for="password" id="password">Password</label><br>
                        <div class="col-sm-4">
                            <input class="form-control" type="password" name="password" placeholder="sesam">
                        </div>
                        <!--                    </div>-->
                        <c:if test="${requestScope.errorLogin != null }">
                            <p style="color:red">
                                    ${requestScope.errorLogin}
                            </p>
                        </c:if>

                        <c:if test="${not empty param.msg}">
                            <p style="font-size: large">${param.msg}</p>
                        </c:if>
                        <button class="btn btn-primary mb-3" type="submit" value="Login">Login</button>
                    </form>
                </div>

                <div class="col">
                    <h3>Opret ny konto</h3>
                    <form name="login" action="${pageContext.request.contextPath}/fc/registercommand" method="POST">
                        <!--                    <div class="row mb-3">-->
                        <label class="col-sm-1 col-form-label" for="email">Email</label><br>
                        <div class="col-sm-4">
                            <input id="email" class="form-control" type="text" name="email" placeholder="Skriv din email">
                        </div>
                        <!--                    </div>-->
                        <!--                    <div class="row mb-3">-->
                        <label class="col-sm-1 col-form-label" for="password1">Password</label><br>
                        <div class="col-sm-4">
                            <input id="password1" class="form-control" type="password" name="password1" placeholder="Enter your password">
                        </div>
                        <!--                    </div>-->
                        <!--                    <div class="row mb-3">-->
                        <label class="col-sm-1 col-form-label" for="password2">Password</label><br>
                        <div class="col-sm-4">
                            <input id="password2" class="form-control" type="password" name="password2" placeholder="Repeat the password">
                        </div>
                        <!--                    </div>-->

                        <label class="col-sm-1 col-form-label" for="name">Navn</label><br>
                        <div class="col-sm-4">
                            <input id="name" class="form-control" type="text" name="name" placeholder="Navn">
                        </div>

                        <label class="col-sm-1 col-form-label" for="address">Adresse</label><br>
                        <div class="col-sm-4">
                            <input id="address" class="form-control" type="text" name="address" placeholder="Adresse">
                        </div>

                        <label class="col-sm-1 col-form-label" for="city">By</label><br>
                        <div class="col-sm-4">
                            <input id="city" class="form-control" type="text" name="city" placeholder="By">
                        </div>

                        <label class="col-sm-1 col-form-label" for="zip">Postnummer</label><br>
                        <div class="col-sm-4">
                            <input id="zip" class="form-control" type="number" name="zip" placeholder="Postnummer">
                        </div>

                        <label class="col-sm-1 col-form-label" for="phoneNumber">Telefon</label><br>
                        <div class="col-sm-4">
                            <input id="phoneNumber" class="form-control" type="number" name="phoneNumber" placeholder="Telefon nummer">
                        </div>

                        <button class="btn btn-primary mb-3" type="submit" value="Signup">Signup</button>
                    </form>


                    <c:if test="${requestScope.errorSignup != null }">
                        <p style="color:red">
                                ${requestScope.errorSignup}
                        </p>
                    </c:if>
                </div>
            </div>

        </div>

    </jsp:body>
</t:generic>