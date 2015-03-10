package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import drones.models.Drone;
import drones.models.Fleet;
import play.*;
import play.libs.F;
import play.libs.Json;
import play.mvc.*;

import scala.util.parsing.json.JSON;
import scala.util.parsing.json.JSONObject$;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static F.Promise<Result> initDrone() {
        Drone d = Fleet.getFleet().createBepop("bepop", "192.168.42.1", true);
        return F.Promise.wrap(d.init()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("status", "ok");
            return ok(result);
        });
    }

    public static F.Promise<Result> getBatteryPercentage(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.getBatteryPercentage()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("batteryPercentage", v);
            return ok(result);
        });
    }

    public static F.Promise<Result> getLocation(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.getLocation()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("long", v.getLongtitude());
            result.put("lat", v.getLatitude());
            result.put("altitude", v.getHeigth());
            return ok(result);
        });
    }

    public static F.Promise<Result> getAltitude(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.getAltitude()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("altitude", v);
            return ok(result);
        });
    }

    public static F.Promise<Result> getSpeed(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.getSpeed()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("vx", v.getVx());
            result.put("vy", v.getVy());
            result.put("vz", v.getVz());
            return ok(result);
        });
    }

    public static F.Promise<Result> getRotation(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.getRotation()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("yaw", v.getYaw());
            result.put("pitch", v.getPitch());
            result.put("roll", v.getRoll());
            return ok(result);
        });
    }

    public static F.Promise<Result> takeOff(){
       Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.takeOff()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("status", "ok");
            return ok(result);
        });
    }

    public static F.Promise<Result> land(){
        Drone d = Fleet.getFleet().getDrone("bepop");
        return F.Promise.wrap(d.land()).map(v -> {
            ObjectNode result = Json.newObject();
            result.put("status", "ok");
            return ok(result);
        });
    }


}
