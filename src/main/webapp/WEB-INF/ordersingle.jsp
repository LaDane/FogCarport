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

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm">

                    <c:if test="${sessionScope.user.role == 'employee'}">
                        <table class="table table-striped table-sm">
                            <thead>
                            <tr>
                                <th>Kunde Information</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="width: 50%">Kunde ID</td>
                                <td style="width: 50%"><strong>${sessionScope.orderSingle.user.id}</strong></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><strong>${sessionScope.orderSingle.user.email}</strong></td>
                            </tr>
                            <tr>
                                <td>Navn</td>
                                <td><strong>${sessionScope.orderSingle.user.name}</strong></td>
                            </tr>
                            <tr>
                                <td>Telefon Nummer</td>
                                <td><strong>${sessionScope.orderSingle.user.phoneNumber}</strong></td>
                            </tr>
                            </tbody>
                        </table>
                    </c:if>

                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>Ordre Information</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="width: 50%">Odre Nummer</td>
                            <td style="width: 50%"><strong>${sessionScope.orderSingle.orderId}</strong></td>
                        </tr>
                        <tr>
                            <td>Status</td>
                            <td><strong>${sessionScope.orderSingle.status}</strong></td>
                        </tr>
                        <tr>
                            <td>Oprettet dato</td>
                            <td><strong>${sessionScope.orderSingle.created}</strong></td>
                        </tr>
                        <tr>
                            <td>Leverings dato</td>
                            <td><strong>${sessionScope.orderSingle.deliveryDate}</strong></td>
                        </tr>
                        </tbody>
                    </table>

                    <c:if test="${sessionScope.user.role == 'employee'}">
                        <form action="${pageContext.request.contextPath}/fc/updatePriceTable">
                            <table class="table table-striped table-sm">
                                <thead>
                                <tr>
                                    <th>Pris</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="width: 50%">Udsalgspris pris</td>
                                    <td style="width: 50%"><strong>${sessionScope.orderPrice}</strong> DKK</td>
                                </tr>
                                <tr>
                                    <td>Fremskaf indkøbspris %</td>
                                    <td>
                                        <input style="width: 20%" type="number" class="form-control form-control-sm"
                                               name="priceReductionPercent" id="priceReductionPercent" placeholder="${sessionScope.priceReductionPercent}"
                                               value="${sessionScope.priceReductionPercent}">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Indkøbspris</td>
                                    <td><strong>${sessionScope.purchasePrice}</strong> DKK</td>
                                </tr>
                                <tr>
                                    <td>Fortjeneste %</td>
                                    <td>
                                        <input style="width: 20%" type="number" class="form-control form-control-sm"
                                               name="priceIncreasePercent" id="priceIncreasePercent" placeholder="${sessionScope.priceIncreasePercent}"
                                               value="${sessionScope.priceIncreasePercent}">
                                    </td>
                                </tr>
                                <tr>
                                    <td>Forslået pris</td>
                                    <td><strong>${sessionScope.suggestedPrice}</strong> DKK</td>
                                </tr>
                                <tr>
                                    <td>Avance</td>
                                    <td><strong>TILFØJ DETTE!</strong> DKK</td>
                                </tr>
                                </tbody>
                            </table>
                            <button type="submit" class="btn btn-outline-success btn-sm">Opdater pris tabel</button>
                        </form>
                    </c:if>
                </div>

                <div class="col-sm">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>Carport Information</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td style="width: 50%">Bredde</td>
                            <td style="width: 50%"><strong>${sessionScope.orderSingle.carport.width} cm</strong></td>
                        </tr>
                        <tr>
                            <td>Længde</td>
                            <td><strong>${sessionScope.orderSingle.carport.length} cm</strong></td>
                        </tr>
                        <tr>
                            <td>Højde</td>
                            <c:if test="${sessionScope.orderSingle.carport.height < 0}">
                                <td><strong>Ukendt</strong></td>
                            </c:if>
                            <c:if test="${sessionScope.orderSingle.carport.height >= 0}">
                                <td><strong>${sessionScope.orderSingle.carport.height} cm</strong></td>
                            </c:if>
                        </tr>
                        <tr>
                            <td>Tag hældning</td>
                            <td><strong>${sessionScope.orderSingle.carport.roof.slope}°</strong></td>
                        </tr>
                        <tr>
                            <td>Tag Type</td>
                            <td><strong>${sessionScope.orderSingle.carport.roof.roofMaterial.name}</strong></td>
                        </tr>
                        </tbody>
                    </table>


                    <c:if test="${sessionScope.orderSingle.carport.shed != null}">
                        <table class="table table-striped table-sm">
                            <thead>
                            <tr>
                                <th>Skur Information</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td style="width: 50%">Bredde</td>
                                <td style="width: 50%"><strong>${sessionScope.orderSingle.carport.shed.width}
                                    cm</strong></td>
                            </tr>
                            <tr>
                                <td>Længde</td>
                                <td><strong>${sessionScope.orderSingle.carport.shed.length} cm</strong></td>
                            </tr>
                            <tr>
                                <td>Beklædning</td>
                                <td><strong>${sessionScope.orderSingle.carport.shed.cladding.name}</strong></td>
                            </tr>
                            <tr>
                                <td>Placering</td>
                                <td><strong>${sessionScope.orderSingle.carport.shed.placement}</strong></td>
                            </tr>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>

        <br><br>

        <h3>Materiale liste</h3>
        <h4>Træ og tagplader</h4>

        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>Vare</th>
                <th>Dimension</th>
                <th>Længde</th>
                <th>Antal</th>
                <th>Beskrivelse</th>
                    <%--                <th>Pris</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.orderLines}" var="ol">
                <tr>
                    <td>${ol.material.name}</td>
                    <td>${ol.material.dimension}</td>
                    <td>${ol.length}</td>
                    <td>${ol.amount}</td>
                    <td>${ol.material.description}</td>
                        <%--                    <td>${ol.price}</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br>
        <h4>Beslag og skruer</h4>

        <table class="table table-striped table-sm">
            <thead>
            <tr>
                <th>Vare</th>
                <th>Dimension</th>
                <th>Antal</th>
                <th>Beskrivelse</th>
                    <%--<th>Pris</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.orderLinesFittings}" var="ol">
                <tr>
                    <td>${ol.material.name}</td>
                    <td>${ol.material.dimension}</td>
                    <td>${ol.amount}</td>
                    <td>${ol.material.description}</td>
                        <%--                    <td>${ol.price}</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:body>
</t:generic>