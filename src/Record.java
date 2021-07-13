public final class Record {
  public final String state;
  public final Gender gender;
  public final int year;
  public final String name;
  public final int frequency;

  Record(String state, Gender gender, int year, String name, int frequency) {
    this.state = state;
    this.gender = gender;
    this.year = year;
    this.name = name;
    this.frequency = frequency;
  }

  public enum Gender {
    Female,
    Male,
  }
}
