import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable, Injector, OnInit} from '@angular/core';
import {HeroResourceService} from './hero/api/heroResource.service';
import {SpeciesResourceService} from './species/api/speciesResource.service';
import {Router} from '@angular/router';


@Injectable()
export class AuthService {

  public data;

  constructor(private http: HttpClient,
              private router: Router,
              private injector: Injector){}


  public login(pUser:string, pPassword:string){
    let headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
    let body = "username=" + pUser + "&password=" + pPassword + "&grant_type=password&client_id=account";
      this.http.post("https://iam-medical.drhealth.cloud/auth/realms/hoe/protocol/openid-connect/token",body,{headers :headers})
        .subscribe(data => {
          this.data=data;
          const speciesService: SpeciesResourceService = this.injector.get(SpeciesResourceService);
          speciesService.configuration.accessToken=this.data.access_token;

          const heroService: HeroResourceService = this.injector.get(HeroResourceService);
          heroService.configuration.accessToken=this.data.access_token;
          this.router.navigateByUrl("/hero/list");
        });
  }
}
