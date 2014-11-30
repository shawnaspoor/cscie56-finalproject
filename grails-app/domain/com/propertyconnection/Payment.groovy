package com.propertyconnection

class Payment {

    String paymentId
    Date dateCreated

    static belongsTo = [ landlord: Landlord , tenant: Tenant, homes: Home ]

    static mapping = {
        sort dateCreated: "desc"
    }

    static constraints = {
        paymentId nullable: false
        dateCreated nullable: true


    }
}
