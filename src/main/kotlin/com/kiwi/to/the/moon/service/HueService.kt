package com.kiwi.to.the.moon.service

import com.kiwi.to.the.moon.model.HueAttributes
import io.github.zeroone3010.yahueapi.HueBridge
import io.github.zeroone3010.yahueapi.discovery.HueBridgeDiscoveryService
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.concurrent.Future
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import io.github.zeroone3010.yahueapi.Hue

@ApplicationScoped
class HueService {

    @ConfigProperty(name = "hue.name")
    private var hueName: String? = null

    @ConfigProperty(name = "hue.apiKey")
    private var hueApiKey: String? = null

    private var bridgeIp: String? = null

    @PostConstruct
    fun initialize() {
        val bridgesFuture: Future<List<HueBridge>> = HueBridgeDiscoveryService()
                .discoverBridges({ bridge: HueBridge -> println("Bridge found: $bridge") })
        val bridges: List<HueBridge> = bridgesFuture.get()
        bridgeIp = bridges.firstOrNull()?.ip
    }

    fun ip() : HueAttributes = HueAttributes(bridgeIp)

    fun initApiConnection(): String = Hue.hueBridgeConnectionBuilder(bridgeIp).initializeApiConnection(hueName).get()

    fun listDevices() : String {
        val hue = Hue(bridgeIp, hueApiKey)
        var rooms = ""
        hue.rooms.forEach {
            it -> rooms += it.name + "\n"
        }
        return rooms
    }
}