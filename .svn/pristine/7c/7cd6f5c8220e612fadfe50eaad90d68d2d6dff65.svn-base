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

import cz.muni.fi.Numbermat.GUI.Config;
import cz.muni.fi.Numbermat.Pair;
import java.util.List;

/**
 * Generic class for math problem.
 * 
 * @author Valdemar Svabensky <395868(at)mail(dot)muni(dot)cz>
 */
public abstract class MathProblem {
    
    /**
     * Bounds to random number generation for easy difficulty.
     */
    protected static Pair<Integer, Integer> easyBounds;
    
    /**
     * Bounds to random number generation for medium difficulty.
     */
    protected static Pair<Integer, Integer> mediumBounds;
    
    /**
     * Bounds to random number generation for hard difficulty.
     */
    protected static Pair<Integer, Integer> hardBounds;
    
    /**
     * Math problem in plaintext form.
     */
    protected String problemPlaintext;
    
    /**
     * Math problem in LaTeX form.
     */
    protected String problemLaTeX;
    
    /**
     * Solution to math problem in plaintext form.
     */
    protected String solutionPlaintext;
    
    /**
     * Solution to math problem in LaTeX form.
     */
    protected String solutionLaTeX;
    
    /**
     * Number(s) specifying result.
     */
    protected List<Integer> result;

    /**
     * Fills variable problemPlaintext with content.
     */
    protected abstract void prepareProblemPlaintext();
    
    /**
     * Fills variable problemLaTeX with content.
     */
    protected abstract void prepareProblemLaTeX();
    
    /**
     * Fills variable solutionPlaintext with content.
     */
    protected abstract void prepareSolutionPlaintext();
    
    /**
     * Fills variable solutionLaTeX with content.
     */
    protected abstract void prepareSolutionLaTeX();
    
    /**
     * Initializes all class variables.
     */
    protected final void prepareAll() {
        prepareProblemPlaintext();
        prepareProblemLaTeX();
        prepareSolutionPlaintext();
        prepareSolutionLaTeX();
    }
    
    public final String getProblemPlaintext() {
        return problemPlaintext;
    }

    public final String getProblemLaTeX() {
        return problemLaTeX;
    }

    public final String getSolutionPlaintext() {
        return solutionPlaintext;
    }

    public final String getSolutionLaTeX() {
        return solutionLaTeX;
    }
    
    public final List<Integer> getResult() {
        return result;
    }
    
    /**
     * Initializes difficulty bounds.
     * @param difficulty One of Config.EASY, Config.MEDIUM, Config.HARD
     * @return One of easyBounds, mediumBounds, hardBounds
     */
    protected static final Pair<Integer, Integer> initBounds(final String difficulty) {
        switch (difficulty) {
            case Config.EASY:   return easyBounds;
            case Config.MEDIUM: return mediumBounds;
            case Config.HARD:   return hardBounds;
            default:
                throw new IllegalArgumentException("Unknown difficulty.");
        }
    }
}
