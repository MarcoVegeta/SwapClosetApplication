import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.routes').then((m) => m.routes),
  },
  {
    path: 'prenda/:id',
    loadComponent: () => import('./pages/detalle-prenda/detalle-prenda.page').then((m) => m.DetallePrendaPage),
  },
];
