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
import { checkmark, checkmarkCircle, swapHorizontalOutline } from 'ionicons/icons';

interface PrendaResumen {
  id: number;
  titulo: string;
  talla: string;
  estado: string;
  imagen: string;
  propietario: string;
  propietarioId: string;
}

interface MiPrenda {
  id: number;
  titulo: string;
  talla: string;
  estado: string;
  imagen: string;
}

@Component({
  selector: 'app-solicitar-intercambio',
  templateUrl: './solicitar-intercambio.page.html',
  styleUrls: ['./solicitar-intercambio.page.scss'],
  standalone: true,
  imports: [FormsModule, IonHeader, IonToolbar, IonButtons, IonBackButton, IonTitle, IonContent, IonFooter, IonIcon],
})
export class SolicitarIntercambioPage implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  catalogo: PrendaResumen[] = [
    {
      id: 1,
      titulo: 'CAMISETA MÁLAGA CF',
      talla: 'M',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-malaga.jpg',
      propietario: 'Nuria',
      propietarioId: 'nuria',
    },
    {
      id: 2,
      titulo: 'CAMISETA REAL BETIS',
      talla: 'XL',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-betis.jpg',
      propietario: 'Lucía',
      propietarioId: 'lucia',
    },
    {
      id: 3,
      titulo: 'CAMISETA REAL MADRID',
      talla: 'S',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-madrid.jpg',
      propietario: 'Jason',
      propietarioId: 'jason',
    },
    {
      id: 4,
      titulo: 'CAMISETA FC BARCELONA',
      talla: 'L',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-barca.jpg',
      propietario: 'Katie',
      propietarioId: 'katie',
    },
  ];

  misPrendas: MiPrenda[] = [
    {
      id: 101,
      titulo: 'CAMISETA INTER',
      talla: 'M',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-inter.jpg',
    },
    {
      id: 102,
      titulo: 'CAMISETA REAL BETIS 2',
      talla: 'M',
      estado: 'Buen estado',
      imagen: 'assets/img/camiseta-betis2.jpg',
    },
  ];

  prendaSolicitada: PrendaResumen = this.catalogo[0];
  prendaSeleccionadaId: number | null = null;
  mensaje = '';
  enviado = false;

  constructor() {
    addIcons({ checkmark, checkmarkCircle, swapHorizontalOutline });
  }

  ngOnInit(): void {
    const idParam = Number(this.route.snapshot.paramMap.get('id'));
    const encontrada = this.catalogo.find((p) => p.id === idParam);
    if (encontrada) {
      this.prendaSolicitada = encontrada;
    }
  }

  seleccionarPrenda(id: number): void {
    this.prendaSeleccionadaId = this.prendaSeleccionadaId === id ? null : id;
  }

  enviarSolicitud(): void {
    if (!this.prendaSeleccionadaId) {
      return;
    }
    this.enviado = true;
  }

  aceptar(): void {
    void this.router.navigate(['/tabs/inicio']);
  }
}
