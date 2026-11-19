import { Location } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IonButton, IonButtons, IonContent, IonHeader, IonIcon, IonTitle, IonToolbar } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { arrowBackOutline, chatbubbleOutline, star } from 'ionicons/icons';

import { SwapService } from '../../services/swap.service';

interface PrendaPerfil {
  id: number;
  titulo: string;
  talla: string;
  estado: string;
  imagen: string;
}

interface PerfilPublico {
  id: string;
  nombre: string;
  ciudad: string;
  avatar: string;
  valoracion: string;
  intercambios: number;
  prendas: PrendaPerfil[];
  estilos: string[];
  tallas: string[];
}

@Component({
  selector: 'app-perfil-usuario',
  templateUrl: './perfil-usuario.page.html',
  styleUrls: ['./perfil-usuario.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonButtons, IonButton, IonTitle, IonContent, IonIcon],
})
export class PerfilUsuarioPage implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private location = inject(Location);
  private swapService = inject(SwapService);

  private perfilesPorUsuario: Record<string, PerfilPublico> = {
    nuria: {
      id: 'nuria',
      nombre: 'Nuria',
      ciudad: 'Málaga',
      avatar: 'assets/img/avatar-nuria.jpg',
      valoracion: '4,8',
      intercambios: 12,
      prendas: [
        {
          id: 1,
          titulo: 'CAMISETA MÁLAGA CF',
          talla: 'M',
          estado: 'Buen estado',
          imagen: 'assets/img/camiseta-malaga.jpg',
        },
        {
          id: 5,
          titulo: 'CAMISETA MÁLAGA CF 2',
          talla: 'M',
          estado: 'Como nueva',
          imagen: 'assets/img/camiseta-malaga2.jpg',
        },
      ],
      estilos: ['Casual', 'Vintage', 'Streetwear'],
      tallas: ['S', 'M', '38'],
    },
    lucia: {
      id: 'lucia',
      nombre: 'Lucía',
      ciudad: 'Sevilla',
      avatar: 'assets/img/avatar-lucia.jpg',
      valoracion: '4,9',
      intercambios: 18,
      prendas: [
        {
          id: 2,
          titulo: 'CAMISETA REAL BETIS',
          talla: 'XL',
          estado: 'Buen estado',
          imagen: 'assets/img/camiseta-betis.jpg',
        },
        {
          id: 6,
          titulo: 'CAMISETA REAL BETIS 2',
          talla: 'M',
          estado: 'Como nueva',
          imagen: 'assets/img/camiseta-betis2.jpg',
        },
      ],
      estilos: ['Deportivo', 'Casual', 'Urbano'],
      tallas: ['M', 'L', '40'],
    },
    jason: {
      id: 'jason',
      nombre: 'Jason',
      ciudad: 'Madrid',
      avatar: 'assets/img/avatar-jason.jpg',
      valoracion: '4,6',
      intercambios: 7,
      prendas: [
        {
          id: 3,
          titulo: 'CAMISETA REAL MADRID',
          talla: 'S',
          estado: 'Buen estado',
          imagen: 'assets/img/camiseta-madrid.jpg',
        },
        {
          id: 7,
          titulo: 'CAMISETA ENTRENAMIENTO RM',
          talla: 'M',
          estado: 'Buen estado',
          imagen: 'assets/img/camiseta-madrid2.jpg',
        },
      ],
      estilos: ['Streetwear', 'Sport', 'Oversize'],
      tallas: ['S', 'M', '41'],
    },
    katie: {
      id: 'katie',
      nombre: 'Katie',
      ciudad: 'Barcelona',
      avatar: 'assets/img/avatar-katie.jpg',
      valoracion: '5,0',
      intercambios: 21,
      prendas: [
        {
          id: 4,
          titulo: 'CAMISETA FC BARCELONA',
          talla: 'L',
          estado: 'Buen estado',
          imagen: 'assets/img/camiseta-barca.jpg',
        },
        {
          id: 8,
          titulo: 'CAMISETA BARÇA RETRO',
          talla: 'L',
          estado: 'Como nueva',
          imagen: 'assets/img/camiseta-barca2.jpg',
        },
      ],
      estilos: ['Vintage', 'Retro', 'Básicos'],
      tallas: ['L', 'XL', '39'],
    },
  };

  perfil: PerfilPublico = this.perfilesPorUsuario['nuria'];

  constructor() {
    addIcons({ arrowBackOutline, star, chatbubbleOutline });
  }

  ngOnInit(): void {
    const param = (this.route.snapshot.paramMap.get('id') || '').trim();
    const limpio = param ? this.normalizar(param) : 'nuria';

    const usuarioServicio =
      this.swapService.getUsuarioById(limpio) ||
      this.swapService.usuarios.find((u) => this.normalizar(u.id) === limpio || this.normalizar(u.nombre) === limpio);

    const base = this.perfilesPorUsuario[limpio] || this.perfilesPorUsuario['nuria'];

    this.perfil = {
      ...base,
      id: usuarioServicio?.id || base.id,
      nombre: usuarioServicio?.nombre || base.nombre,
      avatar: usuarioServicio?.avatar || base.avatar,
    };
  }

  private normalizar(texto: string): string {
    return texto
      .toLowerCase()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '');
  }

  volver(): void {
    if (window.history.length > 1) {
      this.location.back();
    } else {
      void this.router.navigate(['/tabs/inicio']);
    }
  }

  verPrenda(id: number): void {
    void this.router.navigate(['/tabs/prenda', id]);
  }

  abrirChat(): void {
    void this.router.navigate(['/tabs/chat-detalle', this.perfil.id]);
  }
}
