import { NgModule } from '@angular/core';
import { MatButtonModule} from '@angular/material/button';
import { MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';

// tslint:disable-next-line:max-line-length
const MatComp = [MatButtonModule, MatIconModule, MatMenuModule, MatButtonToggleModule, MatFormFieldModule, MatDatepickerModule, MatNativeDateModule, MatInputModule];

@NgModule({
  imports: [MatComp],
  exports: [MatComp]
})
export class MaterialModule { }
