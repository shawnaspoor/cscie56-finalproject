package com.propertyconnection

import spock.lang.*

class LandlordIntegrationSpec extends Specification {
    Date newDate = new Date()

    def "Saving a landlord to the database" () {
        given: "A new landlord"
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'charliebooker'

        )

        when: "The landlord is saved"
        charlie.save()

        then: "The landlord saved successfully and is in the database"
        charlie.errors.errorCount == 0
        charlie.id != null
        Landlord.get(charlie.id).email == charlie.email
    }

    def "Updating a landlord and changing its properties" () {
        given: "A landlord"
        def existingLandlord = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'charliebooker'
        )
        existingLandlord.save(failOnError: true)

        when: "The landlord has its properties changed"
        def chosenLandlord = Landlord.get(existingLandlord.id)
        chosenLandlord.loginId = 'charlie'
        chosenLandlord.save(failOnError: true)


        then: "The landlord is updated in the database"
        Landlord.get(existingLandlord.id).loginId =='charlie'
    }

    def "Deleting a landlord and making sure it's gone from the db" () {
        given: "A landlord"
        def newLandlord = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'charliebooker'
        )
        newLandlord.save(failOnError: true)

        when: "The landlord is deleted"
        def chosenLandlord = Landlord.get(newLandlord.id)
        chosenLandlord.delete(flush: true)


        then: "The landlord is removed from the database"
        !Landlord.exists(chosenLandlord.id)
    }

    def "Saving a landlord with invalid properties causes an error"() {
        given: "A landlord that fails to validate"
        def newLandlord = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'cb',
                loginId: 'charliebooker'
        )

        when:"The landlord is validated"
        newLandlord.validate()

        then:
        newLandlord.hasErrors()

        "size.toosmall" == newLandlord.errors.getFieldError("password").code
        "cb"== newLandlord.errors.getFieldError("password").rejectedValue
        !newLandlord.errors.getFieldError("loginId")
    }

    def "Recover from a failed validation"() {
        given: "A landlord that has an invalid property"
        def issues = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'cb',
                loginId: 'charliebooker'
        )
        assert issues.save() == null
        assert issues.hasErrors()

        when: "the invalid properties are fixed"
        issues.password = "Password1"
        issues.validate()

        then: "the user saves with the updated info and validates"
        !issues.hasErrors()
        issues.save()
    }

}
