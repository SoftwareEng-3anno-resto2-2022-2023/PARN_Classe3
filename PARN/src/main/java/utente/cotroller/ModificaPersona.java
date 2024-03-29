package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Persona;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ModificaPersona", value = "/ModificaPersona")
public class ModificaPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        if(utente != null){
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                persona.setNome(request.getParameter("nomePersona"));
                persona.setCognome(request.getParameter("cognome"));
                persona.setTelefono(request.getParameter("telefonoPersona"));
                persona.setCodiceFiscale(request.getParameter("codiceFiscale"));
                persona.setDataDiNascita(LocalDate.parse(request.getParameter("data_n")));
                persona.setRegione(request.getParameter("regionePersona"));
                persona.setProvincia(request.getParameter("provinciaPersona"));
                persona.setCitta(request.getParameter("cittaPersona"));
                persona.setVia(request.getParameter("viaPersona"));
                persona.setCap(request.getParameter("capPersona"));
                persona.setPosizioneDesiderata(request.getParameter("posizione"));
                persona.setFiltroMacroarea(request.getParameter("filtroMacroarea"));
                persona.setFoto(request.getParameter("fotoPersona"));
                persona.setMail(request.getParameter("mailPersona"));

                service.aggiornaPersona(persona);
                session.setAttribute("utente", persona);
                request.getRequestDispatcher("./WEB-INF/areaPersonalePersona.jsp").forward(request, response);
            } else response.sendRedirect(".");
        } else response.sendRedirect(".");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
