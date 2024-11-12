import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlocComponent } from './bloc/bloc.component';
import { AddBlocComponent } from './add-bloc/add-bloc.component';  
import { ListBlocComponent } from './list-bloc/list-bloc.component'; 

const routes: Routes = [
  { path: '', redirectTo: '/list-bloc', pathMatch: 'full' },  // Rediriger la route par défaut vers la page des blocs
  // { path: 'addblocs', component: BlocComponent },  // Affiche la liste des blocs
  { path: 'add-bloc', component: AddBlocComponent },  // Route pour ajouter un bloc
  { path: 'list-bloc', component: ListBlocComponent },  // Route pour afficher la liste des blocs
  { path: '**', redirectTo: '/blocs' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
