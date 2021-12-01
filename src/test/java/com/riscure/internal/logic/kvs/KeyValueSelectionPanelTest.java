package com.riscure.internal.logic.kvs;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.riscure.internal.util.MapUtils;
import com.riscure.internal.view.Bits;
import com.riscure.internal.view.Pieces;
import com.riscure.internal.view.SelectionMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class KeyValueSelectionPanelTest {

    static KeyValueSelectionPanel keyValueSelectionPanel;

    @BeforeAll
    public static void init(){

        KeyValueSelectionSet keyValueSet = new KeyValueSelectionSet() {
            private Map<String, List<String>> keyValueSet = MapUtils.newLinkedHashMap(
                    MapUtils.<String, List<String>>newEntry("Selection mode", Lists.newArrayList("Bits", "Pieces", "Bits & Pieces")),
                    MapUtils.<String, List<String>>newEntry("Bits", Lists.newArrayList("0", "1")),
                    MapUtils.<String, List<String>>newEntry("Pieces", Lists.newArrayList("Pie", "ces"))
            );

            @Override
            public List<String> getKeys() {
                return ImmutableList.copyOf(keyValueSet.keySet());
            }

            @Override
            public List<String> getValues(String key) {
                return ImmutableList.copyOf(keyValueSet.get(key));
            }
        };

        keyValueSelectionPanel = new KeyValueSelectionPanel(keyValueSet);

    }


    @Test
    void shouldInvalidWhenInvalidCombinationSelected(){

        String selectedSelectionMode = SelectionMode.BITS.getValue();
        String selectedBits = "";
        String selectedPieces = Pieces.PIE.getValue();

        List<String> selectedValues = new ArrayList<>(Arrays.asList(selectedSelectionMode,selectedBits,selectedPieces));

        boolean result = keyValueSelectionPanel.isValidCombination(selectedValues);

        Assertions.assertEquals(false,result);

    }

    @Test
    void shouldValidWhenValidCombinationSelected(){

        String selectedSelectionMode = SelectionMode.BITS.getValue();
        String selectedBits = Bits.ZERO.getValue();
        String selectedPieces = "";

        List<String> selectedValues = new ArrayList<>(Arrays.asList(selectedSelectionMode,selectedBits,selectedPieces));

        boolean result = keyValueSelectionPanel.isValidCombination(selectedValues);

        Assertions.assertEquals(true,result);

    }

}
