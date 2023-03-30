		<ul class="navbar-nav mr-auto">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Ajouter</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="/AppJoueur/ajouterjoueur">Ajouter joueur</a> 
					<a class="dropdown-item" href="/AppJoueur/ajoutertournoi">Ajouter tournoi</a> 
					<a class="dropdown-item" href="/AppJoueur/ajouterepreuve">Ajouter épreuve</a> 
					<a class="dropdown-item" href="/AppJoueur/ajoutermatch">Ajouter match</a>
				</div>
			</li>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Lister</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="/AppJoueur/listjoueur">Lister joueur</a> 
					<a class="dropdown-item" href="/AppJoueur/listtournoi">Lister tournoi</a> 
					<a class="dropdown-item" href="/AppJoueur/listepreuve">Lister épreuve</a> 
					<a class="dropdown-item" href="/AppJoueur/listmatch">Lister match</a>
				</div>
			</li>
			<li class="nav-item">
				<form class="form-inline my-2 my-lg-0" action="listjoueur" method="post">
					<button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="deconnexion">Deconnexion</button>
				</form>
			</li>
		</ul>
