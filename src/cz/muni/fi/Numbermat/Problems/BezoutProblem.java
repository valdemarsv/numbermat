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

import cz.muni.fi.Numbermat.Utils;
import cz.muni.fi.Numbermat.Algorithms;
import cz.muni.fi.Numbermat.AlgorithmsSteps;

/**
 * Solving Bezout's identity: finding d = gcd(a,b) and x, y such that ax + by = d.
 * Based on GCDProblem. Basis for InverseModProblem.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class BezoutProblem extends MathProblem {
    
    private GCDProblem gcdProblem;
    private int a;
    private int b;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     * @param coprimeNumbers Require generating coprime a, b (Used by InverseModProblem)
     */
    public BezoutProblem(final String difficulty, final boolean coprimeNumbers) {
        gcdProblem = new GCDProblem(difficulty, coprimeNumbers);
        setVariables();
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param aa Non-negative integer, a >= b
     * @param bb Non-negative integer
     */
    public BezoutProblem(final int aa, final int bb) {
        gcdProblem = new GCDProblem(aa, bb);
        setVariables();
    }
    
    private void setVariables() {
        a = gcdProblem.getA();
        b = gcdProblem.getB();
        result = Algorithms.bezout(a, b);
        prepareAll();
    }

    public GCDProblem getGcdProblem() {
        return gcdProblem;
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append("Nalezněte čísla d, x, y, aby").append(Utils.NEWLINE);
        sb.append(a).append("x + ").append(b).append("y = d").append(Utils.NEWLINE);
        problemPlaintext = sb.toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final StringBuilder sb = new StringBuilder(64);
        sb.append("\\text{Nalezněte čísla }$d, x, y$, \\text{aby}\\\\ \\[");
        sb.append(a).append("x + ").append(b).append("y = d\\] \\\\");
        problemLaTeX = sb.toString();
    }

    @Override
    protected void prepareSolutionPlaintext() {
        final String solutionGCD = gcdProblem.getSolutionPlaintext();
        final String solutionBezout = AlgorithmsSteps.bezoutSteps(a, b);
        final StringBuilder sb = new StringBuilder(512);
        sb.append(solutionGCD).append(solutionBezout);
        solutionPlaintext = sb.toString();
    }

    @Override
    protected void prepareSolutionLaTeX() {
        // This method does not use Utils.prepareAlignMath() !
        String solutionBezout = AlgorithmsSteps.bezoutSteps(a, b);
        solutionBezout = Utils.prepareBasicMath(solutionBezout);
        solutionBezout = solutionBezout.replaceAll("=", "&=");
        solutionBezout = Utils.replaceLast(solutionBezout, "&=", "=");
        
        String solutionGCD = gcdProblem.getSolutionLaTeX();
        final int newLineReplacementPosition = solutionGCD.lastIndexOf("{align}") + 9;
        solutionGCD = Utils.replaceLast(solutionGCD, Utils.NEWLINE + "\\end{align}",
                "\\\\" + Utils.NEWLINE);
        
        final StringBuilder sb = new StringBuilder(512);
        sb.append(solutionGCD).append(solutionBezout);
        int newlineIndex = sb.indexOf(Utils.NEWLINE, newLineReplacementPosition);
        while (newlineIndex < sb.lastIndexOf(Utils.NEWLINE)) {
            sb.insert(newlineIndex, "\\\\");
            newlineIndex = sb.indexOf(Utils.NEWLINE, newlineIndex + 3);
        }
        solutionLaTeX = sb.append("\\end{align}").toString();
    }
}
