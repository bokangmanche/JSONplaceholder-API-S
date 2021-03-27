import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Endpoints;
import utilities.Path;
import utilities.RestAssuredUtilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
public class GetPetByIDTest {
    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

@BeforeClass
    public void Setup(){
         reqSpec= RestAssuredUtilities.getRequestSpecification();
        //reqSpec.queryParam("Id","1");
         reqSpec.basePath(Path.BASE_URI);

        resSpec=RestAssuredUtilities.getResponseSpecification();

    }
@Test
    public void VerifyGetPetByID() {
        given()
                .spec(reqSpec)
.when().get(Endpoints.GET_PET_BY_ID).then().spec(resSpec);
    }

}
