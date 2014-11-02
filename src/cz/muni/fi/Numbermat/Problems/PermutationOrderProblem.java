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
import cz.muni.fi.Numbermat.Pair;
import cz.muni.fi.Numbermat.Utils;
import java.util.ArrayList;
import java.util.List;

/**
 * Finding order of a permutation.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class PermutationOrderProblem extends MathProblem {
    
    private List<Integer> permutation;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public PermutationOrderProblem(final String difficulty) {
        easyBounds = new Pair(2, 5);
        mediumBounds = new Pair(6, 8);
        hardBounds = new Pair(9, 9);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        final int size = Algorithms.randInt(lowerBound, upperBound);
        final List<Integer> perm = Algorithms.randPermutation(size);
        setVariables(perm);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param perm Permutation (a subgroup of Sn for n >= 1)
     */
    public PermutationOrderProblem(final List<Integer> perm) {
        setVariables(perm);
    }
    
    private void setVariables(final List<Integer> perm) {
        permutation = new ArrayList<>(perm);
        result = new ArrayList<>(1);
        result.add(Algorithms.permutationOrder(permutation));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Určete řád permutace ").append(AlgorithmsSteps.SIGMA).append(" = ");
        final String matrix = AlgorithmsSteps.permutationToMatrix(permutation);
        sb.append(Utils.NEWLINE).append(matrix);
        problemPlaintext = sb.toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\\text{Určete řád permutace }$\\sigma =$ \\\\");
        String matrix = AlgorithmsSteps.permutationToMatrix(permutation);
        matrix = Utils.prepareMatrix(matrix);
        sb.append(Utils.NEWLINE).append(Utils.prepareDisplayMath(matrix));
        problemLaTeX = sb.toString();
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.permutationOrderSteps(permutation);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        final String solution = solutionPlaintext.replaceAll(AlgorithmsSteps.CIRC, "\\\\circ");
        solutionLaTeX = Utils.prepareAlignMath("\\sigma = " + solution);
        if (Algorithms.permutationCycles(permutation).size() > 1)
            solutionLaTeX = Utils.replaceLast(solutionLaTeX, "&=", "=");
    }
}
