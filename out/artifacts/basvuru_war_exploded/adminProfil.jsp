<%@ page import="database.Db" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: murat
  Date: 27.07.2018
  Time: 11:44
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

        .btn-ilanlar {
            background-color: #1CB94E;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #1CB94A;
        }

        .btn-ilanlar:hover,
        .btn-ilanlar:focus {
            color: #fff;
            background-color: #1CA347;
            border-color: #1CA347;
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
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-4">
                            <a href="#" id="login-form-link">Direktörlükler</a>
                        </div>
                        <div class="col-xs-4">
                            <a href="#" id="register-form-link">Adminler</a>
                        </div>
                        <div class="col-xs-4">
                            <a href="#" class="active" id="ilanlar-form-link">İlanlar</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/DirektorlukServlet" method="post" role="form"
                                  style="display: none;">
                                <div class="form-group">
                                    <%
                                        Db db = new Db();
                                        for (int i = 0; i < db.direktorlukleriCek().size(); i++) {
                                            out.print("<div class=\"row\">" +
                                                    "<div class=\"col-xs-6\" style =\" margin-top: 7px \">" + db.direktorlukleriCek().get(i).getDirekad() + "</div>" +
                                                    "<div class=\"col-xs-2\"><a href=\"direktorlukDuzenle.jsp?id=" + db.direktorlukleriCek().get(i).getDirekid() + "\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-pencil\" > </i></button></a></div>" +
                                                    "<div class=\"col-xs-2\"><a href=\"dirSilServlet?id=" + db.direktorlukleriCek().get(i).getDirekid() + "\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-trash\" > </i></button></a></div>" +
                                                    "</div>");
                                        }
                                        out.print("<br><div class=\"col-sm-6 col-sm-offset-3\">" +
                                                "    <a href=\"direktorlukEkle.jsp\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\">Direktörlük Ekle</button>" +
                                                "    </a>" +
                                                "</div>");
                                    %>
                                </div>
                            </form>
                            <form id="register-form" action="/WelcomeServlet" method="post" role="form"
                                  style="display: none;">
                                <div class="form-group">
                                    <%
                                        for (int i = 0; i < db.adminCek().size(); i++) {
                                            out.print("<div class=\"row\">" +
                                                    "<div class=\"col-xs-6\" style =\" margin-top: 7px \">" + db.adminCek().get(i).getAd() + "</div>" +
                                                    "<div class=\"col-xs-2\"><a href=\"adminDuzenle.jsp?id=" + db.adminCek().get(i).getIdadmin() + "\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-pencil\" > </i></button></a></div>" +
                                                    "<div class=\"col-xs-2\"><a href=\"adminSilServlet?id=" + db.adminCek().get(i).getIdadmin() + "\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\"><i class =\"glyphicon glyphicon-trash\" > </i></button></a></div>" +
                                                    "</div>");
                                        }
                                        out.print("<br><div class=\"col-sm-6 col-sm-offset-3\">" +
                                                "    <a href=\"adminEkle.jsp\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\">Admin Ekle</button></a>" +
                                                "</div>");

                                    %>
                                </div>
                            </form>
                            <form id="ilanlar-form" action="/ilanlarServlet" method="post" role="form"
                                  style="display: block;">
                                <div class="form-group">

                                    <%

                                        for (int i = 0; i < db.direktorlukleriCek().size(); i++) {
                                            try {

                                                out.print("<div class=\"row\">" +
                                                        "<div class=\"col-xs-7\" style =\" margin-top: 7px \">" + db.direktorlukleriCek().get(i).getDirekad() + "</div>" +
                                                        "<div class=\"col-xs-3\"><a href=\"adminilan.jsp?id=" + db.direktorlukleriCek().get(i).getDirekid() + "\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\">" + "İlanlar" + "</button></a></div>" +
                                                        "</div>"
                                                );
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        out.print("<br><div  class=\"col-sm-6 col-sm-offset-3\" >" +
                                                "    <a href=\"ilanEkle.jsp\"><button style=\"color:white\" type=\"button\" class=\"form-control btn btn-ilanlar\">İlan Ekle</button>" +
                                                "    </a>" +
                                                "</div>");
                                    %>
                                </div>
                                <div style="color:red;
                                position: relative;
                                left: 34%;
                                "><%
                                    if (null != request.getAttribute("errorMessage5")) {
                                        out.print("<br><br><br>");

                                        out.println(request.getAttribute("errorMessage5"));

                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 42%;
                                "><%
                                    if (null != request.getAttribute("errorMessage6")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage6"));

                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 42%;
                                "><%
                                    if (null != request.getAttribute("errorMessage7")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage7"));

                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 36%;
                                "><%
                                    if (null != request.getAttribute("errorMessage8")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage8"));

                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 39%;
                                "><%
                                    if (null != request.getAttribute("errorMessage9")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage9"));
                                        }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 40%;
                                "><%
                                    if (null != request.getAttribute("errorMessage10")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage10"));
                                        }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 41%;
                                "><%
                                    if (null != request.getAttribute("errorMessage11")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage11"));
                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 43%;
                                "><%
                                    if (null != request.getAttribute("errorMessage12")) {
                                        out.print("<br><br><br>");
                                        out.println(request.getAttribute("errorMessage12"));
                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 44%;
                                "><%
                                if (null != request.getAttribute("errorMessage13")) {
                                    out.print("<br><br><br>");

                                    out.println(request.getAttribute("errorMessage13"));
                                }
                            %></div>

                                <div style="color:red;
                                position: relative;
                                left: 37%;
                                "><%
                                    if (null != request.getAttribute("errorMessage14")) {
                                        out.print("<br><br><br>");

                                        out.println(request.getAttribute("errorMessage14"));
                                    }
                                %></div>

                                <div style="color:red;
                                position: relative;
                                left: 37%;
                                "><%
                                    if (null != request.getAttribute("errorMessage15")) {
                                        out.print("<br><br><br>");

                                        out.println(request.getAttribute("errorMessage15"));
                                    }
                                %></div>
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

        $('#ilanlar-form-link').click(function (e) {
            $("#ilanlar-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');

            e.preventDefault();
        });
        $('#login-form-link').click(function (e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $("#ilanlar-form").fadeOut(100);
            $('#ilanlar-form-link').removeClass('active');
            $(this).addClass('active');

            e.preventDefault();
        });
        $('#register-form-link').click(function (e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $("#ilanlar-form").fadeOut(100);
            $('#ilanlar-form-link').removeClass('active');
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
