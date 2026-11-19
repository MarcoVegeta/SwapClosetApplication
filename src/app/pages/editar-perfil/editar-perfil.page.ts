import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
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
import { pencil } from 'ionicons/icons';

import { SwapService } from '../../services/swap.service';

@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.page.html',
  styleUrls: ['./editar-perfil.page.scss'],
  standalone: true,
  imports: [FormsModule, IonHeader, IonToolbar, IonButtons, IonBackButton, IonTitle, IonContent, IonIcon, IonToast],
})
export class EditarPerfilPage implements OnInit {
  private swapService = inject(SwapService);
  private router = inject(Router);

  nombre = '';
  ubicacion = '';
  avatar = '';
  estilosSeleccionados: string[] = [];
  tallasSeleccionadas: string[] = [];
  toastMensaje = '';
  toastAbierto = false;

  opcionesEstilos = ['Casual', 'Vintage', 'Streetwear', 'Deportivo', 'Básicos', 'Formal'];

  opcionesTallas = ['XS', 'S', 'M', 'L', '36', '38', '40'];

  constructor() {
    addIcons({ pencil });
  }

  ngOnInit(): void {
    const actual = this.swapService.usuarioActual;
    this.nombre = actual.nombre;
    this.ubicacion = actual.ciudad;
    this.avatar = actual.avatar;
    this.estilosSeleccionados = [...actual.estilos];
    this.tallasSeleccionadas = actual.tallas
      .split(' ')
      .map((t) => t.trim())
      .filter((t) => t.length > 0);
  }

  cambiarFoto(): void {
    this.toastMensaje = 'Selector de foto disponible próximamente';
    this.toastAbierto = true;
  }

  toggleEstilo(estilo: string): void {
    if (this.estilosSeleccionados.includes(estilo)) {
      this.estilosSeleccionados = this.estilosSeleccionados.filter((e) => e !== estilo);
    } else {
      this.estilosSeleccionados = [...this.estilosSeleccionados, estilo];
    }
  }

  toggleTalla(talla: string): void {
    if (this.tallasSeleccionadas.includes(talla)) {
      this.tallasSeleccionadas = this.tallasSeleccionadas.filter((t) => t !== talla);
    } else {
      this.tallasSeleccionadas = [...this.tallasSeleccionadas, talla];
    }
  }

  guardarCambios(): void {
    if (!this.nombre.trim() || !this.ubicacion.trim()) {
      return;
    }

    this.swapService.usuarioActual.nombre = this.nombre.trim();
    this.swapService.usuarioActual.ciudad = this.ubicacion.trim();
    this.swapService.usuarioActual.estilos = [...this.estilosSeleccionados];
    this.swapService.usuarioActual.tallas = this.tallasSeleccionadas.join(' ');

    this.toastMensaje = 'Cambios guardados correctamente';
    this.toastAbierto = true;

    setTimeout(() => {
      void this.router.navigate(['/tabs/perfil']);
    }, 800);
  }
}
