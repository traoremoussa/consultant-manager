import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/features/services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  currentUser: any;
  dec_conne!: string;

  constructor(private storageService: StorageService, private route: Router) {}

  ngOnInit(): void {
    this.currentUser = this.storageService.getUser();
    if (this.currentUser) {
      this.dec_conne = 'deconecter';
    } else {
      this.dec_conne = 'connecter';
    }
  }
  dec_con() {
    if (this.currentUser) {
      this.storageService.remove();
      this.dec_conne = 'connecter';

    }
    this.route.navigateByUrl('/features/login');
  }
}
