package com.play.gameprogect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MuvieApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MuvieApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 372, 195);
        stage.setTitle("Dog-Maks");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        // відслідковуємо нажаті клавіші рухаємо зображення

        scene.setOnKeyPressed(e -> { // обробник дії
            if (e.getCode() == KeyCode.SPACE && !MarioController.jump) // обробляємо пробіл
                MarioController.jump = true;
            if (e.getCode() == KeyCode.A) // відслідковуємо натиск клавіші а
                MarioController.left = true;
            if (e.getCode() == KeyCode.D) // відслідковуємо натиск клавіші d
                MarioController.right = true;
        });
        // далі обробник в момент відтискання кнопки

        scene.setOnKeyReleased(e -> { // обробник дії

            if (e.getCode() == KeyCode.A) // відслідковуємо відтиск клавіші а
                MarioController.left = false;
            if (e.getCode() == KeyCode.D) // відслідковуємо відтиск клавіші d
                MarioController.right = false;
        });



        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}