package test.satoshi.ecuacionesCuadraticas;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.concurrent.Callable;


public class ViewBuilder implements Builder<Region> {

    private final Model model;
//    private final Runnable calcFunction;
	
    public ViewBuilder(Model model){
        this.model = model;
//        this.calcFunction = calcFunct;
    }

    @Override
    public Region build(){
        VBox result = new VBox(6,
                createEquationPrompt(),
                createEquationRow(),
                createOutputRow());
        result.setAlignment(Pos.CENTER);
        result.setPadding(new Insets(10));
        result.setPrefWidth(175);
        return result;
    }

    private Region createEquationPrompt(){
        Label result = styledLabel("Inserte los valores de a, b y c", "");
        result.setMinWidth(152);
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

    private Region createCalculateButton(){
        Button result = new Button("Calcular");
        result.setMaxWidth(Double.MAX_VALUE);
        result.disableProperty().bind(model.okToCalculateProperty().not());
//        result.setOnAction(evt -> calcFunction.run());
        return result;
    }

    private Region createOutputRow(){
        HBox outputRow = new HBox(10,
            createOutputField("x1 =", model.x1Property(), model.X1EnabledProperty()),
            createOutputField("x2 =", model.x2Property(), model.X2EnabledProperty()));
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
        result.setMinWidth(24);
        return result;
    }

    private Label equationLabel(String text){
        Label result = styledLabel(text, "");
        result.setMinWidth(25);
        return result;
    }

    private Label outputLabel(String text){
        Label result = styledLabel(text, "");
        result.setMinWidth(24);
        return result;
    }

    private Label styledLabel(String text, String styleClass){
        Label label = new Label(text);
        label.setStyle(styleClass);
        return label;
    }
}
