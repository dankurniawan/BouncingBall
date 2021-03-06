import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application{
	
	private static final int X_LAYOUT_SIZE = 600;
	private static final int Y_LAYOUT_SIZE = 300;
	private static final int INIT_X_BALL_POS = 10;
	private static final int INIT_Y_BALL_POS = 10;
	
	double ballRadius = 10;
	Timeline timeline;
	double ballSpeed = 0;
	double ballAcceleration = 0.1;
	double xSpeed = 1;
	double restitutionCoefficient = 0.95;
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Circle ball = new Circle(ballRadius);
		Pane pane = new Pane();
		pane.getChildren().add(ball);
		ball.setLayoutX(INIT_X_BALL_POS);
		ball.setLayoutY(INIT_Y_BALL_POS);
		Scene scene = new Scene(pane);
		pane.setPrefHeight(Y_LAYOUT_SIZE);
		pane.setPrefWidth(X_LAYOUT_SIZE);
		stage.setScene(scene);
		stage.show();
	
		timeline = new Timeline(new KeyFrame(Duration.millis(5), e -> drop(ball, pane)));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	private void drop(Circle ball, Pane pane){
		
		ball.setLayoutX(ball.getLayoutX() + xSpeed);
		
		if (collide(ball, pane) || ballSpeed == 0){
			ballSpeed = - (int) (ballSpeed * restitutionCoefficient);
		}
		
		if (collide(ball, pane) && ballSpeed == 0){
			timeline.stop();
		}
		
		ballSpeed = ballSpeed + ballAcceleration;
		ball.setLayoutY(ball.getLayoutY() + ballSpeed);
		
		
	}
	
	private boolean collide(Circle ball, Pane pane){
		
		return (ball.getLayoutY() + ballRadius + 10 > pane.getHeight()); //+10 is a buffer region, which somehow I need to add.
		
	}
	
	
}
