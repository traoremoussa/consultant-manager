import { ConsultantService } from './../../services/consultant.service';
import { Country } from '@angular-material-extensions/select-country';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable, tap } from 'rxjs';
import { Consultant } from '../../models/consultant-model';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss']
})
export class InscriptionComponent implements OnInit, OnDestroy{
//je vais faire un seul group de form
// et les autres form control

mainForm!: FormGroup;

consultant$!:Observable<Consultant>;


  defaultValue: Country = {
    name: 'France',
    alpha2Code: 'FR',
    alpha3Code: 'FRA',
    numericCode: '250',
    callingCode: '+33'
  };
  constructor(private formBuilder: FormBuilder,
    private consultantService:ConsultantService,
    private storageService: StorageService
    ) { }


  ngOnInit(): void {
    this.initMainForm();
    this.consultant();
    this.mainForm.setValue(this.consultant$.subscribe(console.log));
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

  private consultant():void{
    let idConsultant= this.storageService.getUser().id;

    this.consultant$=this.consultantService.getConsultant(idConsultant )
    .pipe(tap(()=> console.log("charger")));
  }

  //TODO usubscribtion

  ngOnDestroy(): void {
    //this.consultant$.unsubscribe();
  }
}
