/*
 * Code by: William Colachicco
 * Animation of planets rotating around a sun.
 * Button to start a parallel animation and button to start sequential animation.
 * Stop and pause buttons.
 * 
 */

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String[] args) 
	{
		launch(args);
	}
	
	@Override
	public void start(Stage stage)
	{
		Pane pane = new Pane();
		
		Circle sun = new Circle(400, 240, 30);
		sun.setFill(Color.YELLOW);
		sun.setStroke(Color.BLACK);
		
		Circle earth = new Circle(500, 260, 18);
		earth.setFill(Color.LIGHTBLUE);
		earth.setStroke(Color.BLACK);
		
		Circle saturn = new Circle(700, 460, 28);
		saturn.setFill(Color.LIGHTCORAL);
		saturn.setStroke(Color.BLACK);
		
		Circle venus = new Circle(470, 110, 15);
		venus.setFill(Color.BISQUE);
		venus.setStroke(Color.BLACK);
		pane.getChildren().addAll(sun, earth, venus);
		
		Arc arcEarth = new Arc(300, 260, 200, 100, 0, 360);
		arcEarth.setStroke(Color.rgb(210, 210, 210));
		arcEarth.setFill(null);
		
		Arc arcSaturn = new Arc(320, 230, 170, 115, 0, 360);
		arcSaturn.setStroke(Color.rgb(210, 210, 210));
		arcSaturn.setFill(null);
		arcSaturn.setRotate(-25.0);
		
		Arc arcVenus = new Arc(340, 210, 170, 115, 0, 360);
		arcVenus.setStroke(Color.rgb(210, 210, 210));
		arcVenus.setFill(null);
		arcVenus.setRotate(-35.0);
		
		PathTransition pt1 = new PathTransition();
		pt1.setDuration(Duration.millis(5000));
		pt1.setPath(arcEarth);
		pt1.setNode(earth);
		pt1.setInterpolator(Interpolator.LINEAR);
		
		PathTransition pt2 = new PathTransition();
		pt2.setDuration(Duration.millis(5000));
		pt2.setPath(arcSaturn);
		pt2.setNode(saturn);
		pt2.setInterpolator(Interpolator.LINEAR);
		
		PathTransition pt3 = new PathTransition();
		pt3.setDuration(Duration.millis(5000));
		pt3.setPath(arcVenus);
		pt3.setNode(venus);
		pt3.setInterpolator(Interpolator.LINEAR);
		
		SequentialTransition seqTransition = new SequentialTransition();
		seqTransition.getChildren().addAll(pt1, pt2, pt3);
		seqTransition.setCycleCount(Timeline.INDEFINITE);
		seqTransition.setAutoReverse(false);
		
		ParallelTransition pTransition = new ParallelTransition();
		pTransition.getChildren().addAll(pt1, pt2, pt3);
		pTransition.setCycleCount(Timeline.INDEFINITE);
		pTransition.setAutoReverse(false);
		
		Pane paneAnimate = new Pane();
		paneAnimate.setMinSize(600,  400);
		paneAnimate.getChildren().addAll(sun, arcEarth, arcVenus, arcSaturn, saturn, earth, venus);
		
		Button startSeqBtn = new Button("Start Sequential Animation");
		startSeqBtn.setOnAction((ActionEvent e) -> {
			pTransition.stop();
			seqTransition.play();
		});
		
		Button startPBtn = new Button("Start Parallel Animation");
		startPBtn.setOnAction((ActionEvent e) -> {
			seqTransition.stop();
			pTransition.play();
		});
		
		Button pauseButton = new Button("Pause");
		pauseButton.setOnAction((ActionEvent e) -> {
			seqTransition.pause();
			pTransition.pause();
		});
		
		Button stopButton = new Button("Stop");
		stopButton.setOnAction((ActionEvent e) -> {
			seqTransition.stop();
			pTransition.stop();
		});
		
		HBox hbP = new HBox();
		hbP.setSpacing(10);
		hbP.setPadding(new Insets(10, 0, 0, 10));
		hbP.getChildren().addAll(startSeqBtn, startPBtn, pauseButton, stopButton);
		
		VBox vb = new VBox();
		vb.setSpacing(10);
		vb.setPadding(new Insets(10, 0, 0, 10));
		vb.getChildren().addAll(paneAnimate, hbP);

		pTransition.play();
		
		Scene scene = new Scene(vb, 600, 600);
		stage.setTitle("Planets");
		stage.setScene(scene);
		stage.show();
		
	}

}
