package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


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

    def "Add a valid new service order"() {
        given: "A mock service order service"
        def mockServiceOrderService = Mock(ServiceOrderService)
        1 * mockServiceOrderService.createServiceOrder(_, _, _) >>
            new ServiceOrder(description: "Mock broken toilet", location:"Mock bathroom")
            controller.serviceOrderService = mockServiceOrderService

        when:"the controller is called"
        def result = controller.createServiceOrder(
                "broken toilet",
                "bathroom",
                "shawna"
        )

        then: "redirected to the listing, flash notification ok"
        flash.message ==~ /Added a new service order: Mock broken toilet */
        response.redirectedUrl == '/serviceOrder/listing/shawna'
    }

/*
      def "Add a valid new service order"() {
          given: "A tenant with service orders in the database"
          Tenant shawna = new Tenant (
                  firstName: 'Shawna',
                  lastName: 'Spoor',
                  email: 'shawnaspoor@gmail.com',
                  dateCreated: newDate,
                  password:  'password',
                  loginId: 'shawna'
          ).save(failOnError: true)

          and: "A loginId Parameter"
          params.id = shawna.loginId

          and: "A service order"
          params.description="stuff is broken"
          params.location="here"

          when:"createServiceOrder is called"
          def creation = controller.createServiceOrder()

          then: "the flash message and redirect shows it was successfully added"
          flash.message == "Your service order has been submitted"
          response.redirectedUrl == "/serviceOrder/listing/${shawna.loginId}"
         erviceOrder.countByTenants(shawna) == 1
      }

      def "Add an invalid new service order"() {
          given: "A tenant with service orders in the database"
          Tenant shawna = new Tenant (
                  firstName: 'Shawna',
                  lastName: 'Spoor',
                  email: 'shawnaspoor@gmail.com',
                  dateCreated: newDate,
                  password:  'password',
                  loginId: 'shawna'
          ).save(failOnError: true)

          and: "A loginId Parameter"
          params.id = shawna.loginId

          and: "A service order"
          params.description= null
          params.location="here"

          when:"createServiceOrder is called"
          def creation = controller.createServiceOrder()

          then: "the flash message and redirect shows it was not successfully added"
          flash.message == "Sorry there seems to be something wrong, try that again."
          response.redirectedUrl == "/serviceOrder/listing/${shawna.loginId}"
          ServiceOrder.countByTenants(shawna) == 0
      }
*/
    def "Add an invalid new service order"() {
        given: "a tenant with service orders"
        Tenant shawna = new Tenant (
                firstName: 'Shawna',
                lastName: 'Spoor',
                email: 'shawnaspoor@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'shawna'
        ).save(failOnError: true)

        and: "A service order service that throws an exception with the input"
        def errorMsg = "Invalid or empty field(s)"
        def mockServiceOrderService = Mock(ServiceOrderService)
        controller.serviceOrderService = mockServiceOrderService
        1 * mockServiceOrderService.createServiceOrder(null, "bathroom", shawna.loginId) >>{
            throw new ServiceOrderException(message: errorMsg)
        }

        and: "A loginId"
        params.id = shawna.loginId

        and: "Some content for the service order"
        params.description = null
        params.location = "bathroom"

        when:"the controller is called"
        def result = controller.createServiceOrder()

        then: "redirected to the listing, flash notification ok"
        flash.message == errorMsg
        response.redirectedUrl == "/serviceOrder/listing/shawna"
        ServiceOrder.countByTenants(shawna) == 0
    }


    @Unroll
    def "testing id"() {
        given:"an id supplied via params"
        params.id = suppliedId

        when: "the controller is invoked"
        controller.home()

        then:
        response.redirectedUrl == expectedUrl

        where:
        suppliedId  |   expectedUrl
        'areeves'   |   '/serviceOrder/listing/areeves'
        null        |   '/serviceOrder/listing/shawna'

    }



}
