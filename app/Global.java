import akka.actor.ActorRef;
import akka.actor.Props;
import drones.models.scheduler.SimpleScheduler;
import play.Application;
import play.GlobalSettings;
import play.libs.Akka;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.GlobalSettings;
import play.libs.F;
import play.libs.Json;
import play.libs.Scala;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.api.mvc.Results.Status;
import play.mvc.Results;
import scala.Tuple2;
import scala.collection.Seq;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.internalServerError;

/**
 * Created by matthias on 20/03/2015.
 */
public class Global extends GlobalSettings {

    private class ActionWrapper extends Action.Simple {
        public ActionWrapper(Action<?> action) {
            this.delegate = action;
        }

        @Override
        public F.Promise<Result> call(Http.Context ctx) throws java.lang.Throwable {
            F.Promise<Result> result = this.delegate.call(ctx);
            Http.Response response = ctx.response();
            response.setHeader("Access-Control-Allow-Origin", "*");
            return result;
        }
    }

    @Override
    public F.Promise<Result> onError(Http.RequestHeader request, Throwable t) {
        ObjectNode result = Json.newObject();
        result.put("reason", t.getMessage());

        // Giant -hack- to add Origin control to error messages
        List<Tuple2<String, String>> list = new ArrayList<>();
        Tuple2<String, String> h = new Tuple2<>("Access-Control-Allow-Origin","*");
        list.add(h);
        Seq<Tuple2<String, String>> seq = Scala.toSeq(list);
        Result error = () -> Results.internalServerError(result).toScala().withHeaders(seq);
        return F.Promise.pure(error);
    }

    public void onStart(Application application) {
        super.onStart(application);
        startDroneScheduler();
    }

    @Override
    public void onStop(Application application) {
        super.onStop(application);
        stopDroneScheduler();
    }


    private static ActorRef scheduler;

    public static void startDroneScheduler(){
        if(scheduler != null) return;
        scheduler = Akka.system().actorOf(Props.create(SimpleScheduler.class),"Scheduler");
    }

    public static void stopDroneScheduler(){
        Akka.system().stop(scheduler);
    }

    /**
     * Get an actor reference to the drone scheduler
     * @return
     */
    public static ActorRef getDroneScheduler() {
        return scheduler;
    }

    public Action<?> onRequest(Http.Request request, java.lang.reflect.Method actionMethod) {
        return new ActionWrapper(super.onRequest(request, actionMethod));
    }

}
