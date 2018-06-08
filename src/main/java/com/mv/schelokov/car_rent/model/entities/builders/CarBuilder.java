package com.mv.schelokov.car_rent.model.entities.builders;

import com.mv.schelokov.car_rent.model.entities.Car;

/**
 *
 * @author Maxim Chshelokov <schelokov.mv@gmail.com>
 */
public class CarBuilder {
    
    private Car car;
    
    public CarBuilder() {
        this.car = new Car();
    }
    
    public CarBuilder setId(int id) {
        this.car.setId(id);
        return this;
    }
    
    public CarBuilder setModel(int model) {
        this.car.setModel(model);
        return this;
    }
    
    public CarBuilder setLicensePlate(String licensePlate) {
        this.car.setLicensePlate(licensePlate);
        return this;
    }
    
    public CarBuilder setYearOfMake(int year) {
        this.car.setYearOfMake(year);
        return this;
    }
    
    public CarBuilder setPrice(int price) {
        this.car.setPrice(price);
        return this;
    }
    
    public Car getCar() {
        return this.car;
    }
    
}