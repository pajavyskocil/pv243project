import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {Product} from "../product/product.service";

@Injectable()
export class ProductCategoryService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/productCategories";

  constructor(protected httpClient : HttpClient) { }

  public createProductCategory(productCategoryCreate: ProductCategoryCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, productCategoryCreate,{});
  }

  public deleteProductCategory(productCategory: ProductCategory) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${productCategory.id}`,{});
  }

  public getAllProductCategories() : Observable<Array<ProductCategory>> {
    return this.httpClient.get<Array<ProductCategory>>(this.URL, {});
  }

  public findProductCategoryById(id: LongRange) : Observable<ProductCategory> {
    return this.httpClient.get<ProductCategory>(`${this.URL}/${id}`,{});
  }

  public addSubCategory(productCategory : ProductCategory, subCategory : ProductCategory) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${productCategory.id}/addSubCategory/${subCategory.id}`, {});
  }

  public removeSubCategory(productCategory : ProductCategory, subCategory : ProductCategory) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${productCategory.id}/removeSubCategory/${subCategory.id}`, {});
  }

  public addProduct(productCategory : ProductCategory, product : Product) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${productCategory.id}/addProduct/${product.id}`, {});
  }

  public removeProduct(productCategory : ProductCategory, product : Product) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${productCategory.id}/removeProduct/${product.id}`, {});
  }
}

export interface ProductCategory {
  id: LongRange,
  name: String,
  image: String,
  parentCategory: ProductCategory,
  subCategories: ProductCategory[]
}

export interface ProductCategoryCreate {
  name: String,
  image: String,
  parentCategory: LongRange
}
