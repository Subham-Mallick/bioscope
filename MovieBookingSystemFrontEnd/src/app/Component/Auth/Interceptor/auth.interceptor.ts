import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { EMPTY, Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
       
        console.log("Request Client ->", req.url.includes("/client"))
        console.log("REquest Login -> ", req.url.includes("/authenticate"))
        
        if(req.url.includes("/client") || req.url.includes("/authenticate") ) return next.handle(req);
        
      
        if(localStorage.getItem("authResult") === "true")   return next.handle(req);

        return EMPTY;
    }
}