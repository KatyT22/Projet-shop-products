import { ChangeDetectorRef, Component, Input, input, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { TagModule } from 'primeng/tag';
import { SelectModule } from 'primeng/select';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Product } from '../products/product.model';
import { CommonModule } from '@angular/common';
import { ProductsService } from '../service/products.service';
import { RatingModule } from 'primeng/rating';
import { Dialog } from 'primeng/dialog';
import { ConfirmDialog } from 'primeng/confirmdialog';
import { RadioButton } from 'primeng/radiobutton';
import { ToolbarModule } from 'primeng/toolbar';
import { IconField } from 'primeng/iconfield';
import { FormsModule } from '@angular/forms';
import { InputIcon } from 'primeng/inputicon';
import { InputNumber } from 'primeng/inputnumber';
import { Observable } from 'rxjs';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { TextareaModule } from 'primeng/textarea';

import { Ripple } from 'primeng/ripple';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { FileUpload } from 'primeng/fileupload';



interface Column {
  field: string;
  header: string;
  customExportHeader?: string;
}

interface ExportColumn {
  title: string;
  dataKey: string;
}


@Component({
  selector: 'app-dashboard',
  imports: [TableModule, ToastModule, TagModule, SelectModule, ButtonModule, InputTextModule, RatingModule, CommonModule, Dialog, ConfirmDialog, RadioButton, ToolbarModule, IconField, FormsModule, InputIcon, InputNumber, TextareaModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  providers: [ProductsService, MessageService, ConfirmationService]
})
export class DashboardComponent implements OnInit{

  products! : Product[];
  statuses!: SelectItem[];
  submitted: boolean = false;
  product! : Product;
  productDialog: boolean = false;
  selectedProducts!: Product[] | null;
  products$! : Observable<Product[]>;
  cols!: Column[];
  exportColumns!: ExportColumn[];
  inventoryStatus! : string;

  constructor(
    private productsService: ProductsService,
    private messageService: MessageService,
    private ref: ChangeDetectorRef
  ) {}

  ngOnInit(){
    this.products$ = this.productsService.getProducts();
    this.products$.subscribe(data => {
      this.products = data,
      this.ref.markForCheck();
      console.log( " Doashboard Component oninit => "+ data)
    });
    /*this.productsService.getProducts().then((data) => {
      this.products = data;
      
   });*/

    this.statuses = [
        { label : 'INSTOCK', value : 'instock'}, 
        { label: 'LOWSTOCK', value : 'lowstock'},
        { label: 'OUTOFSTOCK', value : 'outofstock'}
    ];

    this.cols = [
        { field: 'code', header: 'Code', customExportHeader: 'Product Code' },
        { field: 'name', header: 'Name' },
        { field: 'image', header: 'Image' },
        { field: 'price', header: 'Price' },
        { field: 'category', header: 'Category' }
    ];

    this.exportColumns = this.cols.map((col) => ({ title: col.header, dataKey: col.field }));
  }


  openNew() {
    this.product = {};
    this.submitted = false;
    this.productDialog = true;
  }

/*deleteSelectedProducts() {
  this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          this.products = this.products.filter((val) => !this.selectedProducts?.includes(val));
          this.selectedProducts = null;
          this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Products Deleted',
              life: 3000
          });
      }
  });
}*/

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
  }

  editProduct(product: Product){
    console.log("Edit PRoduct");
  }

  deleteProduct(product_id : number){
    console.log("Delete PRoduct");
  }

  hideDialog() {
    this.productDialog = false;
    this.submitted = false;
  }

  findIndexById(id: number): number {
    let index = -1;
    for (let i = 0; i < this.products.length; i++) {
        if (this.products[i].id === id) {
            index = i;
            break;
        }
    }

    return index;
  }

  createCode(): string {
    let code = '';
    var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    for (var i = 0; i < 5; i++) {
        code += chars.charAt(Math.floor(Math.random() * chars.length));
    }
    return code;
  }

  mapInventoryStatus(product : Product) : string {
    let _status : string = '';
    let _element : any = product.inventoryStatus;
    switch (_element.label) {
      case 'INSTOCK':
        _status = 'INSTOCK';
        break;
      case 'LOWSTOCK':
        _status = 'LOWSTOCK';
        break;
      case 'OUTOFSTOCK':
        _status = 'OUTOFSTOCK';
        break;
      default:
        break;
      }
    return _status;
  }

  saveProduct(product : Product) {
    this.submitted = true;
    product.inventoryStatus = this.mapInventoryStatus(this.product);
    if (product.name?.trim()) {
        if (product.id) {
            //this.products[this.findIndexById(product.id)] = product;
            this.productsService.update(product);
            this.messageService.add({
                severity: 'success',
                summary: 'Successful',
                detail: 'Product Updated',
                life: 3000
            });
        } else {
          
            product.code = this.createCode();
            product.image = 'image';
            product.rating = 0;
            //this.products.push(product);
            this.productsService.create(product);
            this.messageService.add({
                severity: 'success',
                summary: 'Successful',
                detail: 'Product Created',
                life: 3000
            });
        }

        this.products = [...this.products];
        this.productDialog = false;
        this.product = {};
    }
  }
}

