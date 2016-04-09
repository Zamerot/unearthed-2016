package com.thales.window;

import com.thales.model.Manifest;
import com.thales.model.Vessel;

/**
 * Created by Administrator on 9/04/2016.
 */
public interface IVesselOptimizationController {

    void setIteration(double fitness, Manifest manifest, Vessel vessel);
}
