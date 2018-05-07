import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product, ProductService} from "../../services/product/product.service";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  @Input()
  public products : Product[];

  @Output()
  delete : EventEmitter<any> = new EventEmitter();

  constructor(private productService: ProductService) { }


  ngOnInit() {
  }

  public deleteProduct(i : number) {
    this.productService.deleteProduct(this.products[i]).subscribe((removedProduct) => {
      this.products.splice(i, 1);
    });
  }
}
