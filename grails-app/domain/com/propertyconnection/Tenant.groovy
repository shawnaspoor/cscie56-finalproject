package com.propertyconnection

class Tenant {

    String firstName
    String lastName
    String email
    String password
    String loginId
    Date dateCreated
    byte[] photo
    static belongsTo = [landlord : Landlord, homes: Home]
    static hasMany = [messages: Message, serviceOrders: ServiceOrder, payments: Payment ]

    static mapping = {
        payments cascade: "all-delete-orphan"
    }



    static constraints = {

        importFrom Landlord, include:['password']
        importFrom Landlord, include: ['photo']
        firstName blank: false
        lastName blank: false
        loginId nullable: false
        email email: true, blank: false, nullable: false
        dateCreated nullable: true
        homes nullable: true //homes nullable true for when the tenant leaves but the tenant wishes to keep their info on file
        landlord nullable: true //landlord nullable true for when a tenant leaves but the tenant wishes to keep their info on file
        messages nullable: true
        serviceOrders nullable: true
        payments nullable: true

    }




}