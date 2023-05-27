import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {
  constructor(private productService: ProductService) {}
  ngOnInit() {}
  public addProduct(name: String, url: String, msrp: String): void {
    this.productService.addProduct(name, url, msrp).subscribe(
      (data) => {
        console.log(data);
        alert('product inserted..');
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
