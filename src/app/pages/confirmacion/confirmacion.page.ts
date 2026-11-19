import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { IonContent, IonIcon } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import {
  checkmarkCircle,
  chatbubblesOutline,
  homeOutline,
  repeatOutline,
  calendarOutline,
  shirtOutline,
} from 'ionicons/icons';

@Component({
  selector: 'app-confirmacion',
  templateUrl: './confirmacion.page.html',
  styleUrls: ['./confirmacion.page.scss'],
  standalone: true,
  imports: [CommonModule, RouterModule, IonContent, IonIcon],
})
export class ConfirmacionPage implements OnInit {
  tipo = 'intercambio';
  prenda = '';
  usuario = '';
  dias = '';

  constructor(private route: ActivatedRoute) {
    addIcons({
      checkmarkCircle,
      chatbubblesOutline,
      homeOutline,
      repeatOutline,
      calendarOutline,
      shirtOutline,
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.tipo = params['tipo'] || 'intercambio';
      this.prenda = params['prenda'] || 'Prenda seleccionada';
      this.usuario = params['usuario'] || 'el propietario';
      this.dias = params['dias'] || '';
    });
  }

  get tituloConfirmacion(): string {
    if (this.tipo === 'prestamo') {
      return '¡Solicitud de préstamo enviada!';
    }
    if (this.tipo === 'publicacion') {
      return '¡Prenda publicada!';
    }
    return '¡Propuesta de intercambio enviada!';
  }

  get iconoTipo(): string {
    if (this.tipo === 'prestamo') return 'calendar-outline';
    if (this.tipo === 'publicacion') return 'shirt-outline';
    return 'repeat-outline';
  }

  get etiquetaTipo(): string {
    if (this.tipo === 'prestamo') return 'Préstamo temporal';
    if (this.tipo === 'publicacion') return 'Nueva prenda en armario';
    return 'Intercambio de prendas';
  }
}
