package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(LandlordController)
@Mock([Landlord, Home])

class LandlordControllerSpec extends Specification {
    def "Get a landlords homes"() {
        Date newDate = new Date()
        given: "A landlord with homes in the database"
        Landlord tom = new Landlord(
                firstName: 'Tom',
                lastName:'Walker',
                loginId:'WomTalker',
                email: 'tomwalker@gmail.com',
                dateCreated: newDate,
                password: 'Password1'
        ).save()
        tom.addToHomes(new Home(
                propertyTitle:'Island Hill Ave',
                streetAddress:'14 Island Hill Ave',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:1,
                baths:2
        ))
        tom.save(failOnError: true)

        and: "A landlord id"
        params.id = tom.loginId

        when: "the property listing is called"
        def propListing = controller.homes()

        then: "the landlord is in the returned propListing"
        propListing.landlord.loginId == "WomTalker"
        propListing.landlord.homes.size() == 1
    }

    def "A new landlord registers with valid parameters" () {
        given:"A set of valid parameter"
        params.with {
            loginId = "shawna"
            firstName = "shawna"
            lastName = "spoor"
            email="shawna@spoor.org"
            password="Password1"
        }

        when: "the landlord is registered"
        request.method = "POST"
        controller.register()

        then:"The new landlord is created and the browser is redirected"
        response.redirectedUrl=='/'
        Landlord.count ==1

    }

    @Unroll
    def "Command object for registration validation"() {
        given: "a mocked command object"
        def lrc= mockCommandObject(LandlordRegistrationCommand)

        and: "A set of values for the test"
        lrc.loginId = loginId
        lrc.firstName = "shawna"
        lrc.lastName = "spoor"
        lrc.email = "shawna@spoor.org"
        lrc.password = password
        lrc.passwordRepeat = passwordRepeat

        when: "the validator is called"
        def isValidRegistration = lrc.validate()

        then: "The fields with errors are flagged"
        isValidRegistration == anticipatedValid
        lrc.errors.getFieldError(fieldInError)?.code == errorCode

        where:
        loginId     |  password          |   passwordRepeat     |   anticipatedValid  |  fieldInError      | errorCode
        "stacey"    | "passowrd"         |   "no-match"         |   false             | "passwordRepeat"   | "validator.invalid"
        "james"     | "password"         |   "password"         |   true              |  null              | null
    }

    def "calling the register2 action"() {
        given: "A configured command object"
        def lrc = mockCommandObject(LandlordRegistrationCommand)
        lrc.with {
            loginId = "shawna"
            firstName = "Shawna"
            lastName = "spoor"
            email = "shawna@spoor.org"
            password = "password1"
            passwordRepeat = "password1"
        }

        and: "which has been validated"
        lrc.validate()

        when: "register2 called"
        controller.register2(lrc)

        then:"the tenant is registered and redirected"
        !lrc.hasErrors()
        response.redirectedUrl=='/'
        Landlord.count() == 1

    }

    def "Add a valid new home"() {
        given: "A mock home service"
        def mockHomeService = Mock(HomeService)
        1 * mockHomeService.createHome(_, _, _, _, _, _, _, _) >>
                new Home(propertyTitle: "Mock home1", streetAddress:"Mock address", city: "Mock city",
                        zipcode: "02138", bedrooms:2, baths: 2, photo: null)
        controller.homeService = mockHomeService

        when:"the controller is called"
        def result = controller.createHome(
                "Mock home1",
                "Mock address",
                "Mock city",
                "02138",
                2,
                1,
                null,
                "charliebooker"
        )

        then: "redirected to the listing, flash notification ok"
        flash.message ==~ /Added a new home: Mock home1 */
        //response.redirectedUrl == '/serviceOrder/listing/shawna'
    }

}
