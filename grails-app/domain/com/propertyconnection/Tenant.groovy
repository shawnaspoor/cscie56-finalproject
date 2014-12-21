package com.propertyconnection

class Tenant extends User{
    String firstName
    String lastName
    String email
    byte[] photo

    static belongsTo = [homes: Home]
    static hasOne = [landlord : Landlord]
    static hasMany = [messages: Message, serviceOrders: ServiceOrder, payments: Payment ]

    static mapping = {
        payments cascade: "all-delete-orphan"
    }



    static constraints = {
        firstName blank: false
        lastName blank: false
        email email: true, blank: false
        homes nullable: true //homes nullable true for when the tenant leaves but the tenant wishes to keep their info on file
        landlord nullable: true //landlord nullable true for when a tenant leaves but the tenant wishes to keep their info on file
        messages nullable: true
        serviceOrders nullable: true
        payments nullable: true
        photo nullable: true, maxSize: 2 * 1024 * 1024
    }




}