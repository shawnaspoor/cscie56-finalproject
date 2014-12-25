package com.propertyconnection

class User {

    String loginId
    String password
    Date dateCreated
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static transients = ['springSecurityService']

    static constraints = {
        loginId  size: 3..20, blank: false, unique: true
        dateCreated nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    String toString() {return "User $loginId (id: $id)"}
    String getDisplayString(){return loginId}


}