<%!
int nombreVisiteursOnligne=0;
%>
<h4>Le nombre des visiteurs est : <%= (String) session.getAttribute("nbrVisiteurs") %></h4>
<h4>Le nombre des visiteurs Online est : <%= ++nombreVisiteursOnligne %></h4>
<hr>