import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { PrimeNG } from 'primeng/config';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, ProductsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})



export class AppComponent implements OnInit{
  title = 'frontend-angular-v19';

  constructor(private primeng: PrimeNG) {}

    ngOnInit() {
        this.primeng.ripple.set(true);
    }
}
