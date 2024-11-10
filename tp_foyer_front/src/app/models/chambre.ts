// chambre.model.ts
export enum TypeChambre {
    SIMPLE = 'SIMPLE',
    DOUBLE = 'DOUBLE',
    TRIPLE = 'TRIPLE'
  }
  
  export class Chambre {
    idChambre!: number;
    numeroChambre!: number;
    typeChambre!: TypeChambre;
  
    
  }
  