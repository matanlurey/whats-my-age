import java.time.Year;
import java.util.HashMap;

public final class Predictor {
  private final HashMap<String, TinyList<Record>> states;

  public Predictor(HashMap<String, TinyList<Record>> states) {
    this.states = states;
  }

  /**
   * Returns the predicted age, or -1 if there was not enough data to predict.
   */
  public int predict(String name, Record.Gender gender, String state) {
    Iterable<Record> records = states.get(state);
    if (records == null) {
      return -1;
    }
    int year = -1;
    int maxSoFar = Integer.MIN_VALUE;
    for (Record r : records) {
      if (r.name.equals(name) && r.gender.equals(gender)) {
        if (r.frequency > maxSoFar) {
          maxSoFar = r.frequency;
          year = r.year;
        }
      }
    }
    return Year.now().getValue() - year;
  }
}
