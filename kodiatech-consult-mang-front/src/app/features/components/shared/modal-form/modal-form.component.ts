import { Component, Inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-modal-form',
  templateUrl: './modal-form.component.html',
  styleUrls: ['./modal-form.component.scss'],
})
export class ModalFormComponent implements OnInit {
  //Titre
  //Forme groupe
  dlgFormGroup!: FormGroup;
  //Service consutant
  //en fonction des objets il faut ouvert Model????
  actionBtn = 'Modifier';

  constructor(
    private formbuilder: FormBuilder,
    public dialogRef: MatDialogRef<ModalFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit(): void {
    this.initMainForm();
    if (this.data) {
      this.dlgFormGroup.patchValue(this.data);
    }
  }

  initMainForm(): void {
    this.dlgFormGroup = this.formbuilder.group({
      competence: new FormControl('', Validators.required),
      description: new FormControl(''),
    });
  }

  validedForm() {
    this.updateTodo();
  }
  public updateTodo() {
    //RECUPERER DONNEE LORS DE MODIFF
    console.info(
      '---------> value modifiee :' + JSON.stringify(this.dlgFormGroup.value)
    );
    this.dlgFormGroup.reset();
    this.dialogRef.close('save');
  }
}
