<%@ page import="database.Db" %>
<%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 30.07.2018
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        if (null == session.getAttribute("admin_username")) {
            session.invalidate();
            request.setAttribute("errorMessage1", "Sisteme giriş yapmış olmanız gerekmektedir!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
            rd.forward(request, response);
        }
    %>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <title>WELCOME</title>
    <style>
        body {
            padding-top: 90px;
        }

        .panel-login {
            border-color: #ccc;
            -webkit-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
            box-shadow: 0px 2px 3px 0px rgba(0, 0, 0, 0.2);
        }

        .panel-login > .panel-heading {
            color: #00415d;
            background-color: #fff;
            border-color: #fff;
            text-align: center;
        }

        .panel-login > .panel-heading a {
            text-decoration: none;
            color: #666;
            font-weight: bold;
            font-size: 15px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }

        .panel-login > .panel-heading a.active {
            color: #029f5b;
            font-size: 18px;
        }

        .panel-login > .panel-heading hr {
            margin-top: 10px;
            margin-bottom: 0px;
            clear: both;
            border: 0;
            height: 1px;
            background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -ms-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
            background-image: -o-linear-gradient(left, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.15), rgba(0, 0, 0, 0));
        }

        .panel-login input[type="text"], .panel-login input[type="email"], .panel-login input[type="password"] {
            height: 45px;
            border: 1px solid #ddd;
            font-size: 16px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }

        .panel-login input:hover,
        .panel-login input:focus {
            outline: none;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            border-color: #ccc;
        }

        .button {
            color: black;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            position: relative;
            left: 70%;
        }


        .button1 {
            color: black;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            position: relative;
            left: 10%;
        }


    </style>

</head>
<body>
<a href="adminProfil.jsp">
    <button type="button" class="button1">Ana sayfa</button>
</a>
<button type="button" class="button" onclick="goBack()">GERİ</button>
<a href="adminLogin.jsp">
    <button type="button" class="button">ÇIKIŞ</button>
</a>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">

                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">

                            <br>
                            <form id="login-form" action="/RegisterServlet" method="post" role="form"
                                  style="display: block;">

                                <%
                                    int dirid = Integer.parseInt(request.getParameter("id"));


                                    Db db = new Db();
                                    for (int i = 0; i < db.ilanlariCek(dirid).size(); i++) {
                                        out.print("<div class=\"row\">" +
                                                "<div class=\"col-xs-3\" style =\" margin-top: 7px \" >" + db.ilanlariCek(dirid).get(i).getIlanadi() + "</div>" +
                                                "<div class=\"col-xs-2\"><a href=\"ilanDuzenle.jsp?id=" + db.ilanlariCek(dirid).get(i).getIlanid() + "\"><button style=\" background-color: #1CB94E\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-pencil\" > </i></button></a></div>" +
                                                "<div class=\"col-xs-2\"><a href=\"ilanSilServlet?id=" + db.ilanlariCek(dirid).get(i).getIlanid() + "\"><button style=\" background-color: #1CB94E\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-trash\" > </i></button></a></div>" +
                                                "<div class=\"col-xs-4\"><a href=\"basvuranlariGor.jsp?id=" + db.ilanlariCek(dirid).get(i).getIlanid() + "\"><button style=\" background-color: #1CB94E\" type=\"button\" class=\"form-control btn btn-ilanlar\">Görüntüle</button></a></div>" +
                                                "</div>");
                                    }

                                %>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {

        $('#login-form-link').click(function (e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });


    });
</script>
<script>
    function goBack() {
        window.history.back();
    }
</script>


</body>
</html>


