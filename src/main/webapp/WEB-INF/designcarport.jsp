<%@ page import="business.services.LogicFacade" %>
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

        <h3 class="mt-5" style="color: #1B4386">QUICKBYG</h3>
        <br>
        <div class="container container-fluid" style="background-color: #F8F8F8">
            <div class="row py-3">
                <div class="col-md-3">
                    <table class="table">
                        <tr>
                            <td>
                                <a class="nav-link"
                                   href="https://www.johannesfog.dk/byggecenter/landingpages/carporte/"><strong>Standard
                                    Carporte</strong></a>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col">
                    <div>
                        <c:if test="${sessionScope.hasFlatRoof == 1}">
                            <h3><img style="float: right; margin-left: 10px;" alt=""
                                     src="${pageContext.request.contextPath}/img/roof_flat.png" height="87" width="165"
                                     class="img-responsive mx-3 my-3">Quick-Byg tilbud - carport med fladt tag</h3>
                        </c:if>
                        <c:if test="${sessionScope.hasFlatRoof == 0}">
                            <h3><img style="float: right; margin-left: 10px;" alt=""
                                     src="${pageContext.request.contextPath}/img/roof_raised.png" height="87"
                                     width="165" class="img-responsive mx-3 my-3">Quick-Byg tilbud - carport med
                                rejsning</h3>
                        </c:if>
                        <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en
                            skitsetegning p?? en carport indenfor vores standardprogram, der tilpasses dine specifikke
                            ??nsker.</p>
                        <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>Ved bestilling medf??lger
                            standardbyggevejledning.</p>
                        <p><strong>Udfyld nedenst??ende omhyggeligt og klik p?? "Bestil tilbud"</strong></p>
                    </div>


                    <form action="${pageContext.request.contextPath}/fc/orderNewCommand" method="post">
                        <div class="row align-items-start">
                            <div class="col-sm mt-4">
                                <h4 class="mb-4"><strong>Carport</strong></h4>

                                <label for="carportWidth">Carport bredde</label>
                                <select class="form-control" name="carportWidth" id="carportWidth">
                                    <c:forEach var="width" items="${applicationScope.carportWidths}">
                                        <option value="${width}">${width} cm</option>
                                    </c:forEach>

                                </select>


                                <label for="carportLength">Carport l??ngde</label>
                                <select class="form-control" name="carportLength" id="carportLength">
                                    <c:forEach var="length" items="${applicationScope.carportLengths}">
                                        <option value="${length}">${length} cm</option>
                                    </c:forEach>
                                </select>

                                    <%--                                <label for="carportPoleDistance">Stolpeafstand</label>--%>
                                    <%--                                <select class="form-control" name="carportPoleDistance" id="carportPoleDistance">--%>
                                    <%--                                    <option value="test">Test</option>--%>
                                    <%--                                </select>--%>

                            </div>

                            <div class="col-sm mt-4">
                                <h4 class="mb-4"><strong>Tag</strong></h4>

                                <label for="roofMaterial">Tag type</label>
                                <select class="form-control" name="roofMaterial" id="roofMaterial">
                                    <c:if test="${sessionScope.hasFlatRoof == 1}">
                                        <c:forEach var="roofFlat" items="${applicationScope.roofFlats}">
                                            <option value="${roofFlat.materialId}">${roofFlat.name}</option>
                                        </c:forEach>
                                    </c:if>

                                    <c:if test="${sessionScope.hasFlatRoof == 0}">
                                        <c:forEach var="roofRaised" items="${applicationScope.roofRaiseds}">
                                            <option value="${roofRaised.materialId}">${roofRaised.name}</option>
                                        </c:forEach>
                                    </c:if>

                                </select>

                                <c:if test="${sessionScope.hasFlatRoof == 0}">
                                    <label for="roofSlope">Tag h??ldning</label>
                                    <select class="form-control" name="roofSlope" id="roofSlope">
                                        <option value="15">15??</option>
                                        <option value="20">20??</option>
                                        <option value="25">25??</option>
                                        <option value="30">30??</option>
                                        <option value="35">35??</option>
                                        <option value="40">40??</option>
                                        <option value="45">45??</option>
                                    </select>
                                </c:if>

                            </div>
                        </div>

                        <c:if test="${sessionScope.hasShed == 1}">
                            <br><br>
                            <div class="text-center">
                                <h4><strong>Skur Placering</strong></h4>
                            </div>

                            <div class="row my-3">
                                <div class="col mx-3">
                                    <input class="form-check-input" type="radio" name="shedPlacement" value="NW"
                                           id="shedNW">
                                    <label class="form-check-label text-center" for="shedNW">
                                        Nord-Vest
                                    </label>
                                        ${sessionScope.shedPlacement1}
                                    <div class="text-center">
                                        <strong>Indgang</strong>
                                    </div>
                                </div>
                                <div class="col mx-3">
                                    <input class="form-check-input" type="radio" name="shedPlacement" value="N"
                                           id="shedN" checked>
                                    <label class="form-check-label text-center" for="shedN">
                                        Nord
                                    </label>
                                        ${sessionScope.shedPlacement2}
                                    <div class="text-center">
                                        <strong>Indgang</strong>
                                    </div>
                                </div>
                                <div class="col mx-3">
                                    <input class="form-check-input" type="radio" name="shedPlacement" value="NE"
                                           id="shedNE">
                                    <label class="form-check-label text-center" for="shedNE">
                                        Nord-??st
                                    </label>
                                        ${sessionScope.shedPlacement3}
                                    <div class="text-center">
                                        <strong>Indgang</strong>
                                    </div>
                                </div>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.hasFlatRoof == 1 && sessionScope.user.role != 'employee'}">
                            <div class="form-group mt-1 text-center mt-5">
                                <button type="submit" class="btn btn-lg btn-primary">Bestil tilbud</button>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.hasFlatRoof == 0 && sessionScope.user.role != 'employee'}">
                            <div class="form-group mt-1 text-center mt-5">
                                <p style="color: red !important;">Vi har ikke f??et implementeret tag med rejsning</p>
                                <button type="submit" class="btn btn-lg btn-primary" disabled>Bestil tilbud</button>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.user.role == 'employee'}">
                            <div class="form-group mt-1 text-center mt-5">
                                <p style="color: red !important;">En admin kan ikke bestille tilbud</p>
                                <button type="submit" class="btn btn-lg btn-primary" disabled>Bestil tilbud</button>
                            </div>
                        </c:if>

                    </form>

                    <br><br>

                </div>
            </div>
        </div>

    </jsp:body>
</t:generic>