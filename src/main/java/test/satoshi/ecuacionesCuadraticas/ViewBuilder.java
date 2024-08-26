package test.satoshi.ecuacionesCuadraticas;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Builder;
import java.util.Objects;


public class ViewBuilder implements Builder<Region> {

    private final Model model;

    public ViewBuilder(Model model){
        this.model = model;
    }

    @Override
    public Region build(){
        VBox result = new VBox(6,
                createEquationPrompt(),
                createEquationRow(),
                createLabelDiscriminante(),
                createOutputRow());
        result.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles.css")).toExternalForm());
        result.setAlignment(Pos.CENTER);
        result.setPadding(new Insets(10));
        result.setPrefWidth(175);
        return result;
    }

    private Region createEquationPrompt(){
        Label result = styledLabel("Inserte los parámetros de la ecuación", "prompt-label");
        result.setWrapText(true);
        result.setTextAlignment(TextAlignment.CENTER);
        result.setMinWidth(170);
        return result;
    }

    private Region createEquationRow(){
        HBox equationRow = new HBox(3,
                inputTextField("a", model.AProperty()),
                equationLabel("x² + "),
                inputTextField("b", model.BProperty()),
                equationLabel("x + "),
                inputTextField("c", model.CProperty()));

        equationRow.setAlignment(Pos.CENTER);

        return equationRow;
    }

    private Region createLabelDiscriminante(){
        Label result = styledLabel("", "discr-label");
        result.textProperty().bind(Bindings.createStringBinding(() -> "D = " + model.getD(), model.DProperty()));
        return result;
    }

    private Region createOutputRow(){
        HBox outputRow = new HBox(10,
            createOutputField("x₁ =", model.x1Property(), model.X1EnabledProperty()),
            createOutputField("x₂ =", model.x2Property(), model.X2EnabledProperty()));
        outputRow.setAlignment(Pos.CENTER);
        return outputRow;
    }

    private Region createOutputField(String text, StringProperty bindProperty, BooleanProperty disableProperty){
        HBox result = new HBox(3,
                outputLabel(text),
                outputTextField(bindProperty, disableProperty));
        HBox.setHgrow(result, Priority.ALWAYS);
        result.setAlignment(Pos.CENTER);
        return result;
    }

    private TextField inputTextField(String prompt, StringProperty bindProperty){
        TextField result = shortTextField();
        result.setPromptText(prompt);
        result.textProperty().bindBidirectional(bindProperty);
        return result;
    }

    private TextField outputTextField(StringProperty bindProperty, BooleanProperty disableProperty){
        TextField result = shortTextField();
        result.textProperty().bind(bindProperty);
        result.disableProperty().bind(disableProperty.not());
        result.setEditable(false);
        return result;
    }

    private TextField shortTextField(){
        TextField result = new TextField();
        result.setMinWidth(33);
        return result;
    }

    private Label equationLabel(String text){
        Label result = styledLabel(text, "prompt-label");
        result.setMinWidth(33);
        return result;
    }

    private Label outputLabel(String text){
        Label result = styledLabel(text, "prompt-label");
        result.setMinWidth(30);
        return result;
    }

    private Label styledLabel(String text, String styleClass){
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        return label;
    }
}
