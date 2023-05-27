import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { Product } from './../product';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  public product: any = [];
  newProduct: Product;
  prod: any;
  private router: Router;
  sum: number;
  constructor(private productService: ProductService) {}

  ngOnInit() {}
  public searchProducts(product_name: String): void {
    console.log(product_name);
    this.productService.searchProduct(product_name).subscribe(
      (data) => {
        //this.product = data,
        this.product.push(data);
        console.log(this.product);
      },
      (error) => {
        console.log(error);
      }
    );
  }

 
  public getProduct(product_id: number): void {
    this.sum = 0;
    console.log('ki' + product_id);
    this.productService.getProduct(product_id).subscribe(
      (data) => {
        (this.prod = data), (this.sum += this.prod.msrp);
        console.log('sum' + this.sum);
        //this.prod.push(data);
        console.log(this.prod);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
