/**
 Design motivations

 -New button combinations should be added easily
 -Main methods such as isValid,  should not be modified on every new combination
 -The code should be readable and easily testable
 -code quality is important, therefore the code should be compliant with Solid design principles

 Preparation

 -An enum for each column on the GUI is created in the view package.
 -Util classes, one for colorizing, another for valid combinations are created in the util package.
 -In the ColorUtils class, all color options that may occur according to the selection of a radio button are defined.
 -In the CombinationUtils class, all valid selections of radio buttons are defined.
 -A RadioButtonColorModel class is created in the model package. In this class, three variables are defined.

 clickedRadioButton :  is used to keep the clicked radio button.

 selectedRadioButtons : is a list of strings. Is used for keeping the selected radio buttons. For example, If Bits is selected from  selection mode and 0 is selected from Bits then :
 selectedRadioButtons = [“Bits”,”0”,””]

 blackRadioButtons : radio buttons that will turn black according to the selected combination.
 RadioButtonColorModel is used in ColorUtils class.

 Logic

 -In KeyValueSelectionPanel class the logic for updating the isvalid label and color of radio buttons is implemented.
 -In the updateIsValidLabel method the isvalid label is updated according to whether selected radio buttons are valid combinations or not.
 -In the isValidCombination method, whether selected radio buttons are valid combinations or not is found by using Combination Utils getValidCombinations method.
 -For updating the color  of radio buttons, prepareColorInfoForRadioButtons and updateColors methods are implemented. In the prepareColorInfoForRadioButtons method ColorUtils, getColorInfo method is used. In this method the black radio buttons are found.
 -In the updateColors method, the colors and deselected the selected invalid radio buttons are updated.

 TEST

 -The KeyValueSelectionPanelTest  class is added  into com.riscure.internal.logic.kvs package.
 -Two tests  shouldInvalidWhenInvalidCombinationSelected and shouldValidWhenValidCombinationSelected are implemented.
 */
package com.riscure.internal.logic.kvs;