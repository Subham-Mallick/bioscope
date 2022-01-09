import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { EMPTY, Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem('jwttoken');
        console.log(req)
        console.log("token ->" ,token);
        console.log("Request Client ->", req.url.includes("/client"))
        console.log("REquest Login -> ", req.url.includes("/authenticate"))
        if(token === undefined || token === null || req.url.includes("/client") || req.url.includes("/authenticate") ) return next.handle(req);
        
        // const reqClone = req.clone({
        //     headers: req.headers.set('AuthorizationToken', `Bearer ${token}`)
        // });
        if(localStorage.getItem("authResult") === "true")   return next.handle(req);

        return EMPTY;
    }
}