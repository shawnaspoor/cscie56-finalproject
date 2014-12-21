package com.propertyconnection

class Landlord extends User{
    String firstName
    String lastName
    String email
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
        email email: true, blank: false
        homes nullable: true
        tenants nullable: true
        messages nullable: true
        serviceOrders nullable: true
        payments nullable: true
        photo nullable: true, maxSize: 2 * 1024 * 1024
    }

}
