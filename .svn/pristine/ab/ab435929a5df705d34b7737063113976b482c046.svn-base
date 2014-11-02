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
 * Finding order of an element in a group Zn^×.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public final class UnitGroupElementOrderProblem extends MathProblem {
    
    private int element;
    private int n;
    
    /**
     * Instantiate with pseudo-randomly generated parameters.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     */
    public UnitGroupElementOrderProblem(final String difficulty) {
        easyBounds = new Pair(5, 11);
        mediumBounds = new Pair(11, 37);
        hardBounds = new Pair(37, 97);
        
        final Pair<Integer, Integer> bounds = initBounds(difficulty);
        final int lowerBound = bounds.getFirst();
        final int upperBound = bounds.getSecond();
        
        int element1 = 0;
        int n1 = 0;
        while ((!Algorithms.isCoprime(element1, n1)) || (element1 > n1)) {
            element1 = Algorithms.randInt(2, 9);
            n1 = Algorithms.randInt(lowerBound, upperBound);
        }
        setVariables(element1, n1);
    }
    
    /**
     * Instantiate with user provided parameters.
     * @param element1 Positive integer
     * @param n1 Integer > 1
     */
    public UnitGroupElementOrderProblem(final int element1, final int n1) {
        setVariables(element1, n1);
    }
    
    private void setVariables(final int element1, final int n1) {
        element = element1;
        n = n1;
        result = new ArrayList<>(1);
        result.add(Algorithms.unitGroupElementOrder(element, n));
        prepareAll();
    }

    @Override
    protected void prepareProblemPlaintext() {
        final StringBuilder sb = new StringBuilder("Určete řád prvku [");
        sb.append(element).append("] v grupě Z");
        sb.append(n).append("×.").append(Utils.NEWLINE);
        problemPlaintext = sb.toString();
    }

    @Override
    protected void prepareProblemLaTeX() {
        final StringBuilder sb = new StringBuilder("\\text{Určete řád prvku }[");
        sb.append(element).append("]\\\\ \\text{ v grupě }$\\mathbb{Z}_{");
        sb.append(n).append("}^\\times $\\text{.}").append(Utils.NEWLINE);
        problemLaTeX = sb.toString();
    }

    @Override
    protected void prepareSolutionPlaintext() {
        solutionPlaintext = AlgorithmsSteps.unitGroupElementOrderSteps(element, n);
    }

    @Override
    protected void prepareSolutionLaTeX() {
        solutionLaTeX = solutionPlaintext.replaceAll("φ", "\\\\varphi");
        solutionLaTeX = prepareSetsMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareCongruencesMath(solutionLaTeX);
        solutionLaTeX = Utils.prepareAlignMath(solutionLaTeX);
        solutionLaTeX = Utils.replaceLast(solutionLaTeX, "&=", "=");
        
        final StringBuilder sb = new StringBuilder(solutionLaTeX);
        fitToLine(sb, sb.indexOf(", "), sb.indexOf("Řád grupy"), 8);
        fitToLine(sb, sb.indexOf("Možné řády prvků"), sb.indexOf("Řád prvku"), 9);
        solutionLaTeX = sb.toString();
    }
    
    private static String prepareSetsMath(final String input) {
        String tmp = input.replaceAll("Z", "\\\\mathbb{Z}_{");
        //tmp = tmp.replaceAll("\\{", "\\\\{");
        //tmp = tmp.replaceAll("\\}", "\\\\}");
        tmp = tmp.replaceAll("\\×", "}^\\\\times");
        tmp = tmp.replaceAll("\\*", "}^\\*");
        if (tmp.contains("Řád")) {
            tmp = tmp.replaceAll("Řád grupy", "\\\\text{Řád grupy}");
            tmp = tmp.replaceAll("Možné řády prvků", "\\\\text{Možné řády prvků}");
            tmp = tmp.replaceAll("Řád prvku", "\\\\text{Řád prvku }");
            tmp = tmp.replaceAll("je", "&\\\\text{ je }");
            tmp = tmp.replaceAll(":", "&\\\\colon");
        }
        return tmp;
    }
    
    private static void fitToLine(final StringBuilder sb, int startIndex, final int stopIndex, final int itemsOnLine) {
        int counter = 1;
        while ((startIndex != -1) && (startIndex < stopIndex)) {
            if (counter % itemsOnLine == 0)
                sb.replace(startIndex, startIndex + 2, ",\\\\&");
            startIndex += 2;
            startIndex = sb.indexOf(", ", startIndex);
            counter++;
        }
    }
}
