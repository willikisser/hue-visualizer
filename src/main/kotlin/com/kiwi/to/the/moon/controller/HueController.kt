package com.kiwi.to.the.moon.controller

import com.kiwi.to.the.moon.model.TemperatureSensors
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
    @Path("/room/list")
    fun list() = hueService.listRooms()

    @GET
    @Path("/room/{name}/lights")
    fun getLightsByRoom(@PathParam("name") name: String) = hueService.getLightsByRoom(name)

    @GET
    @Path("/room/{room_name}/light/{light_name}/toggle")
    fun toggleLightByRoom(@PathParam("room_name") roomName: String,
                          @PathParam("light_name") lightName: String) = hueService.toggleLightByRoomAndName(roomName, lightName)

    @GET
    @Path("/sensor/temperatur/list")
    fun getEvents() = TemperatureSensors(hueService.getTemperatureSensors())

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    fun ping() = "pong"
}
