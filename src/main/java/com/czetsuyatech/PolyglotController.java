package com.czetsuyatech;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;

@Path("/poly")
public class PolyglotController {

  public static Context context = Context.newBuilder().allowIO(true).allowAllAccess(true).build();

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return "Hello RESTEasy";
  }

  /**
   * Test endpoint that runs js code inside GraalVM.
   *
   * @return string confirmation
   */
  @Path("/js")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String helloJs() {

    context.eval(Source.create("js", "console.log('hello js')"));
    return "Running JS script console.log('hello js'). Check the logs.";
  }

  /**
   * Test endpoint that runs py code inside GraalVM.
   *
   * @return a value that is return from an executed python script
   */
  @Path("/py")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String helloPy() throws IOException {

    final URL pyTestPy = getClass().getClassLoader().getResource("pytest.py");
    System.out.println("" + pyTestPy);
    Value effectValue = context.eval(Source.newBuilder("python", pyTestPy).build());
    return "Result read from pytest.py=" + effectValue.toString();
  }
}
