import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TestComponent } from './test/test.component';
import { AddchambreformComponent } from './addchambreform/addchambreform.component';

@NgModule({
  declarations: [
    AppComponent,
    TestComponent,AddchambreformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule  
  ],  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
