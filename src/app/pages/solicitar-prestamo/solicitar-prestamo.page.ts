import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {
  IonBackButton,
  IonButtons,
  IonContent,
  IonFooter,
  IonHeader,
  IonIcon,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { calendarOutline, checkmark, shieldCheckmarkOutline, timeOutline } from 'ionicons/icons';

interface PrendaResumen {
  id: number;
  titulo: string;
  talla: string;
  estado: string;
  imagen: string;
  propietario: string;
  propietarioId: string;
}

@Component({
  selector: 'app-solicitar-prestamo',
  templateUrl: './solicitar-prestamo.page.html',
  styleUrls: ['./solicitar-prestamo.page.scss'],
  standalone: true,
  imports: [FormsModule, IonHeader, IonToolbar, IonButtons, IonBackButton, IonTitle, IonContent, IonFooter, IonIcon],
})
export class SolicitarPrestamoPage implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  catalogo: PrendaResumen[] = [
    {
      id: 1,
      titulo: 'CAMISETA MÁLAGA CF',
      talla: 'M',
      estado: 'Buen estado',
      imagen: 'assets/camiseta-malaga.png',
      propietario: 'Nuria',
      propietarioId: 'nuria',
    },
    {
      id: 2,
      titulo: 'CAMISETA REAL BETIS',
      talla: 'XL',
      estado: 'Buen estado',
      imagen: 'assets/camiseta-betis.png',
      propietario: 'Lucía',
      propietarioId: 'lucia',
    },
    {
      id: 3,
      titulo: 'CAMISETA REAL MADRID',
      talla: 'S',
      estado: 'Buen estado',
      imagen: 'assets/camiseta-madrid.png',
      propietario: 'Jason',
      propietarioId: 'jason',
    },
    {
      id: 4,
      titulo: 'CAMISETA FC BARCELONA',
      talla: 'L',
      estado: 'Buen estado',
      imagen: 'assets/camiseta-barca.png',
      propietario: 'Katie',
      propietarioId: 'katie',
    },
  ];

  prendaSolicitada: PrendaResumen = this.catalogo[0];

  opcionesDuracion: string[] = ['3 días', '7 días', '14 días', '30 días'];
  duracionSeleccionada = '7 días';
  fechaInicio = new Date().toISOString().split('T')[0];
  mensaje = '';
  aceptaCondiciones = true;
  enviado = false;

  constructor() {
    addIcons({ calendarOutline, checkmark, timeOutline, shieldCheckmarkOutline });
  }

  ngOnInit(): void {
    const idParam = Number(this.route.snapshot.paramMap.get('id'));
    const encontrada = this.catalogo.find((p) => p.id === idParam);
    if (encontrada) {
      this.prendaSolicitada = encontrada;
    }
  }

  seleccionarDuracion(duracion: string): void {
    this.duracionSeleccionada = duracion;
  }

  enviarSolicitud(): void {
    if (!this.duracionSeleccionada || !this.fechaInicio || !this.aceptaCondiciones) {
      return;
    }
    this.enviado = true;
  }

  aceptar(): void {
    void this.router.navigate(['/tabs/inicio']);
  }
}
