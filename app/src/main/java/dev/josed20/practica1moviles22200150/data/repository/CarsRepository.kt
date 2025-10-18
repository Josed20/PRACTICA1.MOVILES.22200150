package dev.josed20.practica1moviles22200150.data.repository

import dev.josed20.practica1moviles22200150.data.model.SportCar

object CarsRepository {
    fun getAll(): List<SportCar> = listOf(
        SportCar("Ferrari", "488 GTB", 262000.0, "https://tse2.mm.bing.net/th/id/OIP.3ihpRAGSSR1CwwM9bwttPAHaE7?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3"),
        SportCar("Lamborghini", "Huracán", 249000.0, "https://th.bing.com/th/id/R.0bc43fbba36b562e0081f39ff85ffba1?rik=VDdSzfvBjWDQqg&pid=ImgRaw&r=0"),
        SportCar("Porsche", "911 Turbo S", 204000.0, "https://tse1.mm.bing.net/th/id/OIP.xqXx_cmooo8IcmQpVeZ8MAHaEZ?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3"),
        SportCar("McLaren", "720S", 299000.0, "https://tse3.mm.bing.net/th/id/OIP.YbN37Kr7NTgPedgQ0Cd6ngHaEK?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3"),
        SportCar("Aston Martin", "Vantage", 145000.0, "https://tse1.mm.bing.net/th/id/OIP.Avr969fAe0dT4IMlQzJwqQHaEO?cb=12&rs=1&pid=ImgDetMain&o=7&rm=3"),
    )
}