/**
 * Run KeyValueSelectionMain.java
 * <p>
 * You will see a panel with three columns of which the column headers are "Selection mode", "Bits" and "Pieces". These headers are called keys in this exercise.
 * Every column contains a number of {@link javax.swing.JRadioButton}s. The labels of these JRadioButtons are  called values in this exercise.
 * For example, the values associated to key "Bits" are "0" and "1".
 * <p>
 * When a user is presented with this user interface, he is supposed to toggle a valid combination of JRadioButtons.
 * In this specific example only four valid combinations exist, which are:
 * <ul><li>{Selection mode - Bits, Bits - 0} </li>
 * <li>{Selection mode - Bits, Bits - 1} </li>
 * <li>{Selection mode - Pieces, Pieces - ces} </li>
 * <li>{Selection mode - Bits &amp; Pieces, Bits - 1, Pieces - Pie} </li></ul>
 * <b>Note</b>: It can be assumed that valid combinations have at most one value toggled per key.
 * <p>
 * A user of this UI does not know beforehand which specific combinations are valid.
 * The aim of this exercise is to provide the user with visual feedback that will help him to select a valid combination.
 * The visual feedback should be as follows:
 * <ul>
 *     <li>Given the current (toggled) combination of the user, display the labels of the JRadioButtons which, when toggled, would make creating a valid combination impossible, in the colour red. Display the labels of all other JRadioButtons in the default colour.
 *     <ul>
 *         <li>For example: If the current selection is {Bits - 1}, {Selection mode - pieces} and {Pieces - ces} should be marked red, because there is no valid combination with both {Bits - 1} and either of those two options.</li>
 *         <li>For example: If the current selection is {Bits - 0}, the labels of the JRadioButtons {Selection mode - Bits} and {Bits - 1} should be displayed in black. {Bits - 1} should be displayed in black, because toggling it would force {Bits - 0} to be untoggled.</li>
 *     </ul></li>
 *     <li>A user is always allowed to toggle and untoggle any JRadioButton on the panel. When a user toggles a 'red' JRadioButton, all toggled JRadioButtons that cannot form a valid combination with the 'red' JRadioButton should be untoggled.
 *     <ul>
 *         <li>For example: If the current selection is {Selection mode - Bits, Bits - 1}, and the user toggles {Pieces - Pie}, then {Selection mode - Bits} should be untoggled but {Bits - 1} should remain toggled.</li>
 *     </ul></li>
 *     <li>If, and only if, the user has selected a valid combination, a label on the panel should indicate that he has done so.</li>
 * </ul>
 * <p>
 * A part of the above behaviour has already been implemented. The goal of this exercise is to complete this implementation.
 * More specifically, complete the implementation of {@link com.riscure.internal.logic.kvs.KeyValueSelectionPanel#selectionChanged(ActionEvent)} and {@link com.riscure.internal.logic.kvs.KeyValueSelectionPanel#isValidCombination(java.util.Map)}. A TODO tag can be found in these methods.
 * <p>
 * The combinations that a user can currently select are fixed to the four combinations given earlier.
 * It is expected that in the future valid combinations will be defined in a more flexible way, for example by retrieving them from a data source (e.g. a file).
 */
package com.riscure.internal.logic.kvs;