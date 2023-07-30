import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/features/services/storage.service';
import { AuthService } from 'src/app/features/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  currentUser!: any;
  dec_conne!: string;

  constructor(private authService: AuthService, private route: Router) {}

  ngOnInit(): void {
    //ecoute action de route
    this.route.events.subscribe((val: any) => {
      //
      if (val.url) {
        this.currentUser = this.authService.getUser();
        console.log('user------------------>' + this.currentUser?.nom);
        this.dec_conne = this.isEmpty(this.currentUser)
          ? 'connecter'
          : 'deconecter';
      }
    });
  }

  dec_con() {
    if (!this.isEmpty(this.currentUser)) {
      this.authService.logout();
      this.dec_conne = 'connecter';
    }
    // this.route.navigateByUrl('/features/login');
    window.location.reload();
  }

  private isEmpty(obj: {}): boolean {
    return !obj || Object.keys(obj).length === 0;
  }
}
