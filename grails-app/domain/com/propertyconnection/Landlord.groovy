package com.propertyconnection

class Landlord {

    String firstName
    String lastName
    String email
    String password
    String loginId
    Date dateCreated
    byte[] photo

    static hasMany = [homes : Home, messages: Message, serviceOrders: ServiceOrder,
                      payments: Payment, tenants: Tenant ]

    static mapping = {
        homes cascade: "all-delete-orphan"
        payments cascade: "all-delete-orphan"
    }


    static constraints = {
        firstName blank: false
        lastName blank: false
        loginId nullable: false
        email email: true, blank: false
        dateCreated nullable: true
        password blank: false, size: 8..15, validator: {passwd, landlord -> passwd !=landlord.loginId}
        photo nullable: true, maxSize: 2 * 1024 * 1024
        homes nullable: true
        tenants nullable: true
        messages nullable: true
        serviceOrders nullable: true
        payments nullable: true

    }

}
