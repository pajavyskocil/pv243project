import { BrowserModule } from '@angular/platform-browser';
import { NgModule , NO_ERRORS_SCHEMA} from '@angular/core';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { AppRoutingModule} from "./app-routing.module";
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgSelectModule } from '@ng-select/ng-select';



import { AppComponent } from './app.component';


import { FormsModule } from '@angular/forms';
import { UserDetailComponent } from './components/user-detail/user-detail.component';
import { UserDetailIdComponent } from './components/user-detail-id/user-detail-id.component';
import { UserDetailEmailComponent } from './components/user-detail-email/user-detail-email.component';
import { MarketplaceComponent } from './marketplace/marketplace.component';
import { MarketplaceHeaderComponent } from './marketplace/marketplace-header/marketplace-header.component';
import { EshopComponent } from './marketplace/eshop/eshop.component';
import { ProfileComponent } from './marketplace/profile/profile.component';
import { BasketComponent } from './marketplace/basket/basket.component';

import { UserService } from "./services/user/user.service";
import { ProductCategoryService } from './services/product-category/product-category.service';
import { EshopCategoriesComponent } from './marketplace/eshop/eshop-categories/eshop-categories.component';
import { EshopCategoryComponent } from './marketplace/eshop/eshop-categories/eshop-category/eshop-category.component';

import {ProductService} from "./services/product/product.service";
import {OrderService} from "./services/order/order.service";
import {CreatedProductService} from "./services/createdProduct/created-product.service";
import {TemplateService} from "./services/template/template.service";
import {AttributeService} from "./services/attribute/attribute.service";
import {AttributeCategoryService} from "./services/attributeCategory/attribute-category.service";
import { SubcategoryComponent } from './marketplace/eshop/subcategory/subcategory.component';
import { ProductIconComponent } from './marketplace/eshop/subcategory/product-icon/product-icon.component';
import { ProductDetailComponent } from './marketplace/eshop/product-detail/product-detail.component';
import { TemplateSelectorComponent } from './marketplace/eshop/product-detail/template-selector/template-selector.component';
import { AttributeCategoriesComponent } from './marketplace/eshop/product-detail/attribute-categories/attribute-categories.component';
import { AttributeSelectorComponent } from './marketplace/eshop/product-detail/attribute-categories/attribute-selector/attribute-selector.component';
import { AdminHeaderComponent } from './admin/admin-header/admin-header.component';
import { AdminComponent } from './admin/admin.component';
import { AdminProfileComponent } from './admin/admin-profile/admin-profile.component';
import { UsersListComponent } from './admin/users-page/users-list/users-list.component';
import { UsersPageComponent } from './admin/users-page/users-page.component';
import { UsersListNodeComponent } from './admin/users-page/users-list/users-list-node/users-list-node.component';
import { OrderListComponent } from './components/order-list/order-list.component';
import { OrderListNodeComponent } from './components/order-list/order-list-node/order-list-node.component';
import { OrdersPageComponent } from './admin/orders-page/orders-page.component';
import { RegisterUserPageComponent } from './register-user-page/register-user-page.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { AddUserComponent } from './admin/add-user/add-user.component';
import { OrderDetailPageComponent } from './components/order-detail-page/order-detail-page.component';
import { OrderDetailComponent } from './components/order-detail-page/order-detail/order-detail.component';
import { ProductListComponent } from './components/product-list/product-list.component';

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
    SubcategoryComponent,
    ProductIconComponent,
    ProductDetailComponent,
    TemplateSelectorComponent,
    AttributeCategoriesComponent,
    AttributeSelectorComponent,
    AdminHeaderComponent,
    AdminComponent,
    AdminProfileComponent,
    UsersListComponent,
    UsersPageComponent,
    UsersListNodeComponent,
    OrderListComponent,
    OrderListNodeComponent,
    OrdersPageComponent,
    RegisterUserPageComponent,
    HeaderComponent,
    LoginPageComponent,
    AddUserComponent,
    OrderDetailPageComponent,
    OrderDetailComponent,
    ProductListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MDBBootstrapModule.forRoot(),
    ReactiveFormsModule,
    NgSelectModule
  ],
  providers: [
    UserService,
    ProductCategoryService,
    ProductService,
    OrderService,
    CreatedProductService,
    TemplateService,
    AttributeService,
    AttributeCategoryService,
  ],
  bootstrap: [AppComponent],
  schemas: [ NO_ERRORS_SCHEMA ]
})
export class AppModule { }
