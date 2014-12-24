package com.propertyconnection

class User {

    transient springSecurityService

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
        password blank: false
        dateCreated nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    String toString() {return "User $loginId (id: $id)"}
    String getDisplayString(){return loginId}

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}