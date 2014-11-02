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
import cz.muni.fi.Numbermat.Pair;
import java.util.ArrayList;

/**
 * Greatest common divisor of 2 integers.
 * Basis for BezoutProblem and InverseModProblem
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class GCDProblem extends MathProblem {

    private int a;
    private int b;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     * @param coprimeNumbers Require generating coprime a, b (Used by InverseModProblem)
     */
    public GCDProblem(final String difficulty, final boolean coprimeNumbers) {
        easyBounds = new Pair(1, 127);
        mediumBounds = new Pair(128, 1023);
        hardBounds = new Pair(512, 4095);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        int aa, bb;
        if (coprimeNumbers) {
            final Pair<Integer, Integer> coprimes =
                    Algorithms.randCoprime(lowerBound, upperBound);
            aa = coprimes.getFirst();
            bb = coprimes.getSecond();
        } else {
            aa = Algorithms.randInt(lowerBound, upperBound);
            bb = Algorithms.randInt(lowerBound, upperBound);
        }
        setVariables(aa, bb);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param aa Integer
     * @param bb Integer
     */
    public GCDProblem(final int aa, final int bb) {
        setVariables(aa, bb);
    }
    
    private void setVariables(final int aa, final int bb) {
        if (bb < aa) {
            a = aa;
            b = bb;
        } else {
            a = bb;
            b = aa;
        }
        result = new ArrayList(1);
        result.add(Algorithms.gcd(a, b));
        prepareAll();
    }
    
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
    
    @Override
    protected void prepareProblemPlaintext() {
        problemPlaintext = AlgorithmsSteps.buildGCDEquals(a, b).toString();
    }
    
    @Override
    protected void prepareProblemLaTeX() {
        problemLaTeX = Utils.prepareDisplayMath(problemPlaintext);
    }
    
    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.gcdSteps(a, b);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = Utils.prepareBasicMath(solutionPlaintext);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
    }
}
