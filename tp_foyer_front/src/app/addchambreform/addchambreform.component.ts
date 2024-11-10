import { Component } from '@angular/core';
import { ChambreService } from '../chambre.service';  // Assurez-vous que le service est correctement importé
import { Chambre } from '../models/chambre';  // Assurez-vous que le modèle est correctement importé

@Component({
  selector: 'app-add-chambre-form',
  template: `
    <div class="form-container">
      <h2>Ajouter une Chambre</h2>
      <form (ngSubmit)="onSubmit()">
        <div class="form-group">
          <label for="numeroChambre">Numéro de Chambre</label>
          <input  [(ngModel)]="newChambre.numeroChambre" 
            type="number" 
            id="numeroChambre" 
           
            name="numeroChambre" 
            required 
            class="form-control"
          />
        </div>

        <div class="form-group">
          <label for="typeChambre">Type de Chambre</label>
          <select 
            id="typeChambre" 
            [(ngModel)]="newChambre.typeChambre" 
            name="typeChambre" 
            required 
            class="form-control"
          >
            <option value="SIMPLE">SIMPLE</option>
            <option value="DOUBLE">DOUBLE</option>
            <option value="TRIPLE">TRIPLE</option>
          </select>
        </div>

        <button type="submit" class="btn btn-primary">Ajouter Chambre</button>
      </form>
    </div>
  `,
  styleUrls: ['./addchambreform.component.css']  // Vous pouvez toujours utiliser un fichier CSS séparé
})
export class AddchambreformComponent {
  newChambre: Chambre = new Chambre();  // Objet de chambre vide pour la liaison

  constructor(private chambreService: ChambreService) {}

  // Méthode appelée lors de la soumission du formulaire
  onSubmit(): void {
    this.chambreService.addChambre(this.newChambre).subscribe(
      (response) => {
        console.log('Chambre ajoutée avec succès:', response);
        alert('Chambre ajoutée avec succès');
        this.resetForm();  // Optionnel, pour réinitialiser le formulaire après l'ajout
      },
      (error) => {
        console.error('Erreur lors de l\'ajout de la chambre:', error);
        alert('Erreur lors de l\'ajout de la chambre');
      }
    );
  }

  // Optionnel: Réinitialise le formulaire après ajout
  resetForm(): void {
    this.newChambre = new Chambre();
  }
}
