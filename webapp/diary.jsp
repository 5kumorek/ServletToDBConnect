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
<jsp:useBean id="generations" type="java.util.ArrayList" scope="request" />
<jsp:useBean id="classes" type="java.util.ArrayList" scope="request" />
<div class="panel-group">
    <div class="panel panel-default">
        <div class="col-md-12">
            <h2>Spis roczników</h2>
                <c:forEach items="${generations}" var="generation" varStatus="loop">
                    <div style="float:left;">
                        <button type="button" class="btn btn-primary btn-info btn-lg" data-toggle="collapse" data-target="#klasy<c:out value="${generation.get(0)}"/>">
                             Rocznik ${generation.get(0)} <span class="glyphicon glyphicon-collapse-down"></span>
                        </button>
                        <div id="klasy<c:out value="${generation.get(0)}"/>" class="collapse">
                            <ul>
                                <c:forEach items="${classes.get(generation.get(0)-1)}" var="clas" varStatus="loop">
                                    <li><a style="cursor: pointer;" target="_blank" onclick="document.getElementById('query').value = 'select u.id_ucznia, u.numer, u.imie, u.nazwisko,' +
                                            'ROUND(sum(o.ocena*o.waga_oceny)/sum(waga_oceny),2) as srednia from oceny as o, uczniowie as u where o.id_ucznia=u.id_ucznia and '+
                                            'u.id_klasy=<c:out value="${clas.get(2)}"/> group by u.id_ucznia order by u.nazwisko;';
                                            document.getElementById('myForm').submit();">${clas.get(1)} ${clas.get(0)}</a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </c:forEach>
            </div>
        <form action="query" method="post" id="myForm">
            <input type="hidden" name="query" id="query" >
            <input type="hidden" name="page" value="class.jsp">
        </form>

        </div>
    </div>
</div>
</body>
</html>
