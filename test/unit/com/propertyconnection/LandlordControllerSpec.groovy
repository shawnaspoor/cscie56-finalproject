package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(LandlordController)
@Mock([Landlord, Home])

class LandlordControllerSpec extends Specification {
    def "Get a landlords homes"() {
        Date newDate = new Date()
        given: "A landlord with homes in the database"
        Landlord tom = new Landlord(
                firstName: 'Tom',
                lastName:'Walker',
                loginId:'WomTalker',
                email: 'tomwalker@gmail.com',
                dateCreated: newDate,
                password: 'Password1'
        ).save()
        tom.addToHomes(new Home(
                propertyTitle:'Island Hill Ave',
                streetAddress:'14 Island Hill Ave',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:1,
                baths:2
        ))
        tom.save(failOnError: true)

        and: "A landlord id"
        params.id = tom.loginId

        when: "the property listing is called"
        def propListing = controller.homes()

        then: "the landlord is in the returned propListing"
        propListing.landlord.loginId == "WomTalker"
        propListing.landlord.homes.size() == 1
    }
}
