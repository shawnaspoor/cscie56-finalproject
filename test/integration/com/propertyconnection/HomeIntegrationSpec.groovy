package com.propertyconnection

import grails.test.spock.IntegrationSpec
import spock.lang.*

class HomeIntegrationSpec extends Specification {

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


    def "Saving a Home to the database" () {
        given: "A new home"
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

        when: "The home is saved"
        lynde.save()

        then: "The home saved successfully and is in the database"
        lynde.errors.errorCount == 0
        lynde.id != null
        Home.get(lynde.id).propertyTitle == lynde.propertyTitle
    }

    def "Updating a home and changing its properties" () {
        given: "A home"
        def existingHome = new Home(
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
        existingHome.save(failOnError: true)

        when: "The home has its properties changed"
        def chosenHome = Home.get(existingHome.id)
        chosenHome.zipcode = '02138'
        chosenHome.save(failOnError: true)


        then: "The home is updated in the database"
        Home.get(existingHome.id).zipcode =='02138'
    }

    def "Deleting a home and making sure it's gone from the db" () {
        given: "A home"
        def newHome = new Home(
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
        newHome.save(failOnError: true)

        when: "The home is deleted"
        def chosenHome = Home.get(newHome.id)
        chosenHome.delete(flush: true)



        then: "The home is removed from the database"
        !Home.exists(chosenHome.id)
    }

}
