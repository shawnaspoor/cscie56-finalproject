package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(ServiceOrderController)
@Mock([Tenant, Landlord, Home, ServiceOrder])
class ServiceOrderControllerSpec extends Specification {

    Date newDate = new Date()
   def charlie = new Landlord(
            firstName: 'Charlie',
            lastName: 'Booker',
            email: 'charlie@gmail.com',
            dateCreated: newDate,
            password:  'password',
            loginId: 'charliebooker'

    )
    def lyndeSt = new Home (
            propertyTitle:'Lynde St',
            streetAddress:'29 Lynde St',
            //state nullable: false
            city: 'Melrose',
            zipcode:'02176',
            bedrooms:2,
            baths:1,
            landlord: charlie
    )

   def "Get a tenants service orders given the tenant's id"() {
       given: "A tenant with service orders in the database"
       Tenant shawna = new Tenant (
               firstName: 'Shawna',
               lastName: 'Spoor',
               email: 'shawnaspoor@gmail.com',
               dateCreated: newDate,
               password:  'password',
               loginId: 'shawna'
       ).save()
       shawna.addToServiceOrders(new ServiceOrder(description: "light bulb out in bathroom", location: "bathroom" ,
               homes:lyndeSt ))
       shawna.addToServiceOrders(new ServiceOrder(description: "fridge stopped working", location: "kitchen" ,
               homes:lyndeSt ))
       shawna.save(failOnError: true)

       and: "A loginId Parameter"
       params.id = shawna.loginId


       when: "The service orders are called"
       def list = controller.listing()

       then: "the tenant is in the list"
       list.tenant.loginId == "shawna"
       list.tenant.serviceOrders.size() == 2
   }
}
