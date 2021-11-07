package th.ac.ku.hotel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import th.ac.ku.hotel.controller.HomeController;

public class JavaFxApplication extends Application  {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(HotelApplication.class)
                .run(args);

    }

    @Override
    public void stop() throws Exception {
        this.applicationContext.close();
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws RuntimeException {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HomeController.class);
        Scene scene = new Scene(root,900,700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hotel Booking Application");
        primaryStage.setResizable(false);

        primaryStage.show();

    }
}
