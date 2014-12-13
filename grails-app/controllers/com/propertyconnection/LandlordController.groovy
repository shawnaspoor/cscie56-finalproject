package com.propertyconnection

class LandlordController {
    static scaffold = true

    static defaultAction = "home"

    def homeService

    def home(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!id){
            id = "shawna"
        }
        redirect(action:'profile', id: id)
    }


    def homes(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!landlord){
            render("You have no properties associated with your profile.")
        }else{
            [landlord : landlord]
        }
    }

        def register() {
            if(request.method == "POST"){
                def landlord = new Landlord(params)
                if(landlord.validate()){
                    landlord.save()
                    flash.message="You have successfully registered"
                    redirect(uri: '/')
                }else {
                    flash.message="There was a problem creating your account"
                    return [landlord:landlord]
                }
            }

        }

        def register2(LandlordRegistrationCommand lrc) {
            if(lrc.hasErrors()) {
                render view:"register", model: [landlord: lrc]
            }else{
                def landlord = new Landlord(lrc.properties)
                if(landlord.validate() && landlord.save()) {
                    flash.message="Welcome to Property Connection ${lrc.firstName ?: lrc.loginId}"
                    redirect(uri:'/')
                }else{
                    return[landlord: lrc]
                }
            }
        }

        def profile(String id){
            def landlord = Landlord.findByLoginId(id)
            if(landlord) {
                return [profile: landlord]
            }else {
                response.sendError(404)
            }
        }


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


    }

    class LandlordRegistrationCommand {
        String firstName
        String lastName
        String email
        String password
        String passwordRepeat
        String loginId
        Date dateCreated
        byte[] photo

        static constraints = {
            importFrom Landlord
            password(size: 8..15, validator: {passwd, lrc -> passwd !=lrc.loginId} )
            passwordRepeat (nullable: false, validator: {passwd2, lrc -> return passwd2 == lrc.password})
        }

    }

