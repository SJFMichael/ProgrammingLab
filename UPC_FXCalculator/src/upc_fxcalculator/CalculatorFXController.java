/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upc_fxcalculator;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Administrator
 */
public class CalculatorFXController implements Initializable {

	private float operatorResult;
	private String textTop;
	private String textBottom;
	private boolean shouldCalculate;
	private boolean sct;

	private enum operator {

		NUN, SUM, SUBTRACT, MULTIPLY, DIVIDE, SCT
	};
	operator cal;

	private Label label;
	@FXML
	private TextField resultTF;
	@FXML
	private TextField calculatingTF;
	@FXML
	private Button buttonCos;
	@FXML
	private Button buttonSin;
	@FXML
	private Button buttonTan;
	@FXML
	private Button buttonAC;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button buttonSum;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button buttonSubtract;
	@FXML
	private Button button7;
	@FXML
	private Button button8;
	@FXML
	private Button button9;
	@FXML
	private Button buttonMultiply;
	@FXML
	private Button buttonDot;
	@FXML
	private Button button0;
	@FXML
	private Button buttonEqual;
	@FXML
	private Button buttonDivide;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		operatorResult = 0;
		textTop = "";
		textBottom = "0";
		shouldCalculate = true;
		sct = false;
		cal = operator.NUN;
		//resultTF.setStyle("-fx-background-color: -fx-control-inner-background;");
		//calculatingTF.setStyle("-fx-background-color: -fx-control-inner-background;");
	}

	@FXML
	private void numbersButtonListener(ActionEvent event) {
		String s = ((Button) event.getSource()).getText();
		System.out.println(s);

		if (textBottom.equals("0")) {
			textBottom = s;
			if (textBottom.equals(".")) {
				textBottom = "0.";
			}
			resultTF.setText(textBottom);
		} else if (s.equals(".")) {
			if (!textBottom.contains(".")) {
				textBottom += s;
				resultTF.setText(textBottom);
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		} else {
			textBottom += s;
			resultTF.setText(textBottom);
		}
		shouldCalculate = true;
	}

	@FXML
	private void commandsButtonListener(ActionEvent event) {
		String s = ((Button) event.getSource()).getText();
		System.out.println(s);
		switch (s) {
			case "+":
				if (shouldCalculate) {
					textBottom = "0";
					if (sct) {
						textTop += " + ";
						sct = false;
					} else {
						textTop += resultTF.getText() + " + ";
					}
					switch (cal) {
						case SUM:
							operatorResult += Float.parseFloat(resultTF.getText());
							//System.out.println("RST1 : " + resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							//System.out.println("Cal1 : " + cal);
							break;
						case SUBTRACT:
							operatorResult -= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case MULTIPLY:
							operatorResult *= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case DIVIDE:
							operatorResult /= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case NUN:
							operatorResult = Float.parseFloat(resultTF.getText());
							//System.out.println("OP1 : " + operatorResult);
							//System.out.println("Cal5 : " + cal);
							break;
					}
					cal = operator.SUM;
					calculatingTF.setText(textTop);
					shouldCalculate = false;
				} else {
					textBottom = "0";
					textTop = textTop.substring(0, textTop.length() - 3);
					textTop += " + ";
					calculatingTF.setText(textTop);
					cal = operator.SUM;
				}
				break;
			case "-":
				if (shouldCalculate) {
					textBottom = "0";
					if (sct) {
						textTop += " - ";
						sct = false;
					} else {
						textTop += resultTF.getText() + " - ";
					}
					switch (cal) {
						case SUM:
							operatorResult += Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case SUBTRACT:
							operatorResult -= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case MULTIPLY:
							operatorResult *= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case DIVIDE:
							operatorResult /= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case NUN:
							operatorResult = Float.parseFloat(resultTF.getText());
							break;
					}
					cal = operator.SUBTRACT;
					calculatingTF.setText(textTop);
					shouldCalculate = false;
				} else {
					textBottom = "0";
					textTop = textTop.substring(0, textTop.length() - 3);
					textTop += " - ";
					calculatingTF.setText(textTop);
					cal = operator.SUBTRACT;
				}
				break;
			case "*":
				if (shouldCalculate) {
					textBottom = "0";
					if (sct) {
						textTop += " * ";
						sct = false;
					} else {
						textTop += resultTF.getText() + " * ";
					}
					switch (cal) {
						case SUM:
							operatorResult += Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case SUBTRACT:
							operatorResult -= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case MULTIPLY:
							operatorResult *= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case DIVIDE:
							operatorResult /= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case NUN:
							operatorResult = Float.parseFloat(resultTF.getText());
							break;
					}
					cal = operator.MULTIPLY;
					calculatingTF.setText(textTop);
					shouldCalculate = false;
				} else {
					textBottom = "0";
					textTop = textTop.substring(0, textTop.length() - 3);
					textTop += " * ";
					calculatingTF.setText(textTop);
					cal = operator.MULTIPLY;
				}
				break;
			case "/":
				if (shouldCalculate) {
					textBottom = "0";
					if (sct) {
						textTop += " / ";
						sct = false;
					} else {
						textTop += resultTF.getText() + " / ";
					}
					switch (cal) {
						case SUM:
							operatorResult += Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case SUBTRACT:
							operatorResult -= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case MULTIPLY:
							operatorResult *= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case DIVIDE:
							operatorResult /= Float.parseFloat(resultTF.getText());
							resultTF.setText(String.valueOf(operatorResult));
							break;
						case NUN:
							operatorResult = Float.parseFloat(resultTF.getText());
							break;
					}
					cal = operator.DIVIDE;
					calculatingTF.setText(textTop);
					shouldCalculate = false;
				} else {
					textBottom = "0";
					textTop = textTop.substring(0, textTop.length() - 3);
					textTop += " / ";
					calculatingTF.setText(textTop);
					cal = operator.DIVIDE;
				}
				break;
			case "=":
				textBottom = "0";
				textTop = "";
				switch (cal) {
					case SUM:
						operatorResult += Float.parseFloat(resultTF.getText());
						break;
					case SUBTRACT:
						operatorResult -= Float.parseFloat(resultTF.getText());
						break;
					case MULTIPLY:
						operatorResult *= Float.parseFloat(resultTF.getText());
						break;
					case DIVIDE:
						operatorResult /= Float.parseFloat(resultTF.getText());
						break;
					case NUN:
						operatorResult = Float.parseFloat(resultTF.getText());
						break;
				}
				cal = operator.NUN;
				resultTF.setText(String.valueOf(operatorResult));
				operatorResult = 0;
				calculatingTF.setText(textTop);
				shouldCalculate = true;
				break;

			case "AC":
				textBottom = "0";
				textTop = "";
				cal = operator.NUN;
				operatorResult = 0;
				resultTF.setText(textBottom);
				calculatingTF.setText(textTop);
				shouldCalculate = true;
				break;

			case "Sin":
				if (shouldCalculate) {
					textTop += "Sin(" + resultTF.getText() + ")";
					Float fs = new Float(Float.parseFloat(resultTF.getText()));
					double ds = fs.doubleValue();
					double xs = Math.toRadians(ds);
					ds = Math.sin(xs);
					calculatingTF.setText(textTop);
					resultTF.setText(String.format("%.3f", ds));
					sct = true;
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
				break;

			case "Cos":
				if (shouldCalculate) {
					textTop += "Cos(" + resultTF.getText() + ")";
					Float fc = new Float(Float.parseFloat(resultTF.getText()));
					double dc = fc.doubleValue();
					double xc = Math.toRadians(dc);
					dc = Math.cos(xc);
					calculatingTF.setText(textTop);
					resultTF.setText(String.format("%.3f", dc));
					sct = true;
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
				break;

			case "Tan":
				if (shouldCalculate) {
					textTop += "Tan(" + resultTF.getText() + ")";
					Float ft = new Float(Float.parseFloat(resultTF.getText()));
					double dt = ft.doubleValue();
					double xt = Math.toRadians(dt);
					dt = Math.tan(xt);
					calculatingTF.setText(textTop);
					resultTF.setText(String.format("%.3f", dt));
					sct = true;
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
				break;
		}
	}

}
