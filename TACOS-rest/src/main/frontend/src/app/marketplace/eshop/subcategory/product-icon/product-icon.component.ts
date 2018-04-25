import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../../../services/user/user.service';

@Component({
  selector: 'app-product-icon',
  templateUrl: './product-icon.component.html',
  styleUrls: ['./product-icon.component.css']
})
export class ProductIconComponent implements OnInit {

  constructor() { }

  @Input()
  private product : Product;
  
  ngOnInit() {
  }

}
