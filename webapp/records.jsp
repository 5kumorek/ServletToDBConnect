<%@ page import="java.util.ArrayList" %><%--
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
    <title>Table description</title>
    <link rel="stylesheet" type="text/css" href="records.css">
</head>
<body>
<jsp:useBean id="tableName" type="java.lang.String" scope="request" />
<jsp:useBean id="records" type="java.util.ArrayList" scope="request" />
<jsp:useBean id="columns" type="java.util.ArrayList" scope="request" />
<jsp:useBean id="types" type="java.util.ArrayList" scope="request" />

<div class="page-header"><h1>Tabela: <c:out value="${tableName}"/></h1></div>
<div class="modal-body-row" >
    <div class="col-md-5">
        <div class="container-fluid">
            <table class="table table-inverse table table-bordered">
                <thead class="thead-inverse">
                <c:forEach items="${columns}" var="column" varStatus="loop_2">
                        <th>${column}</th>
                </c:forEach>
                <th>Usuń</th>
                </thead>
                <tbody>
                <c:forEach items="${records}" var="row"  varStatus="loop">
                        <tr >
                            <c:forEach items="${row}" var="record" varStatus="loop_2">
                                <c:choose>
                                    <c:when test="${loop_2.index=='0'}" ><th scope="row">${record}</th></c:when>
                                    <c:otherwise><td>${record}</td></c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <td><form method="post">
                                <input type="hidden" name="tableName">
                                <button type="submit" class="btn btn-danger btn-sm" data-toggle="collapse">
                                    <span class="glyphicon glyphicon-minus"></span>
                                </button>
                            </form></td>
                        </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
<!--    <div class="col-md-1">
        <div class="container-fluid">
            <table class="table table-borderless" style="border: none;">
                <thead class="thead-inverse">
                    <th>c</th>
                </thead>
                <tbody>
                <tr><td style="border: none;"><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></td></tr>
                <tr><td><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></td></tr>
                <tr><td><span class="glyphicon glyphicon-minus" aria-hidden="true"></span></td></tr>
                </tbody>
            </table>
        </div>
    </div>-->
<div class="col-md-6">
    <div class="container-fluid">
        <div class="panel panel-success">
                <h2>&nbsp;&nbsp;Add next record</h2>
                <form class="form-horizontal" action="insert" method="post" >
                    <table class="table">
                    <c:forEach items="${columns}" var="column" varStatus="loop">
                        <tr>
                            <td><label class="control-label col-sm-3">${column}</label></td>
                            <td><input type="text" name="<c:out value="${loop.index}"/>"/></td>
                            <td><label style="text-align: right;">${types.get(loop.index)}</label></td>
                        </tr>
                    </c:forEach>
                    </table>
                    <input type="hidden" name="how_many" value="<c:out value="${columns.size()}"/>">
                    <input type="hidden" name="tableName" value=<c:out value="${tableName}"/>>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <input type="submit" class="btn btn-primary" value="Submit" />
                        </div>
                    </div>
                    <c:catch var="exception">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <label style="text-align: right;">${exception}</label>
                        </div>
                    </div>
                    </c:catch>
                </form>
        </div>
    </div>
</div>
</div>
</body>
</html>