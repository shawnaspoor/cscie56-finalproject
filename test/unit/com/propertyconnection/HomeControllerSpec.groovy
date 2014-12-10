package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(HomeController)
@Mock([Tenant, Landlord, Home, ServiceOrder])

class HomeControllerSpec extends Specification {
    Date newDate = new Date()
    def charlie = new Landlord(
            firstName: 'Charlie',
            lastName: 'Booker',
            email: 'charlie@gmail.com',
            dateCreated: newDate,
            password:  'password',
            loginId: 'charliebooker'
    )

    def "Get a landlords homes given the landlord's id"() {
        given: "A landlord with homes in the database"
        Landlord charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'charliebooker'

        ).save()
        charlie.addToHomes(new Home(propertyTitle: "property1", streetAddress: "1 street", city:"melrose",
        zipcode: "02176", bedrooms: 2, baths: 1, photo: null))
        charlie.addToHomes(new Home(propertyTitle: "property2", streetAddress: "1 street", city:"melrose",
                zipcode: "02176", bedrooms: 2, baths: 1, photo: null))
        charlie.save(failOnError: true)

        and: "A loginId Parameter"
        params.id = charlie.loginId


        when: "The homes are called"
        def list = controller.listing()

        then: "the tenant is in the list"
        list.landlord.loginId == "charliebooker"
        list.landlord.homes.size() == 2
    }


}
