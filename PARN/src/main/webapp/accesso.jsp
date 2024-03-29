<%@ page import="storage.entity.Utente" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>login</title>

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
      <a id="logo-container" href="index.jsp" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
      <ul class="right hide-on-med-and-down">
        <li><a class ="white-text" href="register.jsp">Registrazione</a></li>
        <li class="active"><a class ="white-text" href="accesso.jsp">Accedi</a></li>
      </ul>

      <ul id="nav-mobile" class="sidenav">
        <li><a class ="white-text" href="register.jsp">Registrazione</a></li>
        <li class="active"><a class ="white-text" href="accesso.jsp">Accedi</a></li>
      </ul>
      <a href="#" data-target="nav-mobile" class="sidenav-trigger"><i class="material-icons white-text">menu</i></a>
    </div>
  </nav>

  <div class="container">
    <div class="section-main min">

      <div class="row">
        <div class="col s12 m12 center">
          <h4>Accedi</h4>
		  <div class="row">
    <form class="col s12" method="post" action="Login">
	     <div class="row">
		 <div class="col m2">
        </div>
        <div class="input-field col s12 m8">
          <input placeholder="Mail" id="email" name="email" type="email" class="validate valid" required>
          <label for="email" class="label">Inserisci la mail</label>
        </div>
		<div class="col m2">
        </div>
      </div>
      <div class="row">
	  <div class="col m2">
        </div>
        <div class="input-field col s12 m8">
          <input placeholder="Password" id="password" name="password" type="password" class="validate valid" required>
          <label for="password" class="label">Inserisci la password</label>
        </div>
		<div class="col m2">
        </div>
      </div>
      <div class="row">
        <div class="col s12 m12">
            <button class="btn waves-effect waves-light default-color" type="submit" name="action">Accedi<i class="material-icons right">send</i></button>
			<p size="4">Non sei ancora iscritto? Clicca <a size="4" href="register.jsp">qui</a>.</p>

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

  </body>
</html>