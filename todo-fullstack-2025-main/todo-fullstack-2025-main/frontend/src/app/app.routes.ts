import { Routes } from '@angular/router';
import { TodoListComponent } from './todo/todo-list/todo-list.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthGuard } from './auth/auth.guard';



export const routes: Routes = [
  { path: 'login', component: LoginComponent },

  {
    path: 'todos',
    component: TodoListComponent,
    canActivate: [AuthGuard]   // 🔐 Protection ici
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' }
];
