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
    <meta http-equiv="content-type" content="text/html; charset=windows-1254"/>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-9" />
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
            -webkit-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
            -moz-box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
            box-shadow: 0px 2px 3px 0px rgba(0,0,0,0.2);
        }
        .panel-login>.panel-heading {
            color: #00415d;
            background-color: #fff;
            border-color: #fff;
            text-align:center;
        }
        .panel-login>.panel-heading a{
            text-decoration: none;
            color: #666;
            font-weight: bold;
            font-size: 15px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }
        .panel-login>.panel-heading a.active{
            color: #029f5b;
            font-size: 18px;
        }
        .panel-login>.panel-heading hr{
            margin-top: 10px;
            margin-bottom: 0px;
            clear: both;
            border: 0;
            height: 1px;
            background-image: -webkit-linear-gradient(left,rgba(0, 0, 0, 0),rgba(0, 0, 0, 0.15),rgba(0, 0, 0, 0));
            background-image: -moz-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
            background-image: -ms-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
            background-image: -o-linear-gradient(left,rgba(0,0,0,0),rgba(0,0,0,0.15),rgba(0,0,0,0));
        }
        .panel-login input[type="text"],.panel-login input[type="email"],.panel-login input[type="password"] {
            height: 45px;
            border: 1px solid #ddd;
            font-size: 16px;
            -webkit-transition: all 0.1s linear;
            -moz-transition: all 0.1s linear;
            transition: all 0.1s linear;
        }
        .panel-login input:hover,
        .panel-login input:focus {
            outline:none;
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
            border-color: #ccc;
        }
        .btn-login {
            background-color: #59B2E0;
            outline: none;
            color: #fff;
            font-size: 14px;
            height: auto;
            font-weight: normal;
            padding: 14px 0;
            text-transform: uppercase;
            border-color: #59B2E6;
        }
        .btn-login:hover,
        .btn-login:focus {
            color: #fff;
            background-color: #53A3CD;
            border-color: #53A3CD;
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
            left: 80%;
        }


    </style>

</head>
<body>
<button type="button" class="button" onclick="goBack()">GERİ</button>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">

                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/RegisterServlet" method="post" role="form" style="display: block;">

                                <div style="color:red;
                                position: relative;
                                left: 30%;
                                "><%
                                    if (null != request.getAttribute("errorMessage16")) {

                                        out.println(request.getAttribute("errorMessage16"));
                                        out.print("<br>");
                                    }
                                %></div>

                                <div class="form-group">
                                    <input name="tc" type="text" class="form-control" id="tc"
                                           value="" tabindex="1"
                                           autocomplete="off" maxlength="11" pattern="[0-9]{11}"
                                           title="Kimlik numaranız 11 adet rakamdan oluşmalıdır"
                                           aria-required="true" placeholder="Tc" required/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="ad" id="ad" tabindex="2" autocomplete="off" class="form-control" placeholder="Ad Soyad"  aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="3" autocomplete="off" class="form-control" placeholder="Email"  aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="sifre" id="sifre" tabindex="4" autocomplete="off" class="form-control" placeholder="Sifre"  aria-required="true" required/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="okul" id="okul" tabindex="5" autocomplete="off" class="form-control" placeholder="Okulu"  aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="bolumu" id="bolumu" tabindex="6" autocomplete="off" class="form-control" placeholder="Bölümü"  aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="sinifi" id="sinifi" tabindex="7" autocomplete="off" class="form-control" pattern="[0-9]" placeholder="Sınıfı" aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="dtarihi" id="dtarihi" tabindex="8" autocomplete="off" class="form-control"  placeholder="Doğum Tarihi" aria-required="true" required/>
                                </div>
                                <div class="form-group">
                                    <%--@declare id="kadın"--%><input type="radio" name="cinsiyet" value="kadın" aria-required="true"  required/><label>kadın</label>
                                    <input type="radio" name="cinsiyet" value="erkek"><label > erkek</label>

                                </div>
                                <div class="form-group">
                                    <input type="text" name="ort" id="ort" tabindex="10" autocomplete="off" pattern="[0-9]" class="form-control" placeholder="Ortalama"  aria-required="true"  required/>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="11" class="form-control btn btn-login" value="Register">
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="container signin">
                                    <p>Already have an account? <a href="login.jsp">Sign in</a>.</p>
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function goBack() {
        window.history.back();
    }
</script>


</body>
</html>
