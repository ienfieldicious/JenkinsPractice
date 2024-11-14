import com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;

public class Test1
{
    RequestSpecification reqspec=null;
    Response resp=null;
    @BeforeTest
    public void seturl()
    {
         reqspec=RestAssured.given().baseUri("https://reqres.in/")
                 .contentType("application/json");
    }
    @Test
    public void validation1StatusCode()
    {
       resp=reqspec.get("/api/users?page=2").then().extract().response();

       resp.then().statusCode(200);
        System.out.println(resp.getCookies());
        System.out.println(resp.hashCode());
        System.out.println(resp.getStatusLine());
        System.out.println(resp.time());


    }

    @Test
    public void validation2GetHeaders()
    {
         resp=reqspec.get("/api/users?page=2").
                then().extract().response();

        String respBody=resp.getBody().asString();
        System.out.println(respBody);

        Assert.assertTrue(respBody.contains("page"));
        System.out.println("HEaders*******"+resp.getHeaders());
        Assert.assertTrue(resp.contentType().contains("application/json"));
    }

    @Test
    public void validation3GetResponseJson()
    {
        Response resp=reqspec.get("/api/users?page=2").
                then().extract().response();

        JsonObject jo=new Gson().fromJson(resp.body().asString(),JsonObject.class);
        System.out.println("Page is **  "+jo.get("page"));
        JsonArray ja=jo.getAsJsonArray("data");

        for(JsonElement je:ja)
        {
            JsonObject jo1=je.getAsJsonObject();
            System.out.println("Email id is"+jo1.get("email"));
        }

    }
    @Test
    public void sendBodyAsJson() throws FileNotFoundException {

        File f=new File("D:\\Automation\\APITest\\Data\\Testdata1.json");
        Reader r=new FileReader(f);
        JsonParser p=new JsonParser();
        JsonObject jo=p.parse(r).getAsJsonObject();
        System.out.println("Sample data read"+jo.get("name"));

        Response rsp=reqspec.body(f)
        .when()
                .post("/api/users").then().extract().response();
        System.out.println(rsp.statusCode()+rsp.body().asString());
    }



}
