import { Injectable } from '@angular/core';

export interface Usuario {
  id: string;
  nombre: string;
  ciudad: string;
  avatar: string;
  valoracion: string;
  intercambios: number;
  estilos: string[];
  tallas: string;
  online?: boolean;
}

export interface Prenda {
  id: string;
  titulo: string;
  talla: string;
  estado: string;
  categoria: string;
  descripcion: string;
  imagen: string;
  propietarioId: string;
}

export interface ChatPreview {
  userId: string;
  nombre: string;
  avatar: string;
  hora: string;
  ultimoMensaje: string;
  noLeidos: number;
  leido: boolean;
}

@Injectable({
  providedIn: 'root',
})
export class SwapService {
  usuarioActual: Usuario = {
    id: 'mariana',
    nombre: 'Mariana',
    ciudad: 'Sevilla',
    avatar: 'assets/img/avatar-mariana.jpg',
    valoracion: '3,9',
    intercambios: 4,
    estilos: ['Casual', 'Vintage', 'Streetwear'],
    tallas: 'S M 38',
  };

  usuarios: Usuario[] = [
    {
      id: 'nuria',
      nombre: 'Nuria',
      ciudad: 'Málaga',
      avatar: 'assets/img/avatar-nuria.jpg',
      valoracion: '4,8',
      intercambios: 12,
      estilos: ['Casual', 'Vintage', 'Streetwear'],
      tallas: 'S M 38',
      online: true,
    },
    {
      id: 'lucia',
      nombre: 'Lucía',
      ciudad: 'Málaga',
      avatar: 'assets/img/avatar-lucia.jpg',
      valoracion: '4,8',
      intercambios: 12,
      estilos: ['Casual', 'Vintage', 'Streetwear'],
      tallas: 'S M 38',
      online: true,
    },
    {
      id: 'jason',
      nombre: 'Jason',
      ciudad: 'Málaga',
      avatar: 'assets/img/avatar-jason.jpg',
      valoracion: '4,8',
      intercambios: 12,
      estilos: ['Casual', 'Vintage', 'Streetwear'],
      tallas: 'S M 38',
      online: true,
    },
    {
      id: 'katie',
      nombre: 'Katie',
      ciudad: 'Málaga',
      avatar: 'assets/img/avatar-katie.jpg',
      valoracion: '4,8',
      intercambios: 12,
      estilos: ['Casual', 'Vintage', 'Streetwear'],
      tallas: 'S M 38',
      online: true,
    },
  ];

  prendas: Prenda[] = [
    {
      id: '1',
      titulo: 'Camiseta Málaga CF',
      talla: 'M',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Primera equipación de la temporada 2026/27 del Málaga CF, completamente nueva sin usar.',
      imagen: 'assets/img/camiseta-malaga.jpg',
      propietarioId: 'nuria',
    },
    {
      id: '2',
      titulo: 'Camiseta Real Betis',
      talla: 'XL',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Primera equipación del Real Betis Balompié en perfecto estado.',
      imagen: 'assets/img/camiseta-betis.jpg',
      propietarioId: 'lucia',
    },
    {
      id: '3',
      titulo: 'Camiseta Real Madrid',
      talla: 'S',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Camiseta oficial Real Madrid talla S, muy poco uso.',
      imagen: 'assets/img/camiseta-madrid.jpg',
      propietarioId: 'jason',
    },
    {
      id: '4',
      titulo: 'Camiseta FC Barcelona',
      talla: 'L',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Camiseta oficial FC Barcelona talla L en buen estado.',
      imagen: 'assets/img/camiseta-barca.jpg',
      propietarioId: 'katie',
    },
    {
      id: '5',
      titulo: 'Camiseta Inter',
      talla: 'M',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Camiseta del Inter de Milán.',
      imagen: 'assets/img/camiseta-inter.jpg',
      propietarioId: 'mariana',
    },
    {
      id: '6',
      titulo: 'Camiseta Real Betis 2',
      talla: 'M',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Segunda equipación verde del Real Betis talla M.',
      imagen: 'assets/img/camiseta-betis2.jpg',
      propietarioId: 'mariana',
    },
    {
      id: '7',
      titulo: 'Camiseta Málaga CF 2',
      talla: 'M',
      estado: 'Buen estado',
      categoria: 'Camisetas',
      descripcion: 'Equipación negra y rosa del Málaga CF en buen estado.',
      imagen: 'assets/img/camiseta-malaga2.png',
      propietarioId: 'nuria',
    },
  ];

  chats: ChatPreview[] = [
    {
      userId: 'nuria',
      nombre: 'Nuria',
      avatar: 'assets/img/avatar-nuria.jpg',
      hora: '11:15',
      ultimoMensaje: '¡Holi! ¿Quieres la camiseta?',
      noLeidos: 0,
      leido: true,
    },
    {
      userId: 'lucia',
      nombre: 'Lucía',
      avatar: 'assets/img/avatar-lucia.jpg',
      hora: '13:00',
      ultimoMensaje: 'Hola, ¿sigue la camiseta disponible?',
      noLeidos: 1,
      leido: false,
    },
    {
      userId: 'jason',
      nombre: 'Jason',
      avatar: 'assets/img/avatar-jason.jpg',
      hora: '11:15',
      ultimoMensaje: '¡Holi! ¿Quieres la camiseta?',
      noLeidos: 0,
      leido: true,
    },
    {
      userId: 'katie',
      nombre: 'Katie',
      avatar: 'assets/img/avatar-katie.jpg',
      hora: '11:15',
      ultimoMensaje: '¡Holi! ¿Quieres la camiseta?',
      noLeidos: 0,
      leido: true,
    },
  ];

  getPrendaById(id: string): Prenda | undefined {
    return this.prendas.find((p) => p.id === id);
  }

  getUsuarioById(id: string): Usuario {
    return this.usuarios.find((u) => u.id === id) || this.usuarioActual;
  }

  getPrendasDeUsuario(userId: string): Prenda[] {
    return this.prendas.filter((p) => p.propietarioId === userId);
  }
}
