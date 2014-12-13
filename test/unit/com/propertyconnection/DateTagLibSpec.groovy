package com.propertyconnection

import grails.test.mixin.TestFor
import spock.lang.*
import groovy.time.TimeCategory

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(DateTagLib)
class DateTagLibSpec extends Specification {

   @Unroll
    void "testing the date taglib to ensure the distanceInTime is working"() {
       expect:
       applyTemplate('<pc:dateFrom date="${date}" />', [date: testDate])== expectedDateResult

       where:
       testParam            |   testDate                |   expectedDateResult
       "Now"                |   new Date()              |   "Right now"
       "Now - 1 day"        |   new Date().minus(1)     |   "1 day ago"
       "Now - 4 days"       |   new Date().minus(4)     |   "4 days ago"
    }

    @Unroll
    void "testing the date taglib with TimeCategory for hours and seconds"() {
        use(TimeCategory){
            expect:
            applyTemplate('<pc:dateFrom date="${date}" />', [date: testDate])== expectedDateResult

            where:
            testParam            |   testDate                |   expectedDateResult
            "Now"                |   new Date()              |   "Right now"
            "Now - 1 hour"       |   new Date()- 1.hour      |   "1 hour ago"
            "Now - 4 hours"      |   new Date() - 4.hours    |   "4 hours ago"
            "Now - 1 sec "       |   new Date()- 1.second    |   "1 second ago"
            "Now - 4 sec  "      |   new Date() - 4.seconds  |   "4 seconds ago"
        }
    }




}
