import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserDetailComponent} from "./user-detail/user-detail.component";
import {UserDetailIdComponent} from "./user-detail-id/user-detail-id.component";
import {UserDetailEmailComponent} from "./user-detail-email/user-detail-email.component";
import { MarketplaceComponent } from './marketplace/marketplace.component';
import { EshopComponent } from './marketplace/eshop/eshop.component';
import { ProfileComponent } from './marketplace/profile/profile.component';
import { BasketComponent } from './marketplace/basket/basket.component';



const routes: Routes = [
  {
    path: 'users/:id',
    component: UserDetailIdComponent
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
        path: '',
        component: EshopComponent
      },
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
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'marketplace/eshop'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
