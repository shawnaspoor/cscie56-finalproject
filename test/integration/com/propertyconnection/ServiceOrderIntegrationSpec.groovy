package com.propertyconnection

import grails.test.spock.IntegrationSpec
import spock.lang.*

class ServiceOrderIntegrationSpec  extends Specification {


    def charlie = new Landlord(
            firstName: 'Charlie',
            lastName: 'Booker',
            email: 'charlie@gmail.com',
            dateCreated: 'Thu Nov 27 09:17:45 EST 2014',
            password:  'password',
            loginId: 'charliebooker'

    ).save()
    def sally = new Tenant(
            firstName: 'Sally',
            lastName: 'Rider',
            email: 'sally@gmail.com',
            dateCreated: 'Thu Nov 27 09:17:45 EST 2014',
            password:  'password',
            loginId: 'rideSallyride'

    ).save()
    def lyndeSt = new Home(
            propertyTitle:'Lynde St',
            streetAddress:'29 Lynde St',
            //state nullable: false
            city: 'Melrose',
            zipcode:'02176',
            bedrooms:'2',
            baths:'1',
            landlord: charlie,
            tenant: sally
    ).save()

    def "Saving a service order to the database" () {
        given: "A new service order"
        def serviceOrder = new ServiceOrder(
                tenants: sally,
                description: 'toilet is broken',
                location: 'mail bathroom',
                homes: lyndeSt,
                landlord: charlie
        )

        when: "The service order is saved"
        serviceOrder.save()

        then: "The home saved successfully and is in the database"
        serviceOrder.errors.errorCount == 0
        serviceOrder.id != null
        ServiceOrder.get(serviceOrder.id).description == serviceOrder.description
    }

    def "Updating a service order and changing its properties" () {
        given: "A service order"
        def existingServiceOrder = new ServiceOrder(
                tenants: sally,
                description: 'toilet is broken',
                location: 'mail bathroom',
                homes: lyndeSt,
                landlord: charlie
        )
        existingServiceOrder.save(failOnError: true)

        when: "The service order has its properties changed"
        def chosenServiceOrder = ServiceOrder.get(existingServiceOrder.id)
        chosenServiceOrder.location = 'main bathroom'
        chosenServiceOrder.save(failOnError: true)


        then: "The home is updated in the database"
        ServiceOrder.get(existingServiceOrder.id).location =='main bathroom'
    }

    def "Deleting a service order and making sure it's gone from the db" () {
        given: "A new service order"
        def serviceOrder = new ServiceOrder(
                tenants: sally,
                description: 'toilet is broken',
                location: 'mail bathroom',
                homes: lyndeSt,
                landlord: charlie
        )
        serviceOrder.save(failOnError: true)

        when: "The home is deleted"
        def chosenServiceOrder = ServiceOrder.get(serviceOrder.id)
        chosenServiceOrder.delete(flush: true)



        then: "The home is removed from the database"
        !ServiceOrder.exists(chosenServiceOrder.id)
    }


    def "Adding a service order that links to a home"() {
        given: "A brand new service order"
        def lynde = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:'2',
                baths:'1',
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

        when: "A service order is added"
        lynde.addToServiceOrders(new ServiceOrder(
                tenants: sally,
                description: 'toilet is broken',
                location: 'mail bathroom',
                landlord: charlie))

        then: "The home has a list of service orders attached"
        1 == Home.get(lynde.id).serviceOrders.size()

    }

    def "Ensure service orders linked to a home can be retrieved"() {
        given: "A home with a service order"
        def lynde = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:'2',
                baths:'1',
                landlord: charlie,
                tenant: sally
        )
        lynde.addToServiceOrders(new ServiceOrder(
                tenants: sally,
                description: 'toilet is broken',
                location: 'main bathroom',
                landlord: charlie)
        )
        lynde.save(failOnError: true)

        when: "The home is found via its id"
        def foundHome = Home.get(lynde.id)
        def sortedServiceOrderDescription = foundHome.serviceOrders.collect {
            it.description
        }.sort()

        then: "The service order appears on the home's listing"
        sortedServiceOrderDescription == ['toilet is broken']


    }


}
