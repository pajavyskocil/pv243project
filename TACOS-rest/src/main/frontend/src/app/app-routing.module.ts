import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserDetailIdComponent} from "./components/user-detail-id/user-detail-id.component";
import {UserDetailEmailComponent} from "./components/user-detail-email/user-detail-email.component";
import { MarketplaceComponent } from './marketplace/marketplace.component';
import { EshopComponent } from './marketplace/eshop/eshop.component';
import { ProfileComponent } from './marketplace/profile/profile.component';
import { BasketComponent } from './marketplace/basket/basket.component';
import { SubcategoryComponent } from './marketplace/eshop/subcategory/subcategory.component';
import { ProductDetailComponent } from './marketplace/eshop/product-detail/product-detail.component';
import {AdminComponent} from "./admin/admin.component";
import {AdminProfileComponent} from "./admin/admin-profile/admin-profile.component";
import {UsersPageComponent} from "./admin/users-page/users-page.component";
import {OrdersPageComponent} from "./admin/orders-page/orders-page.component";
import {RegisterUserPageComponent} from "./register-user-page/register-user-page.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {AddUserComponent} from "./admin/add-user/add-user.component";
import {OrderDetailPageComponent} from "./components/order-detail-page/order-detail-page.component";
import {AddProductComponent} from "./components/add-product/add-product.component";
import {AddProductPageComponent} from "./admin/add-product-page/add-product-page.component";



const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      {
        path: 'profile',
        component: AdminProfileComponent
      },
      {
        path: 'users',
        component: UsersPageComponent
      },
      {
        path: 'addUser',
        component: AddUserComponent
      },
      {
        path: 'orders',
        component: OrdersPageComponent
      },
      {
        path: 'orders/:id',
        component: OrderDetailPageComponent
      },
      {
        path: 'users/:id',
        component: UserDetailIdComponent
      },
      {
        path:'addProduct',
        component: AddProductPageComponent
      }
    ]
  },
  {
    path: 'users/filter/email/:email',
    component: UserDetailEmailComponent
  },
  {
    path: 'marketplace',
    component: MarketplaceComponent,
    children: [
      {
        path: 'eshop',
        component: EshopComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'basket',
        component: BasketComponent
      },
      {
        path: 'eshop-category/:id',
        component: SubcategoryComponent
      },
      {
        path: 'product/:id',
        component: ProductDetailComponent
      },
      {
        path: '',
        redirectTo: '/eshop',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'register',
    component: RegisterUserPageComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
