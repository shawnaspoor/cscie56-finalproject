package com.propertyconnection

class ServiceOrder {

    String description
    Date dateCreated
    String location
    byte[] photo
    boolean completed

    static belongsTo = [homes : Home]
    static hasOne  = [landlord: Landlord, tenants: Tenant]



    static constraints = {
        homes nullable: false
        landlord nullable: false
        tenants nullable: false
        description nullable: false
        dateCreated nullable: true
        location nullable: false
        photo nullable: true
        completed nullable: true
    }
}
