package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {

    public Problem problem;

    public TspFitnessFunction(Problem problem) {
        this.problem = problem;
    }

    private double calc_dist(int idx_first, int idx_second) {
        Problem.Node node_first = this.problem.nodes.get(idx_first);
        Problem.Node node_second = this.problem.nodes.get(idx_second);
        return Math.sqrt(Math.pow(node_first.x - node_second.x, 2) + Math.pow(node_first.y - node_second.y, 2));
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        double fitness = 0.;
        List<Integer> idxs = solution.getSolution();
        int pred_idx = idxs.get(idxs.size() - 1);
        for (int idx : idxs) {
            fitness += calc_dist(pred_idx, idx);
            pred_idx = idx;
        }

        solution.setFitness(fitness);

        return fitness;
    }

    public boolean isNatural() {
        return false;
    }
}
