package com.play.gameprogect;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class DogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bj1, bj2, player;

    private final int BJ_WITH = 356;

    private ParallelTransition parallelTransition;

    @FXML
    void initialize() { // тут створюємо анімацію

        // Duration.millis(5000) час який буде йти анімація + для якого обьекта

        TranslateTransition bjFirstTranzition =
                new TranslateTransition(Duration.millis(5000), bj1);
        // далі задаємо характеристики
        bjFirstTranzition.setFromX(0);// з якої точки старт анімаціїї 0 - с того же місця де обьект
        bjFirstTranzition.setToX(BJ_WITH * -1); // до якого моменту йде
        bjFirstTranzition.setInterpolator(Interpolator.LINEAR); // формат анімаціїї LINEAR - лінійна анімація


        TranslateTransition bjSecondTranzition =
                new TranslateTransition(Duration.millis(5000), bj2);
        // далі задаємо характеристики
        bjSecondTranzition.setFromX(0);// з якої точки старт анімаціїї 0 - с того же місця де обьект
        bjSecondTranzition.setToX(BJ_WITH * -1); // до якого моменту йде
        bjSecondTranzition.setInterpolator(Interpolator.LINEAR); // формат анімаціїї LINEAR - лінійна анімація

        // далі ніби синхронізуємо анімації
        parallelTransition = new ParallelTransition(bjFirstTranzition, bjSecondTranzition);
        parallelTransition.setCycleCount(Animation.INDEFINITE); // безкінечне число повторів
        parallelTransition.play();


    }
}