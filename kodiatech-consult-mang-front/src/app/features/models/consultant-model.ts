import { Adresse } from "./Adresse-model";

export class Consultant{
id! :string;

nom!:string;
prenom!:string;
email!:string;
telephone!:string;
adresse!:Adresse;
fonctionTitle?:string;
statut!: 'EN_ATTENTE' | 'ACTIF' | 'INACTIF';

photoProfilUrl?: string;

completudeProfil?: number; // %
profileComplete!: boolean;

}
