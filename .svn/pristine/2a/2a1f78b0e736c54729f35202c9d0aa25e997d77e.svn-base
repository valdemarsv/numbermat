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
 * Legendre symbol.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class LegendreSymbolProblem extends MathProblem {
    
    private int a;
    private int p;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public LegendreSymbolProblem(final String difficulty) {
        easyBounds = new Pair(2, 49);
        mediumBounds = new Pair(10, 79);
        hardBounds = new Pair(49, 119);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        final int aa = Algorithms.randInt(lowerBound, upperBound);
        final int pp = Algorithms.randPrime(lowerBound, upperBound);
        setVariables(aa, pp);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param aa Integer
     * @param pp Odd prime
     */
    public LegendreSymbolProblem(final int aa, final int pp) {
        setVariables(aa, pp);
    }
    
    private void setVariables(final int aa, final int pp) {
        a = aa;
        p = pp;
        result = new ArrayList<>(1);
        result.add(Algorithms.legendreSymbol(a, p));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        problemPlaintext = AlgorithmsSteps.buildLegendreSymbolEquals(a, p).toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final String tmp = Utils.prepareFractionsMath(problemPlaintext);
        problemLaTeX = Utils.prepareDisplayMath(tmp);
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.legendreSymbolSteps(a, p);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
        solutionLaTeX = Utils.prepareFractionsMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
    }
}
