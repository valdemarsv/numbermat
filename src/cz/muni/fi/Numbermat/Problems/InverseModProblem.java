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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

/**
 * Finding an inverse to a number modulo n.
 * Based on BezoutProblem.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class InverseModProblem extends MathProblem {
    
    private BezoutProblem bezoutProblem;
    private int a;
    private int b;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public InverseModProblem(final String difficulty) {
        bezoutProblem = new BezoutProblem(difficulty, true);
        setVariables();
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param aa Positive integer
     * @param bb Positive integer, (aa, bb) = 1
     */
    public InverseModProblem(final int aa, final int bb) {
        bezoutProblem = new BezoutProblem(aa, bb);
        setVariables();
    }
    
    private void setVariables() {
        a = bezoutProblem.getGcdProblem().getA();
        b = bezoutProblem.getGcdProblem().getB();
        
        final List<Integer> bezout = bezoutProblem.getResult();
        result = new ArrayList(1);
        result.add(Algorithms.normalizeIntModulo(bezout.get(2), a));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder sb = new StringBuilder("[").append(b).append("]^(-1)");
        sb.append(AlgorithmsSteps.buildModLineEnd(a)).append(" = ");
        problemPlaintext = sb.toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        StringBuilder sb = new StringBuilder("\\[[").append(b).append("]^{-1}_{");
        sb.append(a).append("} = \\]\\\\");
        problemLaTeX = sb.toString();
    }

    @Override
    protected void prepareSolutionPlaintext() {
        final String solutionBezout = bezoutProblem.getSolutionPlaintext();
        final Pattern pattern = Pattern.compile("x = .*", Pattern.DOTALL);
        final String replacement = prepareSolutionMatcher(solutionBezout, pattern);
        
        final StringBuilder sb = new StringBuilder(256);
        sb.append(Utils.replaceLast(problemPlaintext, Utils.NEWLINE, ""));
        sb.append(result.get(0)).append(Utils.NEWLINE);
        solutionPlaintext = Utils.replaceLast(solutionBezout, replacement, sb.toString());
    }

    @Override
    protected void prepareSolutionLaTeX() {
        final String solutionBezout = bezoutProblem.getSolutionLaTeX();
        final Pattern pattern = Pattern.compile("x &= .*");
        final String replacement = prepareSolutionMatcher(solutionBezout, pattern);
        
        final StringBuilder sb = new StringBuilder("[").append(b).append("]^{-1}_{");
        sb.append(a).append("} &= ").append(result.get(0));
        solutionLaTeX = Utils.replaceLast(solutionBezout, replacement, sb.toString());
    }
    
    private static String prepareSolutionMatcher(final String solutionBezout, final Pattern pattern) {
        final Matcher matcher = pattern.matcher(solutionBezout);
        matcher.find();
        return matcher.group(0);
    }
}
