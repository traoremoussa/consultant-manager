import { Country } from '@angular-material-extensions/select-country';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit {
//je vais faire un seul group de form
// et les autres form control

mainForm!: FormGroup;




  defaultValue: Country = {
    name: 'France',
    alpha2Code: 'FR',
    alpha3Code: 'FRA',
    numericCode: '250',
    callingCode: '+33'
  };
  constructor(    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initMainForm();
  }


  initMainForm():void{
    this.mainForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      adresse:new FormControl('',Validators.required),
      email: new FormControl('',[Validators.required, Validators.email]),
      complementAdresse :new FormControl(''),
      codePostal : new FormControl(''),
      ville : new FormControl(''),
      telephone : new FormControl('')
    });
  }

  onSubmit(){
  // TODO: Use EventEmitter with form value
  console.warn(this.mainForm.value);
  }
}
