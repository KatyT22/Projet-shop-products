import { Component, inject } from '@angular/core';
import { DataView } from 'primeng/dataview';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import { CommonModule } from '@angular/common';
import { ProductsService } from '../service/products.service';
import { Product } from './product.model';
import { Observable } from 'rxjs';


@Component({
    selector: 'app-products',
    templateUrl: './products.component.html',
    standalone: true,
    imports: [DataView, ButtonModule, TagModule, CommonModule],
    providers: [ProductsService]
})
export class ProductsComponent{
    products$! : Observable<Product[]>;
    products! : Product[];
    

    private productsService = inject(ProductsService);

    constructor() {
    }

    ngOnInit() {

        this.products$ = this.productsService.getProducts();
        
        this.products$.subscribe(data => {
            this.products = data
        });
        
    }

    ngOnChange(){
      
    }

    getSeverity (product: Product) {
        switch (product.inventoryStatus) {
            case 'INSTOCK':
                return 'success';

            case 'LOWSTOCK':
                return 'warn';

            case 'OUTOFSTOCK':
                return 'danger';

            default:
                return undefined;
        }
    };
}