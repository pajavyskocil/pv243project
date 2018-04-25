import { BrowserModule } from '@angular/platform-browser';
import { NgModule , NO_ERRORS_SCHEMA} from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule} from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";


import { AppComponent } from './app.component';


import { FormsModule } from '@angular/forms';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { UserDetailIdComponent } from './user-detail-id/user-detail-id.component';
import { UserDetailEmailComponent } from './user-detail-email/user-detail-email.component';
import { MarketplaceComponent } from './marketplace/marketplace.component';
import { MarketplaceHeaderComponent } from './marketplace/marketplace-header/marketplace-header.component';
import { EshopComponent } from './marketplace/eshop/eshop.component';
import { ProfileComponent } from './marketplace/profile/profile.component';
import { BasketComponent } from './marketplace/basket/basket.component';

import { UserService } from "./services/user/user.service";
import { ProductCategoryService } from './services/product-category/product-category.service';
import { EshopCategoriesComponent } from './marketplace/eshop/eshop-categories/eshop-categories.component';
import { EshopCategoryComponent } from './marketplace/eshop/eshop-categories/eshop-category/eshop-category.component';

@NgModule({
  declarations: [
    AppComponent,
    UserDetailComponent,
    UserDetailIdComponent,
    UserDetailEmailComponent,
    MarketplaceComponent,
    MarketplaceHeaderComponent,
    EshopComponent,
    ProfileComponent,
    BasketComponent,
    EshopCategoriesComponent,
    EshopCategoryComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    UserService,
    ProductCategoryService
  ],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
