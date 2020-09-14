<%-- 
    Document   : index
    Created on : 2 mars 2016, 10:08:01
    Author     : Lachgar
--%>

<%@page import="ma.projet.entity.Panier"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="ma.projet.entity.Produit"%>
<%@page import="ma.projet.service.ServiceProduit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/css.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panier Simple</title>
    </head>
    <body>
        <fieldset>
            <legend>Liste des produits</legend>
            <form action="AjouterPanier" method="GET">
                <%!int i = 0;%>
                <%
                    HttpSession sessio = request.getSession();
                    List<Panier> paniers = (List<Panier>) sessio.getAttribute("listpr");
                    if (paniers != null) {
                        i = paniers.size();
                    }
                %>
                <div style="text-align: right;">
                    Pannier :<a href="panier.jsp"> <img src="ressource/images/images.png" style="width: 25px; height: 25px;" /> <%= i%></a>
                </div>
                <div>
                    <%
                        ServiceProduit p = new ServiceProduit();
                        for (Produit pp : p.getAll()) {
                    %>
                    <figure>
                        <img src=<%="ressource\\images\\" + pp.getPhoto()%>><br/>
                        <strong>Libelle:&nbsp; <%= pp.getLibelle()%></strong>&nbsp;&nbsp;<strong>Prix :&nbsp;<%= pp.getPrix()%>&nbspDH</strong><br>    

                        <button><a name="id" href="AjouterPanier?id=<%= pp.getId()%>" >Ajouter</a></button>
                    </figure>
                    <%}%>
                </div>

            </form>
        </fieldset>
    </body>
</html>
