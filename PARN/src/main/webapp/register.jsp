<%@ page import="storage.entity.Utente" %>
<%@ page import="storage.entity.Lingua" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Registrazione</title>

  <!-- CSS  -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  <link href="css/progetto.css" type="text/css" rel="stylesheet" media="screen,projection"/>
<script>
        <%
            session=request.getSession();
            Utente u= (Utente) session.getAttribute("utente");
            if (u!=null){
        %>
        window.location.href = "./Login";
        <%
            }
        %>
        function add(){
        alert("ciao");
    }
    var userSelection = document.getElementById('.agg');
    userSelection.addEventListener('click', add);
</script>
</head>
<body>
    <nav class="default-color" role="navigation">
        <div class="nav-wrapper container">
          <a id="logo-container" href="index.jsp" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
          <ul class="right hide-on-med-and-down">
            <li class="active"><a class ="white-text" href="register.jsp">Registrazione</a></li>
            <li ><a class ="white-text" href="accesso.jsp">Accedi</a></li>
          </ul>
    
          <ul id="nav-mobile" class="sidenav">
            <li class="active"><a class ="white-text" href="register.jsp">Registrazione</a></li>
            <li ><a class ="white-text" href="accesso.jsp">Accedi</a></li>
          </ul>
          <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons white-text">menu</i></a>
        </div>
      </nav>
  
  <div class="container">
    <div class="section">

        <div class="row">
            <div class="col s12 m12 center">
                <h4>Registrazione</h4>
            </div>
            <div class="col s12">
                <ul id="tabs-swipe-demo1" class="tabs tabs-fixed-width">
                    <li class="tab col s3"><a href="#Azienda">Azienda</a></li>
                    <li class="tab col s3"><a class="active" href="#Persona">Persona</a></li>
                </ul>
                <div id="Azienda" class="col s12 m12 center">
                    <form class="col s12" method="post" id="invioAzienda" action="registerAzienda" enctype="multipart/form-data">
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Nome" id="nomeAzienda" name="nomeAzienda" type="text" class="validate" required>
                                <label for="nomeAzienda">Inserisci il nome</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="P.Iva" id="piva" name="piva" type="text" class="validate" required>
                                <label for="piva">Inserisci la partita iva</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Telefono" id="telefonoAzienda" name="telefonoAzienda" type="text" class="validate" required>
                                <label for="telefonoAzienda">Inserisci il telefono</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Ragione Sociale" type="text" id="ragioneSociale" name="ragioneSociale"  class="validate" required>
                                <label for="ragioneSociale">Ragione sociale</label> 
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Sito web" id="sitoWeb" name="sitoWeb" type="text" class="validate" required>
                                <label for="sitoWeb">Inserisci il link del sito web</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Regione" id="regioneAzienda" name="regioneAzienda" type="text" class="validate" required>
                                <label for="regioneAzienda">Inserisci la regione</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Via" id="viaAzienda" name="viaAzienda" type="text" class="validate" required>
                                <label for="viaAzienda">Inserisci la via</label>
                            </div>
                        </div>
                        <div class="row"> 
                            <div class="input-field col s12 m4">
                                <input placeholder="Provincia" type="text" id="provinciaAzienda" name="provinciaAzienda"  class="validate" required>
                                <label for="provinciaAzienda">Inserici la provincia</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Citta" type="text" id="cittaAzienda" name="cittaAzienda"  class="validate" required>
                                <label for="cittaAzienda">Inserici la citta</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="CAP" id="capAzienda" name="capAzienda" type="text" class="validate" required>
                                <label for="capAzienda">Inserisci il CAP</label>
                              </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m5">
                                <input placeholder="Area di interesse" id="areaInteresse" name="areaInteresse" type="text" class="validate" required>
                                <label for="areaInteresse">Inserisci il l'area di competenza</label>
                            </div>
                            <div class="input-field col s12 m5">
                                <input placeholder="Settore di competenza" id="settoriCompetenza" name="settoriCompetenza" type="text" class="validate" required>
                                <label for="settoriCompetenza">Inserisci i settori di competenza</label>
                              </div>
                            <div class="input-field col s12 m2">
                                <input placeholder="Numero" id="dipendenti" name="dipendenti" type="text" class="validate" required>
                                <label for="dipendenti">Numero di dipendenti</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder="e-mail" id="emailAzienda" name="emailAzienda" type="email" class="validate" required>
                                <label for="emailAzienda">Inserisci la Mail:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Mail" id="email_Conferma" name="email_Conferma" type="email" class="validate" required>
                                <label for="email_Conferma">Conferma la Mail:</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder="Password" id="password_Azienda" name="password_Azienda" type="password" class="validate" required>
                                <label for="password_Azienda">Inserisci la password:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Password" id="password_Conferma_Azienda" name="password_Conferma_Azienda" type="password" class="validate" required>
                                <label for="password_Conferma_Azienda">Inserisci la password:</label>
                            </div>
                            <div class="input-field col s12 m12">
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span>File</span>
                                        <input type="file" accept="image/png, image/jpeg" name="immagine">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" id="logo" name="logo" placeholder="Logo azienda">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12 m12 center">
                                <h4>Sede</h4>
                            </div>
                            <div class="col s12">
                                <ul id="tabs-swipe-demo2" class="tabs tabs-fixed-width">
                                    <li class="tab col s3"><a href="#Discorde">Non coincide</a></li>
                                    <li class="tab col s3"><a class="active" href="#Concorde">Coincide sede fisica con la fiscale</a></li>
                                </ul>
                                <div id="Discorde" class="col s12 m12 center">
                                    <div class="row"> 
                                        <div class="input-field col s12 m3">
                                            <input placeholder="Regione Sede" type="text" id="regioneSede" name="regioneSede"  class="validate" >
                                            <label for="regioneSede">Inserici la regione</label> 
                                        </div>
                                        <div class="input-field col s12 m3">
                                            <input placeholder="Provincia Sede" type="text" id="provinciaSede" name="provinciaSede"  class="validate" >
                                            <label for="provinciaSede">Inserici la provincia</label> 
                                        </div>
                                        <div class="input-field col s12 m3">
                                            <input placeholder="Citta Sede" type="text" id="cittaSede" name="cittaSede"  class="validate" >
                                            <label for="cittaSede">Inserici la citta</label> 
                                        </div>
                                        <div class="input-field col s12 m3">
                                            <input placeholder="CAP Sede" id="capSede" name="capSede" type="text" class="validate" >
                                            <label for="capSede">Inserisci il CAP</label>
                                          </div>
                                    </div>
                                    <div class="row"> 
                                        <div class="input-field col s12 m4">
                                            <input placeholder="Telefono Sede" id="telefonoSede" name="telefonoSede" type="text" class="validate" >
                                            <label for="telefonoSede">Inserisci il telefono</label>
                                        </div>
                                        <div class="input-field col s12 m4">
                                            <input placeholder="Via Sede" type="text" id="viaSede" name="viaSede"  class="validate" >
                                            <label for="viaSede">Inserici la via della sede</label> 
                                        </div>
                                        <div class="input-field col s12 m4">
                                            <input placeholder="e-mail" id="emailSede" name="emailSede" type="email" class="validate" >
                                            <label for="emailSede">Inserisci la Mail:</label>
                                        </div>
                                    </div>     
                                </div>
                                <div id="Concorde" class="col s12 m12 center"></div>
                            </div>
                            
                        </div> 
                        <div class="row">
                            <div class="col s12 m12">
                                <button class="btn waves-effect waves-light" type="submit" name="action">Invia<i class="material-icons right">send</i></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="Persona" class="col s12">
                    <form class="col s12" method="post" id="invioPersona" action="registerPersona" enctype="multipart/form-data">
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <input placeholder="Nome" id="nomePersona" name="nomePersona" type="text" class="validate" required>
                                <label for="nomePersona">Inserisci il nome</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Cognome" id="cognome" name="cognome" type="text" class="validate" required>
                                <label for="cognome">Inserisci il cognome</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Telefono" id="telefonoPersona" name="telefonoPersona" type="text" class="validate" required>
                                <label for="telefonoPersona">Inserisci il telefono</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Codice fiscale" type="text" id="codiceFiscale" name="codiceFiscale"  class="validate" required>
                                <label for="codiceFiscale">Codice fiscale</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Data" type="date" id="data_n" name="data_n"  class="validate" max="<%=LocalDate.now()%>" required>
                                <label for="data_n">Data di nascita:</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Regione" id="regionePersona" name="regionePersona" type="text" class="validate" required>
                                <label for="regionePersona">Inserisci la regione</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Provincia" type="text" id="provinciaPersona" name="provinciaPersona"  class="validate" required>
                                <label for="provinciaPersona">Inserici la provincia</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m5">
                                <input placeholder="Via" type="text" id="viaPersona" name="viaPersona"  class="validate" required>
                                <label for="viaPersona">Inserici la via</label>
                            </div>
                            <div class="input-field col s12 m5">
                                <input placeholder="Citta" type="text" id="cittaPersona" name="cittaPersona"  class="validate" required>
                                <label for="cittaPersona">Inserici la citta</label>
                            </div>
                            <div class="input-field col s12 m2">
                                <input placeholder="CAP" id="capPersona" name="capPersona" type="text" class="validate" required>
                                <label for="capPersona">Inserisci il CAP</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder="Posizione Desiderata" type="text" id="posizione" name="posizione"  class="validate" required>
                                <label for="posizione">Inserici la posizione che si vuole ricoprire</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Filtro annunci" type="text" id="filtroMacroarea" name="filtroMacroarea"  class="validate" required>
                                <label for="filtroMacroarea">Inserici preferenza</label>
                            </div>
                            <div class="input-field col s12 m12">
                                <div class="file-field input-field">
                                    <div class="btn">
                                        <span>File</span>
                                        <input type="file" accept="image/png, image/jpeg" name = "immagine">
                                    </div>
                                    <div class="file-path-wrapper">
                                        <input class="file-path validate" type="text" id="fotoPersona" name="fotoPersona" placeholder="Foto personale">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder="Mail" id="mailPersona" name="mailPersona" type="email" class="validate" required>
                                <label for="mailPersona">Inserisci la Mail:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Mail" id="email_Conferma_Persona" name="email_Conferma_Persona" type="email" class="validate" required>
                                <label for="email_Conferma_Persona">Conferma la Mail:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Password" id="password_Persona" name="password_Persona" type="password" class="validate" required>
                                <label for="password_Persona">Inserisci la password:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Password" id="password_Conferma_Persona" name="password_Conferma_Persona" type="password" class="validate" required>
                                <label for="password_Conferma_Persona">Inserisci la password:</label>
                            </div>
                        </div>
                        <div class="curriculum">
                            <h4>Curriculum</h4>

                            <div class="esperienzaLavorativa" id="esperienzaLavorativa">
                                <div class="input-field col s11 m11">
                                    <h5>Esperienza lavorativa</h5>
                                </div>
                                <div class="input-field col s1 m1">
                                    <a class="btn-floating btn-small waves-effect waves-light default-color" id="agg" onclick="addEsperienza()"><i class="material-icons">add</i></a>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 m4">
                                        <input placeholder="Nome azienda" id="nomeAziendaEsperienza1" name="nomeAziendaEsperienza1" type="text" class="validate">
                                        <label for="nomeAziendaEsperienza1">Inserisci il nome del azienda</label>
                                    </div>
                                    <div class="input-field col s12 m4">
                                        <input placeholder="Tipo azienda" id="tipoAzienda1" name="tipoAzienda1" type="text" class="validate">
                                        <label for="tipoAzienda1">Inserisci il tipo di azienda</label>
                                    </div>
                                    <div class="input-field col s12 m4">
                                        <input placeholder="Tipo impiego" id="tipoImpiego1" name="tipoImpiego1" type="text" class="validate">
                                        <label for="tipoImpiego1">Inserisci il tipo di impiego svolto</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 m3">
                                        <input placeholder="Nome Datore" id="nomeDatore1" name="nomeDatore1" type="text" class="validate">
                                        <label for="nomeDatore1">Inserisci il nome del datore</label>
                                    </div>
                                    <div class="input-field col s12 m3">
                                        <input placeholder="Contatto" id="contattoAzienda1" name="contattoAzienda1" type="text" class="validate">
                                        <label for="contattoAzienda1">Inserisci il contatto del referente</label>
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Mansioni" id="mansioni1" name="mansioni1" type="text" class="validate">
                                        <label for="mansioni1">Inserisci le mansioni svolte</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 m6">
                                        <input placeholder=" inizio" type="date" id="data_in_e1" name="data_in_e1"  class="validate" max="<%=LocalDate.now()%>">
                                        <label for="data_in_e1">Data di inizio esperienza:</label>
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Data fine" type="date" id="data_fin_e1" name="data_fin_e1"  class="validate" max="<%=LocalDate.now()%>">
                                        <label for="data_fin_e1">Data di fine esperienza:</label>
                                    </div>
                                </div>
                            </div>
                            <div class="Lingua" id="lingua">
                                <div class="input-field col s11 m11">
                                    <h5>Lingua</h5>
                                </div>
                                <div class="input-field col s1 m1">
                                    <a class="btn-floating btn-small waves-effect waves-light default-color" onclick="addLingua()"><i class="material-icons">add</i></a>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12 m6">
                                        <input placeholder="Nome lingua" id="nomeLingua1" name="nomeLingua1" type="text" class="validate" required>
                                        <label for="nomeLingua1">Inserisci il nome della lingua</label>
                                    </div>
                                    <div class="input-field col s12 m6">
                                        <select name="livelloLingua1" id="livelloLingua1">
                                            <option value="" disabled>Scegli il livello</option>
                                            <%
                                                for (String livello: Lingua.LIVELLI){
                                            %>
                                                <option value="<%=livello%>"><%=livello%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                        <label for="livelloLingua1">Inserisci il livello della lingua</label>
                                    </div>
                                </div>
                            </div>
                            <div class="Istruzione" id="istruzione">
                                <div class="input-field col s11 m11">
                                    <h5>Istruzione</h5>
                                </div>
                                <div class="input-field col s1 m1">
                                    <a class="btn-floating btn-small waves-effect waves-light default-color" onclick="addIstruzione()"><i class="material-icons">add</i></a>
                                </div>
                                <div class="row">

                                    <div class="input-field col s12 m4">
                                        <input placeholder="Nome Istituto" id="nomeIstituto1" name="nomeIstituto1" type="text" class="validate" required>
                                        <label for="nomeIstituto1">Inserisci il nome del istituto</label>
                                    </div>

                                    <div class="input-field col s12 m4">
                                        <input placeholder="Tipo Istruttore" id="tipoIstruzione1" name="tipoIstruzione1" type="text" class="validate" required>
                                        <label for="tipoIstruzione1">Inserisci il tipo di istruzione</label>
                                    </div>

                                    <div class="input-field col s12 m4">
                                        <input placeholder="Nome Qualifica" id="nomeQualifica1" name="nomeQualifica1" type="text" class="validate" required>
                                        <label for="nomeQualifica1">Inserisci il nome della qualifica</label>
                                    </div>

                                </div>

                                <div class="row">

                                    <div class="input-field col s12 m6">
                                        <input placeholder=" inizio" type="date" id="data_in_i1" name="data_in_i1"  class="validate" max="<%=LocalDate.now()%>" required>
                                        <label for="data_in_i1">Data di inizio Istruzione:</label>
                                    </div>

                                    <div class="input-field col s12 m6">
                                        <input placeholder="Data fine" type="date" id="data_fin_i1" name="data_fin_i1"  max="<%=LocalDate.now()%>" class="validate">
                                        <label for="data_fin_i1">Data di fine Istruzione:</label>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="input-field col s12 m12">
                                        <input placeholder="Soft Skills" type="text" id="soft_skills" name="soft_skills"  class="validate" required>
                                        <label for="soft_skills">Soft Skills</label>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col center s12 m12">
                                <button class="btn waves-effect waves-light" type="submit" name="action">Invia<i class="material-icons right">send</i></button>
                            </div>
                        </div>
                    </form>
                </div>
          </div>

        </div>

    </div>
  </div>
  
  <footer class="page-footer default-color">
    <div class="container">
      <div class="row">
        <div class="col l6 s12">
          <h5 class="white-text">PARN</h5>
          <p class="grey-text text-lighten-4">Siamo un gruppo di studenti universitari che ha ideato questo progetto per il corso di Ingegneria del Software.</p>


        </div>
        <div class="col l3 s12">           
        </div>
        <div class="col l3 s12">
          <h5 class="white-text">Link</h5>
          <ul>
            <li><a class="white-text" href="register.jsp">Registrazione</a></li>
            <li><a class="white-text" href="accesso.jsp">Login</a></li>
            <li><a class="white-text" href="index.jsp">Home</a></li>
          </ul>
        </div>
      </div>
    </div>
    <div class="footer-copyright">
      <div class="container">
      Made by <a class="brown-text text-lighten-3" href="http://materializecss.com">Materialize</a>
      </div>
    </div>
  </footer>


  <!--  Scripts-->
  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
    <script src="js/script.js"></script>
    <script src="js/creazione.js"></script>

</body>
</html>