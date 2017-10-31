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
                <div class="page-header"><h1>Klasa</h1></div>
                <div class="container-fluid">
                    <c:if test="${not empty records}">
                        <table class="table table-inverse table table-bordered">
                            <thead class="thead-inverse">
                            <c:forEach items="${columns}" var="column" varStatus="loop_2">
                                <th>${column}</th>
                            </c:forEach>
                            <th>Oceny</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${records}" var="row"  varStatus="loop">
                                <tr>
                                    <c:forEach items="${row}" var="record" varStatus="loop_2">
                                        <td>${record}</td>
                                    </c:forEach>
                                    <td><a href="#" onclick="document.getElementById('query').value='select u.imie, u.nazwisko, '+
                                    'o.ocena, p.nazwa as przedmiot, w.za_co, o.waga_oceny as waga from '+
                                    'wagi_ocen as w, przedmioty as p, uczniowie as u inner join oceny as'+
                                    ' o on u.id_ucznia=o.id_ucznia where u.id_ucznia=<c:out value="${row.get(0)}"/> and o.id_przedmiotu=p.id_przedmiotu'+
                                    ' and o.waga_oceny=w.waga_oceny;';document.getElementById('myForm').submit();">sprawdź</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </c:catch>
        </c:catch>
        <form action="query" method="post" id="myForm">
        <input type="hidden" name="query" id="query" >
        <input type="hidden" name="page" value="student.jsp">
        </form>
    </div>
</div>
</body>
</html>
