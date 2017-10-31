<%--
  Created by IntelliJ IDEA.
  User: radoslaw
  Date: 19.09.17
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp" />
<html>
<head>
    <title>Baza danych</title>
    <link rel="stylesheet" type="text/css" href="records.css">
</head>
<body>
<div class="col-sm-6 col-sm-offset-3">
    <div class="panel panel-default">
        <c:catch var="records">
            <c:catch var="columns">
                <c:if test="${not empty records}">
                    <div class="page-header"><h1><c:out value="${records.get(0).get(0)}"/> <c:out value="${records.get(0).get(1)}"/></h1></div>
                        <div class="container-fluid">
                            <table class="table table-inverse table table-bordered">
                                <thead class="thead-inverse">
                                <c:forEach items="${columns}" var="column" varStatus="loop_2">
                                    <c:choose>
                                        <c:when test="${loop_2.index!='0' and loop_2.index!='1'}" ><th>${column}</th></c:when>
                                    </c:choose>
                                </c:forEach>
                                </thead>
                                <tbody>
                                <c:forEach items="${records}" var="row"  varStatus="loop">
                                    <tr>
                                        <c:forEach items="${row}" var="record" varStatus="loop_2">
                                            <c:choose>
                                                <c:when test="${loop_2.index!='0' and loop_2.index!='1'}" ><td>${record}</td></c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:if>
            </c:catch>
        </c:catch>
    </div>
</div>
</body>
</html>
