module com.play.gameprogect {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.play.gameprogect to javafx.fxml;
    exports com.play.gameprogect;
}