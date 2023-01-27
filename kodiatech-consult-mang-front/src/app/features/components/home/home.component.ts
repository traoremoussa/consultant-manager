import { AuthService } from 'src/app/features/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { PrimeIcons } from 'primeng/api';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  events!: any[];











  constructor(private storageService: StorageService,private authService:AuthService) {}

  ngOnInit(): void {


    this.events = [
      {
        status: 'Enregistrement de vos informations personnelles',
        Icon: PrimeIcons.SORT_DOWN,
        color: '#9C27B0',
      },
      {
        status: 'Enregistement de votre cv',
        Icon: PrimeIcons.SORT_DOWN,
        color: '#673AB7',
      },
      { status: 'Renseignement de vos diverses compétences' ,
      Icon: PrimeIcons.SORT_DOWN,
      color: "#FF9800"},
      { status: 'Finalisation de votre dossier de conadidature',
      Icon: PrimeIcons.SORT_DOWN,
      color: "#607D8B" },
    ];
  }



  clean() {
    this.storageService.remove();
  }
}
