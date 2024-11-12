import { Component, OnInit } from '@angular/core';
import { BlocService } from '../services/bloc.service'; 
import { Bloc } from '../bloc.model'; 

@Component({
  selector: 'app-bloc',
  templateUrl: './bloc.component.html',
  styleUrls: ['./bloc.component.css']
})
export class BlocComponent implements OnInit {
  blocs: Bloc[] = [];  // Tableau pour stocker les blocs récupérés
  newBloc: Bloc = { idBloc: 0, nomBloc: '', capaciteBloc: 0 };  // Objet pour le bloc à ajouter

  constructor(private blocService: BlocService) { }

  ngOnInit(): void {
    this.getBlocs();  // Récupérer la liste des blocs au démarrage
  }

  // Méthode pour récupérer tous les blocs
  getBlocs(): void {
    this.blocService.getBlocs().subscribe((data: Bloc[]) => {
      this.blocs = data;
    });
  }

  // Méthode pour ajouter un bloc
  addBloc(): void {
    if (this.newBloc.nomBloc && this.newBloc.capaciteBloc) {
      this.blocService.createBloc(this.newBloc).subscribe((addedBloc: Bloc) => {
        this.blocs.push(addedBloc);  // Ajouter le bloc ajouté à la liste
        this.newBloc = { idBloc: 0, nomBloc: '', capaciteBloc: 0 };  // Réinitialiser le formulaire
      });
    }
  }
}
