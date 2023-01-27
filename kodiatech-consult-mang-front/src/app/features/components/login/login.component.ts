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



 isLoginFailed = false;
 errorMessage = '';

  constructor(private formBuilder: FormBuilder,
    private authService:AuthService, private router: Router) { }

  ngOnInit(): void {
    this.mainForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit(){
    this.authenticationRequest = this.mainForm.value;
    this.authService.login(this.authenticationRequest).subscribe({
      next: data => {

        this.isLoginFailed = false;

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
