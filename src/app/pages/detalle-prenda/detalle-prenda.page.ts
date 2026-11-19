import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  IonBackButton,
  IonButtons,
  IonContent,
  IonHeader,
  IonIcon,
  IonTitle,
  IonToast,
  IonToolbar,
} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { chatbubbleOutline, star } from 'ionicons/icons';

export interface PrendaDetalle {
  id: number;
  titulo: string;
  talla: string;
  estado: string;
  descripcion: string;
  imagen: string;
  propietario: string;
  ciudad: string;
  avatarPropietario: string;
  valoracion: string;
  intercambios: number;
}

@Component({
  selector: 'app-detalle-prenda',
  templateUrl: './detalle-prenda.page.html',
  styleUrls: ['./detalle-prenda.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonButtons, IonBackButton, IonTitle, IonContent, IonIcon, IonToast],
})
export class DetallePrendaPage implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  toastMensaje = '';
  toastAbierto = false;

  prendas: PrendaDetalle[] = [
    {
      id: 1,
      titulo: 'CAMISETA MÁLAGA CF',
      talla: 'M',
      estado: 'Buen estado',
      descripcion: 'Primera equipación de la temporada 2026/27 del Málaga CF, completamente nueva sin usar.',
      imagen: 'assets/img/camiseta-malaga.jpg',
      propietario: 'Nuria',
      ciudad: 'Málaga',
      avatarPropietario: 'assets/img/avatar-nuria.jpg',
      valoracion: '4,8',
      intercambios: 12,
    },
    {
      id: 2,
      titulo: 'CAMISETA REAL BETIS',
      talla: 'XL',
      estado: 'Buen estado',
      descripcion: 'Primera equipación de la temporada 2026/27 del Real Betis, completamente nueva sin usar.',
      imagen: 'assets/img/camiseta-betis.jpg',
      propietario: 'Lucía',
      ciudad: 'Málaga',
      avatarPropietario: 'assets/img/avatar-lucia.jpg',
      valoracion: '4,8',
      intercambios: 12,
    },
    {
      id: 3,
      titulo: 'CAMISETA REAL MADRID',
      talla: 'S',
      estado: 'Buen estado',
      descripcion: 'Primera equipación de la temporada 2026/27 del Real Madrid, completamente nueva sin usar.',
      imagen: 'assets/img/camiseta-madrid.jpg',
      propietario: 'Jason',
      ciudad: 'Málaga',
      avatarPropietario: 'assets/img/avatar-jason.jpg',
      valoracion: '4,8',
      intercambios: 12,
    },
    {
      id: 4,
      titulo: 'CAMISETA FC BARCELONA',
      talla: 'L',
      estado: 'Buen estado',
      descripcion: 'Primera equipación de la temporada 2026/27 del FC Barcelona, completamente nueva sin usar.',
      imagen: 'assets/img/camiseta-barca.jpg',
      propietario: 'Katie',
      ciudad: 'Málaga',
      avatarPropietario: 'assets/img/avatar-katie.jpg',
      valoracion: '4,8',
      intercambios: 12,
    },
  ];

  prenda: PrendaDetalle = this.prendas[0];

  constructor() {
    addIcons({ chatbubbleOutline, star });
  }

  ngOnInit(): void {
    const idParam = Number(this.route.snapshot.paramMap.get('id'));
    const encontrada = this.prendas.find((item) => item.id === idParam);
    if (encontrada) {
      this.prenda = encontrada;
    }
  }

  private normalizarId(nombre: string): string {
    return nombre
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');
  }

  solicitarIntercambio(): void {
    void this.router.navigate(['/tabs/solicitar-intercambio', this.prenda.id]);
  }

  solicitarPrestamo(): void {
    void this.router.navigate(['/tabs/solicitar-prestamo', this.prenda.id]);
  }

  verPerfilUsuario(): void {
    void this.router.navigate(['/tabs/perfil-usuario', this.normalizarId(this.prenda.propietario)]);
  }

  abrirChat(): void {
    void this.router.navigate(['/tabs/chat-detalle', this.normalizarId(this.prenda.propietario)]);
  }
}
