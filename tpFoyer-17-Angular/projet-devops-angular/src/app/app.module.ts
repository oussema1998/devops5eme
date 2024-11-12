import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BlocComponent } from './bloc/bloc.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddBlocComponent } from './add-bloc/add-bloc.component';
import { ListBlocComponent } from './list-bloc/list-bloc.component';

@NgModule({
  declarations: [
    AppComponent,
    BlocComponent,
    AddBlocComponent,
    ListBlocComponent
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
