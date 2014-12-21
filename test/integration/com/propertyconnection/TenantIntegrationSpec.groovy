package com.propertyconnection

import spock.lang.*

class TenantIntegrationSpec extends Specification {

    def "Saving a tenant to the database" () {
        given: "A new tenant"
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'Sallyride'

        )

        when: "The tenant is saved"
        sally.save()

        then: "The tenant saved successfully and is in the database"
        sally.errors.errorCount == 0
        sally.id != null
        Tenant.get(sally.id).email == sally.email
    }

    def "Updating a tenant and changing its properties" () {
        given: "A tenant"
        def existingTenant = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'sallyride'
        )
        existingTenant.save(failOnError: true)

        when: "The tenant has its properties changed"
        def chosenTenant = Tenant.get(existingTenant.id)
        chosenTenant.email = 'rider@gmail.com'
        chosenTenant.save(failOnError: true)


        then: "The tenant is updated in the database"
        Tenant.get(existingTenant.id).email =='rider@gmail.com'
    }

    def "Deleting a tenant and making sure it's gone from the db" () {
        given: "A tenant"
        def newTenant = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreated: new Date(),
                password:  'password',
                loginId: 'rideride'
        )
        newTenant.save(failOnError: true)

        when: "The tenant is deleted"
        def chosenTenant = Tenant.get(newTenant.id)
        chosenTenant.delete(flush: true)


        then: "The tenant is removed from the database"
        !Tenant.exists(chosenTenant.id)
    }

    def "Saving a tenant with invalid properties causes an error"() {
        given: "A tenant that fails to validate"
        def newTenant = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                dateCreate: new Date(),
                password:  'sr',
                loginId: 'sr'
        )

        when:"The tenant is validated"
        newTenant.validate()

        then:
        newTenant.hasErrors()

        "size.toosmall" == newTenant.errors.getFieldError("loginId").code
        "sr"== newTenant.errors.getFieldError("loginId").rejectedValue
        !newTenant.errors.getFieldError("firstName")
    }

    def "Recover from a failed validation"() {
        given: "A tenant that has an invalid property"
        def issuesTenant = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'sr',
                loginId: 's'
        )
        assert issuesTenant.save() == null
        assert issuesTenant.hasErrors()

        when: "the invalid properties are fixed"
        issuesTenant.loginId = "riderSally"
        issuesTenant.validate()

        then: "the user saves with the updated info and validates"
        !issuesTenant.hasErrors()
        issuesTenant.save()
    }


}
