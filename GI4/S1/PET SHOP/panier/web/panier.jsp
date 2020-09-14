<%-- 
    Document   : panier
    Created on : 2 mars 2016, 12:18:14
    Author     : Lachgar
--%>

<%@page import="ma.projet.entity.Panier"%>
<%@page import="java.util.List"%>
<%@page import="ma.projet.entity.Produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/css.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier Simple</title>
    </head>
    <body>
        <div style="text-align: center;">
            <h2 >Votre Pannier</h2>
            <img src="ressource/images/images.png" style="width: 40px; height: 40px;"/>
        </div>
        <table border="1">
            <thead>
                <tr>
                    <th>Quantité</th>
                    <th>Libelle</th>
                    <th>Prix</th>
                    <th>Photo</th>
                </tr>
            </thead>
            <tbody>
                <%! double total = 0;%>
                <%
                    HttpSession sessio = request.getSession();
                    List<Panier> paniers = (List<Panier>) sessio.getAttribute("listpr");
                    if (paniers != null) {
                        for (Panier p : paniers) {
                            total += p.getQte() * p.getProduit().getPrix();

                %>
                <tr>
                    <td><%= p.getQte()%></td>
                    <td><%= p.getProduit().getLibelle()%></td>
                    <td><%= p.getProduit().getPrix()%></td>
                    <td><img src=<%="ressource\\images\\" + p.getProduit().getPhoto()%>></td>
                </tr>
                <%    }
                    }%>
            </tbody>
        </table>
        <h2 style="text-align: center;">Total: <%=total%></h2>

    </body>
</html>
