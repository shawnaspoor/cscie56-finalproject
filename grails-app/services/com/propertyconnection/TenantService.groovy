package com.propertyconnection

import grails.transaction.Transactional

import java.util.concurrent.TimeoutException

//forces transactions to roll back if exceptions occur
class TenantException extends RuntimeException{
    String message
    Tenant tenant
}

@Transactional
class TenantService {

    static Home getHome (String loginId){
        def tenant = Tenant.findByLoginId(loginId)
        if(tenant){
            def home = tenant.homes
            if(home) {
                return home
            }
           throw new TenantException(message: "Tenant is homeless")
        }
        throw new TenantException(message: "Invalid login id")
    }

    static Landlord getLandlord (String loginId){
        def home = Tenant.findByLoginId(loginId)
        if(home){
            def landlord = home.landlord
            if(landlord) {
                return landlord
            }

            throw new TenantException(message: "Tenant has no landlord")
        }
        throw new TenantException(message: "Invalid login id")
    }

}
