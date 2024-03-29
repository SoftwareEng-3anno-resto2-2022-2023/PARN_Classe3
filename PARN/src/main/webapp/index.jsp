<%@ page import="storage.entity.Utente" %>
<%@ page import="storage.entity.Azienda" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>PARN</title>

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

  </script>
</head>

<body>
  <nav class="default-color" role="navigation">
    <div class="nav-wrapper container">
      <a id="logo-container" href="#" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
      <ul class="right hide-on-med-and-down">
        <li><a class ="white-text" href="register.jsp">Registrazione</a></li>
        <li><a class ="white-text" href="accesso.jsp">Accedi</a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav">
        <li><a class ="white-text" href="register.jsp">Registrazione</a></li>
        <li><a class ="white-text" href="accesso.jsp">Accedi</a></li>
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons white-text">menu</i></a>
    </div>
  </nav>

  <div id="index-banner" class="parallax-container">
    <div class="section no-pad-bot">
      <div class="container">
        <br><br>
        <h1 class="header left-align white-text text-lighten-2">PARN</h1>
        <div class="row left-align">
          <h5 class="header col s12 light">Portale</h5>
          <h5 class="header col s12 light">Assunzioni</h5>
          <h5 class="header col s12 light">Ricollocamento</h5>
          <h5 class="header col s12 light">Nazionale</h5>
        </div>
      <div class="row">
      </div>

        <br><br>

      </div>
    </div>
    <div class="parallax"><img src="resource/roma.jpg" alt="Unsplashed background img 1"></div>
  </div>


  <div class="container">
    <div class="section">
        <h2 class="header center black-text text-lighten-2">Cosa possiamo offrire?</h2>
      <div class="row">

      </div>

      <!--   Icon Section   -->
      <div class="row">
        <div class="col s12 m6">
          <div class="icon-block">
            <h2 class="center brown-text"><i class="material-icons black-text">person</i></h2>
            <h5 class="center">Persona</h5>

            <p class="light">Il PARN offre alle persone la possibilita di registrarsi e esprimere le proprie preferenze di lavoro e su questa base il sistema offrira una serie di annunci pertitenti alla persona.</p>
          </div>
        </div>

        <div class="col s12 m6">
          <div class="icon-block">
            <h2 class="center brown-text"><i class="material-icons black-text">business</i></h2>
            <h5 class="center">Azienda</h5>

            <p class="light">Il PARN nasce per aiutare le aziende nella ricerca del personale, con tutti i tipi di qualifica, attraverso la pubblicazione di annunci di lavoro o la ricerca nel caso di una figura altamente specializzata.</p>
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

  </body>
</html>
