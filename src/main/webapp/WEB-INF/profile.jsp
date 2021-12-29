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
        <h3 class="mt-5" style="color: #1B4386">${sessionScope.user.name}'s Profil</h3>
        <br>
        <div class="container container-fluid" style="background-color: #F8F8F8">
            <table class="table table-sm mb-5 col-3">
                <tbody>
                <tr>
                    <td>Bruger Id</td>
                    <td><strong>${sessionScope.user.id}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><strong>${sessionScope.user.email}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Navn</td>
                    <td><strong>${sessionScope.user.name}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Adresse</td>
                    <td><strong>${sessionScope.user.address}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>By</td>
                    <td><strong>${sessionScope.user.city}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Postnummer</td>
                    <td><strong>${sessionScope.user.zip}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Telefon nummer</td>
                    <td><strong>${sessionScope.user.phoneNumber}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                    <td>Rolle</td>
                    <td><strong>${sessionScope.user.role}</strong></td>
                    <td> </td>
                </tr>
                <tr>
                </tbody>
            </table>
        </div>

    </jsp:body>
</t:generic>