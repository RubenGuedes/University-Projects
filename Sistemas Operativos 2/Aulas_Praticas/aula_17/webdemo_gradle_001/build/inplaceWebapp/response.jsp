<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Hello Page</title>
    </head>
    
    <%
        String exemplo= "Um subtitulo";

        // outro codigo java qualquer...


        String sufixo= "ALGO QUE PODIA VIR DE UM SERVICO OU BD";

    %>
    
    <body>
    
        <h2>Hello, ${user}!</h2>

        <h3> <%=exemplo%> </h3>

        <p> <%=sufixo%> </p>       
        <p> <%=request.getAttribute("user") + " : " + request.getParameter("name")%> </p> 
         
    </body>
</html>
