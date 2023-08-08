package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class Euro {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Euro");
    }
}