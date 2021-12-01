package com.riscure.internal.util;

import com.riscure.internal.model.RadioButtonColorModel;
import com.riscure.internal.view.Bits;
import com.riscure.internal.view.Pieces;
import com.riscure.internal.view.SelectionMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * all color options that may occur according to the selection of a radio button
 */
public interface ColorUtils {

    static List<RadioButtonColorModel> getColorInfo() {

        List<RadioButtonColorModel> colorList = new ArrayList<>();

        colorList.add(new RadioButtonColorModel(SelectionMode.BITS.getValue(), null, Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue())));
        colorList.add(new RadioButtonColorModel(SelectionMode.BITS.getValue(), Arrays.asList("", Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));


        colorList.add(new RadioButtonColorModel(SelectionMode.PIECES.getValue(), null, Arrays.asList(SelectionMode.PIECES.getValue(), Pieces.CES.getValue())));

        colorList.add(new RadioButtonColorModel(SelectionMode.BITS_PIECES.getValue(), null, Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(SelectionMode.BITS_PIECES.getValue(), Arrays.asList("", Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));

        colorList.add(new RadioButtonColorModel(Bits.ZERO.getValue(), null, Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue())));

        colorList.add(new RadioButtonColorModel(Pieces.PIE.getValue(), null, Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Pieces.PIE.getValue(), Arrays.asList("", Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));

        colorList.add(new RadioButtonColorModel(Pieces.CES.getValue(), null, Arrays.asList(SelectionMode.PIECES.getValue(), Pieces.CES.getValue())));

        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList("", Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.PIECES.getValue(), Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.PIECES.getValue(), Bits.ONE.getValue(), Pieces.CES.getValue()), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), ""), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList("", Bits.ONE.getValue(), Pieces.CES.getValue()), Arrays.asList(SelectionMode.BITS.getValue(), Bits.ZERO.getValue(), Bits.ONE.getValue(), SelectionMode.BITS_PIECES.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList("", Bits.ONE.getValue(), Pieces.PIE.getValue()), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue()), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));
        colorList.add(new RadioButtonColorModel(Bits.ONE.getValue(), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), "", Pieces.PIE.getValue()), Arrays.asList(SelectionMode.BITS_PIECES.getValue(), Bits.ONE.getValue(), Pieces.PIE.getValue())));

        return colorList;
    }
}
