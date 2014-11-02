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

/**
 * Binomial congruence of form x^n ≡ a (mod m).
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class BinomialCongruenceProblem extends MathProblem {
    
    private int n;
    private int a;
    private int m;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public BinomialCongruenceProblem(final String difficulty) {
        easyBounds = new Pair(2, 11);
        mediumBounds = new Pair(9, 19);
        hardBounds = new Pair(2, 37);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        final int nn = Algorithms.randInt(3, 5);
        int aa, mm;
        while (true) {
            aa = Algorithms.randInt(lowerBound, upperBound);
            if (difficulty.equals(Config.HARD))
                mm = Algorithms.generateModulus(false); // 6 to 95
            else
                mm = Algorithms.randPrime(lowerBound, upperBound);
            if (!Algorithms.binomialCongruence(nn, aa, mm).isEmpty())
                break;
        }
        setVariables(nn, aa, mm);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param nn Positive integer
     * @param aa Integer
     * @param mm Positive integer > 1 so that primitive roots mod m exist
     */
    public BinomialCongruenceProblem(final int nn, final int aa, final int mm) {
        setVariables(nn, aa, mm);
    }
    
    private void setVariables(final int nn, final int aa, final int mm) {
        n = nn;
        a = aa;
        m = mm;
        result = Algorithms.binomialCongruence(n, a, m);
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder lineEnd = AlgorithmsSteps.buildModLineEnd(m);
        problemPlaintext = AlgorithmsSteps.buildBinomialCongruence(
                1, 'x', n, 0, -a, lineEnd).toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final String tmp = Utils.prepareCongruencesMath(problemPlaintext);
        problemLaTeX = Utils.prepareDisplayMath(tmp).toString();
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.binomialCongruenceSteps(n, a, m);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
        solutionLaTeX = Utils.prepareCongruencesMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareFractionsMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignatMath(solutionLaTeX);
    }
}
