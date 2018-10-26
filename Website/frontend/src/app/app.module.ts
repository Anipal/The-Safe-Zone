import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { ValidateService } from './services/validate.service';
import { AuthService } from './services/auth.service';
import { SafezoneService } from './services/safezone.service';
import { SafezoneRegComponent } from './safezone-reg/safezone-reg.component';
import { SafezonesComponent } from './safezones/safezones.component';
import { UserComponent } from './user/user.component';
import { VolunteersComponent } from './volunteers/volunteers.component';
import { NavbarComponent } from './navbar/navbar.component';

const appRoutes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: HomeComponent },
  { path: 'safezoneReg', component: SafezoneRegComponent },
  { path: 'safezone', component: SafezonesComponent },
  { path: 'civilians', component: UserComponent },
  { path: 'volunteers', component: VolunteersComponent },
  { path: 'dashboard', component: DashboardComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    DashboardComponent,
    SafezoneRegComponent,
    SafezonesComponent,
    UserComponent,
    VolunteersComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    ValidateService,
    AuthService,
    SafezoneService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
