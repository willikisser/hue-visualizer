package com.kiwi.to.the.moon.controller

import com.kiwi.to.the.moon.service.HueService
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.Consumes


@Path("/hue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class HueController {

    @Inject
    lateinit var hueService: HueService

    @GET
    @Path("/ip")
    fun ip() = hueService.ip()

    @GET
    @Path("/init")
    fun init() = hueService.initApiConnection()

    @GET
    @Path("/list/devices")
    fun list() = hueService.listRooms()

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping() = "pong"

}