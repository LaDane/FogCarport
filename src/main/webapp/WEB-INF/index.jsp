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
        <div class="container container-fluid my-5" style="background-color: #F8F8F8">
            <div class="row">
                <div class="col-md-3 my-3">
                    <table class="table">
                        <tr>
                            <td>
                                Standard Carporte
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col my-3">
                    <div>
                        <h3>Quick-Byg tilbud</h3>
                        <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en
                            skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke
                            ønsker.</p>
                        <p>Tilbud og skitsetegning fremsendes med post hurtigst muligt.<br>Ved bestilling medfølger
                            standardbyggevejledning.</p>
                        <p><strong>Rekvirér tilbud - start med at vælge type:</strong></p>
                    </div>

                    <div class="row mt-5">

                        <form action="${pageContext.request.contextPath}/fc/designCarportCheck"  method="post">
                            <input type="hidden" name="newInquiryStarted" value="1">

                            <div class="col form-check-inline px-2">
                                <input class="form-check-input" type="radio" name="hasFlatRoof" value="1" id="roofTypeFlat" checked>
                                <label class="form-check-label" for="roofTypeFlat">
                                    Carport med fladt tag<br>
                                    <img src="${pageContext.request.contextPath}/img/roof_flat.png">
                                </label>

                                <div class="col form-check-inline px-2">
                                    <input class="form-check-input" type="radio" name="hasFlatRoof" value="0" id="roofTypeRaised">
                                    <label class="form-check-label" for="roofTypeRaised">
                                        Carport med rejsning<br>
                                        <img src="${pageContext.request.contextPath}/img/roof_raised.png">
                                    </label>
                                </div>
                            </div>

                            <div class="col form-check-inline px-2">
                                <div class="shed-option">
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="hasShed" value="1" id="withShed" checked>
                                        <label class="form-check-label" for="withShed">
                                            Med skur
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="hasShed" value="0" id="noShed">
                                        <label class="form-check-label" for="noShed">
                                            Uden skur
                                        </label>

                                    </div>
                                    <div class="form-group my-3">
                                        <div class="submitknapindex align-top">
                                            <button type="submit" class="btn btn-lg btn-primary float-end">Design Carport
                                            </button>
                                            <br>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <br>
                        </form>
                        <br>
                        <br>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:generic>