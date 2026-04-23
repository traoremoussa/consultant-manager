import { Consultant } from './../../models/consultant-model';
import { ConsultantService } from './../../services/consultant.service';

import { Component, OnDestroy, OnInit } from '@angular/core';
import {
  UntypedFormBuilder,
  UntypedFormControl,
  UntypedFormGroup,
  Validators,
} from '@angular/forms';
import { Subscription, tap } from 'rxjs';

import { StorageService } from '../../services/storage.service';

@Component({
    selector: 'app-inscription',
    templateUrl: './inscription.component.html',
    styleUrls: ['./inscription.component.scss'],
    standalone: false
})
export class InscriptionComponent implements OnInit, OnDestroy {
  //je vais faire un seul group de form
  // et les autres form control

  mainForm!: UntypedFormGroup;
  adresseFormGroup!: UntypedFormGroup;
  consultant!: Consultant;
  ConsultantSub!: Subscription; //pour destruct a la fin de subscription (possible Async dans html )

  //TODO dehors
  /*defaultValue: Country = {
    name: 'France',
    alpha2Code: 'FR',
    alpha3Code: 'FRA',
    numericCode: '250',
    callingCode: '+33',
  };*/

  countries = [
    { code: 'FR', name: 'France' },
    { code: 'BE', name: 'Belgique' },
    { code: 'SN', name: 'Sénégal' },
    { code: 'MA', name: 'Maroc' },
  ];

  constructor(
    private formBuilder: UntypedFormBuilder,
    private consultantService: ConsultantService,
    private storageService: StorageService,
  ) {}

  ngOnInit(): void {
    this.initMainForm();
    this.consultantCharger();

    //this.mainForm.patchValue(this.consultant);
  }

  initMainForm(): void {
    this.adresseFormGroup = this.formBuilder.group({
      adresse: new UntypedFormControl('', Validators.required),
      complementAdresse: new UntypedFormControl(''),
      codePostal: new UntypedFormControl(''),
      ville: new UntypedFormControl(''),
    });
    this.mainForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: new UntypedFormControl('', [
        Validators.required,
        Validators.email,
      ]),
      telephone: new UntypedFormControl(''),
      adresse: this.adresseFormGroup,
      //-------
    });
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.warn(this.mainForm.value);
    alert(` Form Valide: ${this.mainForm.value}`);
  }

  private consultantCharger(): void {
    let idConsultant = this.storageService.getUser().id;

    this.ConsultantSub = this.consultantService
      .getConsultant(idConsultant)
      .pipe(tap(() => console.log('charger')))
      .subscribe((reponse: Consultant) =>
        //data,error, complete
        {
          this.consultant = reponse;
          console.log('REPONSE' + JSON.stringify(this.consultant));
          console.log('INIT-REPONSE' + JSON.stringify(this.consultant));

          //alimenter les diffrent value DU FROM GROUP
          this.adresseFormGroup.patchValue(this.consultant.adresse);
          this.mainForm.patchValue(this.consultant);
        },
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
    if (this.ConsultantSub != null) this.ConsultantSub.unsubscribe();
  }
}
