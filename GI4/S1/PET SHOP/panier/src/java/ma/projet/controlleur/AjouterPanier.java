
package ma.projet.controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ma.projet.entity.Panier;
import ma.projet.entity.Produit;
import ma.projet.service.ServiceProduit;

/**
 *
 * @author Lachagr
 */
@WebServlet(name = "AjouterPanier", urlPatterns = {"/AjouterPanier"})
public class AjouterPanier extends HttpServlet {

    List<Panier> paniers = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ServiceProduit sp = new ServiceProduit();
        Produit p = sp.getById(id);
        HttpSession session = request.getSession();
        if (p != null) {
            int etat = 0;
            if (session.getAttribute("listpr") != null) {
                paniers = (List<Panier>) session.getAttribute("listpr");
                for (Panier p1 : paniers) {
                    if (p1.getProduit().getId() == id) {
                        p1.setQte(p1.getQte() + 1);
                        etat = 1;
                    }
                }
            }
            if (etat == 0) {
                paniers.add(new Panier(p, 1));
                session.setAttribute("listpr", paniers);
            }

        }

        response.sendRedirect("catalogue.jsp");

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @Override
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
