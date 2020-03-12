import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {IndexPageComponent} from './pages/index/index-page.component';
import {HeroPageComponent} from './pages/user/hero/hero-page/hero-page.component';
import {SpeciesPageComponent} from './pages/admin/species-page/species-page.component';
import {AbilityPageComponent} from './pages/admin/ability-page/ability-page.component';
import {LoginComponent} from './pages/index/login/login.component';
import {NewheroPageComponent} from './pages/user/hero/newhero-page/newhero-page.component';


const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexPageComponent },
  { path: 'login', component: LoginComponent },
  { path: 'hero', component: HeroPageComponent },
  { path: 'species', component: SpeciesPageComponent },
  { path: 'ability', component: AbilityPageComponent },
  { path: 'hero-entity/:id', component: HeroPageComponent },
  { path: 'hero/new', component: NewheroPageComponent },
  { path: 'hero/list', component: HeroPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
