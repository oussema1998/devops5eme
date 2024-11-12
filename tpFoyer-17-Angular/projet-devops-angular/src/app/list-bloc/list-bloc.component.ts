import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';  // Importer Router
import { BlocService } from '../services/bloc.service';
import { Bloc } from '../bloc.model';

@Component({
  selector: 'app-list-bloc',
  templateUrl: './list-bloc.component.html',
  styleUrls: ['./list-bloc.component.css']
})
export class ListBlocComponent implements OnInit {
  blocs: Bloc[] = [];  // Liste des blocs

  constructor(
    private blocService: BlocService,
    private router: Router  // Injection du Router
  ) {}

  ngOnInit(): void {
    this.getBlocs();  // Récupérer la liste des blocs au démarrage
  }

  // Méthode pour récupérer les blocs
  getBlocs(): void {
    this.blocService.getBlocs().subscribe((data: Bloc[]) => {
      this.blocs = data;
    });
  }

  // Méthode pour supprimer un bloc
  deleteBloc(id: number): void {
    // Appeler la méthode de suppression de service et mettre à jour la liste
    this.blocService.deleteBloc(id).subscribe(() => {
      this.blocs = this.blocs.filter(bloc => bloc.idBloc !== id);
    });
  }

  // Méthode pour naviguer vers le formulaire d'ajout
  goToAddBloc(): void {
    this.router.navigate(['/add-bloc']);  // Naviguer vers la route /add-bloc
  }
}
