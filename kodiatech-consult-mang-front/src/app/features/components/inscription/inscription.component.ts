import { Consultant } from './../../models/consultant-model';
import { ConsultantService } from './../../services/consultant.service';
import { Country } from '@angular-material-extensions/select-country';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {  Subscription, tap } from 'rxjs';

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
adresseFormGroup!: FormGroup;
consultant!:Consultant;
ConsultantSub!:Subscription; //pour destruct a la fin de subscription (possible Async dans html )

//TODO dehors
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
    this.consultantCharger();

    //this.mainForm.patchValue(this.consultant);
  }


  initMainForm():void{
    this.adresseFormGroup=this.formBuilder.group({
      adresse:new FormControl('',Validators.required),
      complementAdresse :new FormControl(''),
      codePostal : new FormControl(''),
      ville : new FormControl(''),

    });
    this.mainForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: new FormControl('',[Validators.required, Validators.email]),
      telephone : new FormControl(''),
      adresse:this.adresseFormGroup
      //-------



    });



  }

  onSubmit(){
  // TODO: Use EventEmitter with form value
  console.warn(this.mainForm.value);
  alert(` Form Valide: ${this.mainForm.value}`)
  }

  private consultantCharger():void{
    let idConsultant= this.storageService.getUser().id;

    this.ConsultantSub=this.consultantService.getConsultant(idConsultant )
    .pipe(tap(()=> console.log("charger")))
    .subscribe((reponse:Consultant)=>
    //data,error, complete
    {
this.consultant=reponse
console.log("REPONSE"+JSON.stringify(this.consultant));
console.log("INIT-REPONSE"+JSON.stringify(this.consultant));

this.adresseFormGroup.patchValue(this.consultant.adresse);
this.mainForm.patchValue(this.consultant);
     }

    );

    /*on pipe si j'ai envi d'applique une operation sur la donne
    map : pour transformer
    take
    delay
    .....
    */
  }

  //TODO usubscribtion

  ngOnDestroy(): void {
    if(this.ConsultantSub!=null)
    this.ConsultantSub.unsubscribe();
  }
}
