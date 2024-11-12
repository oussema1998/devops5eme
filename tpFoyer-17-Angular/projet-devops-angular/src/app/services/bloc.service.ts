import { HttpClient , HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable , throwError } from 'rxjs';
import { Bloc } from '../bloc.model';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BlocService {
  private apiUrl = 'http://localhost:8082/tpFoyer17/api/blocs';  // URL avec le préfixe /tpFoyer17

  constructor(private http: HttpClient) { }

  // Méthode pour créer un nouveau Bloc
  // createBloc(bloc: Bloc): Observable<Bloc> {
  //  return this.http.post<Bloc>(`${this.apiUrl}/addBloc`, bloc);  // Utilisation du bon endpoint
  // }

  createBloc(bloc: Bloc): Observable<Bloc> {
    return this.http.post<Bloc>(`${this.apiUrl}/addBloc`, bloc).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          console.error('An error occurred:', error.error.message);
        } else {
          console.error(`Backend returned code ${error.status}, body was: `, error.error);
        }
        console.log('Request body:', bloc);  // Vérification des données envoyées
        console.log('API URL:', `${this.apiUrl}/addBloc`);
        return throwError(() => new Error('Error while creating bloc'));
      })
    );
  }
  
  
  

  // Méthode pour récupérer tous les Blocs
  getBlocs(): Observable<Bloc[]> {
    return this.http.get<Bloc[]>(`${this.apiUrl}/retrieveBlocs`);  // Utilisation du bon endpoint
  }

  // Méthode pour récupérer un Bloc spécifique par ID
  getBlocById(id: number): Observable<Bloc> {
    return this.http.get<Bloc>(`${this.apiUrl}/retrieveBloc/${id}`);  // Utilisation du bon endpoint
  }

  // Méthode pour mettre à jour un Bloc
  updateBloc(id: number, bloc: any): Observable<any> {
    return this.http.put<Bloc>(`${this.apiUrl}/updateBloc`, bloc);  // Utilisation du bon endpoint
  }

  // Méthode pour supprimer un Bloc
  deleteBloc(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/removeBloc/${id}`);  // Utilisation du bon endpoint
  }
}
