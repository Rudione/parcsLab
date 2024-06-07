import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
    List<Integer> res;

    public Result() {
        res = new ArrayList<>();
    }

    public void addIndex(int index) {
        res.add(index);
    }

    public List<Integer> getRes() {
        return res;
    }
}
