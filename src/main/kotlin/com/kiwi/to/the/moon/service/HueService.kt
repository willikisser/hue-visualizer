package com.kiwi.to.the.moon.service

import com.kiwi.to.the.moon.model.HueAttributes
import com.kiwi.to.the.moon.model.Room
import com.kiwi.to.the.moon.model.Rooms
import io.github.zeroone3010.yahueapi.HueBridge
import io.github.zeroone3010.yahueapi.discovery.HueBridgeDiscoveryService
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.concurrent.Future
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import io.github.zeroone3010.yahueapi.Hue
import io.github.zeroone3010.yahueapi.Light

@ApplicationScoped
class HueService {

    @ConfigProperty(name = "hue.name")
    private var hueName: String? = null

    @ConfigProperty(name = "hue.apiKey")
    private var hueApiKey: String? = null

    private var bridgeIp: String? = null

    private var hue: Hue? = null

    @PostConstruct
    fun initialize() {
        val bridgesFuture: Future<List<HueBridge>> = HueBridgeDiscoveryService()
            .discoverBridges({ bridge: HueBridge -> println("Bridge found: $bridge") })
        val bridges: List<HueBridge> = bridgesFuture.get()
        bridgeIp = bridges.firstOrNull()?.ip

        if (hueApiKey != null) {
            hue = Hue(bridgeIp, hueApiKey)
        }
    }

    fun ip(): HueAttributes = HueAttributes(bridgeIp)

    fun initApiConnection(): String = Hue.hueBridgeConnectionBuilder(bridgeIp).initializeApiConnection(hueName).get()

    fun listRooms(): Rooms {
        val rooms: MutableList<Room> = mutableListOf()
        hue?.rooms?.forEach { it ->
            rooms.add(
                Room(it.name, it.type.name, it.isAllOn, it.isAnyOn)
            )
        }
        return Rooms(rooms)
    }

    fun getRoom(roomName: String) : List<Light> {
        var lightList: List<Light>? = hue?.rooms?.filter { it.name == roomName }?.firstOrNull()?.lights?.toList()
        return lightList ?: listOf()
    }
}