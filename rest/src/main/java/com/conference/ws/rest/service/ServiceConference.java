package com.conference.ws.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.conference.ws.rest.modelentity.TalkBinding;
import com.conference.ws.rest.models.TalkBLL;
import com.conference.ws.rest.utility.Util;

@Path("/conference")
public class ServiceConference {

	@POST
	@Path("/organize")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<TalkBinding> organize(List<TalkBinding> talksBinding) {
		return TalkBLL.Organize(Util.convertStringToTalk(talksBinding));
	}
}
