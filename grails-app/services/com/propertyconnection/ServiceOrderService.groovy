package com.propertyconnection

import grails.transaction.Transactional

//forces transactions to roll back if exceptions occur
class ServiceOrderException extends RuntimeException{
    String message
    ServiceOrder serviceOrder
}

@Transactional
class ServiceOrderService {
    ServiceOrder createServiceOrder (String description, String location, Long id){
        def tenant = Tenant.findById(id)
        def landlord = TenantService.getLandlord(id)
        def home = TenantService.getHome(id)
        if(tenant){
            println "serviceOrderService inside tenant if"
            def serviceOrder = new ServiceOrder(description: description, location: location, homes: home, landlord: landlord )
            tenant.addToServiceOrders(serviceOrder)
            if(serviceOrder.validate() && tenant.save() && serviceOrder.save()) {
                println "serviceOrderService inside validate and save"
                return serviceOrder
            }else {
                throw new ServiceOrderException(
                        message: "Invalid or empty field(s)", serviceOrder: serviceOrder)
            }
        }
        throw new ServiceOrderException(message: "Invalid Tenant Id")
    }
}
