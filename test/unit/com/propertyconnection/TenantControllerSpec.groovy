package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll


@TestFor(TenantController)
@Mock([Tenant])
class TenantControllerSpec extends Specification {
  /*  def "A new tenant registers with valid parameters" () {
        given:"A set of valid parameter"
        params.with {
            loginId = "shawna"
            firstName = "shawna"
            lastName = "spoor"
            email="shawna@spoor.org"
            password="Password1"
        }

        when: "the tenant is registered"
        request.method = "POST"
        controller.register()

        then:"The new tenant is created and the browser is redirected"
        response.redirectedUrl=='/'
        Tenant.count ==1

    }

    @Unroll
    def "Command object for registration validation"() {
        given: "a mocked command object"
        def trc= mockCommandObject(TenantRegistrationCommand)

        and: "A set of values for the test"
        trc.loginId = loginId
        trc.firstName = "shawna"
        trc.lastName = "spoor"
        trc.email = "shawna@spoor.org"
        trc.password = password
        trc.passwordRepeat = passwordRepeat

        when: "the validator is called"
        def isValidRegistration = trc.validate()

        then: "The fields with errors are flagged"
        isValidRegistration == anticipatedValid
        trc.errors.getFieldError(fieldInError)?.code == errorCode

        where:
        loginId     |  password          |   passwordRepeat     |   anticipatedValid  |  fieldInError      | errorCode
        "stacey"    | "passowrd"         |   "no-match"         |   false             | "passwordRepeat"   | "validator.invalid"
        "james"     | "password"         |   "password"         |   true              |  null              | null
    }

    def "calling the register2 action"() {
        given: "A configured command object"
        def trc = mockCommandObject(TenantRegistrationCommand)
        trc.with {
            loginId = "shawna"
            firstName = "Shawna"
            lastName = "spoor"
            email = "shawna@spoor.org"
            password = "password1"
            passwordRepeat = "password1"
        }

        and: "which has been validated"
        trc.validate()

        when: "register2 called"
        controller.register2(trc)

        then:"the tenant is registered and redirected"
        !trc.hasErrors()
        response.redirectedUrl=='/'
        Tenant.count() == 1

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
            !model.tenantInstanceList
            model.tenantInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tenantInstance!= null
    }





    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tenant = new Tenant(params)
            controller.edit(tenant)

        then:"A model is populated containing the domain instance"
            model.tenantInstance == tenant
    }



}
