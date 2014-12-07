package com.propertyconnection

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification


@TestFor(HomeController)
@Mock([Tenant, Landlord, Home, ServiceOrder])

class HomeControllerSpec extends Specification {

}
