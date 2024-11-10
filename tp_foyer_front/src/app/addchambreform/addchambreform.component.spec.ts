import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddchambreformComponent } from './addchambreform.component';

describe('AddchambreformComponent', () => {
  let component: AddchambreformComponent;
  let fixture: ComponentFixture<AddchambreformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddchambreformComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddchambreformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
