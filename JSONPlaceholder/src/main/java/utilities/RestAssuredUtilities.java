package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class RestAssuredUtilities {
    public static String ENDPOINT;

    public static RequestSpecBuilder REQUEST_BUILDER;
    public static RequestSpecification REQUEST_SPEC;
    public static ResponseSpecBuilder RESPONSE_BUILDER;
    public static ResponseSpecification RESPONSE_SPEC;

    public static void setEndPoint(String epoint){
        ENDPOINT=epoint;
    }
    public static RequestSpecification getRequestSpecification(){
        REQUEST_BUILDER =new RequestSpecBuilder();
        REQUEST_BUILDER.setBaseUri(Path.BASE_URI);
        REQUEST_SPEC=REQUEST_BUILDER.build();
        return REQUEST_SPEC;
    }
    public static ResponseSpecification getResponseSpecification(){
        RESPONSE_BUILDER=new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectStatusCode(200);
        RESPONSE_SPEC=RESPONSE_BUILDER.build();
        return RESPONSE_SPEC;
    }
    public static RequestSpecification createQueryParam(RequestSpecification rspec,String param,
                                                        String value){
        return rspec.queryParam(param,value);
    }
    public static RequestSpecification createQueryParam(RequestSpecification rspec, Map<String,String> queryMap){
        return rspec.queryParams(queryMap);
    }
    public static RequestSpecification createPathParam(RequestSpecification rspec,String param,
                                                        String value){
        return rspec.pathParam(param,value);
    }
    public static Response getResponse(){
        return given().get(ENDPOINT);
    }
    public static Response getResponse(RequestSpecification reqspec,String type){
        REQUEST_SPEC.spec(reqspec);
        Response response=null;
        if(type.equalsIgnoreCase("get")){
            response=given().spec(REQUEST_SPEC).get(ENDPOINT);
        }else if(type.equalsIgnoreCase("put")){
            response=given().spec(REQUEST_SPEC).put(ENDPOINT);
        }else if(type.equalsIgnoreCase("post")){
            response=given().spec(REQUEST_SPEC).post(ENDPOINT);
        }else if(type.equalsIgnoreCase("delete")){
            response=given().spec(REQUEST_SPEC).delete(ENDPOINT);
        }else{
            System.out.println("Type not supported");
        }
        response.then().log().all();
        response.then().spec(RESPONSE_SPEC);
        return response;
    }
    public static JsonPath getJsonPath(Response res){
        String jsonPath=res.asString();
        return new JsonPath(jsonPath);
    }
    public static XmlPath getXmlPath(Response res){
        String xmlPath=res.asString();
        return new XmlPath(xmlPath);
    }
    public static void resetBasePath()
    {
        RestAssured.basePath=null;
    }
    public static void SetContentType(ContentType type){
        given().contentType(type);
    }
}
