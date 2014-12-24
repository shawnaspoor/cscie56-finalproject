package com.propertyconnection

import grails.transaction.Transactional

class HomeException extends RuntimeException{
    String message
    Home home
}

@Transactional
class HomeService {
        Home createHome (String propertyTitle, String streetAddress,
        String city, String state, String zipcode, Integer bedrooms, Integer baths, Long id){
            def landlord = Landlord.findById(id)
            if(landlord){
                def home = new Home(propertyTitle: propertyTitle, streetAddress: streetAddress, city: city, state: state,
                zipcode: zipcode, bedrooms: bedrooms, baths: baths )
                landlord.addToHomes(home)
                if(home.validate() && home.save()) {
                    return home
                }else {
                    throw new HomeException(
                            message: "Invalid or empty field(s)", home: home)
                }
            }
            throw new HomeException(message: "Invalid Landlord Id")
        }

    def getLandlord(Long id){
        def home = Home.findById(id)
        if(home){
            def landlord = home.landlord
            if (landlord){
                return landlord
            }
            throw new HomeException(message: "No landlord associated with that property.")
        }
        throw new HomeException(message: "There is no home with that id.")
    }
    def updateTenant(Long propertyId, String loginId) {
        println "updatetenantService is being called"
        def home = Home.findById(propertyId)
        def tenant = Tenant.findByLoginId(loginId)
        def landlord = getLandlord(home)
        //def landlord = Landlord.findById(landlordId) //think I need to grab this from a service based on the home
        if (home) {
            if (tenant) {
                if (landlord) {
                    home.setTenant(tenant)
                    landlord.addToTenants(tenant)
                    tenant.setHomes(home)
                    if(home.save() && landlord.save() && tenant.save()){
                        return home
                    }else{
                        throw new HomeException(
                                message: "Sorry, there appears to something wrong", home: home)
                    }
                }else {
                    throw new HomeException(
                            message: "Sorry, there appears to something wrong with your id", home: home)
                }

            }else {
                throw new HomeException(
                        message: "Sorry, there appears to something wrong with the tenant id", home: home)

        }
    }else {
            throw new HomeException(
                    message: "Sorry, there appears to something wrong with the home id", home: home)
        }
    }
/*
    def removeTenant(String propertyId, Long tenantId, Long landlordId) {
        def home = Home.findById(propertyId)
        def tenant = Tenant.findById(tenantId)
        def landlord = Landlord.findById(landlordId)
        if (home) {
            if (tenant) {
                if (landlord) {
                    home.removeFromTenant(tenant)
                    landlord.removeFromTenants(tenant)
                }else {
                    throw new HomeException(
                            message: "Sorry, there appears to something wrong with your id", home: home)
                }

            }else {
                throw new HomeException(
                        message: "Sorry, there appears to something wrong with the tenant id", home: home)

            }
        }else {
            throw new HomeException(
                    message: "Sorry, there appears to something wrong with the home id", home: home)
        }
    }

    /* this doesn't work, :(
    def update (String propertyTitle, String streetAddress,
                     String city, String state, String zipcode, Integer bedrooms, Integer baths, Integer id){
        println "home id: ${id}"
        println "baths:  ${baths}"
        def home = Home.findById(id)
        home.save(propertyTitle: propertyTitle, streetAddress: streetAddress, city: city, state: state,
                zipcode: zipcode, bedrooms: bedrooms, baths: baths)
            if(home.validate() && home.save()) {
                return home
            }else {
                throw new HomeException(
                        message: "Invalid or empty field(s)", home: home)
            }
        throw new HomeException(message: "Invalid home Id")
    }*/
}
