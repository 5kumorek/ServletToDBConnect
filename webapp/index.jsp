<%--
  Created by IntelliJ IDEA.
  User: radoslaw
  Date: 19.09.17
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<html>
  <head>
    <title>Baza danych</title>
      <link rel="stylesheet" type="text/css" href="records.css">
  </head>
  <body>
      <div class="page-header"><h1>Zarządzanie Bazą Danych pt. Szkoła</h1></div>
      <div class="modal-body-row" >
          <div class="col-md-5" style="margin: 30px; margin-top:10px;font-size: 16px;">
            <p>Servlet został zrealizowany w ramach projektu na przedmiot Bazy Danych. Baza danych zawiera: </p>
              <ul>
                  <li>dziewięć tabel związanych relacjami</li>
                  <li>klucze obce</li>
                  <li>różne widoki zawierające klauzury group i having, generowane przez program w zależności od potrzeby</li>
                  <li>funkcje oraz wyzwalacze</li>
                  <li>kontrole spójności danych</li>
              </ul>
              <br>
              <p>Ponadto program pozwalana na realizacje najróżniejszych działań na niej: </p>
              <ul>
                  <li>przeglądanie tabel (zakładka tabele)</li>
                  <li>dodawanie rekordów poprzez formularz (w zakładce tabele, należy kliknąć zobacz tabele przy tabeli do której chcemy dodać record)</li>
                  <li>wprowadzanie danych poprzez raporty(jeszcze nie gotowe)</li>
                  <li>wykonywanie zapytań(zakładka zapytania)</li>
                  <li>zaprezentowanie użycia funkcji agregujących(zakładka zapytania)</li>
              </ul>
          </div>
          <div class="col-md-6">
              <img src="szkola.jpg" height="420" width="600"/>
          </div>
      </div>
  </body>
</html>
