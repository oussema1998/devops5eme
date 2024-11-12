import { Component } from '@angular/core';
import { Router } from '@angular/router';  // Importer Router pour la navigation
import { BlocService } from '../services/bloc.service';
import { Bloc } from '../bloc.model';

@Component({
  selector: 'app-add-bloc',
  templateUrl: './add-bloc.component.html',
  styleUrls: ['./add-bloc.component.css']
})
export class AddBlocComponent {
  newBloc: Bloc = { idBloc: 0, nomBloc: '', capaciteBloc: 0 };  // Objet pour le bloc à ajouter
  message: string | null = null;  // Variable pour afficher un message de confirmation

  constructor(
    private blocService: BlocService,
    private router: Router // Injecter Router
  ) { }

  // Méthode pour ajouter un bloc
  addBloc(): void {
    if (this.newBloc.nomBloc && this.newBloc.capaciteBloc) {
      this.blocService.createBloc(this.newBloc).subscribe((addedBloc: Bloc) => {
        // Réinitialiser le formulaire immédiatement
        this.newBloc = { idBloc: 0, nomBloc: '', capaciteBloc: 0 };

        // Naviguer immédiatement vers la liste des blocs
        this.router.navigate(['/list-bloc']); 
      });
    } else {
      this.message = 'Veuillez remplir tous les champs du bloc.';
      setTimeout(() => {
        this.message = null;
      }, 3000);
    }
  }
}
