import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationRequest } from '../../models/authentication-request-model';
import { AuthService } from '../../services/auth.service';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  mainForm!: FormGroup;

 authenticationRequest!: AuthenticationRequest;


 isLoggedIn = false;
 isLoginFailed = false;
 errorMessage = '';

  constructor(private formBuilder: FormBuilder,
    private authService:AuthService, private router: Router,
    private storageService: StorageService) { }

  ngOnInit(): void {
    this.mainForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });

    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;

    }
  }

  onSubmit(){
    this.authenticationRequest = this.mainForm.value;
    //alert(this.authenticationRequest);
    this.authService.login(this.authenticationRequest).subscribe({
      next: data => {
        //le retour
        this.storageService.saveUser(data);
      //  sessionStorage.setItem('authenticationToken',data.authenticationToken);
        //console.log("-------------------------------"+this.storageService.getUser())
        this.isLoginFailed = false;
        this.isLoggedIn = true;

        this.reloadPage();
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    });
}
reloadPage(): void {

  this.router.navigateByUrl('/');
  //window.location.reload();
}
}
