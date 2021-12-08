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

        <h3 class="mt-5" style="color: #1B4386">Ordre Oversigt</h3>

        <c:if test="${requestScope.inquiryMsg != null}">
            <h4>
                    ${requestScope.inquiryMsg}
            </h4>
        </c:if>
        <c:if test="${sessionScope.user.role == 'customer'}">
            <h5>
                Nedenfor kan du se alle dine registrede forespørgsler / tilbud
            </h5>
        </c:if>

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Order Nummer</th>
                <c:if test="${sessionScope.user.role == 'employee'}">
                    <th>Kunde Navn</th>
                    <th>Kunde Email</th>
                </c:if>
                <th>Forespørgsel oprettet</th>
                <th>Tag matriale</th>
                <th>Ordre Status</th>
                <th> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${sessionScope.allOrderViews}">
                <tr>
                    <td>${order.orderId}</td>
                    <c:if test="${sessionScope.user.role == 'employee'}">
                        <td>${order.user.name}</td>
                        <td>${order.user.email}</td>
                    </c:if>
                    <td>${order.created}</td>
                    <td>${order.roof.roofMaterial.name}</td>
                    <td>${order.status}</td> <%--TODO : Change to buttons with different colors depending on status--%>
                    <td>
                        <c:if test="${sessionScope.user.role == 'employee'}">
                            <form action="${pageContext.request.contextPath}/fc/orderSingleEmployee" method="post">
                                <input type="hidden" name="orderUserId" value="${order.user.id}">
                                <button type="submit" class="btn btn-info btn-sm"
                                        name="viewOrderId" value="${order.orderId}">Se Ordre</button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'customer'}">
                            <form action="${pageContext.request.contextPath}/fc/orderSingleCustomer" method="post">
                                <input type="hidden" name="orderUserId" value="${order.user.id}">
                                <button type="submit" class="btn btn-info btn-sm"
                                        name="viewOrderId" value="${order.orderId}">Se Ordre</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:generic>