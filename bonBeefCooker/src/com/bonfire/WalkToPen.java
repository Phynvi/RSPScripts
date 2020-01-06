package com.bonfire;

import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class WalkToPen extends Task {

    private Area cowPenArea = Area.polygonal(
            new Position(3265, 3296, 0),
            new Position(3265, 3255, 0),
            new Position(3253, 3255, 0),
            new Position(3253, 3272, 0),
            new Position(3251, 3274, 0),
            new Position(3251, 3276, 0),
            new Position(3249, 3278, 0),
            new Position(3246, 3278, 0),
            new Position(3244, 3280, 0),
            new Position(3244, 3281, 0),
            new Position(3240, 3285, 0),
            new Position(3240, 3286, 0),
            new Position(3241, 3287, 0),
            new Position(3241, 3288, 0),
            new Position(3242, 3289, 0),
            new Position(3242, 3292, 0),
            new Position(3242, 3293, 0),
            new Position(3241, 3294, 0),
            new Position(3241, 3295, 0),
            new Position(3240, 3296, 0),
            new Position(3240, 3297, 0),
            new Position(3240, 3298, 0),
            new Position(3241, 3298, 0),
            new Position(3263, 3298, 0));

    @Override
    public boolean validate() {
        Player localPlayer = Players.getLocal();

        // If our inventory is empty and we're not at the cow pen, then we should head there
        return Inventory.isEmpty() && !cowPenArea.contains(localPlayer.getPosition());
    }

    @Override
    public int execute() {
        // Turn on run before we start if we can
        if (Movement.getRunEnergy() > Random.nextInt(7,13) && !Movement.isRunEnabled()){
            Log.fine("Turning on run");
            Movement.toggleRun(true);
            return Random.nextInt(1000,2500);
        }

        // If we're executing, we need to walk to the cow pen!
        Log.fine("Running to the cow pen");
        Movement.walkToRandomized(cowPenArea.getCenter());

        // Sleep for a random amount of time
        return Random.nextInt(1000,3000);
    }
}