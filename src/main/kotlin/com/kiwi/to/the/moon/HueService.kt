package com.kiwi.to.the.moon

import io.github.zeroone3010.yahueapi.HueBridge
import io.github.zeroone3010.yahueapi.discovery.HueBridgeDiscoveryService
import java.util.concurrent.Future
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class HueService {

    private var bridgeIp: String? = null

    @PostConstruct
    fun initialize() {
        val bridgesFuture: Future<List<HueBridge>> = HueBridgeDiscoveryService()
                .discoverBridges({ bridge: HueBridge -> println("Bridge found: $bridge") })
        val bridges: List<HueBridge> = bridgesFuture.get()
        bridgeIp = bridges.firstOrNull()?.ip
    }

    fun ip() = bridgeIp
}