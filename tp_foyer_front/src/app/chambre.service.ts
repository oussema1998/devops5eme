import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Chambre } from './models/chambre';  // Assurez-vous que ce modèle existe

@Injectable({
  providedIn: 'root'
})
export class ChambreService {
  private apiUrl = 'http://localhost:8082/tpFoyer17/api/chambres/'; // L'URL de l'API Spring Boot

  constructor(private http: HttpClient) {}

  // Méthode pour ajouter une chambre
  addChambre(chambre: Chambre): Observable<Chambre> {
    return this.http.post<Chambre>(this.apiUrl + 'addChambre', chambre);
  }
}
