

import com.propertyconnection.*
import grails.test.mixin.TestFor
import spock.lang.*

@TestFor(UrlMappings)
@Mock(ServiceOrderController)
class UrlMappingsSpec extends Specification {
    def "Ensure basic mapping operations for service order permalinks"() {
        expect:
        assertForwardUrlMapping(url, controller:expectCtrl, action: expectAction) {
            id = expectId
        }
        where:
        url                         |       expectCtrl      |       expectAction        |       expectId
        '/serviceorders/areeves'    |   'serviceOrder'      |       'listing'           |       'areeves'
    }


}
