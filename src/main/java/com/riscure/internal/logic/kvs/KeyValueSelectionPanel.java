package com.riscure.internal.logic.kvs;

import com.google.common.base.Predicate;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.riscure.internal.model.RadioButtonColorModel;
import com.riscure.internal.util.CombinationUtils;
import com.riscure.internal.util.ColorUtils;
import com.riscure.internal.util.MapUtils;
import com.riscure.internal.view.Bits;
import com.riscure.internal.view.Pieces;
import com.riscure.internal.view.SelectionMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
public class KeyValueSelectionPanel extends JPanel {

    // Constants for implementing de-toggle functionality on a button group
    private static final String NOT_SELECTED = "NOT_SELECTED";
    private static final String SELECTED = "SELECTED";

    // The model for the panel
    private KeyValueSelectionSet keyValueSet;

    // The label used to provide feedback to the user on whether or not a valid combination was selected
    private JLabel statusLabel;
    // A ButtonGroup per key (column)
    private BiMap<String, ButtonGroup> buttonGroups;
    // All JRadioButton on the panel, alongside their associated key-value combination
    private BiMap<Entry<String, String>, JRadioButton> radioButtons;

    //to define the click event is for deselect the invalid radio buttons
    private boolean flagForIgnoreDoClickOfRadioButtonToUnToggle = false;

    //selected radio button from key selection mode
    private String selectedSelectionMode;
    //selected radio button from key bits
    private String selectedBits;
    //selected radio button from key pieces
    private String selectedPieces;

    // Catch-all action listener for all JRadioButtons on the panel
    private ActionListener optionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectionChanged(e);
        }
    };

    public KeyValueSelectionPanel(KeyValueSelectionSet keyValueSet) {
        this.keyValueSet = keyValueSet;

        buttonGroups = HashBiMap.create();
        radioButtons = HashBiMap.create();

        this.initGrid();
    }

    /*
     * GUI CONSTRUCTION CODE
     */
    private void initGrid() {
        GridBagLayout gridBagLayout = createLayout();
        setLayout(gridBagLayout);

        // For each property
        for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
            // Create a label ("column header")
            String property = getText(columnIndex, 0);

            JLabel keyLabel = new JLabel(property);
            add(keyLabel, createConstraints(columnIndex, 0));

            ButtonGroup columnButtonGroup = new ButtonGroup();
            // For each value in this property
            for (int valueIndex = 0; valueIndex < getRowCount(property); valueIndex++) {
                // Create a radio button
                String keyValue = getText(columnIndex, valueIndex + 1);

                JRadioButton keyValueButton = new JRadioButton(keyValue);

                add(keyValueButton, createConstraints(columnIndex, valueIndex + 1));
                columnButtonGroup.add(keyValueButton);
                radioButtons.put(MapUtils.newEntry(property, keyValue), keyValueButton);

                keyValueButton.setActionCommand(NOT_SELECTED);
                keyValueButton.addActionListener(optionListener);
            }

            buttonGroups.put(property, columnButtonGroup);
        }

        statusLabel = new JLabel("Invalid combination");
        add(statusLabel, createConstraints(0, getMaxRowCount() + 2));
    }

    private GridBagLayout createLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWidths = new int[getColumnCount() * 2];

        gridBagLayout.rowHeights = new int[getMaxRowCount() + 3];

        gridBagLayout.columnWeights = new double[gridBagLayout.columnWidths.length];
        for (int columnIndex = 0; columnIndex < gridBagLayout.columnWeights.length; columnIndex += 2) {
            gridBagLayout.columnWeights[columnIndex] = 1.0;
        }
        gridBagLayout.columnWeights[gridBagLayout.columnWeights.length - 1] = Double.MIN_VALUE;

        gridBagLayout.rowWeights = new double[gridBagLayout.rowHeights.length];
        gridBagLayout.rowWeights[gridBagLayout.rowWeights.length - 1] = Double.MIN_VALUE;

        return gridBagLayout;
    }

    private GridBagConstraints createConstraints(int columnIndex, int rowIndex) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = columnIndex * 2;
        constraints.gridy = rowIndex;
        return constraints;
    }


    private int getColumnCount() {
        return keyValueSet.getKeys().size();
    }

    private int getMaxRowCount() {
        int rowCount = 0;
        for (String key : keyValueSet.getKeys()) {
            rowCount = Math.max(rowCount, getRowCount(key));
        }
        return rowCount;
    }

    private int getRowCount(String key) {
        return keyValueSet.getValues(key).size();
    }

    private String getText(int columnIndex, int rowIndex) {
        String text;

        String property = keyValueSet.getKeys().get(columnIndex);

        boolean columnHeaderRequested = 0 == rowIndex;
        if (columnHeaderRequested) {
            text = property;
        } else {
            text = keyValueSet.getValues(property).get(rowIndex - 1);
        }

        return text;
    }

    /*
     * DISPLAY LOGIC CODE
     */


    private void selectionChanged(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();
        Entry<String, String> associatedOption = radioButtons.inverse().get(source);

        // Update toggling behaviour information
        if (NOT_SELECTED.equals(source.getActionCommand())) {
            // Mark as selected
            source.setActionCommand(SELECTED);
        } else if (SELECTED.equals(source.getActionCommand())) {
            // Selected and clicked again means deselect
            buttonGroups.get(associatedOption.getKey()).clearSelection();
            source.setActionCommand(NOT_SELECTED);
            if (flagForIgnoreDoClickOfRadioButtonToUnToggle) {
                flagForIgnoreDoClickOfRadioButtonToUnToggle = false;
                return;
            }
        }

        // Deselect buttons in same column
        Enumeration<AbstractButton> columnButtons = buttonGroups.get(associatedOption.getKey()).getElements();
        while (columnButtons.hasMoreElements()) {
            AbstractButton button = columnButtons.nextElement();

            boolean buttonsAreNotSame = button != source;
            if (buttonsAreNotSame) {
                button.setActionCommand(NOT_SELECTED);
                button.setSelected(false);
            }
        }

        Map<String, String> candidate = MapUtils.newHashMap(getSelectedCombination());

        selectedSelectionMode = "";
        selectedBits = "";
        selectedPieces = "";

        if (candidate.containsKey(SelectionMode.SELECTION_MODE.getValue())) {
            selectedSelectionMode = candidate.get(SelectionMode.SELECTION_MODE.getValue());
        }
        if (candidate.containsKey(Bits.BITS.getValue())) {
            selectedBits = candidate.get(Bits.BITS.getValue());
        }
        if (candidate.containsKey(Pieces.PIECES.getValue())) {
            selectedPieces = candidate.get(Pieces.PIECES.getValue());
        }

        prepareColorInfoForRadioButtons(associatedOption, selectedSelectionMode, selectedBits, selectedPieces);
        updateIsValidLabel(selectedSelectionMode, selectedBits, selectedPieces);

    }

    /**
     * update the label whether selected combination is valid or not
     */
    private void updateIsValidLabel(String selectedSelectionMode, String selectedBits, String selectedPieces) {

        List<String> selectedValues = new ArrayList<>(Arrays.asList(selectedSelectionMode, selectedBits, selectedPieces));

        if (isValidCombination(selectedValues)) {
            statusLabel.setText("Valid combination");
            statusLabel.setForeground(Color.BLACK);
        } else {
            statusLabel.setText("Invalid combination");
            statusLabel.setForeground(Color.RED);
        }
    }

    /**
     * estimate selected combination  is whether valid or not
     */
    public boolean isValidCombination(List<String> selectedValues) {
        List<List<String>> validCombinations = CombinationUtils.getValidCombinations();
        return validCombinations.stream().anyMatch(selectedValues::equals);

    }

    /**
     * finds black radio buttons after clicking a radio button.
     */
    private void prepareColorInfoForRadioButtons(Entry<String, String> associatedOption, String selectedSelectionMode, String selectedBits, String selectedPieces) {

        List<String> blackRadioButtons;
        String clickedRadioButton = associatedOption.getValue();

        if (!clickedRadioButton.equals(Bits.ONE.getValue())) {
            if (selectedSelectionMode.equals("")
                    && selectedBits.equals(Bits.ONE.getValue())
                    && selectedPieces.equals("")) {
                blackRadioButtons = ColorUtils.getColorInfo().stream()
                        .filter(c -> c.getClickedRadioButton().equals(clickedRadioButton))
                        .filter(f -> f.getSelectedRadioButtons() != null)
                        .map(RadioButtonColorModel::getBlackRadioButtons).collect(Collectors.toList()).get(0);
            } else {
                blackRadioButtons = ColorUtils.getColorInfo().stream()
                        .filter(c -> c.getClickedRadioButton().equals(clickedRadioButton))
                        .filter(f -> f.getSelectedRadioButtons() == null)
                        .map(RadioButtonColorModel::getBlackRadioButtons).collect(Collectors.toList()).get(0);
            }
        } else {
            List<String> selectedRadioButtons = Arrays.asList(selectedSelectionMode, selectedBits, selectedPieces);
            blackRadioButtons = ColorUtils.getColorInfo().stream()
                    .filter(c -> c.getClickedRadioButton().equals(clickedRadioButton))
                    .filter(f -> f.getSelectedRadioButtons().containsAll(selectedRadioButtons))
                    .map(RadioButtonColorModel::getBlackRadioButtons).collect(Collectors.toList()).get(0);
        }

        updateColors(blackRadioButtons);
    }

    /**
     * updates colors and deselects selected radio buttons by defined logic
     */
    private void updateColors(List<String> blackRadioButtons) {
        for (JRadioButton rb : radioButtons.values()) {
            if (getSelectedCombination().size() > 0 && !blackRadioButtons.contains(rb.getText())) {
                if (rb.isSelected()) {
                    flagForIgnoreDoClickOfRadioButtonToUnToggle = true;
                    updateSelections(rb.getText());
                    rb.doClick();
                }
                rb.setForeground(Color.RED);
            } else {
                rb.setForeground(Color.BLACK);
            }
        }
    }

    /**
     * updates selections when deselects selected radio button during color updating
     */
    private void updateSelections(String deSelectedRadioButton) {
        if (selectedSelectionMode.equals(deSelectedRadioButton)) {
            selectedSelectionMode = "";
        } else if (selectedBits.equals(deSelectedRadioButton)) {
            selectedBits = "";
        } else {
            selectedPieces = "";
        }
    }

    private Set<Entry<String, String>> getSelectedCombination() {
        return Sets.newHashSet(
                Maps.filterValues(
                        radioButtons,
                        new Predicate<JRadioButton>() {
                            @Override
                            public boolean apply(JRadioButton input) {
                                return input.isSelected();
                            }
                        }
                ).keySet()
        );
    }

}
