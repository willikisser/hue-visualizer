package com.kiwi.to.the.moon.model

import java.math.BigDecimal

data class TemperatureSensor(
    var id: String,
    var name: String,
    var productName: String,
    var celcius: BigDecimal,
    var sensorType: String)