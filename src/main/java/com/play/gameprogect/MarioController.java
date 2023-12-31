package com.play.gameprogect;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MarioController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bj1, bj2, player, enemy1, enemy2;

    @FXML
    private Label labelPause, labelLose; // змінні буде ссилатися на обьект (кнопка пауза)

    private final int BJ_WITH = 356;

    private ParallelTransition parallelTransition;

    private TranslateTransition enemyTranzition;

   // private TranslateTransition enemyTranzition2;

    // змінні для прижка
    public static boolean right = false;
    public static boolean left = false;

    public static boolean jump = false;

    public static boolean isPause = false;

    private int playerSpeed = 3, jumpDownSpeed = 5;

    // створюємо сам обьект і виділяємо для нього память

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) { // буде виконуватись постійно поки йде
            if (jump && player.getLayoutY() > 25f) // підняття маріо
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if (player.getLayoutY() <= 80f) { // якщо гравець вище цієї координати опускаємо вниз
                jump = false;
                player.setLayoutY(player.getLayoutY() + jumpDownSpeed); // тут вже двігаємо до низу гравця
            }
            if (right && player.getLayoutX() < 250f) // куди рухаємся + обмеження по горизогталі
                player.setLayoutX(player.getLayoutX() + playerSpeed); // якби змінюємо координату по х
            if (left && player.getLayoutX() > 28f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);
            // якщо isPause тру і кнопка не видна то робимо її видимою
            if (isPause && !labelPause.isVisible()) {
                playerSpeed = 0; // при паузі змінюємо данні на 0
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTranzition.pause();
               // enemyTranzition2.pause();
                labelPause.setVisible(true);
            } else if (!isPause && labelPause.isVisible()) {
                playerSpeed = 3;
                jumpDownSpeed = 5;
                parallelTransition.play();
                enemyTranzition.play();
               // enemyTranzition2.play();
                labelPause.setVisible(false);

            }
              // тут відслідковуємо дотик одного обьекту з іншим
            if(player.getBoundsInParent().intersects(enemy1.getBoundsInParent())){
                labelLose.setVisible(true);
                playerSpeed = 0;
                jumpDownSpeed = 0;
                parallelTransition.pause();
                enemyTranzition.pause();
            }
        }
    };

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

        enemyTranzition =
                new TranslateTransition(Duration.millis(2500), enemy1);
        // далі задаємо характеристики
        enemyTranzition.setFromX(0);// з якої точки старт анімаціїї 0 - с того же місця де обьект
        enemyTranzition.setToX(BJ_WITH * -1 - 100); // enemy буде виходити за ширину екрану
        enemyTranzition.setInterpolator(Interpolator.LINEAR); // формат анімаціїї LINEAR - лінійна анімація
        enemyTranzition.setCycleCount(Animation.INDEFINITE); // анімація безкінечна
        enemyTranzition.play();

      /*  enemyTranzition2 =
                new TranslateTransition(Duration.millis(3500), enemy2);
        // далі задаємо характеристики
        enemyTranzition2.setFromX(0);// з якої точки старт анімаціїї 0 - с того же місця де обьект
        enemyTranzition2.setToX(BJ_WITH * -1 - 100); // enemy буде виходити за ширину екрану
        enemyTranzition2.setInterpolator(Interpolator.LINEAR); // формат анімаціїї LINEAR - лінійна анімація
        enemyTranzition2.setCycleCount(Animation.INDEFINITE); // анімація безкінечна
        enemyTranzition2.play(); */

        // далі ніби синхронізуємо анімації
        parallelTransition = new ParallelTransition(bjFirstTranzition, bjSecondTranzition);
        parallelTransition.setCycleCount(Animation.INDEFINITE); // безкінечне число повторів
        parallelTransition.play();

        timer.start(); // запуск таймера в момент запуска вікна


    }
}