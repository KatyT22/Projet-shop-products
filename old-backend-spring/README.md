# Projet shop (*Branch* :  shopProducts_v1_Back)

**Versions**

Java : 21

Spring Boot : 3.2.0

Maven : 3.9.6

Node.js : 20.10

Postgresql/pgAdmin4 : 16.1


# Connect front to back
See file products.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from './product.class';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

    private static productslist: Product[] = null;
    private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);

    constructor(private http: HttpClient) { }

    getProducts(): Observable<Product[]> {
        if( ! ProductsService.productslist )
        {
            this.http.get<any>('http://localhost:8080/api/products').subscribe(data => {
                console.log(data);
                ProductsService.productslist = data;
                
                this.products$.next(ProductsService.productslist);
            });
        }
        else
        {
            this.products$.next(ProductsService.productslist);
        }

        return this.products$;
    }

    create(prod: Product): Observable<Product[]> {
        this.http.post<any>('http://localhost:8080/api/products', prod).subscribe(data => {
            ProductsService.productslist.push(prod);
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }

    update(prod: Product): Observable<Product[]>{
        this.http.patch<any>('http://localhost:8080/api/products/'+prod.id, prod).subscribe(data => {
            ProductsService.productslist.forEach(element => {
                if(element.id == prod.id)
                {
                    element.name = prod.name;
                    element.category = prod.category;
                    element.code = prod.code;
                    element.description = prod.description;
                    element.image = prod.image;
                    element.inventoryStatus = prod.inventoryStatus;
                    element.price = prod.price;
                    element.quantity = prod.quantity;
                    element.rating = prod.rating;
                }
            });
            this.products$.next(ProductsService.productslist);
        })
        
        return this.products$;
    }


    delete(id: number): Observable<Product[]>{
        this.http.delete<any>('http://localhost:8080/api/products/'+id).subscribe(data => {
            ProductsService.productslist = ProductsService.productslist.filter(value => { return value.id !== id } );
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }
}



____________________

# Front 
Inside the **Project-shop-products\frontend-angular\src\app>** folder, launch the front with the "ng serve" command.

# Back
Inside the **Project-shop-products\backend-spring** folder, launch the back with "mvn spring-boot:run" the command.

________________________

# Launch application
url : http://localhost:4200/products

# API Tests

Test with Swagger : http://localhost:8080/swagger-ui/index.html#/product-controller/getAllProducts





