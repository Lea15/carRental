import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { CARS } from './mock-cars';
import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http: HttpClient) {}

  getCars(): Promise<Car[]>{
    return Promise.resolve(CARS);
  }

  getCar(plateNumber: string): Promise<Car> {
    return Promise.resolve(CARS.find(car => car.plateNumber==plateNumber));
  }

  getCarsWithObservable(): Observable<any> {
    return this.http.get("http://localhost:8000/cars").map((response: Response) => response);
  }

   getCarWithObservable(plateNumber): Observable<any> {
      return this.http.get("http://localhost:8000/cars/"+plateNumber).map((response: Response) => response);
   }

   add(car): void{
     CARS.push(car);
     console.log(CARS);

    this.http.post("http://localhost:8000/cars", 
    {
        "brand": car.brand,
        "plateNumber": car.plateNumber,
        "price": car.price,
        "numberOfSeat": car.numberOfSeat
    })
    .subscribe(
        (val) => {
            console.log("POST call successful value returned in body", 
                        val);
        },
        response => {
            console.log("POST call in error", response);
        },
        () => {
            console.log("The POST observable is now completed.");
        });

   }

}
