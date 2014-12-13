package com.propertyconnection

class DateTagLib {

    static namespace = "pc"
   def dateFrom = {
       attrs ->
       def date = attrs.date
       def from = getFrom(date)
       out << from
   }

    protected String getFrom(Date date){
        def now = new Date()
        def distance = Math.abs(now.time - date.time)
        final long second = 1000
        final long minute = second*60
        final long hour = minute*60
        final long day = hour*24

        def distanceInTime = ""
        long timeInSeconds = 0;

        timeInSeconds = Math.floor(distance/day)
        if(timeInSeconds) {
            distanceInTime += timeInSeconds + " day" + (timeInSeconds >1 ? "s " : " ")
            distance %= day
        }

        timeInSeconds = Math.floor(distance/hour)
        if(timeInSeconds) {
            distanceInTime += timeInSeconds + " hour" + (timeInSeconds >1 ? "s " : " ")
            distance %= hour
        }

        timeInSeconds = Math.floor(distance/minute)
        if(timeInSeconds) {
            distanceInTime += timeInSeconds + " minute" + (timeInSeconds >1 ? "s " : " ")
            distance %= minute
        }

        if(!distanceInTime) {
            distanceInTime = "Right now"
        }else{
            distanceInTime +=(date.time > now.time) ? "from now" : "ago"
        }
        return distanceInTime






    }
}
