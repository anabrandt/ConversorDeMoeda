package com.example;

import java.io.IOException;

import com.vaadin.ui.ComboBox;

import javafx.fxml.FXML;

public class PrimaryController {
    private static final double dolarReal = 4.90;
    private static final double euroReal = 5.39;
    private static final double euroDolar = 1.10;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
 private ComboBox<String> fromCurrencyComboBox;
    private ComboBox<String> toCurrencyComboBox;
    private TextField amountTextField;
    private Label resultLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Conversor de Moedas");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        fromCurrencyComboBox = new ComboBox<>();
        fromCurrencyComboBox.getItems().addAll("BRL", "USD", "EUR");
        fromCurrencyComboBox.setValue("BRL");

        toCurrencyComboBox = new ComboBox<>();
        toCurrencyComboBox.getItems().addAll("BRL", "USD", "EUR");
        toCurrencyComboBox.setValue("USD");

        amountTextField = new TextField();
        amountTextField.setPromptText("Digite o valor");

        resultLabel = new Label();

        grid.add(new Label("De:"), 0, 0);
        grid.add(fromCurrencyComboBox, 1, 0);
        grid.add(new Label("Para:"), 0, 1);
        grid.add(toCurrencyComboBox, 1, 1);
        grid.add(amountTextField, 0, 2);
        grid.add(resultLabel, 1, 2);

        fromCurrencyComboBox.setOnAction(event -> convertCurrency());
        toCurrencyComboBox.setOnAction(event -> convertCurrency());
        amountTextField.setOnAction(event -> convertCurrency());

        Scene scene = new Scene(grid, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void convertCurrency() {
        try {
            String fromCurrency = fromCurrencyComboBox.getValue();
            String toCurrency = toCurrencyComboBox.getValue();
            double amount = Double.parseDouble(amountTextField.getText());

            double convertedAmount = convert(fromCurrency, toCurrency, amount);
            resultLabel.setText(String.format("%.2f %s = %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
        } catch (NumberFormatException e) {
            resultLabel.setText("Valor inválido");
        }
    }

    private double convert(String fromCurrency, String toCurrency, double amount) {
        if (fromCurrency.equals("BRL")) {
            if (toCurrency.equals("USD")) {
                return amount / USD_TO_BRL;
            } else if (toCurrency.equals("EUR")) {
                return amount / EUR_TO_BRL;
            }
        } else if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("BRL")) {
                return amount * USD_TO_BRL;
            } else if (toCurrency.equals("EUR")) {
                return amount * EUR_TO_USD;
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("BRL")) {
                return amount * EUR_TO_BRL;
            } else if (toCurrency.equals("USD")) {
                return amount / EUR_TO_USD;
            }
        }

        return amount;
    }
}
