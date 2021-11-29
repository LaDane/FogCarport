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

        <h3 class="mt-5" style="color: #1B4386">Ordre bekræftelse</h3>
        <div class="container container-fluid my-5" style="background-color: #F8F8F8">
            <div class="row">
                <div class="col-md-3 my-3">
                    <table class="table">
                        <tr>
                            <td>
                                Ordre Oversigt (LINK)
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="col my-3">
                    <div>
                        <h3>Din ordre er bekræftet!</h3>
                        <p>Vi sender en bekræftelse på **MAIL**<br>
                            Du kan forvente at høre tilbage fra os indenfor 24 timer.</p>
                        <p>Ordre ID: <strong>{order_ID}</strong></p>
                    </div>

                </div>
            </div>
        </div>

    </jsp:body>
</t:generic>