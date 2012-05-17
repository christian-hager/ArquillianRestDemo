package de.nordiccoding.demos.it;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.restassured.http.ContentType;

/**
 * @author Christian Hager // nordiccoding.blogspot.com
 */
@RunWith(Arquillian.class)
public class ContactResourceIT {

	private static final Logger LOG = LoggerFactory.getLogger(ContactResourceIT.class);

	@Deployment
	public static WebArchive createArchiveAndDeploy() {
		WebArchive war = ShrinkWrap.create(WebArchive.class, "arquillian-rest-demo.war");
		war.addPackages(true, "de.nordiccoding.demos");
		war.addAsResource("META-INF/logback.xml");
		war.addAsWebInfResource(new File("src/main/webapp", "WEB-INF/web.xml"));
		war.addAsWebInfResource(new File("src/main/webapp", "WEB-INF/glassfish-web.xml"));
		File testLibDir = new File("target/test-libs/compile");
		for (File libFile : testLibDir.listFiles()) {
			war.addAsLibraries(libFile);
		}
		LOG.debug(war.toString(true));
		return war;
	}

	@Test
	public void testGetBob() {
		given().contentType(ContentType.JSON).expect().body("name", equalTo("Bob")).log().ifError().statusCode(200).when().get("http://localhost:8080/arquillianRestDemo/contacts/1");
	}

	@Test
	public void testNothingFound() {
		given().contentType(ContentType.JSON).expect().log().ifError().statusCode(Status.NO_CONTENT.getStatusCode()).when().get("http://localhost:8080/arquillianRestDemo/contacts/3");
	}
}
