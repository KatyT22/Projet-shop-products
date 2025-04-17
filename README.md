# Projet Shop

back-end permettant la gestion de produits définis plus bas. 

Technologie en :

- Java/Spring Boot


Le back-end peut gérer les API REST suivantes : 

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new product  | Retrieve all products          | X                                        | X   |     X            |
| **/products/1**    | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

Un produit a les caractéristiques suivantes : 

``` typescript
class Product {
  id: number;
  code: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  inventoryStatus: string;
  category: string;
  image?: string;
  rating?: number;
}
```

Le back-end gère les produits dans une base de données PostgreSQL.

Une liste de produits est disponible dans ce fichier en json : `front/assets/products.json`

Un front-end en Angular 14 et 19 est disponible et permet d'utiliser l'API via cette adresse : `http://localhost:4200` (v19)


Le front-end angular se lance avec la commande 'ng serve'
Le Back-end Spring se lance avec la commande mvn spring-boot:run

# Tests API

Des tests Postman ou Swagger permettent de valider les appels à l'API