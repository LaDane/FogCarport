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
        <div class="row">
            <h3 class="mt-5" style="color: #1B4386">Ordre</h3>
            <c:if test="${sessionScope.user.role == 'employee' && sessionScope.orderSingle.status == 'Forespørgsel'}">
                <form action="${pageContext.request.contextPath}/fc/orderSendPrice">
                    <button type="submit" class="btn btn-primary float-end">Send tilbud</button>
                </form>
            </c:if>
        </div>

        <c:if test="${requestScope.confirmMsg != null}">
            <h4>
                ${requestScope.confirmMsg}
            </h4>
        </c:if>

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

                    <c:if test="${sessionScope.user.role == 'customer' && sessionScope.orderSingle.status != 'Forespørgsel'}">
                        <form action="${pageContext.request.contextPath}/fc/orderPayCommand">
                            <table class="table table-striped table-sm">
                                <thead>
                                <tr>
                                    <th>Pris</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td style="width: 50%">Totale pris</td>
                                    <td style="width: 50%"><strong>${sessionScope.suggestedPriceString}</strong> DKK</td>
                                </tr>
                                </tbody>
                            </table>

                            <c:if test="${sessionScope.orderSingle.status == 'Tilbud sendt'}">
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary btn-lg">Køb og betal</button>
                                </div>
                            </c:if>

                        </form>
                    </c:if>

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
                                    <c:if test="${sessionScope.orderSingle.status == 'Forespørgsel'}">
                                        <td>
                                            <input style="width: 40%" type="number" class="form-control form-control-sm"
                                                   name="priceReductionPercent" id="priceReductionPercent"
                                                   placeholder="${sessionScope.priceReductionPercent}"
                                                   value="${sessionScope.priceReductionPercent}">
                                        </td>
                                    </c:if>
                                    <c:if test="${sessionScope.orderSingle.status != 'Forespørgsel'}">
                                        <td><strong>${sessionScope.priceReductionPercent}</strong> %</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>Indkøbspris</td>
                                    <td><strong>${sessionScope.purchasePrice}</strong> DKK</td>
                                </tr>
                                <tr>
                                    <td>Fortjeneste %</td>
                                    <c:if test="${sessionScope.orderSingle.status == 'Forespørgsel'}">
                                        <td>
                                            <input style="width: 40%" type="number" class="form-control form-control-sm"
                                                   name="priceIncreasePercent" id="priceIncreasePercent"
                                                   placeholder="${sessionScope.priceIncreasePercent}"
                                                   value="${sessionScope.priceIncreasePercent}">
                                        </td>
                                    </c:if>
                                    <c:if test="${sessionScope.orderSingle.status != 'Forespørgsel'}">
                                        <td><strong>${sessionScope.priceIncreasePercent}</strong> %</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>Forslået pris</td>
                                    <td><strong>${sessionScope.suggestedPrice}</strong> DKK</td>
                                </tr>
                                <tr>
                                    <td>Avance</td>
                                    <td><strong>${sessionScope.profit}</strong> DKK</td>
                                </tr>
                                </tbody>
                            </table>

                            <c:if test="${sessionScope.orderSingle.status == 'Forespørgsel'}">
                                <button type="submit" class="btn btn-outline-success btn-sm">Opdater pris tabel</button>
                            </c:if>

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

        <h3>Carport Tegninger</h3>

        <div class="row">
            <div class="col-sm">
                    ${sessionScope.svgDrawingPlan}
            </div>
            <div class="col-sm">
                    ${sessionScope.svgDrawingSide}
            </div>
        </div>

        <br><br>

        <c:if test="${sessionScope.user.role == 'employee' || sessionScope.orderSingle.status == 'Betalt'}">
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
        </c:if>


    </jsp:body>
</t:generic>