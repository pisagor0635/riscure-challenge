package com.riscure.internal.util;

import com.riscure.internal.view.Bits;
import com.riscure.internal.view.Pieces;
import com.riscure.internal.view.SelectionMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * all valid selections of radio buttons
 */
public interface CombinationUtils {
    static List<List<String>> getValidCombinations() {

        List<List<String>> validCombinations = new ArrayList<>();

        validCombinations.add(Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), ""));
        validCombinations.add(Arrays.asList(SelectionMode.BITS.getValue(), Bits.ONE.getValue(), ""));
        validCombinations.add(Arrays.asList(SelectionMode.PIECES.getValue(), "", Pieces.CES.getValue()));
        validCombinations.add(Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue()));

        return validCombinations;
    }
}
