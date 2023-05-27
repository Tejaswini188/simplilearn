import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent implements OnInit {
  public products: Product[];
  public product: Product;

  constructor(private productService: ProductService) {}
  ngOnInit() {
    this.getProducts();
  }

  public getProducts(): void {
    this.productService.getProductList().subscribe(
      (response: Product[]) => {
        this.products = response;
        console.log(this.products);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  public deleteProduct(product_id: number): void {
    this.productService.deleteProduct(product_id).subscribe(
      (data) => {
        console.log(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  
}
