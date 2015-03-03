package com.hackproject.northbound;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.codehaus.enunciate.jaxrs.StatusCodes;
import org.opendaylight.controller.northbound.commons.RestMessages;
import org.opendaylight.controller.northbound.commons.exception.ServiceUnavailableException;
import org.opendaylight.controller.northbound.commons.exception.UnauthorizedException;
import org.opendaylight.controller.northbound.commons.utils.NorthboundUtils;
import org.opendaylight.controller.sal.authorization.Privilege;
import org.opendaylight.controller.sal.utils.ServiceHelper;

import com.hackproject.IHackProjectLearner;

/**
 * HackingProjectApplication Northbound REST API <br>
 * External parties can access the HackingProjectApplication NBI using the
 * definitions in this file. <br>
 * <br>
 * Authentication scheme : <b>HTTP Basic</b><br>
 * Authentication realm : <b>opendaylight</b><br>
 * Transport : <b>HTTP and HTTPS</b><br>
 * <br>
 * HTTPS Authentication is disabled by default.
 * 
 * @author Saurabh Agarwal <skagarwal@merunetworks.com>
 * 
 */
@Path("/")
public class HackProjectNorthbound {
	@Context
	private UriInfo _uriInfo;
	private String username;

	@Context
	public void setSecurityContext(SecurityContext context) {
		if (context != null && context.getUserPrincipal() != null) {
			username = context.getUserPrincipal().getName();
		}
	}

	protected String getUserName() {
		return username;
	}

	@Path("/getSomething/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@StatusCodes()
	public Object getSomething() {
		if (!NorthboundUtils.isAuthorized(getUserName(), "default",
				Privilege.WRITE, this)) {
			throw new UnauthorizedException(
					"User is not authorized to perform this operation");
		}

		// Your implementation here

		IHackProjectLearner learner = (IHackProjectLearner) ServiceHelper
				.getInstance(IHackProjectLearner.class, "default", this);
		if (learner == null) {
			throw new ServiceUnavailableException("Hacking Service "
					+ RestMessages.SERVICEUNAVAILABLE.toString());
		}

		learner.test();
		return "Hacking";
	}

}
