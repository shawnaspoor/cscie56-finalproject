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
/*

    def createHome(String propertyTitle, String streetAddress,
                   String city, String zipcode, Integer bedrooms, Integer baths, String id) {
        try{
            def newHome = homeService.createHome(propertyTitle, streetAddress,
                    city, zipcode, bedrooms, baths, id)
            flash.message = "Added a new home: ${newHome.propertyTitle}"
        } catch (HomeException he) {
            flash.message = he.message
        }
        redirect(action: 'homes', id: id )
    }

*/
}