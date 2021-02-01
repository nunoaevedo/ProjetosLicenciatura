<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring"
           uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form"%>

<%@include file="header.jsp" %>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html;
              charset=UTF-8">
        <title>Faturas</title>
    <html:base/>
</head>
<body style="background-color: white">
    <div style="background-color:blueviolet;height:30px ; fontweight:bold;">
        <h2>Faturas</h2>
    </div>
    <br/>
    <table border="0" width="100%">
        <tbody>
            <tr style="background-color:blueviolet ; fontweight:bold;">       
                <td>Médico</td>
                <td>Especialidade</td>
                <td>Data</td>
                <td>Preço Base</td>
                <td>IVA</td>
                <td>precoTotal</td>
            </tr>
            <c:forEach items="${cons}" var="fatura">
                <tr>
                    
                    <td>${fatura.idCons.idMed.nome} ${fatura.idCons.idMed.apelido}</td>
                    <td>${fatura.idCons.idMed.idEsp.nome}</td>
                    <td>${fatura.idCons.data}</td>
                    <td>${fatura.precoBase}</td>
                    <td>${fatura.iva}</td>
                    <td>${fatura.precoTotal}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html:html>