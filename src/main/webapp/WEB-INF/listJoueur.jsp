<!doctype html>
<html lang="fr">
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Liste Joueur</title>
  </head>
  <body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <%@ include file="navbar.jsp" %>
  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
  <%@ include file="menu.jsp" %>
  		<form class="form-inline my-2 my-lg-0" method="POST" action="listjoueur">
			<input class="form-control mr-sm-2" type="text" placeholder="Rechercher" aria-label="Search" name="txtSearch">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="rechercher">Rechercher</button>
		</form>
	</div>
  </nav>
  <main role="main" class="container">

  <div class="starter-template">
    <h1>Liste des joueurs</h1>
    <p class="lead">Bienvenue <c:out value="${connectedUser.password }"></c:out> !</p>
    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
  </div>

</main><!-- /.container -->
<div class="container">

<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
	<a class="btn btn-primary" href="/AppJoueur/ajouterjoueur" role="button">Ajouter joueur</a>
</div>
<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:10%">#</th>
      <th scope="col" style="width:25%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
      <th scope="col" style="width:20%">Sexe</th>
	  <th scope="col" style="width:20%"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="joueur" items = "${joueurs}">
    	<tr>
      		<th scope="row">${joueur.id}</th>
      		<td><c:out value = "${joueur.nom}" /></td>
      		<td><c:out value = "${joueur.prenom}" /></td>
      		<!-- <td>${ joueur.prenom }</td> -->
      		<td><c:out value = "${joueur.sexe}" /></td>
	  		<td>
	  		<c:if test="${admin == true}">
	  		  <a role="button" class="btn btn-outline-secondary" href="/AppJoueur/modifierjoueur?id=${joueur.id}">Modifier</a>
	  		  <a role="button" class="btn btn-outline-danger" href="/AppJoueur/supprimerjoueur?id=${joueur.id}">Supprimer</a>
	  		</c:if>
	  		</td>
    	</tr>
    </c:forEach>
  </tbody>
</table>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>


