import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonFooter,
  IonIcon,
  IonInput,
  IonTextarea,
} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { cameraOutline, closeCircle, sparklesOutline } from 'ionicons/icons';

import { Prenda, SwapService } from '../../services/swap.service';

@Component({
  selector: 'app-publicar',
  templateUrl: './publicar.page.html',
  styleUrls: ['./publicar.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonFooter,
    IonIcon,
    IonInput,
    IonTextarea,
  ],
})
export class PublicarPage {
  titulo = '';
  categoria = 'Camisetas';
  talla = 'M';
  estado = 'Buen estado';
  descripcion = '';
  imagenPreview = '';

  categorias: string[] = ['Camisetas', 'Sudaderas', 'Pantalones', 'Chaquetas', 'Accesorios'];

  tallas: string[] = ['XS', 'S', 'M', 'L', 'XL'];

  estados: string[] = ['Nuevo con etiqueta', 'Como nuevo', 'Buen estado', 'Usado'];

  constructor(
    private router: Router,
    public swapService: SwapService,
  ) {
    addIcons({
      cameraOutline,
      closeCircle,
      sparklesOutline,
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files || input.files.length === 0) return;

    const file = input.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.imagenPreview = reader.result as string;
    };
    reader.readAsDataURL(file);
  }

  usarFotoMuestra(): void {
    this.imagenPreview = 'assets/img/camiseta-inter.png';
  }

  quitarFoto(event: Event): void {
    event.stopPropagation();
    this.imagenPreview = '';
  }

  get formularioValido(): boolean {
    return (
      this.titulo.trim().length >= 3 && this.categoria.length > 0 && this.talla.length > 0 && this.estado.length > 0
    );
  }

  publicarPrenda(): void {
    if (!this.formularioValido) return;

    const nuevaPrenda: Prenda = {
      id: Date.now().toString(),
      titulo: this.titulo.trim(),
      talla: this.talla,
      estado: this.estado,
      categoria: this.categoria,
      descripcion:
        this.descripcion.trim() || `${this.titulo.trim()} en talla ${this.talla} (${this.estado.toLowerCase()}).`,
      imagen: this.imagenPreview || 'assets/img/camiseta-betis2.png',
      propietarioId: this.swapService.usuarioActual.id,
    };

    this.swapService.prendas.unshift(nuevaPrenda);

    const tituloPublicado = nuevaPrenda.titulo;
    this.limpiarFormulario();

    this.router.navigate(['/tabs/confirmacion'], {
      queryParams: {
        tipo: 'publicacion',
        prenda: tituloPublicado,
      },
    });
  }

  private limpiarFormulario(): void {
    this.titulo = '';
    this.categoria = 'Camisetas';
    this.talla = 'M';
    this.estado = 'Buen estado';
    this.descripcion = '';
    this.imagenPreview = '';
  }
}
