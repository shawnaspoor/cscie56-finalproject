package com.propertyconnection

import grails.transaction.Transactional

class HomeException extends RuntimeException{
    String message
    Home home
}

@Transactional
class HomeService {
        Home createHome (String propertyTitle, String streetAddress,
        String city, String state, String zipcode, Integer bedrooms, Integer baths, String id){
            def landlord = Landlord.findByLoginId(id)
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
    }
}
