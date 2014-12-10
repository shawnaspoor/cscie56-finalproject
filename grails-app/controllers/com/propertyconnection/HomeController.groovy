package com.propertyconnection



class HomeController {
    static scaffold = true


    static defaultAction = "home"

    def homeService

    def home() {
        if(!params.id){
            params.id = "shawna"
        }
        redirect(action:'listing', params: params)
    }

    def listing(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!landlord) {
            render("${landlord} Whoopsie")
            //response.sendError(404)
        }else {
            [landlord: landlord]

        }
    }



}