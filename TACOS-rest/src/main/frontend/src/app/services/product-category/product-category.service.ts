import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import {Product} from "../product/product.service";

@Injectable()
export class ProductCategoryService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/productCategories";

  constructor(protected httpClient : HttpClient) { }

  public createProductCategory(productCategoryCreate: ProductCategoryCreate) : Observable<number>  {
    return this.httpClient.post<number>(this.URL, productCategoryCreate,{});
  }

  public deleteProductCategory(productCategory: ProductCategory) : Observable<number>  {
    return this.httpClient.delete<number>( `${this.URL}/${productCategory.id}`,{});
  }

  public getAllProductCategories() : Observable<Array<ProductCategory>> {
    return this.httpClient.get<Array<ProductCategory>>(this.URL, {});
  }

  public findProductCategoryById(id: number) : Observable<ProductCategory> {
    return this.httpClient.get<ProductCategory>(`${this.URL}/${id}`,{});
  }

  public addSubCategory(productCategory : ProductCategory, subCategory : ProductCategory) : Observable<number> {
    return this.httpClient.put<number>(`${this.URL}/${productCategory.id}/addSubCategory/${subCategory.id}`, {});
  }

  public removeSubCategory(productCategory : ProductCategory, subCategory : ProductCategory) : Observable<number> {
    return this.httpClient.put<number>(`${this.URL}/${productCategory.id}/removeSubCategory/${subCategory.id}`, {});
  }

  public addProduct(productCategory : ProductCategory, product : Product) : Observable<number> {
    return this.httpClient.put<number>(`${this.URL}/${productCategory.id}/addProduct/${product.id}`, {});
  }

  public removeProduct(productCategory : ProductCategory, product : Product) : Observable<number> {
    return this.httpClient.put<number>(`${this.URL}/${productCategory.id}/removeProduct/${product.id}`, {});
  }
}

export interface ProductCategory {
  id: number,
  name: String,
  image: String,
  products: Product[],
  parentCategoryId: number,
  subCategories: ProductCategory[]
}

export interface ProductCategoryCreate {
  name: String,
  image: String,
  parentCategoryId: number
}
