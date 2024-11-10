import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddchambreformComponent } from './addchambreform/addchambreform.component';
import { TestComponent } from './test/test.component';

const routes: Routes = [ { path: 'add-chambre', component: AddchambreformComponent },
  {path:'test', component:TestComponent},  // Définir la route pour le formulaire d'ajout de chambre
  { path: '', redirectTo: '/add-chambre', pathMatch: 'full' }, ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
