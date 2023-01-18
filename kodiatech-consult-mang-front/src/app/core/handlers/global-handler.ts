import { ErrorHandler, Injectable } from "@angular/core";

//TODO import { NGXLogger } from 'ngx-logger';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {




  handleError(error: any): void {
    throw new Error("Method not implemented.");
  }
}
