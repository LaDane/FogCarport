<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

    <meta name="theme-color" content="#7952b3">
</head>
<body>

<div class="container my-3">
    <header>
        <nav class="navbar navbar-expand-lg navbar-light rounded" aria-label="Eleventh navbar example"
             style="background-color: #1B4386">

            <div class="container-fluid">

                <a class="navbar-brand" href="${pageContext.request.contextPath}"><img
                        src="${pageContext.request.contextPath}/img/Fog_logo.png" class="img-fluid"
                        alt="Fog Logo"></a>

                <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse flex-column align-items-start" id="navbarsExample09">

                    <ul class="navbar-nav">

                        <c:if test="${sessionScope.user == null}">
                            <li class="nav-item">
                                <a class="nav-link navbar-link"
                                   href="${pageContext.request.contextPath}/fc/loginSignupCommand">Login / Signup</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <li class="nav-item">
                                <a class="nav-link navbar-text">Hej ${sessionScope.user.getName()}</a>
                            </li>
                            <li class="nav-item">
                                <c:if test="${sessionScope.user.getRole().equals('customer')}">
                                    <a class="nav-link navbar-link" href="${pageContext.request.contextPath}/fc/profileCommandCustomer">Profil</a>
                                </c:if>
                                <c:if test="${sessionScope.user.getRole().equals('employee')}">
                                    <a class="nav-link navbar-link" href="${pageContext.request.contextPath}/fc/profileCommandEmployee">Profil</a>
                                </c:if>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.getRole().equals('customer')}">
                            <li class="nav-item">
                                <a class="nav-link navbar-link" href="${pageContext.request.contextPath}/fc/orderOverviewCustomer">Mine Tilbud</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user.getRole().equals('employee')}">
                            <li class="nav-item">
                                <a class="nav-link navbar-link" href="${pageContext.request.contextPath}/fc/orderOverviewEmployee">Ordre Oversigt</a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <li class="nav-item">
                                <a class="nav-link navbar-link navbar-signout" href="${pageContext.request.contextPath}/fc/logoutcommand">Log ud</a>
                            </li>
                        </c:if>
                    </ul>


                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link navbar-link navbar-bottom-links" href="https://www.johannesfog.dk/">Johannes Fog</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link navbar-link navbar-bottom-links"
                               href="${pageContext.request.contextPath}">QuickByg</a>
                        </li>
                    </ul>
                </div>

                <%--                <div class="mr-auto">--%>
                <%--                    <form>--%>
                <%--                        <input class="form-control hide-in-hamburger" type="text" placeholder="Search" aria-label="Search">--%>
                <%--                    </form>--%>
                <%--                </div>--%>

            </div>
        </nav>
    </header>

    <div id="body" style="min-height: 20vh;">
        <jsp:doBody/>
    </div>

    <!-- Footer -->

    <div class="container px-0 my-3 text-center">
        <img src="${pageContext.request.contextPath}/img/Footer2.PNG" class="img-fluid rounded" width="100%">

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

</body>
</html>