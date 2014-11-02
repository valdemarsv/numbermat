/*
    This file is part of Numbermat: Math Problem Generator.
    Copyright © 2014 Valdemar Svabensky

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package cz.muni.fi.Numbermat.Problems;

import cz.muni.fi.Numbermat.Algorithms;
import cz.muni.fi.Numbermat.AlgorithmsSteps;
import cz.muni.fi.Numbermat.GUI.Config;
import cz.muni.fi.Numbermat.Pair;
import cz.muni.fi.Numbermat.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * System of 'i' linear congruences of type a_i*x ≡ b_i (mod n_i).
 * System of 1 congruence is a basis for LinearCongruenceProblem.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class LinearCongruenceSystemProblem extends MathProblem {
    
    private int equationCount;       // Positive integer
    private List<Integer> aList;     // List of integers
    private List<Integer> bList;     // List of integers
    private List<Integer> nList;     // List of positive integers
    private boolean regularSolution; // False when the system has infinite / empty solution
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public LinearCongruenceSystemProblem(final String difficulty) {
        easyBounds = new Pair(2, 15);
        mediumBounds = new Pair(15, 25);
        hardBounds = new Pair(10, 30);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        int equationCount1 = 2;
        if (difficulty.equals(Config.HARD))
            equationCount1 = 3;
        
        List<Integer> aList1, bList1, nList1;
        while (true) {
            aList1 = new ArrayList<>(equationCount1);
            bList1 = new ArrayList<>(equationCount1);
            nList1 = new ArrayList<>(equationCount1);
            for (int i = 0; i < equationCount1; ++i) {
                int aa = Algorithms.randInt(lowerBound, upperBound);
                if (difficulty.equals(Config.EASY))
                    aa = 1;
                int bb = Algorithms.randInt(lowerBound, upperBound);
                int nn = Algorithms.randInt(lowerBound, upperBound);
                aList1.add(aa);
                bList1.add(bb);
                nList1.add(nn);
            }
            // If generated system has a solution
            final Pair<Integer, Integer> solution =
                    Algorithms.linearCongruenceSystem(equationCount1, aList1, bList1, nList1);
            if (!solution.isEmpty())
                break;
        }
        setVariables(equationCount1, aList1, bList1, nList1);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param equationCount1 Number of equations, positive integer
     * @param aList1 List of integers
     * @param bList1 List of integers
     * @param nList1 List of positive integers
     */
    public LinearCongruenceSystemProblem(final int equationCount1, final List<Integer> aList1,
            final List<Integer> bList1, final List<Integer> nList1) {
        setVariables(equationCount1, aList1, bList1, nList1);
    }
    
    private void setVariables(final int equationCount1, final List<Integer> aList1,
            final List<Integer> bList1, final List<Integer> nList1) {
        
        equationCount = equationCount1;
        aList = new ArrayList<>(aList1);
        bList = new ArrayList<>(bList1);
        nList = new ArrayList<>(nList1);
        result = new ArrayList<>();
        regularSolution = false;
        final Pair<Integer, Integer> solution =
                Algorithms.linearCongruenceSystem(equationCount, aList, bList, nList);
        if (!solution.isEmpty()) {
            result.add(solution.getFirst());
            result.add(solution.getSecond());
            if (!solution.equals(new Pair<>(0, 1)))
                regularSolution = true;
        }
        prepareAll();
    }
    
    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < equationCount; ++i) {
            final int a = aList.get(i);
            final int b = bList.get(i);
            final int n = nList.get(i);
            final StringBuilder lineEnd = AlgorithmsSteps.buildModLineEnd(n);
            sb.append(AlgorithmsSteps.buildLinearCongruence(a, 'x', b, lineEnd));
        }
        problemPlaintext = sb.toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        problemLaTeX = Utils.prepareCongruencesMath(problemPlaintext);
        problemLaTeX = Utils.prepareAlignMath(problemLaTeX);
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.linearCongruenceSystemSteps(
                equationCount, aList, bList, nList);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
        solutionLaTeX = Utils.prepareCongruencesMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
        solutionLaTeX = solutionLaTeX.replace("|", "\\mid");
        solutionLaTeX = solutionLaTeX.replace("∤", "\\nmid");
        if ((regularSolution) && 
                ((equationCount != 1) || (!solutionLaTeX.contains("&="))))
            solutionLaTeX = Utils.prepareAlignatMath(solutionLaTeX);
    }
}
