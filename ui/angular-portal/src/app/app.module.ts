import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { IndexPageComponent } from './pages/index/index-page.component';
import { HeroPageComponent } from './pages/user/hero/hero-page/hero-page.component';
import { EmpirePageComponent } from './pages/user/empire-page/empire-page.component';
import { SpeciesPageComponent } from './pages/admin/species-page/species-page.component';
import { AbilityPageComponent } from './pages/admin/ability-page/ability-page.component';
import {HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './pages/index/login/login.component';
import { AboutComponent } from './pages/index/about/about.component';
import { SpeciesDialogComponent } from './pages/index/species-dialog/species-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import { HeroToplistDialogComponent } from './pages/index/hero-toplist-dialog/hero-toplist-dialog.component';
import { EmpireToplistDialogComponent } from './pages/index/empire-toplist-dialog/empire-toplist-dialog.component';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {AuthService} from './services/AuthService';
import {FormsModule} from '@angular/forms';
import {SpeciesResourceService} from './services/species/api/speciesResource.service';
import {HeroResourceService} from './services/hero/api/heroResource.service';
import { MenuComponent } from './components/menu/menu.component';
import {MatMenu, MatMenuModule} from '@angular/material/menu';
import { NewheroPageComponent } from './pages/user/hero/newhero-page/newhero-page.component';

@NgModule({
  declarations: [
    AppComponent,
    IndexPageComponent,
    HeroPageComponent,
    EmpirePageComponent,
    SpeciesPageComponent,
    AbilityPageComponent,
    LoginComponent,
    AboutComponent,
    SpeciesDialogComponent,
    HeroToplistDialogComponent,
    EmpireToplistDialogComponent,
    MenuComponent,
    NewheroPageComponent
  ],
  entryComponents: [
    SpeciesDialogComponent,
    EmpireToplistDialogComponent,
    HeroToplistDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatMenuModule,
    FormsModule
  ],
  providers: [
    SpeciesResourceService,
    HeroResourceService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
