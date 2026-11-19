import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { IonHeader, IonToolbar, IonContent, IonFooter, IonIcon, NavController } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { arrowBackOutline, send, checkmarkDoneOutline, personOutline } from 'ionicons/icons';

import { SwapService, Usuario } from '../../services/swap.service';

export interface MensajeChat {
  id: string;
  texto: string;
  hora: string;
  esMio: boolean;
  leido?: boolean;
}

@Component({
  selector: 'app-chat-detalle',
  templateUrl: './chat-detalle.page.html',
  styleUrls: ['./chat-detalle.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, IonHeader, IonToolbar, IonContent, IonFooter, IonIcon],
})
export class ChatDetallePage implements OnInit {
  @ViewChild(IonContent) content?: IonContent;

  contacto?: Usuario;
  mensajes: MensajeChat[] = [];
  nuevoMensaje = '';

  respuestasRapidas: string[] = ['¿Sigue disponible?', '¿Te interesa un intercambio?', '¿Dónde te viene bien quedar?'];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private navCtrl: NavController,
    public swapService: SwapService,
  ) {
    addIcons({
      arrowBackOutline,
      send,
      checkmarkDoneOutline,
      personOutline,
    });
  }

  ngOnInit(): void {
    const userId = this.route.snapshot.paramMap.get('id');
    if (userId) {
      this.contacto = this.swapService.getUsuarioById(userId);
      this.cargarHistorialInicial(userId);
    }
  }

  ionViewDidEnter(): void {
    this.bajarAlFinal();
  }

  verPerfilContacto(): void {
    if (!this.contacto) return;
    void this.router.navigate(['/tabs/perfil-usuario', this.contacto.id]);
  }

  private cargarHistorialInicial(userId: string): void {
    const chatExistente = this.swapService.chats.find((c) => c.userId === userId);

    if (userId === 'lucia') {
      this.mensajes = [
        {
          id: '1',
          texto: '¡Hola Mariana! He visto tu armario y me encanta.',
          hora: '12:58',
          esMio: false,
        },
        {
          id: '2',
          texto: chatExistente?.ultimoMensaje || 'Hola, ¿sigue la camiseta disponible?',
          hora: chatExistente?.hora || '13:00',
          esMio: false,
        },
      ];
      return;
    }

    this.mensajes = [
      {
        id: '1',
        texto: '¡Hola! Me ha gustado mucho una camiseta de tu perfil.',
        hora: '11:10',
        esMio: true,
        leido: true,
      },
      {
        id: '2',
        texto: chatExistente?.ultimoMensaje || '¡Holi! ¿Quieres la camiseta?',
        hora: chatExistente?.hora || '11:15',
        esMio: false,
      },
    ];
  }

  usarRespuestaRapida(texto: string): void {
    this.nuevoMensaje = texto;
    this.enviarMensaje();
  }

  enviarMensaje(): void {
    const textoLimpio = this.nuevoMensaje.trim();
    if (!textoLimpio || !this.contacto) return;

    const ahora = new Date();
    const horaFormateada = ahora.toTimeString().slice(0, 5);

    this.mensajes.push({
      id: Date.now().toString(),
      texto: textoLimpio,
      hora: horaFormateada,
      esMio: true,
      leido: true,
    });

    this.actualizarListaChats(textoLimpio, horaFormateada);
    this.nuevoMensaje = '';
    this.bajarAlFinal();
  }

  private actualizarListaChats(ultimoTexto: string, hora: string): void {
    if (!this.contacto) return;

    const existente = this.swapService.chats.find((c) => c.userId === this.contacto!.id);

    if (existente) {
      existente.ultimoMensaje = ultimoTexto;
      existente.hora = hora;
      existente.noLeidos = 0;
      existente.leido = true;
    } else {
      this.swapService.chats.unshift({
        userId: this.contacto.id,
        nombre: this.contacto.nombre,
        avatar: this.contacto.avatar,
        hora,
        ultimoMensaje: ultimoTexto,
        noLeidos: 0,
        leido: true,
      });
    }
  }

  private bajarAlFinal(): void {
    setTimeout(() => {
      this.content?.scrollToBottom(250);
    }, 80);
  }

  volver(): void {
    this.navCtrl.back();
  }
}
