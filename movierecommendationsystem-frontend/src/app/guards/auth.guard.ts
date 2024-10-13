import { inject, Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { catchError, map, of } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  return authService.validateToken().pipe(
    map(response => {
      if(response){
        return true;
      }
      else{
        router.navigate(['/login']);
        return false;
      }
    }),
    catchError(error => {
      router.navigate(['/login']);
      return of(false);
    })
  );
};
