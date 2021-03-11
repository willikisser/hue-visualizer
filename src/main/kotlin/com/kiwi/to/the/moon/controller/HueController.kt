package com.kiwi.to.the.moon.controller

import com.kiwi.to.the.moon.service.HueService
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType


@Path("/v1/hue")
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
    @Path("/rooms")
    fun list() = hueService.listRooms()

    @GET
    @Path("/room/{name}")
    fun getLightsByRoom(@PathParam("name") name: String) = hueService.getRoom(name)

    @GET
    @Path("/events")
    fun getEvents() = hueService.getEvents()

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping() = "pong"

}