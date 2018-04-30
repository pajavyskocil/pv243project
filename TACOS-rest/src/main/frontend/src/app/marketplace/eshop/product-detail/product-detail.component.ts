import {Component, EventEmitter, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Product, ProductService} from "../../../services/product/product.service";
import {Template} from "../../../services/template/template.service";
import {AttributeCategory} from "../../../services/attributeCategory/attribute-category.service";
import {Attribute} from "../../../services/attribute/attribute.service";
import {AttributeSelect} from "./attribute-categories/attribute-selector/attribute-selector.component";
import {
  CreatedProduct,
  CreatedProductCreate,
  CreatedProductService
} from "../../../services/createdProduct/created-product.service";

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private productService : ProductService, private route: ActivatedRoute, private createdProductService : CreatedProductService) { }

  public product : Product;
  public selectedTemplate : Template;
  public selectedAttributes : Map<AttributeCategory, Attribute>;
  public createdProductCreate : CreatedProductCreate;
  public singleProductPrice : number;
  public countOfProducts : number;
  public totalPrice : number;
  public showSuccess : boolean = false;

  ngOnInit() {
    this.route.params.subscribe(res => {
      this.productService.findProductById(res.id).subscribe(product => this.product = product)
    });
  }

  public switchTemplate(t : Template) : void {
    if (this.selectedTemplate == undefined || this.selectedTemplate.id != t.id) {
      this.selectedAttributes = new Map<AttributeCategory, Attribute>();
      this.selectedTemplate = t;
      this.totalPrice = undefined;
      this.singleProductPrice = undefined;
      this.countOfProducts = undefined;
    }
  }

  public setAttribute(as : AttributeSelect) {
    this.selectedAttributes.set(as.category, as.attribute);
    this.checkIfDone();
  }

  private checkIfDone() {
    if (this.selectedAttributes.size == this.selectedTemplate.attributeCategories.length) {
      this.countOfProducts = 1;
      this.createdProductCreate = {
        "attributeIds" : Array.from(this.selectedAttributes.values()).map(x => x.id),
        "productId" : this.product.id,
        "count" : this.countOfProducts,
        "description" : ""
      };
      this.calculatePrice();
    }
  }

  public recalculateTotalPrice() {
    this.createdProductCreate.count = this.countOfProducts;
    this.totalPrice = this.singleProductPrice * this.countOfProducts;
    console.log(this.countOfProducts);
    console.log(this.totalPrice);
  }

  public addProductToBasket() {
    this.createdProductService.createCreatedProduct(this.createdProductCreate).subscribe(success => {
      this.showSuccess = true;
      this.selectedTemplate = undefined;
      this.selectedAttributes = undefined;
      this.createdProductCreate = undefined;
      this.singleProductPrice = undefined;
      this.countOfProducts = undefined;
      this.totalPrice = undefined;
    });
  }

  private calculatePrice() {
    let price = 0;
    console.log(this.selectedAttributes.values());
    for (let attribute of Array.from(this.selectedAttributes.values())) {
      console.log(attribute);
      price += attribute.price;
    }
    this.singleProductPrice = price;
    this.totalPrice = this.singleProductPrice * this.countOfProducts;
  }
}
