package com.propertyconnection

import grails.test.spock.IntegrationSpec
import spock.lang.*

class PaymentIntegrationSpec extends Specification {


    def "Saving a payment to the database" () {
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'password',
                loginId: 'charliebooker'

        ).save(failOnError: true)
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'password',
                loginId: 'rideSallyride'

        ).save(failOnError: true)
        def lyndeSt = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

        given: "A new payment"
        def payment1 = new Payment(
                landlord: charlie,
                tenant: sally,
                paymentId: '123asv',
                homes: lyndeSt
        )

        when: "The payment is saved"
        payment1.save()

        then: "The home saved successfully and is in the database"
        payment1.errors.errorCount == 0
        payment1.id != null
        Payment.get(payment1.id).paymentId == payment1.paymentId
    }

    def "Updating a payment and changing its properties" () {
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'password',
                loginId: 'charliebooker'

        ).save(failOnError: true)
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'password',
                loginId: 'rideSallyride'

        ).save(failOnError: true)
        def lyndeSt = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

        given: "A payment"
        def existingPayment = new Payment(
                landlord: charlie,
                tenant: sally,
                paymentId: '123asv',
                homes: lyndeSt
        )
        existingPayment.save(failOnError: true)

        when: "The payment has its properties changed"
        def chosenPayment = Payment.get(existingPayment.id)
        chosenPayment.paymentId = '456asv'
        chosenPayment.save(failOnError: true)


        then: "The payment is updated in the database"
        Payment.get(existingPayment.id).paymentId =='456asv'
    }

    def "Deleting a payment and making sure it's gone from the db" () {
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'password',
                loginId: 'charliebooker'

        ).save(failOnError: true)
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'password',
                loginId: 'rideSallyride'

        ).save(failOnError: true)
        def lyndeSt = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

        given: "A new payment"
        def payment2 = new Payment(
                landlord: charlie,
                tenant: sally,
                paymentId: '123asv',
                homes: lyndeSt
        )
        payment2.save(failOnError: true)

        when: "The payment is deleted"
        def chosenPayment = Payment.get(payment2.id)
        chosenPayment.delete(flush: true)



        then: "The home is removed from the database"
        !Payment.exists(chosenPayment.id)
    }


    def "Adding a payment that links to a home"() {
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'password',
                loginId: 'charliebooker'

        ).save(failOnError: true)
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'password',
                loginId: 'rideSallyride'

        ).save(failOnError: true)
        def lyndeSt = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

        given: "A brand new payment"
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

        when: "A payment is added"
        lynde.addToPayments(new Payment(
                landlord: charlie,
                tenant: sally,
                paymentId: '123asv' ))

        then: "The home has a list of payments attached"
        1 == Home.get(lynde.id).payments.size()

    }

    def "Ensure payments linked to a home can be retrieved"() {
        def charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                password:  'password',
                loginId: 'charliebooker'

        ).save(failOnError: true)
        def sally = new Tenant(
                firstName: 'Sally',
                lastName: 'Rider',
                email: 'sally@gmail.com',
                password:  'password',
                loginId: 'rideSallyride'

        ).save(failOnError: true)
        def lyndeSt = new Home(
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: charlie,
                tenant: sally
        ).save(failOnError: true)

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
        lynde.addToPayments(new Payment(
                landlord: charlie,
                tenant: sally,
                paymentId: '123asv' )
        )
        lynde.save(failOnError: true)

        when: "The home is found via its id"
        def foundHome = Home.get(lynde.id)
        def sortedPaymentPaymentId = foundHome.payments.collect {
            it.paymentId
        }.sort()

        then: "The payment appears on the home's listing"
        sortedPaymentPaymentId == ['123asv']


    }



}

