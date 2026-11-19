import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SolicitarIntercambioPage } from './solicitar-intercambio.page';

describe('SolicitarIntercambioPage', () => {
  let component: SolicitarIntercambioPage;
  let fixture: ComponentFixture<SolicitarIntercambioPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(SolicitarIntercambioPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
