import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { IonHeader, IonToolbar, IonTitle, IonContent, IonItem, IonInput, IonIcon } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { searchOutline, checkmarkDoneOutline, chatbubblesOutline } from 'ionicons/icons';

import { ChatPreview, SwapService } from '../../services/swap.service';

@Component({
  selector: 'app-chats',
  templateUrl: './chats.page.html',
  styleUrls: ['./chats.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonHeader, IonToolbar, IonTitle, IonContent, IonItem, IonInput, IonIcon],
})
export class ChatsPage {
  busqueda = '';
  filtroActivo: 'todos' | 'no-leidos' = 'todos';

  constructor(
    private router: Router,
    public swapService: SwapService,
  ) {
    addIcons({
      searchOutline,
      checkmarkDoneOutline,
      chatbubblesOutline,
    });
  }

  get totalNoLeidos(): number {
    return this.swapService.chats.reduce((acc, c) => acc + c.noLeidos, 0);
  }

  get chatsFiltrados(): ChatPreview[] {
    const texto = this.busqueda.trim().toLowerCase();

    return this.swapService.chats.filter((chat) => {
      if (this.filtroActivo === 'no-leidos' && chat.noLeidos === 0) {
        return false;
      }
      if (!texto) return true;
      return chat.nombre.toLowerCase().includes(texto) || chat.ultimoMensaje.toLowerCase().includes(texto);
    });
  }

  estaOnline(userId: string): boolean {
    const usuario = this.swapService.usuarios.find((u) => u.id === userId);
    return !!usuario?.online;
  }

  abrirChat(chat: ChatPreview): void {
    chat.noLeidos = 0;
    chat.leido = true;
    void this.router.navigate(['/tabs/chat-detalle', chat.userId]);
  }
}
