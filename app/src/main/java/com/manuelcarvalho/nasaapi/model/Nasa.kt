package com.manuelcarvalho.nasaapi.model
// https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=fhaz&api_key=DEMO_KEY
class Camera {
    var id = 0
    var name: String? = null
    var rover_id = 0
    var full_name: String? = null
}

class Rover {
    var id = 0
    var name: String? = null
    var landing_date: String? = null
    var launch_date: String? = null
    var status: String? = null
}

class Photo {
    var id = 0
    var sol = 0
    var camera: Camera? = null
    var img_src: String? = null
    var earth_date: String? = null
    var rover: Rover? = null
}

class Root {
    var photos: List<Photo>? = null
}

