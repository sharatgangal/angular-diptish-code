import { Component } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public bearerToken: any;
  public submitMessage: string;
  username = new FormControl('', [ Validators.required ]);
  password = new FormControl('', [ Validators.required ]);

  constructor(private authService: AuthenticationService, public routerService: RouterService) { }

  loginSubmit() {
    if (this.username.valid && this.password.valid) {
      this.authService.authenticateUser({
        username: this.username.value,
        password: this.password.value
      }).subscribe(
      res => {
        this.bearerToken = res['token'];
        this.authService.setBearerToken(this.bearerToken);
        this.routerService.routeToDashboard();
      },
      err => {
        if (err.status === 403) {
          this.submitMessage = err.error.message;
        } else {
          this.submitMessage = err.message;
        }
      }
    );
    }
  }

  getErrorMessage() {
    return this.username.hasError('required') ? 'You must enter a value' :
          this.password.hasError('required') ? 'You must enter a value ' :
            '';
  }
}
