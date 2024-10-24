package cs3500.threetrios.model;

public enum Color {
  RED("red"), BLUE("blue");

  private final String color;
  Color(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }
}
