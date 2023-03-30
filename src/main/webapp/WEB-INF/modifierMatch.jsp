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
<title>Modifier un Match</title>
</head>
<body>
  <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <%@ include file="navbar.jsp" %>
  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
  <%@ include file="menu.jsp" %>
  		<form class="form-inline my-2 my-lg-0" method="POST" action="listmatch">
			<input class="form-control mr-sm-2" type="text" placeholder="Rechercher" aria-label="Search" name="txtSearch">
			<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="rechercher">Rechercher</button>
		</form>
	</div>
  </nav>
	<main role="main" class="container">

		<div class="starter-template">
			<h1>Modifier un match</h1>

		</div>
		<div style="width: 40%; margin: auto;">

			<form class="needs-validation " novalidate method="post"
				action="modifiermatch">
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationCustom04">Epreuve</label> <select
							class="custom-select" id="validationCustom04" name="opEpreuve"
							style="width: 400px;" required>
							<option selected disabled>${ match.id_epreuve }</option>
							<c:forEach var="epreuve" items = "${epreuves}">
								<option value="${ epreuve.id }"> ${epreuve.annee} </option>
							</c:forEach>
						</select>
						<div class="valid-feedback">Très bien!</div>
						<div class="invalid-feedback">Veuillez choisir une épreuve!</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationCustom04">Vainqueur</label> <select
							class="custom-select" id="validationCustom04" name="opVainqueur"
							style="width: 400px;" required>
							<option selected disabled>${ match.id_vainqueur }</option>
							<c:forEach var="joueur" items = "${joueurs}">
								<option value="${ joueur.id }"> ${joueur.nom} </option>
							</c:forEach>
						</select>
						<div class="valid-feedback">Très bien!</div>
						<div class="invalid-feedback">Veuillez choisir un vainqueur!</div>
					</div>
				</div>
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="validationCustom04">Finaliste</label> <select
							class="custom-select" id="validationCustom04" name="opFinaliste"
							style="width: 400px;" required>
							<option selected disabled>${ match.id_finaliste }</option>
							<c:forEach var="joueur" items = "${joueurs}">
								<option value="${ joueur.id }"> ${joueur.nom} </option>
							</c:forEach>
						</select>
						<div class="valid-feedback">Très bien!</div>
						<div class="invalid-feedback">Veuillez choisir un finaliste!</div>
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