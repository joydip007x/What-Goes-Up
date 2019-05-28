
package project250;

import java.awt.Button;
import static java.awt.Color.RED;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.sql.JDBCType.NULL;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Project250 extends Application {
    
    private final HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private final ArrayList<Node> blocks = new ArrayList<>();
    private final Pane appRoot = new Pane();
    private final Pane gameRoot = new Pane();
    private final Pane UI = new Pane();
    private Node ball;
    
    private Point2D ballVelocity = new Point2D(0, 0);
    private boolean canJump = true;
    private int levelWidth,levelHeight;
    private void createUI(){
        
        levelWidth = 60 * Level.LEVEL2[0].length();
        levelHeight=720 ;
        for (int i=0; i< Level.LEVEL2.length; i++){
            String line = Level.LEVEL2[i];
            for (int j=0; j <line.length();j++){
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Node platform = createRectangle(j*60, i *60, 60, 60, getRandomColor.color() );
                        blocks.add(platform);
                        break;
                }
            }
        }
    }
     private void createLevel(){
         
        Image background = null;
        try (InputStream imagefile = Files.newInputStream(Paths.get("C:\\Users\\joydi\\Documents\\NetBeansProjects\\Project250\\src\\resources\\images\\a.jpg"))) {
            background = new Image(imagefile);
        } 
        catch (IOException ex) {
            System.out.println(" YO YO 2i BRO , got soum  ERROR ! ");
         }
        ImageView showBackground = new ImageView(background);
        showBackground.setFitWidth(levelWidth);
        showBackground.setFitHeight(levelHeight);
        
        ball=createCircle(0,600,40,Color.DARKRED);
        ball.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 640 && offset < levelWidth-640){
                gameRoot.setLayoutX(-(offset-640));
            }
        });
        appRoot.getChildren().addAll( showBackground,gameRoot, UI);
    }
    private void update(){
        if (isPressed(KeyCode.W) && ball.getTranslateY() >= 5){
            jumpPlayer();
        }
        if (isPressed(KeyCode.A) && ball.getTranslateX() >=5){
            moveBallX(-5);
        }
        if (isPressed(KeyCode.D) && ball.getTranslateX() + 40 <=levelWidth-5){
            moveBallX(5);
        }
        if (ballVelocity.getY() < 10){
            ballVelocity = ballVelocity.add(0, .85);
        }
        moveBallY((int)ballVelocity.getY());
        }

    private void moveBallX(int value){
    boolean movingRight = value > 0;
    for (int i=0; i < Math.abs(value);i++){
        for (Node platform : blocks){
            if(ball.getBoundsInParent().intersects(platform.getBoundsInParent())){
                if(movingRight){
                    if (ball.getTranslateX() + 40-5 == platform.getTranslateX()){
                        return;
                    }
                }else {
                    if (ball.getTranslateX() == platform.getTranslateX() + 60-5) {
                        return;
                    }
                }
            }
        }
        ball.setTranslateX(ball.getTranslateX() + (movingRight ? 1 : -1));
        }
    }
    private void moveBallY(int value){
        boolean movingDown = value > 0;
        for (int i=0; i < Math.abs(value);i++){
            for (Node platform : blocks){
                if(ball.getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(movingDown){
                        if (ball.getTranslateY() + 40 == platform.getTranslateY()){
                            canJump = true;
                            return;
                        }
                    }else {
                        if (ball.getTranslateY()-5 == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            ball.setTranslateY(ball.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    private void jumpPlayer(){
    if(canJump){
        ballVelocity = ballVelocity.add(0, -30);
        canJump = false;
        }
    }
    private Node createRectangle(int x, int y, int w, int h, Color color){
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        gameRoot.getChildren().add(entity);
        return entity;

    }
    private Node createCircle(int x, int y, int w, Color color){
        Circle entity = new Circle(w);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);
        gameRoot.getChildren().add(entity);
        return entity;

    }
    private boolean isPressed(KeyCode key){
    return keys.getOrDefault(key, false);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
       
        createUI();
        createLevel();
        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        primaryStage.setTitle("WhatGoesUP V2.0");
        primaryStage.setScene(scene);
        primaryStage.show();
        AnimationTimer tiktok = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        tiktok.start();
    }
    public static void main(String[] args) {
        
//     JButton b1=new JButton("WOW");
//     JFrame frame=new JFrame();
//     frame.setSize(1280, 720);
//     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
//     frame.add(b1);
//     frame.setVisible(true);
//     b1.addActionListener(new ActionListener() {
//         
//         @Override
//         public void actionPerformed(ActionEvent e) {
//                 launch(args);
//         }
//        });
//        int x;
//        Scanner scan=new Scanner(System.in);
//        x=scan.nextInt();
        CallMEByYourName.main(args);
//        launch(args);
    }

    
}

