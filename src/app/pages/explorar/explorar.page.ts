import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { IonContent, IonItem, IonIcon, IonInput } from '@ionic/angular/standalone';

import { Prenda, SwapService } from '../../services/swap.service';

@Component({
  selector: 'app-explorar',
  templateUrl: './explorar.page.html',
  styleUrls: ['./explorar.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, IonContent, IonItem, IonIcon, IonInput],
})
export class ExplorarPage {
  busqueda = '';
  categoriaSeleccionada = 'Todas';
  tallaSeleccionada = 'Todas';

  categorias: string[] = ['Todas', 'Camisetas', 'Sudaderas', 'Pantalones', 'Chaquetas', 'Accesorios'];

  tallas: string[] = ['Todas', 'XS', 'S', 'M', 'L', 'XL'];

  constructor(public swapService: SwapService) {}

  get prendasFiltradas(): Prenda[] {
    const texto = this.busqueda.trim().toLowerCase();

    return this.swapService.prendas
      .filter((p) => p.propietarioId !== this.swapService.usuarioActual.id)
      .filter((p) => {
        if (!texto) return true;
        return p.titulo.toLowerCase().includes(texto) || p.descripcion.toLowerCase().includes(texto);
      })
      .filter((p) =>
        this.categoriaSeleccionada === 'Todas'
          ? true
          : p.categoria.toLowerCase() === this.categoriaSeleccionada.toLowerCase(),
      )
      .filter((p) =>
        this.tallaSeleccionada === 'Todas' ? true : p.talla.toUpperCase() === this.tallaSeleccionada.toUpperCase(),
      );
  }

  seleccionarCategoria(cat: string): void {
    this.categoriaSeleccionada = cat;
  }

  seleccionarTalla(talla: string): void {
    this.tallaSeleccionada = talla;
  }

  limpiarFiltros(): void {
    this.busqueda = '';
    this.categoriaSeleccionada = 'Todas';
    this.tallaSeleccionada = 'Todas';
  }
}
