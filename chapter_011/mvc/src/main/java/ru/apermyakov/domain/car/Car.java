package ru.apermyakov.domain.car;

import javax.persistence.*;

@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String make;

    private String model;

    private int year;

    @Column(name = "enginetype")
    private String engine;

    private int horsepower;

    @Column(name = "gearboxtype")
    private String gearbox;

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;
        if (year != car.year) return false;
        if (horsepower != car.horsepower) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) return false;
        return gearbox != null ? gearbox.equals(car.gearbox) : car.gearbox == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + year;
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + horsepower;
        result = 31 * result + (gearbox != null ? gearbox.hashCode() : 0);
        return result;
    }
}
