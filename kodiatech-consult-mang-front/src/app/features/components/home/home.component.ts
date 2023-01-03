import { Component, OnInit } from '@angular/core';
import { PrimeIcons } from 'primeng/api';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  currentUser: any;
  events!: any[];
  constructor(private storageService: StorageService) { }

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();


    this.events = [
      {status: 'Enregistrement de vos informations personnelles',},
      {status: 'Enregistement de votre cv', },
      {status: 'Renseignement de vos diverses compétences', },
      {status: 'Finalisation de votre dossier de conadidature', }
  ];
  }

  clean(){
    this.storageService.remove();
  }
}
