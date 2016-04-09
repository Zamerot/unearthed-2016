package com.thales.utils;

import javafx.application.Platform;

import java.util.concurrent.Executor;

/**
 * Created by Administrator on 9/04/2016.
 */
public class FXExecutor implements Executor {

    public static final FXExecutor INSTANCE = new FXExecutor();

    private FXExecutor()
    {

    }


    @Override
    public void execute(Runnable command) {
        if(Platform.isFxApplicationThread())
        {
            command.run();
        }
        else
        {
            Platform.runLater(command);
        }
    }
}
