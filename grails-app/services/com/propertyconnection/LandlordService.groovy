package com.propertyconnection

import grails.transaction.Transactional

//forces transactions to roll back if exceptions occur
class LandlordException extends RuntimeException{
    String message
    Landlord landlord
}


@Transactional
class LandlordService {

    static Home getHome(String loginId) {
        def landlord = Landlord.findByLoginId(loginId)
        if (landlord) {
            def home = landlord.homes
            if (home) {
                return [home: home]
            }
            throw new TenantException(message: "Landlord has no homes")
        }
        throw new TenantException(message: "Invalid login id")
    }

    static Tenant getTenant(String loginId) {
        def home = Landlord.findByLoginId(loginId)
        if (home) {
            def tenant = home.tenants
            if (tenant) {
                return [tenant: tenant]
            }

            throw new TenantException(message: "Landlord has no tenants")
        }
        throw new TenantException(message: "Invalid login id")
    }





}