import {Injectable} from '@angular/core';
import {AbstractControl, FormGroup, ValidatorFn} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class HelperService {
}


export function matchingFieldsValidator(controlName: string, matchingControlName: string): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const formGroup = control as FormGroup;
    const field = formGroup.controls[controlName];
    const matchingField = formGroup.controls[matchingControlName];

    if (!field || !matchingField) {
      return null;
    }

    if (matchingField.errors && !matchingField.errors['matchingFields']) {
      return null;
    }

    if (field.value !== matchingField.value) {
      return {matchingFields: true};
    } else {
      return null;
    }
  };
}

export function extractRoleFromJson(jsonString: string | null): string | null {
  if (jsonString === null) {
    return null;
  }
  try {
    const obj = JSON.parse(jsonString);
    return obj.role || null;
  } catch (error) {
    return null;
  }
}
