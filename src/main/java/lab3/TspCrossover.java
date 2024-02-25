package lab3;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class TspCrossover extends AbstractCrossover<TspSolution> {

    private boolean check;

    protected TspCrossover(boolean check) {
        super(1);
        this.check = check;
    }

    private TspSolution orderCrossover(TspSolution p1, TspSolution p2, int d1, int d2) {
        int n = p1.getDimension();
        List<Integer> children = new ArrayList<>(n);
        Set<Integer> set_value = new HashSet<>();
        for (int i = d1; i <= d2; i++) {
            children.add(p1.getSolution().get(i));
            set_value.add(p1.getSolution().get(i));
        }
        for (int i = 0; i < n; i++) {
            int pos = (i + d2 + 1) % n;
            if (!set_value.contains(p2.getSolution().get(pos))) {
                children.add(p2.getSolution().get(pos));
            }
        }
        Collections.rotate(children, d1);
        if (check) {
            Set<Integer> is_used = new HashSet<>();
            for (int c : children) {
                if (is_used.contains(c)) {
                    throw new Error("error");
                } else {
                    is_used.add(c);
                }
            }
        }
        return new TspSolution(n, children);
    }
    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int  newIndividual, Random random) {
        int n = p1.getDimension();
        List<TspSolution> result = new ArrayList<>();
        for (int i = 0; i < newIndividual; i++) {
            int d1 = random.nextInt(n);
            int d2 = random.nextInt(n);
            if (d1 > d2) {
                int tmp = d1;
                d1 = d2;
                d2 = tmp;
            }
            result.add(orderCrossover(p1, p2, d1, d2));
        }
        return result;
    }
}
