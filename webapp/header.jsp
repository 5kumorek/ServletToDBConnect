<!--OUR BLUEPRINT!-->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Baza danych</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link href='https://fonts.googleapis.com/css?family=Satisfy' rel='stylesheet' type='text/css'>

</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">

        <!--Header-->
        <div class="navbar-header">
            <!--this button appears when device doesn't have space to display our items-->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#topNavBar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <!--when clicked, it redirects to the homepage-->
            <a class="navbar-left" href="#">
                <img src="" height="47" class="embed-responsive-item"/>
            </a>
            <a class="navbar-brand" href="index.jsp">Baza danych</a>
        </div>

        <!--Navigation items go here. For example: Recipes, User Profiles, etc.-->
        <div class="collapse navbar-collapse" id="topNavBar">
            <ul class="nav navbar-nav">
                <li class="">
                    <!--Todo-->
                    <a href="start">
                        <span class="glyphicon glyphicon-list" aria-hidden="true"></span>&nbsp;Tabele
                    </a>
                </li>
                <li class="">
                    <a href="class">
                        <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;Dziennik
                    </a>
                </li>
                <li class="">
                    <a href="query.jsp">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp;Zapytanie
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right" >
                <li class="">
                    <a href="#">
                        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>&nbsp;Log In
                    </a>
                </li>
            </ul>

        </div>

    </div>
</nav>

</body>
</html>