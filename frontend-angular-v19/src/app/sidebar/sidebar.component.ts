import { Component, OnInit } from '@angular/core';
import { ProductsComponent } from '../products/products.component';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from '../dashboard/dashboard.component';


@Component({
  selector: 'app-sidebar',
  imports: [ProductsComponent, DashboardComponent, MenuModule, CommonModule],
  templateUrl: './sidebar.component.html',
})



export class SidebarComponent implements OnInit {
  menuItems! : MenuItem[];
  showDashboard! : boolean;
  showProducts! : boolean;


  constructor() {}

  ngOnInit() {
    this.menuItems = [
        { label: 'Products', icon: 'pi pi-shopping-bag', command: (event) => { this.productsClicked(); }},
        { label: 'Dashboard', icon: 'pi pi-desktop', command: (event) => { this.dashboardClicked(); }},
    ];
    this.showProducts = true;
    this.showDashboard = false;
}

  dashboardClicked() {
    this.showProducts = false;
    this.showDashboard = true;
    console.log("Dashboard clicked in menu");
  }


  productsClicked(){
    this.showProducts = true;
    this.showDashboard = false;
    console.log("Shop clicked in menu");
  }

}
