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
    <div class="modal-body-row" >
        <div class="col-md-4">
            <div class="page-header"><h1>Stwórz zapytanie</h1></div>
            <form action="query" method="post">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="query">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-8">
            <c:catch var="records">
                <c:catch var="columns">
                    <div class="page-header"><h1>Wynik</h1></div>
                    <div class="container-fluid">
                    <c:if test="${not empty records}">
                        <table class="table table-inverse table table-bordered">
                            <thead class="thead-inverse">
                            <c:forEach items="${columns}" var="column" varStatus="loop_2">
                                <th>${column}</th>
                            </c:forEach>
                            </thead>
                            <tbody>
                            <c:forEach items="${records}" var="row"  varStatus="loop">
                                <tr>
                                    <c:forEach items="${row}" var="record" varStatus="loop_2">
                                        <c:choose>
                                            <c:when test="${loop_2.index=='0'}" ><th scope="row">${record}</th></c:when>
                                            <c:otherwise><td>${record}</td></c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    </div>
                </c:catch>
            </c:catch>
            <c:catch var="exception">
                <c:if test="${not empty exception}">
                <div class="alert alert-danger">
                    <strong>Danger!</strong> ${exception}
                </div>
                </c:if>
            </c:catch>
        </div>
    </div>
</body>
</html>
