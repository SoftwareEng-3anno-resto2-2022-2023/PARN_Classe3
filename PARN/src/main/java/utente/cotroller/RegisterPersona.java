package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ImageManager.ImageManager;
import utils.PasswordEncrypter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "RegisterPersona", value = "/registerPersona")
public class RegisterPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtenteServiceInterface service = new UtenteService();


        //Prendo tutti i parametri standard della persona
        String nome = request.getParameter("nomePersona");
        String cognome = request.getParameter("cognome");
        String telefonoPersona = request.getParameter("telefonoPersona");
        String cf = request.getParameter("codiceFiscale");
        LocalDate data_n = LocalDate.parse(request.getParameter("data_n"));
        String regionePersona = request.getParameter("regionePersona");
        String provinciaPersona = request.getParameter("provinciaPersona");
        String citta = request.getParameter("cittaPersona");
        String via = request.getParameter("viaPersona");
        String cap = request.getParameter("capPersona");
        String posizioneDesiderata = request.getParameter("posizione");
        String filtroMacroarea = request.getParameter("filtroMacroarea");
        Part fotoPersona = request.getPart("immagine");
        String emailPersona = request.getParameter("mailPersona");
        String passwordPersona = PasswordEncrypter.encryptThisString(request.getParameter("password_Persona"));

        String imagePath = "";
        if (fotoPersona != null) {
            //Upload immagine sul server
            String rootPath = String.valueOf(request.getServletContext().getResource("/").getPath());
            String fileExtention = fotoPersona.getSubmittedFileName().substring(fotoPersona.getSubmittedFileName().indexOf('.'));
            ImageManager imageManager = new ImageManager(rootPath, emailPersona, fotoPersona, fileExtention);
            imagePath = imageManager.saveImage();
        }

        //Crea la persona
        Persona persona = new Persona(nome, emailPersona, passwordPersona, regionePersona, provinciaPersona,
                imagePath, cap, telefonoPersona, citta, via, cognome, cf, data_n, filtroMacroarea,
                posizioneDesiderata, null, null);

        //Prendo le softskills
        List<String> softSkills = new ArrayList<>();
        for(String s:request.getParameter("soft_skills").split(","))
            softSkills.add(s);

        //Creo il curriculum senza le liste e lo aggiungo alla persona
        Curriculum curriculum = new Curriculum(persona, softSkills);
        persona.setCurriculum(curriculum);

        //Prendo tutte le lingue inserite
        boolean flag = true;
        int counter = 0;
        while(flag){
            counter++;
            String nomeLingua = request.getParameter("nomeLingua"+counter);
            String livellolingua = request.getParameter("livelloLingua"+counter);

            if(nomeLingua != null && livellolingua != null) {
                Lingua lingua = new Lingua(nomeLingua, livellolingua, curriculum);
                curriculum.getLingue().add(lingua);
            } else flag = false;
        }

        //Prendo tutti i campi istruzione inseriti
        flag = true;
        counter = 0;
        while (flag) {
            counter++;
            String nomeIstituto = request.getParameter("nomeIstituto"+counter);
            String tipoIstruzione = request.getParameter("tipoIstruzione"+counter);
            String nomeQualifica = request.getParameter("nomeQualifica"+counter);

            LocalDate ddi = null;
            if(request.getParameter("data_in_i"+counter) != null){
                ddi = LocalDate.parse(request.getParameter("data_in_i"+counter));
            }

            LocalDate ddf = null;
            if(request.getParameter("data_fin_i"+counter) != null){
                ddf = LocalDate.parse(request.getParameter("data_fin_i"+counter));
            }

            if(nomeIstituto != null && tipoIstruzione != null && nomeQualifica != null && ddi != null){
                Istruzione istruzione = new Istruzione(curriculum, ddi, ddf, nomeQualifica, tipoIstruzione, nomeIstituto);
                curriculum.getIstruzioni().add(istruzione);
            } else flag = false;
        }

        //Prendo tutte le esperienze lavorative inserite
        flag = true;
        counter = 0;
        while(flag) {
            counter++;
            String nomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza"+counter);
            String tipoAzienda = request.getParameter("tipoAzienda"+counter);
            String nomeDatore = request.getParameter("nomeDatore"+counter);
            String tipoImpiego = request.getParameter("tipoImpiego"+counter);
            String contattoAzienda = request.getParameter("contattoAzienda"+counter);

            String mansioniString = request.getParameter("mansioni"+counter);
            List<String> mansioni = new ArrayList<>();

            LocalDate ddi = null;
            if(request.getParameter("data_in_e"+counter) != null){
                ddi = LocalDate.parse(request.getParameter("data_in_e"+counter));
            }

            LocalDate ddf = null;
            if(request.getParameter("data_fin_e"+counter) != null){
                ddf = LocalDate.parse(request.getParameter("data_fin_e"+counter));
            }

            if(nomeAziendaEsperienza != null && tipoAzienda != null && nomeDatore != null &&
                    contattoAzienda != null && !mansioni.isEmpty() && ddi != null){
                for(String s:mansioniString.split(","))
                    mansioni.add(s);
                EsperienzaLavorativa esperienzaLavorativa = new EsperienzaLavorativa(ddi, ddf, tipoAzienda, nomeDatore,
                        contattoAzienda, tipoImpiego, mansioni, nomeAziendaEsperienza, curriculum);
                curriculum.getEsperienze().add(esperienzaLavorativa);
            } else flag = false;
        }

        service.registraPersona(persona);
        if(service.autenticazione(emailPersona, passwordPersona) != null) {
            session.setAttribute("utente", persona);
            request.getRequestDispatcher("./WEB-INF/areaPersonalePersona.jsp").forward(request, response);
        } else response.sendRedirect(".");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
