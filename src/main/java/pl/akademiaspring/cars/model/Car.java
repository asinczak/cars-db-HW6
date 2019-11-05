package pl.akademiaspring.cars.model;

public class Car {

  private long id;
  private String mark;
  private String model;
  private String color;
  private long productionYear;

  public Car(long id, String mark, String model, String color, long productionYear) {
    this.id = id;
    this.mark = mark;
    this.model = model;
    this.color = color;
    this.productionYear = productionYear;
  }

  public Car() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }


  public long getProductionYear() {
    return productionYear;
  }

  public void setProductionYear(long productionYear) {
    this.productionYear = productionYear;
  }

}
