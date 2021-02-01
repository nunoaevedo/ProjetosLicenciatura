<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="/css/font-awesome.css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <style>

        body {
            background-color:#605D5D;
        }
        .login{                     
            position: absolute;
            width: auto;
            height: auto;
            font-size: 30px;
            
            /* Center form on page horizontally & vertically */
            top: 50%;
            left: 50%;
            margin-top: -150px;
            margin-left: -150px;
        }


    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entrar</title>
    </head>

    <body>
        <div class="login">



            <form action="LoginServlet" align="center">
                Utilizador:
                <br>
                <input type="text" name="util" id="util" placeholder="Utilizador" size="30">
                <br>
                <br>

                Senha:
                <br>
                <input type="password" name="senha" id="senha" placeholder="Senha" size="30">
                <br>
                <br>

                <input type="submit" value="Entrar">                     


            </form>
        </div>

        
    </body>
</html>
