package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matching.service.MatchingService;
import matching.service.MatchingServiceInterface;
import storage.entity.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AggiungiEsperienza", value = "/creaNuovaEsperienza")
public class AggiungiEsperienza extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza");
                String tipoAzienda = request.getParameter("tipoAzienda");
                String tipoImpiego = request.getParameter("tipoImpiego");
                String nomeDatore = request.getParameter("nomeDatore");
                String contattoAzienda = request.getParameter("contattoAzienda");

                List<String> mansioni = new ArrayList<>();
                for(String s:request.getParameter("mansioni").split(",")){
                    mansioni.add(s);
                }

                LocalDate ddi = null;
                if(request.getParameter("data_in_e") != null){
                    ddi = LocalDate.parse(request.getParameter("data_in_e"));
                }

                LocalDate ddf = null;
                if(request.getParameter("data_fin_e") != null && request.getParameter("data_fin_e").length()>1){
                    ddf = LocalDate.parse(request.getParameter("data_fin_e"));
                }

                if(nomeAziendaEsperienza != null && tipoAzienda != null && tipoImpiego != null
                        && nomeDatore != null && contattoAzienda != null && !mansioni.isEmpty()
                        && ddi != null) {
                    Curriculum curriculum = persona.getCurriculum();
                    EsperienzaLavorativa esperienzaLavorativa = new EsperienzaLavorativa(ddi, ddf, tipoAzienda,
                            nomeDatore, contattoAzienda, tipoImpiego, mansioni, nomeAziendaEsperienza, curriculum);
                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    if(!serviceInterface.aggiungiEsperienzaLavorativa(esperienzaLavorativa))
                        System.err.println("L'aggiunta dell'esperienza non è andata a buon fine");

                    MatchingServiceInterface serviceMat = new MatchingService();
                    List<Annuncio> list= serviceMat.personalizzaAnnunci(persona.getCurriculum());
                    session.setAttribute("myList",list);
                    session.setAttribute("utente", persona);
                    request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
