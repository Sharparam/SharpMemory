package com.sharparam.sharpmemory.tests.helpers;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;

/**
 * Created on 2014-04-04.
 *
 * @author Sharparam
 */
public class JavaFXTester extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Do nothing
    }

    @BeforeClass
    public static void initializeJavaFX() {
        Thread jfxInitThread = new Thread("JavaFX initializer thread") {
            @Override
            public void run() {
                Application.launch(JavaFXTester.class);
            }
        };
        jfxInitThread.setDaemon(true);
        jfxInitThread.start();
    }
}
