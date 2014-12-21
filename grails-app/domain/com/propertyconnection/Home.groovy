package com.propertyconnection

class Home {
    String propertyTitle
    String streetAddress
    //enum state review enum implementation later
    String city
    String zipcode
    Integer bedrooms
    Integer baths
    byte[] photo

    static belongsTo = [landlord: Landlord]
    static hasMany = [serviceOrders: ServiceOrder,payments: Payment]
    static hasOne = [tenant: Tenant]

    static mapping = {
        serviceOrders cascade: "all-delete-orphan"
        tenant cascade: "all-delete-orphan"
        payments cascade: "all-delete-orphan"
    }

    static constraints = {
        propertyTitle nullable: false, maxSize: 30
        streetAddress nullable: false
        city nullable: false
        //state nullable: false
        zipcode nullable: false
        bedrooms nullable:  false
        baths nullable: false
        photo nullable: true
        tenant nullable: true
        serviceOrders nullable: true
    }
}
