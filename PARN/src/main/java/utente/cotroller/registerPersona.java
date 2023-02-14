package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.*;
import utente.service.UtenteService;
import utils.PasswordEncrypter;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "registerPersona", value = "/registerPersona")
public class registerPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        //Prendo tutti i parametri standard della persona
        String nome = request.getParameter("nomePersona");
        String cognome = request.getParameter("cognome");
        String telefonoPersona = request.getParameter("telefonoPersona");
        String cf = request.getParameter("coficeFiscale");
        LocalDateTime data_n = LocalDateTime.parse(request.getParameter("data_n"), formatter);
        String regionePersona = request.getParameter("regionePersona");
        String provinciaPersona = request.getParameter("provinciaPersona");
        String citta = request.getParameter("cittaPersona");
        String via = request.getParameter("viaPersona");
        String cap = request.getParameter("capPersona");
        LocalDateTime data_nascita = LocalDateTime.parse(request.getParameter("data_nascita"), formatter);
        String posizioneDesiderata = request.getParameter("posizione");
        String filtroMacroarea = request.getParameter("filtroMacroarea");
        String fotoPersona = request.getParameter("fotoPersona");
        String emailPersona = request.getParameter("mailPersona");
        String passwordPersona = PasswordEncrypter.encryptThisString(request.getParameter("password_Persona"));

        //Prendo tutti i campi di esperienza lavorativa obbligatoria
        String nomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza1");
        String tipoAzienda = request.getParameter("tipoAzienda1");
        String nomeDatore = request.getParameter("nomeDatore1");
        String contattoAzienda = request.getParameter("contattoAzienda1");
        List<String> mansioni = new ArrayList<>();
        for(String s:request.getParameter("mansioni1").split(","))
            mansioni.add(s);
        LocalDateTime data_in_e = LocalDateTime.parse(request.getParameter("data_in_e1"), formatter);
        LocalDateTime data_fin_e = LocalDateTime.parse(request.getParameter("data_fin_e1"), formatter);
        List<EsperienzaLavorativa> esperienzeLavorative = new ArrayList<>();
        EsperienzaLavorativa espLav = new EsperienzaLavorativa(data_in_e, data_fin_e, tipoAzienda, nomeDatore, contattoAzienda, tipoAzienda, mansioni, nomeAziendaEsperienza, null);
        esperienzeLavorative.add(espLav);

        //Ciclo sulle esperienze successive se ce ne sono
        boolean flag = true;
        int counter = 1;
        while(flag){
            counter++;
            if(request.getParameter("nomeAziendaEsperienza"+counter)!=null){
                String tmpNomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza"+counter);
                String tmpTipoAzienda = request.getParameter("tipoAzienda"+counter);
                String tmpNomeDatore = request.getParameter("nomeDatore"+counter);
                String tmpContattoAzienda = request.getParameter("contattoAzienda"+counter);
                List<String> tmpMansioni = new ArrayList<>();
                for(String s:request.getParameter("mansioni"+counter).split(","))
                    tmpMansioni.add(s);
                LocalDateTime tmpData_in_e = LocalDateTime.parse(request.getParameter("data_in_e"+counter), formatter);
                LocalDateTime tmpData_fin_e = LocalDateTime.parse(request.getParameter("data_fin_e"+counter), formatter);
                EsperienzaLavorativa tmpEspLav = new EsperienzaLavorativa(tmpData_in_e, tmpData_fin_e, tmpTipoAzienda, tmpNomeDatore, tmpContattoAzienda, tmpTipoAzienda, tmpMansioni, tmpNomeAziendaEsperienza, null);
                esperienzeLavorative.add(tmpEspLav);
            }else flag = false;
        }

        //Prendo tutti i campi di linuga obbligatoria
        String nomeLingua = request.getParameter("nomeLingua1");
        String livelloLingua = request.getParameter("livelloLingua1");
        Lingua lingua = new Lingua(nomeLingua, livelloLingua);
        List<Lingua> lingue = new ArrayList<>();
        lingue.add(lingua);

        //Prendo tutti i campi di lingua aggiuntivi se ci sono e li aggiungo alla lista
        flag = true;
        counter = 1;
        while(flag){
            counter++;
            if(request.getParameter("nomeLingua"+counter)!=null){
                String tmpNomeLingua = request.getParameter("nomeLingua"+counter);
                String tmpLivellolingua = request.getParameter("livelloLingua"+counter);
                Lingua tmpLingua = new Lingua(tmpNomeLingua, tmpLivellolingua);
                lingue.add(tmpLingua);
            }else flag = false;
        }

        //Prendo tutti i campi di istruzione obbligatoria
        String nomeIstituto = request.getParameter("nomeIstituto1");
        String tipoIstruzione = request.getParameter("tipoIstruzione1");
        String nomeQualifica = request.getParameter("nomeQualifica1");
        LocalDateTime data_in_i = LocalDateTime.parse(request.getParameter("data_in_i1"), formatter);
        LocalDateTime data_fin_i = LocalDateTime.parse(request.getParameter("data_fin_i1"), formatter);
        Istruzione istruzione = new Istruzione(null, data_in_i, data_fin_i, nomeQualifica, tipoIstruzione, nomeIstituto);
        List<Istruzione> istruzioni = new ArrayList<>();
        istruzioni.add(istruzione);

        //Prendo tutti i campi di istruzione aggiuntivi se ci sono e li aggiungo alla lista
        flag = true;
        counter = 1;
        while (flag){
            counter++;
            if(request.getParameter("nomeIstituto"+counter)!=null){
                String tmpNomeIstituto = request.getParameter("nomeIstituto"+counter);
                String tmpTipoIstruzione = request.getParameter("tipoIstruzione"+counter);
                String tmpNomeQualifica = request.getParameter("nomeQualifica"+counter);
                LocalDateTime tmpData_in_i = LocalDateTime.parse(request.getParameter("data_in_i"+counter), formatter);
                LocalDateTime tmpData_fin_i = LocalDateTime.parse(request.getParameter("data_fin_i"+counter), formatter);
                Istruzione tmpIstruzione = new Istruzione(null, tmpData_in_i, tmpData_fin_i, tmpNomeQualifica, tmpTipoIstruzione, tmpNomeIstituto);
                istruzioni.add(istruzione);
            }else flag = false;
        }

        //Prendo le softskills
        List<String> softSkills = new ArrayList<>();
        for(String s:request.getParameter("soft_skills").split(","))
            softSkills.add(s);

        Persona persona = new Persona(nome, emailPersona, passwordPersona, regionePersona, provinciaPersona, fotoPersona, cap, telefonoPersona, citta, via, cognome, cf, data_nascita, filtroMacroarea, posizioneDesiderata, null, null);
        Curriculum curriculum = new Curriculum(softSkills, persona, esperienzeLavorative, lingue, istruzioni);
        for(EsperienzaLavorativa el : esperienzeLavorative)
            el.setCurriculum(curriculum);
        for(Lingua l : lingue)
            l.setCurriculum(curriculum);
        for(Istruzione i : istruzioni)
            i.setCurriculum(curriculum);
        persona.setCurriculum(curriculum);

        UtenteService service = new UtenteService();
        service.registraPersona(persona);
        if(service.autenticazione(emailPersona, passwordPersona) != null){
            HttpSession session = request.getSession();
            session.setAttribute("utente", persona);
            request.getRequestDispatcher("./WEB-INF/areaPersona.jsp").forward(request, response);
        }else{
            response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}