export class Bloc {
    idBloc: number;
    nomBloc: string;
    capaciteBloc: number;
    foyer?: any;           // Typage approprié si vous avez un modèle `Foyer`
    chambres?: any[];      // Typage approprié si vous avez un modèle `Chambre`
  
    constructor(
      idBloc: number,
      nomBloc: string,
      capaciteBloc: number,
      foyer?: any,
      chambres?: any[]
    ) {
      this.idBloc = idBloc;
      this.nomBloc = nomBloc;
      this.capaciteBloc = capaciteBloc;
      this.foyer = foyer;
      this.chambres = chambres || []; // Par défaut, un tableau vide si non fourni
    }
  }
  