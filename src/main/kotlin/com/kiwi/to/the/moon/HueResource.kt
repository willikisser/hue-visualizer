package com.kiwi.to.the.moon

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.Consumes


@Path("/hue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class HueResource {

    @Inject
    lateinit var hueService: HueService

    @GET
    @Path("/ip")
    fun ip() = hueService.ip()

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping() = "pong"

}