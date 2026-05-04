import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { finalize } from "rxjs";
import { LoaderService } from "../services/LoaderService";

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {

  constructor(private loader: LoaderService) {}

intercept(req: HttpRequest<any>, next: HttpHandler) {

  console.log('Loader START');
  this.loader.show();

  return next.handle(req).pipe(
    finalize(() => {
      console.log('Loader STOP');
      this.loader.hide();
    })
  );
}
}
