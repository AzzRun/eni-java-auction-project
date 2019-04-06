package fr.eni.ecole.servlet;

import java.io.IOException;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.beans.Utilisateur;
import fr.eni.ecole.bll.CredentialManager;
import fr.eni.ecole.bll.EncheresManager;
import fr.eni.ecole.bll.UtilisateursManager;
import fr.eni.ecole.rest.mo.getDetailEnchere;
import fr.eni.ecole.util.Constantes;

/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int recupNoArticle;
		EncheresManager managerEnchere = null;
		getDetailEnchere enchere = null;
		UtilisateursManager managerUtilisiateur = null;
		Utilisateur vendeur = null;
		String pseudoVendeur = null;
		
		recupNoArticle = Integer.parseInt(request.getParameter("noArticle"));
		managerEnchere = new EncheresManager();
		enchere = managerEnchere.getEnchere(recupNoArticle);
		request.setAttribute("nomArticle", enchere.getNomArticle());
		request.setAttribute("description", enchere.getDescriptionArticle());
		request.setAttribute("categorie", enchere.getNomCategorie());
		request.setAttribute("pseudoAcheteur", enchere.getPseudoUtilisateur());
		request.setAttribute("codePostalRetrait", enchere.getCodePostalRetrait());
		request.setAttribute("rueRetrait", enchere.getRueRetrait());
		request.setAttribute("villeRetrait", enchere.getVilleRetrait());
		request.setAttribute("dateFinEnchere", enchere.getDateFinEnchere());
		request.setAttribute("prixInitial", enchere.getPrixInitial());
		request.setAttribute("montantEnchere", enchere.getMontantEnchere());
		request.setAttribute("noArticle", enchere.getNoArticle());
		request.setAttribute("noAcheteur", enchere.getNoAchateur());
		request.setAttribute("noVendeur", enchere.getNoVendeur());
		managerUtilisiateur = new UtilisateursManager();
		vendeur = managerUtilisiateur.getUtilisateur(enchere.getNoVendeur());
		pseudoVendeur = vendeur.getPseudo();
		request.setAttribute("pseudoVendeur", pseudoVendeur);
		request.getRequestDispatcher(Constantes.PAGE_DETAIL_VENTE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
