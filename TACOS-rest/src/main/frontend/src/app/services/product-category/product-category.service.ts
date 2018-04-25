import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ProductCategoryService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/productCategories";

  constructor(protected httpClient : HttpClient) { }

  public getAllCategories() : Observable<Array<ProductCategory>> {
    return this.httpClient.get<Array<ProductCategory>>(this.URL, {});
  }
}

export interface ProductCategory {
  id: LongRange,
  name: String,
  image: String,
  parentCategory: ProductCategory,
  subCategories: ProductCategory[]
}
