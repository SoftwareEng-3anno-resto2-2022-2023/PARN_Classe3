package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Sede;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;

@WebServlet(name = "AggiungiSede", value = "/AggiungiSede")
public class AggiungiSede extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        if(utente instanceof Azienda){
            Azienda azienda = (Azienda) utente;
            String citta = request.getParameter("cittaSede");
            String provincia = request.getParameter("provinciaSede");
            String cap = request.getParameter("capSede");
            String via = request.getParameter("viaSede");
            String regione = request.getParameter("regioneSede");
            String telefono = request.getParameter("telefonoSede");
            String mail = request.getParameter("emailSede");

            Sede sede = new Sede(azienda.getId(), regione, provincia, citta, cap, via, telefono, azienda, mail);
            azienda.getSedi().add(sede);

            System.out.println(service.registraSede(sede));
            System.out.println(service.aggiornaAzienda(azienda));

            session.setAttribute("utente", azienda);
            request.getRequestDispatcher("./WEB-INF/areaPersonaleAzienda.jsp").forward(request, response);
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
