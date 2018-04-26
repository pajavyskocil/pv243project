import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient } from '@angular/common/http';
import {Attribute} from "../attribute/attribute.service";
import {Product} from "../product/product.service";

@Injectable()
export class CreatedProductService {

  private readonly URL = "http://localhost:8080/TACOS-rest/api/v1/createdProducts";

  constructor(protected httpClient:HttpClient) { }

  public createCreatedProduct(createdProductCreate: CreatedProductCreate) : Observable<LongRange>  {
    return this.httpClient.post<LongRange>(this.URL, createdProductCreate,{});
  }

  public deleteCreatedProduct(createdProduct: CreatedProduct) : Observable<LongRange>  {
    return this.httpClient.delete<LongRange>( `${this.URL}/${createdProduct.id}`,{});
  }

  public getAllCreatedProducts() : Observable<Array<CreatedProduct>> {
    return this.httpClient.get<Array<CreatedProduct>>(this.URL, {});
  }

  public findCreatedProductById(id: LongRange) : Observable<CreatedProduct> {
    return this.httpClient.get<CreatedProduct>(`${this.URL}/${id}`,{});
  }

  public addAttribute(createdProduct : CreatedProduct, attribute : Attribute) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${createdProduct.id}/addAttribute/${attribute.id}`, {});
  }

  public removeAttribute(createdProduct : CreatedProduct, attribute : Attribute) : Observable<LongRange> {
    return this.httpClient.put<LongRange>(`${this.URL}/${createdProduct.id}/removeAttribute/${attribute.id}`, {});
  }
}

export interface CreatedProduct {
  id: LongRange,
  price: Number,
  count: LongRange,
  description: String,
  product: Product,
  attributes: Attribute[]
}

export interface CreatedProductCreate {
  count: LongRange,
  description: String,
  productId: LongRange,
  attributeIds: LongRange[]
}
