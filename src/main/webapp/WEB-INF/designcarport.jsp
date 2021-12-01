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
                                <a class="nav-link" href="https://www.johannesfog.dk/byggecenter/landingpages/carporte/"><strong>Standard Carporte</strong></a>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col">
                    <div>
                        <c:if test="${sessionScope.hasFlatRoof == 1}">
                            <h3><img style="float: right; margin-left: 10px;" alt="" src="${pageContext.request.contextPath}/img/roof_flat.png" height="87" width="165" class="img-responsive mx-3 my-3">Quick-Byg tilbud - carport med fladt tag</h3>
                        </c:if>
                        <c:if test="${sessionScope.hasFlatRoof == 0}">
                            <h3><img style="float: right; margin-left: 10px;" alt="" src="${pageContext.request.contextPath}/img/roof_raised.png" height="87" width="165" class="img-responsive mx-3 my-3">Quick-Byg tilbud - carport med rejsning</h3>
                        </c:if>
                        <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.</p>
                        <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>Ved bestilling medfølger standardbyggevejledning.</p>
                        <p><strong>Udfyld nedenstående omhyggeligt og klik på "Bestil tilbud"</strong></p>
                    </div>


                    <form action="${pageContext.request.contextPath}/fc/orderNewCommand" method="post">
                        <div class="row align-items-start">
                            <div class="col-sm mt-4">
                                <h4 class="mb-4"><strong>Carport</strong></h4>

                                <label for="carportWidth">Carport bredde</label>
                                <select class="form-control" name="carportWidth" id="carportWidth">
                                    <c:forEach var="length" items="${applicationScope.carportLengths}">
                                        <option value="${length}">${length} cm</option>
                                    </c:forEach>

                                </select>


                                <label for="carportLength">Carport længde</label>
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
                                    <label for="roofSlope">Tag hældning</label>
                                    <select class="form-control" name="roofSlope" id="roofSlope">
                                        <option value="15">15°</option>
                                        <option value="20">20°</option>
                                        <option value="25">25°</option>
                                        <option value="30">30°</option>
                                        <option value="35">35°</option>
                                        <option value="40">40°</option>
                                        <option value="45">45°</option>
                                    </select>
                                </c:if>

                            </div>

                            <div class="col-sm mt-4">
                                <c:if test="${sessionScope.hasShed == 1}">
                                    <h4 class="mb-4"><strong>Skur</strong></h4>

                                    <label for="shedWidth">Skur bredde</label>
                                    <select class="form-control" name="shedWidth" id="shedWidth">
                                        <c:forEach var="length" items="${applicationScope.standardLengths}">
                                            <option value="${length}">${length} cm</option>
                                        </c:forEach>
                                    </select>

                                    <label for="shedLength">Skur længde</label>
                                    <select class="form-control" name="shedLength" id="shedLength">
                                        <c:forEach var="length" items="${applicationScope.standardLengths}">
                                            <option value="${length}">${length} cm</option>
                                        </c:forEach>
                                    </select>

                                    <label for="shedCladding">Skur beklædning</label>
                                    <select class="form-control" name="shedCladding" id="shedCladding">
                                        <c:forEach var="cladding" items="${applicationScope.claddings}">
                                            <option value="${cladding.materialId}">${cladding.name}</option>
                                        </c:forEach>
                                    </select>

                                    <label for="shedPlacement">Skur placering</label>
                                    <select class="form-control" name="shedPlacement" id="shedPlacement">
                                        <option value="SW">Syd Vest</option>
                                        <option value="W">Vest</option>
                                        <option value="NW">Nord Vest</option>
                                        <option value="N">Nord</option>
                                        <option value="NE">Nord Øst</option>
                                        <option value="E">Øst</option>
                                        <option value="SE">Syd Øst</option>
                                    </select>
                                    <label>Indgang til carport er sydlige side</label>
                                </c:if>

                            </div>
                        </div>

                        <div class="form-group mt-1 text-center mt-5">
                            <button type="submit" class="btn btn-lg btn-primary">Bestil tilbud</button>
                        </div>

                    </form>

                    <br><br>

                </div>
            </div>
        </div>

    </jsp:body>
</t:generic>