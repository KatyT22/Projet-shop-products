import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { TagModule } from 'primeng/tag';
import { SelectItem, SelectModule } from 'primeng/select';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { Product } from '../products/product.model';
import { CommonModule } from '@angular/common';
import { ProductsService } from '../service/products.service';
import { RatingModule } from 'primeng/rating';
import { Dialog } from 'primeng/dialog';
import { ConfirmDialog } from 'primeng/confirmdialog';
import { RadioButton } from 'primeng/radiobutton';
import { FileUpload } from 'primeng/fileupload';
import { ToolbarModule } from 'primeng/toolbar';
import { IconField } from 'primeng/iconfield';
import { FormsModule } from '@angular/forms';
import { InputIcon } from 'primeng/inputicon';


import { ConfirmationService, MessageService } from 'primeng/api';


import { Ripple } from 'primeng/ripple';
import { TextareaModule } from 'primeng/textarea';
import { Tag } from 'primeng/tag';
import { InputNumber } from 'primeng/inputnumber';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { Table } from 'primeng/table';
import { Observable } from 'rxjs';




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
  imports: [TableModule, ToastModule, TagModule, SelectModule, ButtonModule, InputTextModule, RatingModule, CommonModule, Dialog, ConfirmDialog, RadioButton, FileUpload, ToolbarModule, IconField, FormsModule, InputIcon],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
  providers: [ProductsService]
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

  constructor(
    private productsService: ProductsService,
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

    /*this.statuses = [
        { label: 'INSTOCK', value: 'instock' },
        { label: 'LOWSTOCK', value: 'lowstock' },
        { label: 'OUTOFSTOCK', value: 'outofstock' }
    ];*/

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

}
