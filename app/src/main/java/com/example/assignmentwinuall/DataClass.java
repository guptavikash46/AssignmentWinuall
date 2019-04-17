package com.example.assignmentwinuall;



public class DataClass {

    private String cityName;
    private Double speed;
    private Double temp;
    private Double pressure;
    private Integer humidity;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "DataClass{" +
                "cityName='" + cityName + '\'' +
                ", speed=" + speed +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
