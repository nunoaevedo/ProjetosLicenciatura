<%-- 
    Document   : header
    Created on : 17/jul/2020, 13:40:25
    Author     : Nuno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        header {
            text-align: justify;
            padding: 30px;
            background-color: #605D5D;
            color: white;
        }    
    </style>
    
    <body>
        <header>
            
            <div align="center">
                
                <h1>Clínica IPVC</h1>
            </div>
            <br>
            <div>
                <div style="float: left">

                    <a href="listarConsultas.htm"><button> Próximas Consultas</button></a> 
                    <a href="listarConsultasRealizadas.htm"><button>Consultas a Pagar</button></a>
                    <a href="consultasNaoRealizadas.htm"><button>Consultas não Realizadas</button></a>
                    <a href="faturas.htm"><button>Faturas</button></a>

                </div>

                <div style="float: right">

                    <a href="index.htm"><button>Sair</button></a>

                </div>     
            </div>

        </header>
    </body>
</html>
