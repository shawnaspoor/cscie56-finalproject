package com.propertyconnection

import spock.lang.*

class HomeIntegrationSpec extends Specification {
    def "Saving a Home to the database" () {
        def tom = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'tom'
        ).save(failOnError: true)

        def peter = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'peter'

        ).save(failOnError: true)

        given: "A new home"
        def lynde = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: tom,
                tenant: peter
        )

        when: "The home is saved"
        lynde.save()

        then: "The home saved successfully and is in the database"
        lynde.errors.errorCount == 0
        lynde.id != null
        Home.get(lynde.id).propertyTitle == lynde.propertyTitle
    }

    def "Updating a home and changing its properties" () {
        def dick = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'dick'

        ).save(failOnError: true)

        def paul = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'paul'

        ).save(failOnError: true)

        given: "A home"
        def existingHome = new Home(
                propertyTitle:'Oak Grove',
                streetAddress:'29 oak grove St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: dick,
                tenant: paul
        ).save(failOnError: true)

        when: "The home has its properties changed"
        def chosenHome = Home.get(existingHome.id)
        chosenHome.zipcode = '02138'
        chosenHome.save(failOnError: true)


        then: "The home is updated in the database"
        Home.get(existingHome.id).zipcode =='02138'
    }

    def "Deleting a home and making sure it's gone from the db" () {
        def harry = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'harry'

        ).save(failOnError: true)

        def mary = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'mary'

        ).save(failOnError: true)

        given: "A home"
        def newHome = new Home(
                propertyTitle:'Main St',
                streetAddress:'29 Main St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                tenant: mary,
                landlord: harry
        ).save(failOnError: true)

        when: "The home is deleted"
        def chosenHome = Home.get(newHome.id)
        chosenHome.delete(flush: true)



        then: "The home is removed from the database"
        !Home.exists(chosenHome.id)
    }
}
