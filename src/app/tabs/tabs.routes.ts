import { Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

export const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'inicio',
        loadComponent: () => import('../pages/inicio/inicio.page').then((m) => m.InicioPage),
      },
      {
        path: 'explorar',
        loadComponent: () => import('../pages/explorar/explorar.page').then((m) => m.ExplorarPage),
      },
      {
        path: 'publicar',
        loadComponent: () => import('../pages/publicar/publicar.page').then((m) => m.PublicarPage),
      },
      {
        path: 'chats',
        loadComponent: () => import('../pages/chats/chats.page').then((m) => m.ChatsPage),
      },
      {
        path: 'perfil',
        loadComponent: () => import('../pages/perfil/perfil.page').then((m) => m.PerfilPage),
      },
      {
        path: 'prenda/:id',
        loadComponent: () => import('../pages/detalle-prenda/detalle-prenda.page').then((m) => m.DetallePrendaPage),
      },
      {
        path: 'editar-perfil',
        loadComponent: () => import('../pages/editar-perfil/editar-perfil.page').then((m) => m.EditarPerfilPage),
      },
      {
        path: 'perfil-usuario/:id',
        loadComponent: () => import('../pages/perfil-usuario/perfil-usuario.page').then((m) => m.PerfilUsuarioPage),
      },
      {
        path: 'chat-detalle/:id',
        loadComponent: () => import('../pages/chat-detalle/chat-detalle.page').then((m) => m.ChatDetallePage),
      },
      {
        path: 'solicitar-intercambio/:id',
        loadComponent: () =>
          import('../pages/solicitar-intercambio/solicitar-intercambio.page').then((m) => m.SolicitarIntercambioPage),
      },
      {
        path: 'solicitar-prestamo/:id',
        loadComponent: () =>
          import('../pages/solicitar-prestamo/solicitar-prestamo.page').then((m) => m.SolicitarPrestamoPage),
      },
      {
        path: '',
        redirectTo: '/tabs/inicio',
        pathMatch: 'full',
      },
    ],
  },
  {
    path: '',
    redirectTo: '/tabs/inicio',
    pathMatch: 'full',
  },
];
