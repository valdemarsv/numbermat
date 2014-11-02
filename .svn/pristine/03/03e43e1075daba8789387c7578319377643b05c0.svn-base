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
 * Modular exponentiation.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class ModularPowerProblem extends MathProblem {
    
    private int base;
    private int exp;
    private int mod;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public ModularPowerProblem(final String difficulty) {
        easyBounds = new Pair(5, 11);
        mediumBounds = new Pair(11, 37);
        hardBounds = new Pair(37, 97);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        final int b = Algorithms.randInt(lowerBound, upperBound);
        final int e = Algorithms.randInt(lowerBound, upperBound);
        int m = Algorithms.randInt(lowerBound, upperBound);
        if (Algorithms.randInt(1, 5) == 1)
            m = 100;
        setVariables(b, e, m);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param b Integer
     * @param e Non-negative integer
     * @param m Positive integer
     */
    public ModularPowerProblem(final int b, final int e, final int m) {
        setVariables(b, e, m);
    }
    
    private void setVariables(final int b, final int e, final int m) {
        base = b;
        exp = e;
        mod = m;
        result = new ArrayList<>(1);
        result.add(Algorithms.modPow(b, e, m));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder lineStart = AlgorithmsSteps.buildModPowLineStart(base, exp);
        final StringBuilder lineEnd = AlgorithmsSteps.buildModLineEnd(mod);
        problemPlaintext = lineStart.append("x").append(lineEnd).toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final String tmp = Utils.prepareCongruencesMath(problemPlaintext);
        problemLaTeX = Utils.prepareDisplayMath(tmp);
    }

    @Override
    protected void prepareSolutionPlaintext() {
        final StringBuilder sb = new StringBuilder(problemPlaintext);
        sb.append(AlgorithmsSteps.SEPARATOR);
        sb.append(AlgorithmsSteps.modPowSteps(base, exp, mod));
        solutionPlaintext = sb.toString();
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
        solutionLaTeX = Utils.prepareCongruencesMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignatMath(solutionLaTeX);
    }
}
