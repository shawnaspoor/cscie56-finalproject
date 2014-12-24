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

    def addTenant(String propertyId, Long tenantId, Long landlordId) {
        def home = Home.findById(propertyId)
        def tenant = Tenant.findById(tenantId)
        def landlord = Landlord.findById(landlordId)
        if (home) {
            if (tenant) {
                if (landlord) {
                    home.addToTenant(tenant)
                    landlord.addToTenants(tenant)
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
