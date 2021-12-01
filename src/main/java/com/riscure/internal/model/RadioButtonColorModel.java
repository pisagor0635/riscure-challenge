package com.riscure.internal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RadioButtonColorModel {

    private String clickedRadioButton;

    List<String> selectedRadioButtons;

    List<String> blackRadioButtons;
}
