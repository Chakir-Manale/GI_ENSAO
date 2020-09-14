
package ma.projet.controlleur;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.projet.entity.Produit;
import ma.projet.service.ServiceProduit;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author lachgar
 */
@WebServlet(name = "AjouterProduit", urlPatterns = {"/AjouterProduit"})
public class AjouterProduit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String libelle;
    double prix;
    String path;
    private String UPLOAD_DIRECTORY;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
//        String libelle = request.getParameter("libelle");
//        double prix = Double.parseDouble(request.getParameter("prix"));
//        String url=request.getParameter("img");
//        ServiceProduit p=new ServiceProduit();
//        Produit pp=new Produit(libelle, prix, url);
//        p.create(pp);
//        response.sendRedirect("index.jsp");

        UPLOAD_DIRECTORY = getServletContext().getRealPath("/").replace("build\\", "") + "ressource" + File.separator + "images";
        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);
                String name = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        name = new File(item.getName()).getName();
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        path = UPLOAD_DIRECTORY + File.separator + name;
                    } else {
                        InputStream input = item.getInputStream();
                        if (item.getFieldName().equals("libelle")) {
                            byte[] str = new byte[input.available()];
                            input.read(str);
                            libelle = new String(str, "UTF8");
                        }
                        if (item.getFieldName().equals("prix")) {
                            byte[] str = new byte[input.available()];
                            input.read(str);
                            prix = Double.parseDouble(new String(str, "UTF8"));
                        }
                    }
                }
                //File uploaded successfully

                ServiceProduit produit = new ServiceProduit();
                produit.create(new Produit(libelle, prix, name));

                request.setAttribute("message", "File Uploaded Successfully le nom est :" + libelle + " le prenom est :" + prix);
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
           response.sendRedirect("catalogue.jsp");

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
