package com.propertyconnection

import grails.test.mixin.TestFor
import spock.lang.*
import grails.test.mixin.Mock


@TestFor(ServiceOrderService)
@Mock([Tenant, ServiceOrder, Landlord, Home])
class ServiceOrderServiceSpec extends Specification {


    Date newDate = new Date()
    def charlie = new Landlord(
            firstName: 'Charlie',
            lastName: 'Booker',
            email: 'charlie@gmail.com',
            dateCreated: newDate,
            password:  'password',
            loginId: 'charliebooker'

    )
    def shawna = new Tenant (
            firstName: 'Shawna',
            lastName: 'Spoor',
            email: 'shawnaspoor@gmail.com',
            dateCreated: newDate,
            password:  'password',
            loginId: 'shawna'
    )
    def lyndeSt = new Home (
            propertyTitle:'Lynde St',
            streetAddress:'29 Lynde St',
            //state nullable: false
            city: 'Melrose',
            zipcode:'02176',
            bedrooms:2,
            baths:1,
            landlord: charlie,
            tenant: shawna
    )


    def "service order(valid) gets saved and added to the tenant"() {
        given: "a new tenant in the db"
        Tenant shawna = new Tenant (
                firstName: 'Shawna',
                lastName: 'Spoor',
                email: 'shawnaspoor@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'shawna',
                homes: lyndeSt,
                landlord: charlie
        ).save()

        when: "a new service order is created by the service"
        def newServiceOrder = service.createServiceOrder("broken toilet", "bathroom", "shawna")

        then: "the service order is returned and added to the tenant"
        newServiceOrder.description == "broken toilet"
        Tenant.findByLoginId("shawna").serviceOrders.size() == 1
    }

    def "service order(invalid) generates exceptions"() {
        given: "a new tenant in the db"
        Tenant shawna = new Tenant (
                firstName: 'Shawna',
                lastName: 'Spoor',
                email: 'shawnaspoor@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'shawna',
                homes: lyndeSt,
                landlord: charlie
        ).save()


        when: "an invalid new service order is created"
        def newServiceOrder = service.createServiceOrder(null, null, "shawna")

        then: "an exception is thrown and no service order is saved"
        thrown(ServiceOrderException)

    }
}