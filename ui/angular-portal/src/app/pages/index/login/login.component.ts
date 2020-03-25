import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../services/AuthService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public user:string;
  public password:string;

  constructor(private auth:AuthService, private router: Router) {  }

  ngOnInit(): void {
    this.password= 'user0';
    this.user= 'user0';
  }

  doLogin(){
    this.auth.login(this.user,this.password);
  }

}
