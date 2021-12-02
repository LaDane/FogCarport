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

        <h3 class="mt-5" style="color: #1B4386">Ordre</h3>

        <table class="table table-striped table-sm mb-5 col-3">
            <tbody>
            <tr>
                <td style="font-size: larger !important;"><strong>Ordre</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>Odre Nummer</td>
                <td><strong>${sessionScope.orderSingle.orderId}</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Status</td>
                <td><strong>${sessionScope.orderSingle.status}</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Oprettet dato</td>
                <td><strong>${sessionScope.orderSingle.created}</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Leverings dato</td>
                <td><strong>${sessionScope.orderSingle.deliveryDate}</strong></td>
                <td></td>
            </tr>
            <c:if test="${sessionScope.user.role == 'employee'}">
                <tr>
                    <td style="font-size: larger !important;"><strong>Kunde</strong></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Kunde ID</td>
                    <td><strong>${sessionScope.orderSingle.user.id}</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><strong>${sessionScope.orderSingle.user.email}</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Navn</td>
                    <td><strong>${sessionScope.orderSingle.user.name}</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Telefon Nummer</td>
                    <td><strong>${sessionScope.orderSingle.user.phoneNumber}</strong></td>
                    <td></td>
                </tr>
            </c:if>
            <tr>
                <td style="font-size: larger !important;"><strong>Carport</strong></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td>Bredde</td>
                <td><strong>${sessionScope.orderSingle.carport.width} cm</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Længde</td>
                <td><strong>${sessionScope.orderSingle.carport.length} cm</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Højde</td>
                <c:if test="${sessionScope.orderSingle.carport.height < 0}">
                    <td><strong>Ukendt</strong></td>
                </c:if>
                <c:if test="${sessionScope.orderSingle.carport.height >= 0}">
                    <td><strong>${sessionScope.orderSingle.carport.height} cm</strong></td>
                </c:if>
                <td></td>
            </tr>
            <tr>
                <td>Tag hældning</td>
                <td><strong>${sessionScope.orderSingle.carport.roof.slope}°</strong></td>
                <td></td>
            </tr>
            <tr>
                <td>Tag Type</td>
                <td><strong>${sessionScope.orderSingle.carport.roof.roofMaterial.name}</strong></td>
                <td></td>
            </tr>
            <c:if test="${sessionScope.orderSingle.carport.shed != null}">
                <tr>
                    <td style="font-size: larger !important;"><strong>Skur</strong></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Bredde</td>
                    <td><strong>${sessionScope.orderSingle.carport.shed.width} cm</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Længde</td>
                    <td><strong>${sessionScope.orderSingle.carport.shed.length} cm</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Beklædning</td>
                    <td><strong>${sessionScope.orderSingle.carport.shed.cladding.name}</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Placering</td>
                    <td><strong>${sessionScope.orderSingle.carport.shed.placement}</strong></td>
                    <td></td>
                </tr>
            </c:if>
            </tbody>
        </table>

        <div class="col-6">

        </div>


    </jsp:body>
</t:generic>