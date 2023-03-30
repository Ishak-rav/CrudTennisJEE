<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="fr">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="starter-template.css">
<title>Ajouter une Epreuve</title>
</head>
<body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <%@ include file="navbar.jsp" %>
  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
  <%@ include file="menu.jsp" %>
  		<form class="form-inline my-2 my-lg-0" method="POST" action="listepreuve">
			<input class="form-control mr-sm-2" type="text" placeholder="Rechercher" aria-label="Search" name="txtSearch">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="rechercher">Rechercher</button>
		</form>
	</div>
  </nav>
	<main role="main" class="container">

		<div class="starter-template">
			<h1>Ajouter une épreuve</h1>

		</div>
		<div style="width: 40%; margin: auto;">

			<form class="needs-validation " novalidate method="post"
				action="ajouterepreuve">
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationCustom01">Année de l'épreuve</label> <input
							type="text" class="form-control" style="width: 400px;"
							id="validationCustom01" name="txtAnnee" required>
						<div class="valid-feedback">Très bien!</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationCustom04">Type d'épreuve</label> <select
							class="custom-select" id="validationCustom04" name="opType"
							style="width: 400px;" required>
							<option selected disabled>Sélectioner...</option>
							<option value="F">Femme</option>
							<option value="H">Homme</option>
						</select>
						<div class="valid-feedback">Très bien!</div>
						<div class="invalid-feedback">Veuillez choisir un sexe!</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-3 mb-3">
						<label for="validationCustom04">Tournoi</label> 
						<select class="custom-select" id="validationCustom04" name="opTournoi" style="width: 400px;" required>
							<c:forEach var="tournoi" items = "${tournois}">
								<option value="${ tournoi.id }">${ tournoi.nom }</option>
							</c:forEach>
						</select>
						<div class="valid-feedback">Très bien!</div>
						<div class="invalid-feedback">Veuillez choisir un tournoi!</div>
					</div>
				</div>

				<button class="btn btn-primary center" type="submit">Submit form</button>
			</form>

		</div>
	</main>
	<!-- /.container -->

	<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>