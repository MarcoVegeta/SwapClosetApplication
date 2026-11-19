import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { IonContent, IonItem, IonIcon, IonInput, IonButton } from '@ionic/angular/standalone';
import { Prenda, SwapService } from '../../services/swap.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.page.html',
  styleUrls: ['./inicio.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, IonContent, IonItem, IonIcon, IonInput, IonButton],
})
export class InicioPage {
  busqueda = '';

  constructor(public swapService: SwapService) {}

  get prendasCerca(): Prenda[] {
    return this.swapService.prendas
      .filter((p) => p.propietarioId !== this.swapService.usuarioActual.id)
      .filter((p) => p.titulo.toLowerCase().includes(this.busqueda.trim().toLowerCase()))
      .slice(0, 4);
  }
}
