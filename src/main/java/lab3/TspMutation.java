package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class TspMutation implements EvolutionaryOperator<TspSolution> {

    int iters = 0;
    boolean check;

    public TspMutation(boolean check) {
        this.check = check;
    }

    public List<Integer> swap_mutation(List<Integer> solution, int d1, int d2) {
        int tmp = solution.get(d1);
        solution.set(d1, solution.get(d2));
        solution.set(d2, tmp);
        return solution;
    }

    public List<Integer> insert_mutation(List<Integer> solution, int d1, int d2) {
        int n = solution.size();
        List<Integer> new_solution = new ArrayList<>(n);
        for (int i = 0; i <= d1; i++) {
            new_solution.set(i, solution.get(i));
        }
        new_solution.set(d1 + 1, solution.get(d2));
        for (int i = d1 + 1; i < d2; i++) {
            new_solution.set(i + 1, solution.get(i));
        }
        for (int i = d2 + 1; i < n; i++) {
            new_solution.set(i, solution.get(i));
        }
        return new_solution;
    }

    public List<Integer> scramble_mutation(List<Integer> solution, int d1, int d2, Random random) {
        List<Integer> new_solution = new ArrayList<>();
        for (int i = d1; i <= d2; i++) {
            new_solution.add(solution.get(i));
        }
        Collections.shuffle(new_solution, random);
        for (int i = d1; i <= d2; i++) {
            solution.set(i, new_solution.get(i - d1));
        }
        return solution;
    }

    public List<Integer> inversion_mutation(List<Integer> solution, int d1, int d2) {
        int[] slice = new int[d2 - d1 + 1];
        for (int i = d1; i <= d2; i++) {
            slice[i - d1] = solution.get(i);
        }
        for (int i = d1; i <= d2; i++) {
            solution.set(i, slice[d2 - i]);
        }
        return solution;
    }

    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        List<TspSolution> result = new ArrayList<>();
        for (int idx = 0; idx < population.size(); idx++) {
            TspSolution individual = population.get(idx);
            List<Integer> solution = new ArrayList<>(individual.getSolution());
            int n = solution.size();
            int d1 = random.nextInt(n);
            int d2 = random.nextInt(n);
            if (d1 > d2) {
                int tmp = d1;
                d1 = d2;
                d2 = tmp;
            }
//            solution = inversion_mutation(solution, d1, d2);
            if (iters < 60000) {
                solution = inversion_mutation(solution, d1, d2);
//                    solution = scramble_mutation(solution, d1, d2, random);
            } else if (iters < 80000) {
                solution = scramble_mutation(solution, d1, d2, random);

            } else {
                solution = swap_mutation(solution, d1, d2);
            }

            result.add(new TspSolution(n, solution));

            if (check) {
                Set<Integer> is_used = new HashSet<>();
                for (int c : solution) {
                    if (is_used.contains(c)) {
                        throw new Error("error");
                    } else {
                        is_used.add(c);
                    }
                }
            }
        }
        iters += 1;

        //result population
        return result;
    }
}
