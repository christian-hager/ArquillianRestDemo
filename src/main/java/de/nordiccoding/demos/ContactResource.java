package de.nordiccoding.demos;

import java.util.HashMap;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Christian Hager // nordiccoding.blogspot.com
 */
@Stateless
@Path("/contacts")
public class ContactResource {

	private static final Logger LOG = LoggerFactory.getLogger(ContactResource.class);

	@Context
	UriInfo uriInfo;

	private HashMap<Long, Contact> contacts;

	public ContactResource() {
		contacts = new HashMap<Long, Contact>();
		contacts.put(1L, new Contact(1L, "Bob"));
		contacts.put(2L, new Contact(1L, "Sam"));
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") Long id) {
		LOG.debug("Getting contact with id {id}", id);
		if (contacts.containsKey(id)) {
			LOG.debug("Contact found");
			return Response.ok(contacts.get(id)).build();
		}
		LOG.debug("No contact found");
		return Response.noContent().build();
	}

}
