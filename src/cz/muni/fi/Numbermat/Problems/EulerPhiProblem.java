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

/**
 * Euler's totient (phi) function.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class EulerPhiProblem extends MathProblem {
    
    private int n;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public EulerPhiProblem(final String difficulty) {
        easyBounds = new Pair(1, 99);
        mediumBounds = new Pair(100, 999);
        hardBounds = new Pair(1000, 2999);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        final int nn = Algorithms.randInt(lowerBound, upperBound);
        setVariables(nn);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param nn Positive integer
     */
    public EulerPhiProblem(final int nn) {
        setVariables(nn);
    }
    
    private void setVariables(final int nn) {
        n = nn;
        result = new ArrayList<>(1);
        result.add(Algorithms.eulerPhi(n));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        problemPlaintext = AlgorithmsSteps.buildPhiEquals(n).toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        problemLaTeX = Utils.prepareDisplayMath(problemPlaintext);
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.eulerPhiSteps(n);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        if (n == 1) {
            solutionLaTeX = new StringBuilder("\\begin{align}").append(Utils.NEWLINE)
                    .append("\\varphi(1) &= 1").append("\\end{align}").toString();
        } else {
            solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
            solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
        }
    }
}
