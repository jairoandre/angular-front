import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { FormsModule } from '@angular/forms'
import { ClientService } from './client.service'

import { AppComponent } from './app.component';
import { SnackbarComponent } from './component/snackbar/snackbar.component';
import { TimesheetComponent } from './component/timesheet/timesheet.component';

@NgModule({
  declarations: [
    AppComponent,
    SnackbarComponent,
    TimesheetComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    ClientService
  ],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
