package com.propertyconnection
/*Check in to using a plugin for this "quartz" */
class Message {
    String title
    String body
    Date dateCreated
    Boolean read

    static belongsTo = [ landlord: Landlord , tenant: Tenant ]

    static mapping = {
        sort read: false
        sort dateCreated: "desc"
    }

    static constraints = {
        title nullable: false, maxSize: 50
        body nullable: false
        dateCreated nullable: false
        read    nullable: false

    }



}
