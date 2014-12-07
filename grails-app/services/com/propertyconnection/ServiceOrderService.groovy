package com.propertyconnection

import grails.transaction.Transactional

//forces transactions to roll back if exceptions occur
class ServiceOrderException extends RuntimeException{
    String message
    ServiceOrder serviceOrder
}

@Transactional
class ServiceOrderService {
    ServiceOrder createServiceOrder (String description, String location, String loginId){
        def tenant = Tenant.findByLoginId(loginId)
        def landlord = TenantService.getLandlord(loginId)
        def home = TenantService.getHome(loginId)
        if(tenant){
            def serviceOrder = new ServiceOrder(description: description, location: location, homes: home, landlord: landlord )
            tenant.addToServiceOrders(serviceOrder)
            if(serviceOrder.validate() && tenant.save()) {
                return serviceOrder
            }else {
                throw new ServiceOrderException(
                        message: "Invalid or empty field(s)", serviceOrder: serviceOrder)
            }
        }
        throw new ServiceOrderException(message: "Invalid Tenant Id")
    }
}
