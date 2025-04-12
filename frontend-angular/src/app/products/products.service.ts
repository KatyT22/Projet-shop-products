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

    create(product: Product): Observable<Product[]> {
        this.http.post<any>('http://localhost:8080/api/products/new', product).subscribe(data => {
            console.log(data);
            ProductsService.productslist.push(product);
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }

    update(product: Product): Observable<Product[]>{
        this.http.patch<any>('http://localhost:8080/api/products/'+product.id, product).subscribe(data => {
            console.log(data);
            ProductsService.productslist.forEach(element => {
                if(element.id == product.id)
                {
                    element.name = product.name;
                    element.category = product.category;
                    element.code = product.code;
                    element.description = product.description;
                    element.image = product.image;
                    element.inventoryStatus = product.inventoryStatus;
                    element.price = product.price;
                    element.quantity = product.quantity;
                    element.rating = product.rating;
                }
            });
            this.products$.next(ProductsService.productslist);
        })
        
        return this.products$;
    }


    delete(id: number): Observable<Product[]>{
        this.http.delete<any>('http://localhost:8080/api/products/'+id).subscribe(data => {
            console.log(data);
            ProductsService.productslist = ProductsService.productslist.filter(value => { return value.id !== id } );
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }
}