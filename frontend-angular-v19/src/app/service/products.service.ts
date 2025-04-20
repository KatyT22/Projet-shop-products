import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Product } from '../products/product.model';

@Injectable({ providedIn: 'root' })
export class ProductsService {

    public static productslist: Product[] = [];
    private products$: BehaviorSubject<Product[]> = new BehaviorSubject<Product[]>([]);

    private http = inject(HttpClient);

    private backendRoot: string = 'http://localhost:8080/api/products/';




    //Get product list
    getProducts(): Observable<Product[]> {
        if( !ProductsService.productslist || ProductsService.productslist.length == 0 )
        {
            this.http.get<Product[]>(this.backendRoot).subscribe(data => {
                console.log(' Service => ' + data);
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

    //Create new product
    create(product: Product): Observable<Product[]> {
        this.http.post<Product>(this.backendRoot+'/new', product).subscribe(data => {
            console.log(data);
            ProductsService.productslist.push(product);
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }

    //Update a product 
    update(product: Product): Observable<Product[]>{
        
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

                    
                    if(product.id){
                        this.delete(product.id);
                    }
                    this.create(element);
                }
            

            this.products$.next(ProductsService.productslist);
        })
        
        return this.products$;
    }

    // Get a product
    getProduct(id: number): Observable<Product[]>{
        this.http.get<Product>(this.backendRoot+ id).subscribe(data => {
            console.log(data);
            ProductsService.productslist = ProductsService.productslist.filter(value => { return value.id !== id } );
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }

    // Delete a product
    delete(id: number): Observable<Product[]>{
        this.http.delete<Product>(this.backendRoot + id).subscribe(data => {
            console.log(data);
            ProductsService.productslist = ProductsService.productslist.filter(value => { return value.id !== id } );
            this.products$.next(ProductsService.productslist);
        })
        return this.products$;
    }

}