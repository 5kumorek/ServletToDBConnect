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
            <form action="query" method="post" id="myForm">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Search" name="query" id="query">
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                    <input type="hidden" name="page" value="query.jsp">
                </div>
            </form>
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="panel-heading"><p class="h3" style="margin-top:0px;">Wypróbuj te zapytania</p></div>
                    <div class="btn-group" role="group">
                        <input type="hidden" name="tableName" value="select * from company;">
                        <button type="submit" style="font-size:medium;" class="btn btn-success btn-lg btn-block" data-toggle="collapse" data-target="#" onclick="document.getElementById('query').value = 'select * from marks_view;'/*'select o.ocena, o.waga_oceny, u.imie, u.nazwisko, p.nazwa as przedmiot from oceny as o inner join uczniowie as u on o.id_ucznia=u.id_ucznia inner join przedmioty as p on o.id_przedmiotu=p.id_przedmiotu order by o.ocena;'*/; document.getElementById('myForm').submit();">
                            select * from marks_view;
                        </button>
                        <button type="submit" style="font-size:medium;" class="btn btn-warning btn-lg btn-block" data-toggle="collapse" data-target="#" onclick="document.getElementById('query').value = 'select * from teachers_view;'/*'select p.imie,p.nazwisko, r.nazwa as przedmiot from nauczyciele n join pracownicy p on n.id_pracownika=p.id_pracownika join przedmioty r on r.id_przedmiotu = n.id_przedmiotu;'*/; document.getElementById('myForm').submit();">
                            select * from teachers_view;
                        </button>
                        <button type="submit" class="btn btn-default btn-lg btn-block" data-toggle="collapse" data-target="#" onclick="document.getElementById('query').value = 'select * from years_view;'/*'select r.id_rocznika as rok, count(*) as liczba_uczniow, r.liczba_godzin from uczniowie u join klasy k on k.id_klasy=u.id_klasy join roczniki r on r.id_rocznika=k.id_rocznika group by r.id_rocznika;'*/; document.getElementById('myForm').submit();">
                            select * from years_view;
                        </button>
                        <button type="submit" style="font-size:medium;" class="btn btn-info btn-lg btn-block" data-toggle="collapse" data-target="#" onclick="document.getElementById('query').value = 'select * from classes_view;'/*'select k.nazwa as klasa,k.profil,p.imie as imie_wychowawcy,p.nazwisko as nazwisko_wychowawcy from klasy k join nauczyciele n on k.id_nauczyciela=n.id_nauczyciela join pracownicy p on n.id_pracownika=n.id_pracownika order by klasa;'*/; document.getElementById('myForm').submit();">
                            select * from classes_view;
                        </button>
                    </div>
                    <div class="panel-heading"><p class="h3" style="margin-top:10px;">Bezpieczne dodawanie ocen</p></div>
                        <button type="submit" style="font-size:9px;" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" data-target="#" onclick="document.getElementById('query').value = 'insert into oceny (ocena, id_ucznia, id_przedmiotu, waga_oceny) values (4,(select id_ucznia from uczniowie where nazwisko=\'Zagiel\'),(select id_przedmiotu from przedmioty where nazwa=\'matematyka\'),1);'/*'select p.nazwa,count(*) as ilosc_klas,  prac.nazwisko from rocznik_przedmiot r join przedmioty p on p.id_przedmiotu=r.id_przedmiotu_pf join nauczyciele n on p.id_przedmiotu = n.id_przedmiotu join pracownicy prac on prac.id_pracownika=n.id_pracownika group by p.nazwa, prac.nazwisko having count(*) = 3;'*/; document.getElementById('myForm').submit();">
                            insert into oceny (ocena, id_ucznia, id_przedmiotu, waga_oceny)<br> values (4,(select id_ucznia from uczniowie where nazwisko='Zagiel'),<br>(select id_przedmiotu from przedmioty where nazwa='matematyka'),1);
                        </button>
                </div>
            </div>
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
