import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = localStorage.getItem('jwttoken');
        console.log(req)
        console.log(token);
        if(token === undefined || token === null || req.url.includes("/client")) next.handle(req);
        
        const reqClone = req.clone({
            headers: req.headers.set('AuthorizationToken', `Bearer ${token}`)
        });
        return next.handle(reqClone);
    }
}