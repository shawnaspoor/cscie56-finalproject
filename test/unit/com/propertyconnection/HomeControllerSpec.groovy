package com.propertyconnection



import grails.test.mixin.*
import spock.lang.*

@TestFor(HomeController)
@Mock(Home)
class HomeControllerSpec extends Specification {
/*
    Date newDate = new Date()
    def charlie = new Landlord(
            firstName: 'Charlie',
            lastName: 'Booker',
            email: 'charlie@gmail.com',
            dateCreated: newDate,
            password:  'password',
            loginId: 'charliebooker'
    )

    def "Get a landlords homes given the landlord's id"() {
        given: "A landlord with homes in the database"
        Landlord charlie = new Landlord(
                firstName: 'Charlie',
                lastName: 'Booker',
                email: 'charlie@gmail.com',
                dateCreated: newDate,
                password:  'password',
                loginId: 'charliebooker'

        ).save()
        charlie.addToHomes(new Home(propertyTitle: "property1", streetAddress: "1 street", city:"melrose",
                zipcode: "02176", bedrooms: 2, baths: 1, photo: null))
        charlie.addToHomes(new Home(propertyTitle: "property2", streetAddress: "1 street", city:"melrose",
                zipcode: "02176", bedrooms: 2, baths: 1, photo: null))
        charlie.save(failOnError: true)

        and: "A loginId Parameter"
        params.id = charlie.loginId


        when: "The homes are called"
        def list = controller.listing()

        then: "the tenant is in the list"
        list.landlord.loginId == "charliebooker"
        list.landlord.homes.size() == 2
    }

*/

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.homeInstanceList
            model.homeInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.homeInstance!= null
    }


    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def home = new Home(params)
            controller.show(home)

        then:"A model is populated containing the domain instance"
            model.homeInstance == home
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def home = new Home(params)
            controller.edit(home)

        then:"A model is populated containing the domain instance"
            model.homeInstance == home
    }



}
