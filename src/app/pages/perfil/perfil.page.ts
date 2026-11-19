import { Component, inject } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IonContent, IonHeader, IonIcon, IonTitle, IonToast, IonToolbar } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import {
  addOutline,
  createOutline,
  locationOutline,
  repeatOutline,
  shareSocialOutline,
  shirtOutline,
  star,
} from 'ionicons/icons';

import { Prenda, SwapService, Usuario } from '../../services/swap.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
  standalone: true,
  imports: [RouterModule, IonHeader, IonToolbar, IonTitle, IonContent, IonIcon, IonToast],
})
export class PerfilPage {
  public swapService = inject(SwapService);

  tabActiva: 'armario' | 'valoraciones' = 'armario';
  toastAbierto = false;

  resenas = [
    {
      autor: 'Nuria',
      avatar: 'assets/img/avatar-nuria.jpg',
      puntuacion: '5,0',
      fecha: 'Hace 3 días',
      comentario: 'Intercambio súper rápido y la camiseta estaba impecable. ¡Muy recomendable!',
    },
    {
      autor: 'Lucía',
      avatar: 'assets/img/avatar-lucia.jpg',
      puntuacion: '4,8',
      fecha: 'Hace 2 semanas',
      comentario: 'Muy maja y puntual para quedar. Repetiría sin dudarlo.',
    },
  ];

  constructor() {
    addIcons({
      locationOutline,
      star,
      createOutline,
      shareSocialOutline,
      addOutline,
      shirtOutline,
      repeatOutline,
    });
  }

  get usuario(): Usuario {
    return this.swapService.usuarioActual;
  }

  get misPrendas(): Prenda[] {
    return this.swapService.getPrendasDeUsuario(this.usuario.id);
  }

  get listaEstilos(): string[] {
    return this.usuario.estilos;
  }

  get listaTallas(): string[] {
    return this.usuario.tallas
      .split(' ')
      .map((t) => t.trim())
      .filter((t) => t.length > 0);
  }

  compartirPerfil(): void {
    this.toastAbierto = true;
  }
}
