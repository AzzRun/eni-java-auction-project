package fr.eni.ecole.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ecole.beans.Utilisateur;
import fr.eni.ecole.bll.BLLException;
import fr.eni.ecole.bll.UtilisateursManager;
import fr.eni.ecole.util.Constantes;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userCookie = getCookieValue( request, "userCookie" );
		if (userCookie != null) {
			request.setAttribute("login", userCookie);
		}
		this.getServletContext().getRequestDispatcher(Constantes.PAGE_LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recupLogin = null;
		String recupPassword = null;
		Boolean valide = true;
		Utilisateur utilisateur = null;
		String recupRemberMe = null;
		
		if(request.getParameter("login").trim().isEmpty())
			valide = false;
		
		if(request.getParameter("password").trim().isEmpty())
			valide = false;
		
		if (!valide) {
			request.setAttribute("erreur", "L'email et le mot de passe doivent être saisis");
			doGet(request, response);
		}else {
			recupLogin = request.getParameter("login").trim();
			recupPassword = request.getParameter("password".trim());
			
			UtilisateursManager managerUtilisateur = new UtilisateursManager();		
			try {
				utilisateur = managerUtilisateur.connexion(recupLogin, recupPassword);
			} catch (BLLException e) {
				e.printStackTrace();
			}
			if (utilisateur != null) {
				recupRemberMe = request.getParameter("rememberMe");
				if(recupRemberMe != null) {
					Cookie userCookie = new Cookie("userCookie", recupLogin);
					userCookie.setMaxAge(1103200);
					response.addCookie(userCookie);	
				}
				Cookie idUtilisateur = new Cookie("idUtilisateur", String.valueOf(utilisateur.getNoUtilisateur()));
				idUtilisateur.setMaxAge(7500);
				response.addCookie(idUtilisateur);
				request.getSession().setAttribute(Constantes.SESS_PSEUDO, utilisateur.getPseudo());
				//request.getSession().setAttribute(Constantes.SESS_NOM, utilisateur.getNom());
				//request.getSession().setAttribute(Constantes.SESS_PRENOM, utilisateur.getPrenom());
				request.getSession().setAttribute(Constantes.SESS_NUM_UTILISATEUR, utilisateur.getNoUtilisateur());
				response.sendRedirect(Constantes.URL_ACCUEIL);
			}else {
				request.setAttribute("erreur", "Login ou mot de passe incorrect!");
				request.getRequestDispatcher(Constantes.PAGE_LOGIN).forward(request, response);
			}
		}
	}
	
	/**
     * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
     * depuis la requête HTTP.
     */
    private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.equals( cookie.getName() ) ) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
