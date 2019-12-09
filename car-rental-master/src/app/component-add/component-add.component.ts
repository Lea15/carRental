import { Component, OnInit } from '@angular/core';
import { Car } from '../car';
import { CarService } from '../car.service';


@Component({
  selector: 'app-component-add',
  templateUrl: './component-add.component.html',
  styleUrls: ['./component-add.component.css']
})
export class ComponentAddComponent implements OnInit {

  car: Car = {
    brand: '',
    price: 0,
    plateNumber:'',
    numberOfSeat: 0
  };

  constructor(private carService: CarService) {
  }

  ngOnInit() {
  }

  add(car): void {
  //console.log(car);
  this.carService.add(car);
}

}

//
